package hands;

import java.util.List;

public class Highcard extends HandType {

    private final int value;

    public Highcard(List<Integer> cardValues) {
        super(cardValues);
        this.value = super.getHighestCardValue();
    }

    @Override
    public List<Integer> getKickers() {
        List<Integer> cardValues = super.getCardValues();
        int indexOfHighestValue = cardValues.size() - 1;
        cardValues.remove(indexOfHighestValue);
        return cardValues;
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
