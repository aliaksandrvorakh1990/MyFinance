package by.vorakh.training.my_finance.validation;

import java.math.BigDecimal;

public interface CurrencyValidator extends NotNullValidator {

    default boolean isCorrectCurrency(String str) {
        String regex = "(\\d+|\\d+.\\d+)";
        return ((!isEqualsNull(str)) && (str.matches(regex)));
    }

    default boolean isCorrectValue(BigDecimal money) {
        BigDecimal zero = new BigDecimal(0);
        return ((!isEqualsNull(money)) && (money.compareTo(zero)>= 0));
    }
}
