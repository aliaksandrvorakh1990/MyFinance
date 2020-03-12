package by.vorakh.training.my_finance.validation.type;

import java.math.BigDecimal;

public class CurrencyValidator {
    
    private CurrencyValidator() {}

    public static boolean isCorrectCurrency(String str) {
        String regex = "(\\d+|\\d+.\\d+)";
        return ((str != null) && (str.matches(regex)));
    }

    public static boolean isCorrectValue(BigDecimal money) {
        BigDecimal zero = new BigDecimal(0);
        return ((money != null) && (money.compareTo(zero)>= 0));
    }
}
