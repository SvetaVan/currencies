create table CurrenciesRates (
    ID int not null auto_increment,
    Currency varchar (3) not null ,
    Rate int not null ,
    Date date,
    primary key (id)
);