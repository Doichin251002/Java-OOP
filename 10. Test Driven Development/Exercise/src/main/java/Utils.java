import java.util.Comparator;

public class Utils {
    public static Comparator<Transaction> byAmountDesc() {
        return Comparator.comparing(Transaction::getAmount).reversed();
    }
}
