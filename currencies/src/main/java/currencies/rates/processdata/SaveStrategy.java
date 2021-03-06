package currencies.rates.processdata;

import currencies.rates.CurrencyRate;
import currencies.rates.CurrencyRateRepository;
import currencies.rates.CurrencyRateRepositoryImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SaveStrategy implements Runnable {

    @Override
    public void run() {
        System.out.println("Please provide currency, rate, date. ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Currency: ");
        String currency = scanner.nextLine();
        System.out.println("Rate: ");
        String rateS = scanner.nextLine();
        int rate = Integer.parseInt(rateS);
        System.out.println("Date in format yyyy.MM.dd:");
        String dateString = scanner.nextLine();
        Date date;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
            simpleDateFormat.setLenient(false);
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        CurrencyRate currencyRate = new CurrencyRate(currency, rate, date);
        CurrencyRateRepository currencyRateRepository = new CurrencyRateRepositoryImpl();
        currencyRateRepository.saveOrUpdate(null,currencyRate);
    }
}
