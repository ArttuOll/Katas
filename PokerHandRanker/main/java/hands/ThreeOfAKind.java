package hands;

import java.util.List;

public class ThreeOfAKind extends HandType {

    private final int value;

    public ThreeOfAKind(List<Integer> cardValues) {
        super(cardValues);
        this.value = calculateValue(cardValues);
    }

    private int calculateValue(List<Integer> cardValues) {
        return 300 + super.getUtils().getHighestValueOfSetOfSize(3, cardValues);
    }

    @Override
    public List<Integer> getKickers() {
        return super.getUtils().getValuesNotAppearingNumberOfTimes(3, super.getCardValues());
    }

    @Override
    public int getValue() {
        return this.value;
    }
}