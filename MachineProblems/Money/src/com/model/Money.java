package com.model;

import java.math.BigDecimal;

public class Money {
  
  private Currency currency;
  private int wholeAmount;
  private int centAmount;
  private BigDecimal moneyValue;

  public static void main(String[] args) {
    System.out.println(new Money(Currency.PHP, "12.01"));
    System.out.println(new Money(Currency.PHP, "12.1"));
  }

  public Money(Currency currency, String amount) {
    this.currency = currency;
    splitAmount(amount);
    setMoneyValue();
  }
  
  private void setMoneyValue() {
    final String ZERO = "0";
    final String DOT = ".";
    String cent = String.valueOf(centAmount);
    if(centAmount < 1000) {
      cent = ZERO + cent;
    }
    try
    {
      this.moneyValue = new BigDecimal(wholeAmount + DOT + cent.substring(0,2));
    }
    catch(Exception e) {
      throw new IllegalMoneyObjectCreationException("Invalid Amount of money!");
    }
  }
  
  public void splitAmount(String amount)  {
    if(amount.contains(".")) 
    {
      final String ZERO = "0";
      String[] amounts = amount.split("\\.");
      if(amounts.length > 2) {
          throw new IllegalMoneyObjectCreationException("Invalid Amount of money!");
      }
      amounts[1] += ZERO;
      String cent = (amounts[1].length() > 2) ? amounts[1].substring(0, 2) : amounts[1];

      wholeAmount = Integer.parseInt(amounts[0]);
      centAmount = Integer.parseInt(cent) * 100;
    }
    else 
    {
      wholeAmount = Integer.parseInt(amount);
    }
  }

  public Money add(Money money2) throws CurrencyMismatchException {
    if(!(this.currency.equals(money2.currency))){
        throw new CurrencyMismatchException("Incompatible Currency!");
    }
    BigDecimal result = this.moneyValue.add(money2.moneyValue);
    return new Money(currency, result.toString());
  }
  
  public Money subtract(Money money2) throws CurrencyMismatchException {
    if(!(this.currency.equals(money2.currency))){
        throw new CurrencyMismatchException("Incompatible Currency!");
    }
    BigDecimal result = this.moneyValue.subtract(money2.moneyValue);
    return new Money(currency, result.toString());
  }

  public Money multiply(int multiplier) {
    BigDecimal result = this.moneyValue.multiply(new BigDecimal(multiplier));
    return new Money(currency, result.toString());
  }

  public Money divide(int divisor) {
    BigDecimal result = this.moneyValue.divide(new BigDecimal(divisor));
    return new Money(currency, result.toString());
  }

  public void setMoneyValue(String wholeAmount, String centAmount) {
      moneyValue = new BigDecimal(wholeAmount + centAmount).divide(new BigDecimal(100));
  }


  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof Money)) {
      return false;
    }
    final Money other = (Money)object;
    if (!(currency == null ? other.currency == null : currency.equals(other.currency))) {
      return false;
    }
    if (!(moneyValue == null ? other.moneyValue == null : moneyValue.equals(other.moneyValue))) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int PRIME = 37;
    int result = 1;
    result = PRIME * result + ((currency == null) ? 0 : currency.hashCode());
    result = PRIME * result + ((moneyValue == null) ? 0 : moneyValue.hashCode());
    return result;
  }
  
  @Override
  public String toString() {
    return currency + " " + moneyValue.toString();
  }
}
