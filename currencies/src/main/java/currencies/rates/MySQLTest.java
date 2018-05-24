package currencies.rates;


import currencies.rates.processdata.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class MySQLTest {
    static final HashMap<Integer, Runnable> strategyMapping = new HashMap<>();

    static {
        strategyMapping.put(1, new SaveStrategy());
        strategyMapping.put(2, new UpdateStrategy());
        strategyMapping.put(3, new DeleteStrategy());
        strategyMapping.put(4, new SelectAllStrategy());
        strategyMapping.put(5, new SelectByDateStrategy());
        strategyMapping.put(6, new SelectToXML());

    }

    public static void main(String[] args) throws ParseException, SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dear user, please enter the number of operation you want to perform from the list below: " + "\n" +
                "1. Insert; \n" +
                "2. Update; \n" +
                "3. Delete; \n" +
                "4. SelectAll; \n" +
                "5. Select by date;\n"+
                "6. Select All to XML.");
        int numberOfOperation = scanner.nextInt();
        strategyMapping.get(numberOfOperation).run();


    }

}



