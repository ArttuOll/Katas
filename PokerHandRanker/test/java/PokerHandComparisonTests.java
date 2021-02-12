import enums.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PokerHandComparisonTests {

    @Test
    void highestStraightFlushWins() {
        PokerHand lowerFlush = new PokerHand("2H 3H 4H 5H 6H");
        PokerHand higherFlush = new PokerHand("KS AS TS QS JS");
        Result actual = lowerFlush.compareWith(higherFlush);
        Assertions.assertEquals(Result.LOSS, actual);
    }

    @Test
    void straightFlushWinsFourOfAKind() {
        PokerHand straightFlush = new PokerHand("2H 3H 4H 5H 6H");
        PokerHand fourOfAKind = new PokerHand("AS AD AC AH JD");
        Result actual = straightFlush.compareWith(fourOfAKind);
        Assertions.assertEquals(Result.WIN, actual);
    }

    @Test
    void highestFourOfAKindWins() {
        PokerHand lowerFourOfAKind = new PokerHand("JS JD JC JH 3D");
        PokerHand higherFourOfAKind = new PokerHand("AS AH 2H AD AC");
        Result actual = lowerFourOfAKind.compareWith(higherFourOfAKind);
        Assertions.assertEquals(Result.LOSS, actual);
    }

    @Test
    void fourOfAKindWinsFullHouse() {
        PokerHand fourOfAKind = new PokerHand("JS JD JC JH AD");
        PokerHand fullHouse = new PokerHand("2S AH 2H AS AC");
        Result actual = fourOfAKind.compareWith(fullHouse);
        Assertions.assertEquals(Result.WIN, actual);
    }

    @Test
    void fullHouseWinsFlush() {
        PokerHand fullHouse = new PokerHand("2S AH 2H AS AC");
        PokerHand flush = new PokerHand("2H 3H 5H 6H 7H");
        Result actual = fullHouse.compareWith(flush);
        Assertions.assertEquals(Result.WIN, actual);
    }

    @Test
    void highestFlushWins() {
        PokerHand higherFlush = new PokerHand("AS 3S 4S 8S 2S");
        PokerHand lowerFlush = new PokerHand("2H 3H 5H 6H 7H");
        Result actual = higherFlush.compareWith(lowerFlush);
        Assertions.assertEquals(Result.WIN, actual);
    }

    @Test
    void flushWinsStraight() {
        PokerHand flush = new PokerHand("2H 3H 5H 6H 7H");
        PokerHand straight = new PokerHand("2S 3H 4H 5S 6C");
        Result actual = flush.compareWith(straight);
        Assertions.assertEquals(Result.WIN, actual);
    }

    @Test
    void equalStraightsTie() {
        PokerHand straight1 = new PokerHand("2S 3H 4H 5S 6C");
        PokerHand straight2 = new PokerHand("3D 4C 5H 6H 2S");
        Result actual = straight1.compareWith(straight2);
        Assertions.assertEquals(Result.TIE, actual);
    }

    @Test
    void straightWinsThreeOfAKind() {
        PokerHand straight = new PokerHand("2S 3H 4H 5S 6C");
        PokerHand threeOfAKind = new PokerHand("AH AC 5H 6H AS");
        Result actual = straight.compareWith(threeOfAKind);
        Assertions.assertEquals(Result.WIN, actual);
    }

    @Test
    void threeOfAKindWinsTwoPair() {
        PokerHand twoPair = new PokerHand("2S 2H 4H 5S 4C");
        PokerHand threeOfAKind = new PokerHand("AH AC 5H 6H AS");
        Result actual = threeOfAKind.compareWith(twoPair);
        Assertions.assertEquals(Result.WIN, actual);
    }

    @Test
    void twoPairWinsPair() {
        PokerHand twoPair = new PokerHand("2S 2H 4H 5S 4C");
        PokerHand pair = new PokerHand("AH AC 5H 6H 7S");
        Result actual = twoPair.compareWith(pair);
        Assertions.assertEquals(Result.WIN, actual);
    }

    @Test
    void highestPairWins() {
        PokerHand higherPair = new PokerHand("AH AC 5H 6H 7S");
        PokerHand lowerPair = new PokerHand("6S AD 7H 4S AS");
        Result actual = higherPair.compareWith(lowerPair);
        Assertions.assertEquals(Result.WIN, actual);
    }

    @Test
    void highestPairWins2() {
        PokerHand higherPair = new PokerHand("KD 4S KC 3H 8S");
        PokerHand lowerPair = new PokerHand("QH 8H KD JH 8S");
        Result actual = higherPair.compareWith(lowerPair);
        Assertions.assertEquals(Result.WIN, actual);
    }

    @Test
    void pairWinsHighcard() {
        PokerHand pair = new PokerHand("AH AC 5H 6H 7S");
        PokerHand highCard = new PokerHand("2S AH 4H 5S KC");
        Result actual = pair.compareWith(highCard);
        Assertions.assertEquals(Result.WIN, actual);
    }

    @Test
    void highestCardLosesByKickers() {
        PokerHand higherHighestCard = new PokerHand("7H 3C TH 6H 9S");
        PokerHand lowerHighestCard = new PokerHand("2S 3H 6H 7S 9C");
        Result actual = higherHighestCard.compareWith(lowerHighestCard);
        Assertions.assertEquals(Result.WIN, actual);
    }

    @Test
    void highestCardWinsByLastKicker() {
        PokerHand higherHighestCard = new PokerHand("4S 5H 6H TS AC");
        PokerHand lowerHighestCard = new PokerHand("3S 5H 6H TS AC");
        Result actual = higherHighestCard.compareWith(lowerHighestCard);
        Assertions.assertEquals(Result.WIN, actual);
    }

    @Test
    void equalHandsTie() {
        PokerHand hand1 = new PokerHand("2S AH 4H 5S 6C");
        PokerHand hand2 = new PokerHand("AD 4C 5H 6H 2C");
        Result actual = hand1.compareWith(hand2);
        Assertions.assertEquals(Result.TIE, actual);
    }

    @Test
    void higherThreeOfAKindWins() {
        PokerHand lowerThreeOfAKind = new PokerHand("7C 7S 5S 3S 7H");
        PokerHand highertThreeOfAKind = new PokerHand("7C 7S KH 2H 7H");
        Result actual = lowerThreeOfAKind.compareWith(highertThreeOfAKind);
        Assertions.assertEquals(Result.LOSS, actual);
    }
}