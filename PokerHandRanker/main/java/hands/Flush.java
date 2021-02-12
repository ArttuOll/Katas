package hands;

import java.util.List;

public class Flush extends HandType {

    private final int value;

    public Flush(List<Integer> cardValues) {
        super(cardValues);
        this.value = calculateValue();
    }

    private int calculateValue() {
        return 500 + super.getHighestCardValue();
    }

    @Override
    public List<Integer> getKickers() {
        return super.getCardValues();
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
