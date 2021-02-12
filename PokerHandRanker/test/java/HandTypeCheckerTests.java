import enums.SpecialValue;
import enums.Suit;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HandTypeCheckerTests {

    static HandTypeChecker checker;

    @BeforeAll
    static void setup() {
        checker = new HandTypeChecker();
    }

    @Test
    void flushIdentified() {
        List<Suit> flush = Arrays
            .asList(Suit.HEARTS, Suit.HEARTS, Suit.HEARTS, Suit.HEARTS, Suit.HEARTS);
        Assertions.assertTrue(checker.isFlush(flush));
    }

    @Test
    void nonFlushNotIdentifiedAsFlush() {
        List<Suit> nonFlush = Arrays
            .asList(Suit.CLUBS, Suit.HEARTS, Suit.HEARTS, Suit.SPADES, Suit.DIAMONDS);
        Assertions.assertFalse(checker.isFlush(nonFlush));
    }

    @Test
    void straightWithNormalValuesIdentified() {
        List<Integer> straight = Arrays.asList(1, 2, 3, 4, 5);
        Assertions.assertTrue(checker.isStraight(straight));
    }

    @Test
    void straightWithSpecialValuesIdentified() {
        List<Integer> straight = Arrays
            .asList(8, 9, SpecialValue.T.value, SpecialValue.J.value, SpecialValue.Q.value);
        Assertions.assertTrue(checker.isStraight(straight));
    }

    @Test
    void nonStraightNotIdentifiedAsStraight() {
        List<Integer> nonStraight = Arrays.asList(1, 3, 5, 8, 9);
        Assertions.assertFalse(checker.isStraight(nonStraight));
    }

    @Test
    void royalFlushIdentified() {
        List<Suit> flush = Arrays
            .asList(Suit.CLUBS, Suit.CLUBS, Suit.CLUBS, Suit.CLUBS, Suit.CLUBS);
        List<Integer> royalStraight = Arrays.asList(
            SpecialValue.T.value,
            SpecialValue.J.value,
            SpecialValue.Q.value,
            SpecialValue.K.value,
            SpecialValue.A.value
        );
        Assertions.assertTrue(checker.isRoyalFlush(flush, royalStraight));
    }

    @Test
    void nonRoyalFlushNotIdentifiedAsRoyalFlush() {
        List<Suit> nonFlush = Arrays
            .asList(Suit.CLUBS, Suit.HEARTS, Suit.SPADES, Suit.CLUBS, Suit.CLUBS);
        List<Integer> nonRoyalStraight = Arrays.asList(
            SpecialValue.T.value,
            9,
            SpecialValue.Q.value,
            SpecialValue.K.value,
            SpecialValue.A.value
        );
        Assertions.assertFalse(checker.isRoyalFlush(nonFlush, nonRoyalStraight));
    }

    @Test
    void straightFlushIdentified() {
        List<Suit> flush = Arrays
            .asList(Suit.CLUBS, Suit.CLUBS, Suit.CLUBS, Suit.CLUBS, Suit.CLUBS);
        List<Integer> straight = Arrays.asList(1, 2, 3, 4, 5);
        Assertions.assertTrue(checker.isStraightFlush(flush, straight));
    }

    @Test
    void nonStraightFlushNotIdentifiedAsStraightFlush() {
        List<Suit> nonFlush = Arrays
            .asList(Suit.HEARTS, Suit.CLUBS, Suit.SPADES, Suit.CLUBS, Suit.CLUBS);
        List<Integer> nonStraight = Arrays.asList(1, 2, 3, 4, 6);
        Assertions.assertFalse(checker.isStraightFlush(nonFlush, nonStraight));
    }

    @Test
    void twoPairsIdentified() {
        List<Integer> twoPairs = Arrays.asList(2, 2, 1, 1, 4);
        Assertions.assertTrue(checker.isTwoPairs(twoPairs));
    }

    @Test
    void twoPairsWithSpecialValuesIdentified() {
        List<Integer> twoPairs = Arrays.asList(
            SpecialValue.A.value,
            SpecialValue.A.value,
            SpecialValue.Q.value,
            SpecialValue.Q.value,
            SpecialValue.K.value
        );
        Assertions.assertTrue(checker.isTwoPairs(twoPairs));
    }

    @Test
    void fullHouseIdentified() {
        List<Integer> fullHouse = Arrays.asList(2, 2, 1, 1, 1);
        Assertions.assertTrue(checker.isFullHouse(fullHouse));
    }

    @Test
    void fullHouseWithSpecialValuesIdentified() {
        List<Integer> fullHouse = Arrays.asList(
            SpecialValue.A.value,
            SpecialValue.A.value,
            SpecialValue.A.value,
            SpecialValue.T.value,
            SpecialValue.T.value
        );
        Assertions.assertTrue(checker.isFullHouse(fullHouse));
    }

    @Test
    void nonFullHouseNotIdentified() {
        List<Integer> fullHouse = Arrays.asList(
            SpecialValue.A.value,
            SpecialValue.A.value,
            SpecialValue.K.value,
            SpecialValue.T.value,
            SpecialValue.T.value
        );
        Assertions.assertFalse(checker.isFullHouse(fullHouse));
    }

    @Test
    void twoPairsWithNormalValuesIdentified() {
        List<Integer> twoPairs = Arrays.asList(2, 2, 1, 1, 4);
        Assertions.assertTrue(checker.isTwoPairs(twoPairs));
    }

    @Test
    void nonTwoPairsNotIdentified() {
        List<Integer> nonTwoPairs = Arrays.asList(
            SpecialValue.A.value,
            SpecialValue.A.value,
            SpecialValue.A.value,
            SpecialValue.Q.value,
            4
        );
        Assertions.assertFalse(checker.isTwoPairs(nonTwoPairs));
    }
}