import enums.SpecialValue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParsingTests {

    @Test
    void cardsParsedCorrectly() {
        Assertions.assertDoesNotThrow(() -> {
            PokerHand hand = new PokerHand("TS 3A 3C 4D 9H");
        });
    }

    @Test
    void exceptionThrownWhenTooFewCardsGiven() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PokerHand hand = new PokerHand("2S 3C 4D 9H");
        });
    }

    @Test
    void exceptionThrownWhenTooManyCardsGiven() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PokerHand hand = new PokerHand("2S TS 3A 3C 4D 9H");
        });
    }

    @Test
    void exceptionThrownWhenCardsAreMoreThanTwoCharacters() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PokerHand hand = new PokerHand("2SS TS 3A3 43C 4D 9H");
        });
    }

    @Test
    void normalValuesParsedCorrectly() {
        PokerHand hand = new PokerHand("1H 2S 3C 4D 9H");
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(9);
        List<Integer> actual = hand.getCardValues();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void specialValuesParsedCorrectly() {
        PokerHand hand = new PokerHand("TH JS QC KD AH");
        List<Integer> expected = new ArrayList<>();
        expected.add(SpecialValue.T.value);
        expected.add(SpecialValue.J.value);
        expected.add(SpecialValue.Q.value);
        expected.add(SpecialValue.K.value);
        expected.add(SpecialValue.A.value);
        List<Integer> actual = hand.getCardValues();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void invalidSpecialValuesThrowException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PokerHand hand = new PokerHand("2G QW 4B 3L 4Ä 9O");
        });
    }
}