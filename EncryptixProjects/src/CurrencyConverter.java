import java.util.HashMap;
import java.util.Scanner;

public class CurrencyConverter {

    private static final HashMap<String, Double> exchangeRates = new HashMap<>();

    static {
        exchangeRates.put("USD_EUR", 0.85);
        exchangeRates.put("EUR_USD", 1.18);
        exchangeRates.put("USD_INR", 74.50);
        exchangeRates.put("INR_USD", 0.013);
        exchangeRates.put("EUR_INR", 88.00);
        exchangeRates.put("INR_EUR", 0.011);
    }

    public static double getExchangeRate(String baseCurrency, String targetCurrency) {
        String key = baseCurrency + "_" + targetCurrency;
        return exchangeRates.getOrDefault(key, -1.0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Currency Converter");
        System.out.print("Enter the base currency (e.g., USD, EUR, INR): ");
        String baseCurrency = scanner.next().toUpperCase();

        System.out.print("Enter the target currency (e.g., USD, EUR, INR): ");
        String targetCurrency = scanner.next().toUpperCase();

        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        double rate = getExchangeRate(baseCurrency, targetCurrency);

        if (rate != -1.0) {
            double convertedAmount = amount * rate;
            System.out.println(amount + " " + baseCurrency + " = " + convertedAmount + " " + targetCurrency);
        } else {
            System.out.println("Exchange rate for " + baseCurrency + " to " + targetCurrency + " is not available.");
        }

        scanner.close();
    }
}

