package currencies.rates;

import currencies.generated.CurrenciesRates;
import currencies.generated.CurrencyRate;
import currencies.generated.ObjectFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import java.io.File;
import java.io.StringWriter;
import java.util.GregorianCalendar;
import java.util.List;

public class CurrencyRateService {
        //return type string
    public File loadAllInXML() {

        try {
            CurrencyRateRepository currencyRateRepository = new CurrencyRateRepositoryImpl();

            ObjectFactory objectFactory = new ObjectFactory();
            CurrenciesRates currenciesRates = objectFactory.createCurrenciesRates();
            List<currencies.generated.CurrencyRate> listCR = currenciesRates.getCurrencyRate();

            CurrencyRate generatedCurrencyRate = new CurrencyRate();
            for (currencies.rates.CurrencyRate currencyRate : currencyRateRepository.loadAll()) {
                generatedCurrencyRate.setId(currencyRate.getId());
                generatedCurrencyRate.setCurrency(currencyRate.getCurrency());
                generatedCurrencyRate.setRate(currencyRate.getRate());

                java.util.Date date = currencyRate.getDate();
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.setTime(date);
                generatedCurrencyRate.setDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar));

                listCR.add(generatedCurrencyRate);
            }
            JAXBContext jaxbContext = JAXBContext.newInstance("currencies.generated");
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter stringWriter = new StringWriter();

            //marshaller.marshal(currenciesRates, stringWriter);
            File file = new File("/home/svetka/Рабочий стол/1.xml");
            marshaller.marshal(currenciesRates,file);
            //return stringWriter.toString();
            return file;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
