package hands;

import java.util.ArrayList;
import java.util.List;

public class RoyalFlush extends HandType {

    private final int value;

    public RoyalFlush(List<Integer> cardValues) {
        super(cardValues);
        this.value = calculateValue();
    }

    private int calculateValue() {
        return 900 + super.getHighestCardValue();
    }

    @Override
    public List<Integer> getKickers() {
        return new ArrayList<>();
    }

    @Override
    public int getValue() {
        return this.value;
    }
}