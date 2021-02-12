package hands;

import java.util.List;

public class FourOfAKind extends HandType {

    private final int value;

    public FourOfAKind(List<Integer> cardValues) {
        super(cardValues);
        this.value = calculateValue(cardValues);
    }

    private int calculateValue(List<Integer> cardValues) {
        return 700 + super.getUtils().getHighestValueOfSetOfSize(4, cardValues);
    }

    @Override
    public List<Integer> getKickers() {
        return super.getUtils().getValuesNotAppearingNumberOfTimes(4, getCardValues());
    }

    @Override
    public int getValue() {
        return this.value;
    }
}