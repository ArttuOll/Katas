package hands;

import java.util.List;

public class Pair extends HandType {

    private final int value;

    public Pair(List<Integer> cardValues) {
        super(cardValues);
        this.value = 100 + super.getUtils().getHighestValueOfSetOfSize(2, cardValues);
    }

    @Override
    public List<Integer> getKickers() {
        return super.getUtils().getValuesNotAppearingNumberOfTimes(2, super.getCardValues());
    }

    @Override
    public int getValue() {
        return this.value;
    }
}