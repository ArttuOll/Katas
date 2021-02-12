import enums.SpecialValue;
import enums.Suit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class HandParser {

    private final static int VALUE = 0;
    private final static int SUIT = 1;

    /**
     * @param hand String representation of a poker hand.
     * @return List of suits of the cards in the hand.
     */
    List<Suit> parseSuits(String hand) {
        return parseCardComponent(hand, SUIT, this::convertStringToSuit);
    }

    private Suit convertStringToSuit(String suitString) {
        return switch (suitString) {
            case "H" -> Suit.HEARTS;
            case "S" -> Suit.SPADES;
            case "C" -> Suit.CLUBS;
            case "D" -> Suit.DIAMONDS;
            default -> null;
        };
    }

    /**
     * @param hand String representation of a poker hand.
     * @return List of values of the cards in the hand.
     */
    List<Integer> parseValues(String hand) {
        List<Integer> values = parseCardComponent(hand, VALUE, this::convertStringToValue);
        Collections.sort(values);
        return values;
    }

    private <T> List<T> parseCardComponent(
        String hand,
        int component,
        Function<String, T> fromStringConversion
    ) {
        String[] cards = hand.split(" ");
        var values = new ArrayList<T>();
        if (cardsValid(cards)) {
            for (String fragment : cards) {
                String componentString =
                    component == VALUE ? fragment.substring(0, 1) : fragment.substring(SUIT);
                T value = fromStringConversion.apply(componentString);
                values.add(value);
            }
        } else {
            throw new IllegalArgumentException(
                "Invalid string of cards. Check card string formatting.");
        }
        return values;
    }

    private boolean cardsValid(String[] cards) {
        return cards.length == 5 && allCardsContainTwoCharacters(cards);
    }

    private boolean allCardsContainTwoCharacters(String[] cards) {
        return Arrays.stream(cards).allMatch(card -> card.length() == 2);
    }

    private int convertStringToValue(String valueString) {
        if (isValueBetweenOneAndNine(valueString)) {
            return Integer.parseInt(valueString);
        } else {
            return SpecialValue.valueOf(valueString).value;
        }
    }

    private boolean isValueBetweenOneAndNine(String valueString) {
        return Character.isDigit(valueString.charAt(0));
    }
}