package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

public class ValueUtils {

    /**
     * @param values List of values in a poker hand.
     * @return Map containing as key the individual card-values in the hand, as value the number of
     * appearances of each card-value in the hand.
     */
    public Map<Integer, Integer> countAppearancesOfValues(List<Integer> values) {
        var numbersOfAValue = new HashMap<Integer, Integer>();
        for (int value : values) {
            int currentNumber = numbersOfAValue.getOrDefault(value, 0);
            numbersOfAValue.put(value, currentNumber + 1);
        }
        return numbersOfAValue;
    }

    /**
     * @param number     Number of times a value should appear in the card-values.
     * @param cardValues List of values in a poker hand.
     * @return List of the values of the poker hand that appear in it the specified number of times.
     */
    public List<Integer> getValuesAppearingNumberOfTimes(int number, List<Integer> cardValues) {
        List<Entry<Integer, Integer>> entriesAppearingNumberOfTimes = getEntriesAppearingNumberOfTimes(
            number,
            cardValues
        );
        return entriesAppearingNumberOfTimes.stream().map(Entry::getKey)
            .collect(Collectors.toList());
    }

    /**
     * @param number     Number of times a value should not appear in the card-values.
     * @param cardValues List of values in a poker hand.
     * @return List of the values of the poker hand that do not appear in it the specified number of
     * times.
     */
    public List<Integer> getValuesNotAppearingNumberOfTimes(int number, List<Integer> cardValues) {
        List<Integer> valuesAppearingNumberOfTimes = getValuesAppearingNumberOfTimes(
            number,
            cardValues
        );
        return cardValues
            .stream()
            .filter(value -> !valuesAppearingNumberOfTimes.contains(value))
            .collect(Collectors.toList());
    }

    /**
     * @param setSize    Size of the sets of interest. Set means an amount of cards with the same
     *                   value. For example, a set size of two would inspect all pairs in the poker
     *                   hand.
     * @param cardValues List of values in a poker hand.
     * @return Highest value in the sets of specified size in this hand.
     */
    public int getHighestValueOfSetOfSize(int setSize, List<Integer> cardValues) {
        List<Entry<Integer, Integer>> entriesAppearingNumberOfTimes = getEntriesAppearingNumberOfTimes(
            setSize,
            cardValues
        );
        Optional<Entry<Integer, Integer>> maxEntry = entriesAppearingNumberOfTimes
            .stream()
            .max(Entry.comparingByKey());
        return maxEntry.isPresent() ? maxEntry.get().getKey() : 0;
    }

    private List<Entry<Integer, Integer>> getEntriesAppearingNumberOfTimes(
        int number,
        List<Integer> cardValues
    ) {
        ValueUtils utils = new ValueUtils();
        Map<Integer, Integer> numbersOfAValue = utils.countAppearancesOfValues(cardValues);
        return numbersOfAValue.entrySet()
            .stream()
            .filter(entry -> entry.getValue() == number)
            .collect(Collectors.toList());
    }

    /**
     * @param number  Amount of sets of the specified size that should be contained in the hand.
     * @param setSize Size of the sets of interest. Set means an amount of cards with the same
     *                value. For example, a set size of two would inspect all pairs in the poker
     *                hand.
     * @param values  List of the values of the poker hand.
     * @return true, if the poker hand contains the specified number of hand of the specified size,
     * false, if not.
     */
    public boolean containsNumberOfSetsOfSize(int number, int setSize, List<Integer> values) {
        List<Entry<Integer, Integer>> entriesAppearingNumberOfTimes = getEntriesAppearingNumberOfTimes(
            setSize,
            values
        );
        int numberOfSetsOfSize = (int) entriesAppearingNumberOfTimes.stream()
            .filter(entry -> entry.getValue() == setSize).count();
        return numberOfSetsOfSize == number;
    }
}