package currencies.rates;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CurrencyRateRepositoryImpl implements CurrencyRateRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/accountDB?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String USERPASSWORD = "root";
    private static final String INSERT_QUERY = "insert into CurrenciesRates (id,currency, rate, date) values (null,?,?,?)";
    private static final String DELETE_QUERY_BY_ID = "delete from CurrenciesRates where id=?";
    private static final String UPDATE_CURRENCY_QUERY_BY_ID = "update CurrenciesRates set currency = ?,  rate=?,  date=? where id=?";
    private static final String SELECT_LIST_ALL = "select * from CurrenciesRates";

    private static final String SELECT_BY_DATE =
            "select t.id, t.currency, t.rate, t.date " +
                    "from CurrenciesRates as t " +
                    "join (select currency, max(date) as mdate from CurrenciesRates where date <= ? group by currency) as t1 " +
                    "on t.currency = t1.currency and t.date= t1.mdate";


    private static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//рег драйвер
            connection = DriverManager.getConnection(URL, USERNAME, USERPASSWORD);//устанавливаем связь
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    // можно ли использовать Integer instead of int ?
    public CurrencyRate saveOrUpdate(Integer id, CurrencyRate currencyRate) {
        CurrencyRate currencyRateToReturn;
        if (id == null) {
            currencyRateToReturn = CurrencyRateRepositoryImpl.save(currencyRate);
        } else {
            currencyRateToReturn = CurrencyRateRepositoryImpl.update(id, currencyRate);
        }
        return currencyRateToReturn;
    }


    private static CurrencyRate save(CurrencyRate currencyRate) {
        if (!isValidCurrency(currencyRate.getCurrency())) {
            throw new IllegalArgumentException("incorrect currency name");
        }
        int id = -1;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, currencyRate.getCurrency());
            preparedStatement.setInt(2, currencyRate.getRate());
            preparedStatement.setDate(3, new java.sql.Date(currencyRate.getDate().getTime()));
            preparedStatement.executeUpdate();
            ResultSet generatedKeysSet = preparedStatement.getGeneratedKeys();
            while (generatedKeysSet.next()) {
                id = generatedKeysSet.getInt(1);
            }
            currencyRate.setId(id); // отсюда надо вытащить id запихнуть его в currencyRate и это и вернуть !
            return currencyRate;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //что мне здесь нужно вернуть? то, что обновилось в базе и создать новый объект?
    // Нужен один метод saveOrUpdate - котороый в зависимости от currencyRate.getId == null понимаем что ему делать insert или update
    private static CurrencyRate update(int id, CurrencyRate currencyRate) {
        if (!isValidCurrency(currencyRate.getCurrency())) {
            return null;
        }
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CURRENCY_QUERY_BY_ID)) {
            preparedStatement.setString(1, currencyRate.getCurrency());
            preparedStatement.setInt(2, currencyRate.getRate());
            preparedStatement.setDate(3, new java.sql.Date(currencyRate.getDate().getTime()));
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            return currencyRate;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public  void delete(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public  Collection<CurrencyRate> loadAll() {
        List<CurrencyRate> currencyRateList = new ArrayList<>();
        CurrencyRate currencyRate = new CurrencyRate();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIST_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                currencyRate.setId(resultSet.getInt(1));
                currencyRate.setCurrency(resultSet.getString(2));
                currencyRate.setRate(resultSet.getInt(3));
                currencyRate.setDate(resultSet.getDate(4));
                currencyRateList.add(currencyRate);
            }
            return currencyRateList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Collection<CurrencyRate> loadByDate(java.util.Date dateToFilter) {
        List<CurrencyRate> currencyRateList = new ArrayList<>();
        CurrencyRate currencyRate = new CurrencyRate();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_DATE)) {
            preparedStatement.setDate(1, new java.sql.Date(dateToFilter.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                currencyRate.setId(resultSet.getInt(1));
                currencyRate.setCurrency(resultSet.getString(2));
                currencyRate.setRate(resultSet.getInt(3));
                currencyRate.setDate(resultSet.getDate(4));
                currencyRateList.add(currencyRate);
            }
            return currencyRateList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isValidCurrency(String name) {
        Currencies[] values = Currencies.values();
        for (Currencies currency : values) {
            if (currency.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
