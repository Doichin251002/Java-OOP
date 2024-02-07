package foodShortage;

import java.util.List;
import java.util.Map;

public class FoodShortageHandler extends CommandHandler {
    private Map<String, Buyer> buyers;
    protected FoodShortageHandler(Map<String, Buyer> buyers, String endCommand) {
        super(endCommand);
        this.buyers = buyers;
    }

    @Override
    protected void processNonEndCommand(String name) {
        Buyer targetPerson = findPerson(name);

        if (targetPerson != null) {
            targetPerson.buyFood();
        }
    }

    private Buyer findPerson(String name) {
        return buyers.get(name);
    }
}
