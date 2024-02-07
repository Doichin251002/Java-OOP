package cardsWithPower;

public class Card {
    public enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
        SEVEN(7), EIGHT(8), NINE(9), TEN(10),
        JACK(11), QUEEN(12), KING(13), ACE(14);

        private int power;

        Rank(int power) {
            this.power = power;
        }
    }
    public enum Suit {
        CLUBS(0), DIAMONDS(13), HEARTS(26), SPADES(39);

        private int power;

        Suit(int suitPower) {
            this.power = suitPower;
        }
    }
    private Rank rank;
    private Suit suit;
    private int power;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        this.power = this.rank.power + this.suit.power;
    }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %d"
                , this.rank.name(), this.suit.name(), this.power);
    }
}
