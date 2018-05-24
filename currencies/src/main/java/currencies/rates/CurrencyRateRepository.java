package currencies.rates;

import java.util.Collection;
import java.util.Date;

public interface CurrencyRateRepository {

    CurrencyRate saveOrUpdate(Integer id, CurrencyRate entity);

    Collection<CurrencyRate> loadByDate(Date date);

    Collection<CurrencyRate> loadAll();

    void delete(int id);
}
