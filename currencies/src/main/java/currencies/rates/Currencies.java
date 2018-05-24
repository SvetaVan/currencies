package currencies.rates;

enum Currencies {

    RUBLE("RUR"), DOLLAR("USD"), EURO("EUR");
    private String name;
    Currencies(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
