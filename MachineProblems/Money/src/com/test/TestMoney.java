import com.model.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMoney {
  @Test
  public void testAdditionOfPHP() throws CurrencyMismatchException {
    Money money1 = new Money(Currency.PHP, "12.111");
    Money money2 = new Money(Currency.PHP, "12.122");
    Money result = money1.add(money2);
    Money expextedMoneyResult = new Money(Currency.PHP, "24.23");
    assertEquals( expextedMoneyResult, result);
  }
  
  @Test
  public void testSubtractionOfPHP() throws CurrencyMismatchException {
    Money money1 = new Money(Currency.PHP, "12.12");
    Money money2 = new Money(Currency.PHP, "24.12");
    Money result = money1.subtract(money2);
    Money expextedMoneyResult = new Money(Currency.PHP, "-12.00");
    assertEquals( expextedMoneyResult, result);
  }
  @Test
  public void testMultiplicationOfPHP() {
    Money money1 = new Money(Currency.PHP, "12.12");
    Money result = money1.multiply(3);
    Money expextedMoneyResult = new Money(Currency.PHP, "36.36");
    assertEquals( expextedMoneyResult, result);
  }
  
  @Test
  public void testDivisionOfPHP() {
    Money money1 = new Money(Currency.PHP, "12.12");
    Money result = money1.divide(2);
    Money expextedMoneyResult = new Money(Currency.PHP, "6.06");
    assertEquals( expextedMoneyResult, result);
  }
  @Test(expected = CurrencyMismatchException.class)
  public void testComputeDifferentCurrency() throws CurrencyMismatchException {
    Money money1 = new Money(Currency.USD, "12.12");
    Money money2 = new Money(Currency.PHP, "12.12");
    Money result = money1.add(money2);
    Money expextedMoneyResult = new Money(Currency.PHP, "24.24");
    assertEquals( expextedMoneyResult, result);
  }
  @Test(expected = IllegalMoneyObjectCreationException.class)
  public void testInvalidMoneyObjectHavingMultiplieDots() {
    Money money1 = new Money(Currency.USD, "12.1.1");
  }
  @Test(expected = IllegalMoneyObjectCreationException.class)
  public void testInvalidMoneyObjectHavingNonNumericValue() {
    Money money1 = new Money(Currency.USD, "12.-1");
  }
}
