package currencies.rates.processdata;

import currencies.rates.CurrencyRateRepository;
import currencies.rates.CurrencyRateRepositoryImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SelectByDateStrategy implements Runnable {


    @Override
    public void run() {
        System.out.println("Please provide date. ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Date in format yyyy.MM.dd:");
        String dateString = scanner.nextLine();
        Date date;
        try {
            date = new SimpleDateFormat("yyyy.MM.dd").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        CurrencyRateRepository currencyRateRepository = new CurrencyRateRepositoryImpl();
        currencyRateRepository.loadByDate(date);

    }

}
