import enums.SpecialValue;
import enums.Suit;
import hands.Flush;
import hands.FourOfAKind;
import hands.FullHouse;
import hands.HandType;
import hands.Highcard;
import hands.Pair;
import hands.RoyalFlush;
import hands.Straight;
import hands.StraightFlush;
import hands.ThreeOfAKind;
import hands.TwoPairs;
import java.util.Arrays;
import java.util.List;
import utils.ValueUtils;

public class HandTypeChecker {

    private final ValueUtils utils;

    public HandTypeChecker() {
        this.utils = new ValueUtils();
    }

    /**
     * Returns a HandType-object that matches the type (or game-value) of the given PokerHand.
     * @param hand PokerHand, the type (pair, straight, flush, etc.) of which is to be found out.
     * @return HandType-object that matches the type of the PokerHand.
     */
    HandType getHandType(PokerHand hand) {
        HandTypeChecker checker = new HandTypeChecker();
        List<Integer> cardValues = hand.getCardValues();
        List<Suit> cardSuits = hand.getSuits();

        if (checker.isRoyalFlush(cardSuits, cardValues)) {
            return new RoyalFlush(cardValues);
        } else if (checker.isStraightFlush(cardSuits, cardValues)) {
            return new StraightFlush(cardValues);
        } else if (checker.isFourOfAKind(cardValues)) {
            return new FourOfAKind(cardValues);
        } else if (checker.isFullHouse(cardValues)) {
            return new FullHouse(cardValues);
        } else if (checker.isFlush(cardSuits)) {
            return new Flush(cardValues);
        } else if (checker.isStraight(cardValues)) {
            return new Straight(cardValues);
        } else if (checker.isThreeOfAKind(cardValues)) {
            return new ThreeOfAKind(cardValues);
        } else if (checker.isTwoPairs(cardValues)) {
            return new TwoPairs(cardValues);
        } else if (checker.isPair(cardValues)) {
            return new Pair(cardValues);
        } else {
            return new Highcard(cardValues);
        }
    }

    boolean isFlush(List<Suit> suits) {
        Suit suitOfHand = suits.get(0);
        return suits.stream().allMatch(suit -> suit == suitOfHand);
    }

    boolean isStraight(List<Integer> values) {
        int previousValue = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            int value = values.get(i);
            if (!(value == previousValue + 1)) {
                return false;
            }
            previousValue = value;
        }
        return true;
    }

    boolean isRoyalFlush(List<Suit> suits, List<Integer> values) {
        return isStraight(values) && isFlush(suits) && containsAllSpecialValues(values);
    }

    private boolean containsAllSpecialValues(List<Integer> values) {
        return Arrays.stream(SpecialValue.values())
            .allMatch(specialValue -> values.contains(specialValue.value));
    }

    boolean isStraightFlush(List<Suit> suits, List<Integer> values) {
        return isStraight(values) && isFlush(suits);
    }

    boolean isFullHouse(List<Integer> values) {
        return this.utils.containsNumberOfSetsOfSize(1, 3, values) && this.utils
            .containsNumberOfSetsOfSize(1, 2, values);
    }

    boolean isFourOfAKind(List<Integer> values) {
        return this.utils.containsNumberOfSetsOfSize(1, 4, values);
    }

    boolean isThreeOfAKind(List<Integer> values) {
        return this.utils.containsNumberOfSetsOfSize(1, 3, values);
    }

    boolean isTwoPairs(List<Integer> values) {
        return this.utils.containsNumberOfSetsOfSize(2, 2, values);
    }

    boolean isPair(List<Integer> values) {
        return this.utils.containsNumberOfSetsOfSize(1, 2, values);
    }
}