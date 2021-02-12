package hands;

import java.util.List;

public class TwoPairs extends HandType {

    private final int value;

    public TwoPairs(List<Integer> cardValues) {
        super(cardValues);
        this.value = calculateValue(cardValues);
    }

    private int calculateValue(List<Integer> cardValues) {
        return 200 + super.getUtils().getHighestValueOfSetOfSize(2, cardValues);
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
