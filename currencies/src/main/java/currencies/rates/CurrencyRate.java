package currencies.rates;


import java.util.Date;

public class CurrencyRate {
    private int id;
    private String currency;
    private int rate;
    private Date date;

    public CurrencyRate(String currency, int rate, Date date) {
        this.currency = currency;
        this.rate = rate;
        this.date = date;
    }

    public CurrencyRate(int id, String currency, int rate, Date date) {
        this.id = id;
        this.currency = currency;
        this.rate = rate;
        this.date = date;
    }

    public CurrencyRate(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public int getRate() {
        return rate;
    }

    public Date getDate() {
        return date;
    }
}
