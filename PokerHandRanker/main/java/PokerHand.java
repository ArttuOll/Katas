import enums.Result;
import enums.Suit;
import hands.HandType;
import java.util.List;

/*
Tehtävänanto

Luo luokka PokerHand, jolla on metodi compareWith(PokerHand hand), jonka avulla kahta pokerikättä
voidaan vertailla.

PokerHand saa konstruktoriparametrinaan merkkijonon, joka kuvaa sen edustamaa pokerikättä. Merkkijono
on muotoa "AC TD 3S 8H 3C", missä jokaisen merkkiparin ensimmäinen merkki edustaa pokerikäden kortin
arvoa ja toinen maata. Arvot ovat [1, 2, 3, 4, 5 ,6, 7, 8, 9, T, J, Q, K A] (T = Ten, J = Jack,
Q = Queen, K = King, A = Ace) ja maat ovat H, C, D, S (Hearts, Clubs, Diamonds, Spades).

Pokerikäsien vertailuarvojen osalta noudatetaan Texas Hold'em -sääntöjä. Ässät ovat aina arvoltaan
14, eivät koskaan 1. Mailla ei ole keskinäistä järjestystä.

Sanastoa

Koodissa sana "kicker" tarkoittaa käden arvon ulkopuolelle jääviä kortteja. Esimerkiksi kahden
parin kädessä viides kortti, joka ei kuulu kumpaankaan pareista, on kicker. Yhden parin kädessä pariin
kuulumattomat kolme korttia ovat kaikki kickereitä.

Sana value tarkoittaa yksittäisen kortin lukuarvoa. HandType tarkoittaa kättä, eli usean kortin
yhdistelmää, jolla on pokerissa tietty arvo.
 */

public class PokerHand {

    private final List<Suit> suits;
    private final List<Integer> cardValues;
    private final HandType handType;

    PokerHand(String hand) {
        HandParser parser = new HandParser();
        this.suits = parser.parseSuits(hand);
        this.cardValues = parser.parseValues(hand);

        HandTypeChecker handTypeChecker = new HandTypeChecker();
        this.handType = handTypeChecker.getHandType(this);
    }

    /**
     * Compares this PokerHand to the one given as argument.
     * @param otherHand PokerHand to which this hand is compared to.
     * @return Result.WIN, if this hand is greater than the given one, Result.LOSS, if smaller.
     * Result.TIE, if the hands are equal.
     */
    public Result compareWith(PokerHand otherHand) {
        HandType otherHandType = otherHand.getHandType();
        if (otherHandType.getValue() > this.handType.getValue()) {
            return Result.LOSS;
        } else if (otherHandType.getValue() < this.handType.getValue()) {
            return Result.WIN;
        } else {
            return compareKickers(otherHandType.getKickers(), this.handType.getKickers());
        }
    }

    List<Suit> getSuits() {
        return this.suits;
    }

    List<Integer> getCardValues() {
        return this.cardValues;
    }

    HandType getHandType() {
        return this.handType;
    }

    private Result compareKickers(List<Integer> otherHandKickers, List<Integer> kickers) {
        for (int i = otherHandKickers.size() - 1; i >= 0; i--) {
            int otherHandKicker = otherHandKickers.get(i);
            int kicker = kickers.get(i);
            if (otherHandKicker > kicker) {
                return Result.LOSS;
            } else if (kicker > otherHandKicker) {
                return Result.WIN;
            }
        }
        return Result.TIE;
    }
}