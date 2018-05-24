package currencies.rates.processdata;


import currencies.rates.CurrencyRateRepository;
import currencies.rates.CurrencyRateRepositoryImpl;

public class SelectAllStrategy implements Runnable {

    @Override
    public void run() {
        CurrencyRateRepository currencyRateRepository = new CurrencyRateRepositoryImpl();
        currencyRateRepository.loadAll();
    }



}