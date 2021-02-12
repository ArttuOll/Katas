package hands;

import java.util.ArrayList;
import java.util.List;

public class FullHouse extends HandType {

    private final int value;

    public FullHouse(List<Integer> cardValues) {
        super(cardValues);
        this.value = calculateValue(cardValues);
    }

    private int calculateValue(List<Integer> cardValues) {
        int valueOfHighestOfThree = super.getUtils().getHighestValueOfSetOfSize(3, cardValues) * 5;
        int valueOfHighestOfPair = super.getUtils().getHighestValueOfSetOfSize(2, cardValues);
        return 600 + valueOfHighestOfThree + valueOfHighestOfPair;
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
