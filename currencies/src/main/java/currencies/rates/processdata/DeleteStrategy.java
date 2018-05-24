package currencies.rates.processdata;

import currencies.rates.CurrencyRateRepository;
import currencies.rates.CurrencyRateRepositoryImpl;

import java.util.Scanner;

public class DeleteStrategy implements Runnable {

    @Override
    public void run() {
        System.out.println("Please provide id. ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Id: ");
        int id = scanner.nextInt();
        CurrencyRateRepository currencyRateRepository = new CurrencyRateRepositoryImpl();
        currencyRateRepository.delete(id);
    }
}
