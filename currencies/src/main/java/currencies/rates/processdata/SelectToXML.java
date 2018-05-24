package currencies.rates.processdata;

import currencies.rates.CurrencyRateRepositoryImpl;
import currencies.rates.CurrencyRateService;

public class SelectToXML implements Runnable {
    @Override
    public void run() {
        CurrencyRateService currencyRateService = new CurrencyRateService();
        currencyRateService.loadAllInXML();
    }
}
