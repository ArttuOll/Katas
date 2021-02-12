package hands;

import java.util.Collections;
import java.util.List;
import utils.ValueUtils;

public abstract class HandType {

    private final List<Integer> cardValues;
    private final int highestCardValue;
    private final ValueUtils utils;

    public HandType(List<Integer> cardValues) {
        this.cardValues = cardValues;
        this.highestCardValue = Collections.max(cardValues);
        this.utils = new ValueUtils();
    }

    public abstract List<Integer> getKickers();

    public abstract int getValue();

    ValueUtils getUtils() {
        return this.utils;
    }

    public int getHighestCardValue() {
        return highestCardValue;
    }

    public List<Integer> getCardValues() {
        return cardValues;
    }
}