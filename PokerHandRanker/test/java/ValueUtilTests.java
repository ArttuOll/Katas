import enums.SpecialValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.ValueUtils;

public class ValueUtilTests {

    private static ValueUtils valueUtils;

    @BeforeAll
    static void setup() {
        valueUtils = new ValueUtils();
    }

    @Test
    void appearancesOfValuesInListCountedCorrectly() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 4, 4, 5, 5));
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(1, 2);
        expected.put(2, 1);
        expected.put(3, 1);
        expected.put(4, 2);
        expected.put(5, 2);
        Map<Integer, Integer> actual = valueUtils.countAppearancesOfValues(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void appearancesOfValuesInEmptyListCountedCorrectly() {
        List<Integer> input = new ArrayList<>(Collections.emptyList());
        Map<Integer, Integer> expected = new HashMap<>();
        Map<Integer, Integer> actual = valueUtils.countAppearancesOfValues(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void valuesAppearingNumberOfTimesReturnedCorrectly() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 4, 4, 5, 5));
        int numberOfTimes = 2;
        var expected = Arrays.asList(1, 4, 5);
        List<Integer> actual = valueUtils.getValuesAppearingNumberOfTimes(numberOfTimes, input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void emptyListValueAppearancesCountedCorrectly() {
        List<Integer> input = new ArrayList<>(Collections.emptyList());
        int numberOfTimes = 2;
        var expected = Collections.emptyList();
        List<Integer> actual = valueUtils.getValuesAppearingNumberOfTimes(numberOfTimes, input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void valuesNotAppearingNumberOfTimesReturnedCorrectly() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 4, 4, 5, 5));
        int numberOfTimes = 2;
        var expected = Arrays.asList(2, 3);
        List<Integer> actual = valueUtils.getValuesNotAppearingNumberOfTimes(numberOfTimes, input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void valuesNotAppearingNumberOfTimesReturnsCorrectlyWithEmptyList() {
        List<Integer> input = new ArrayList<>(Collections.emptyList());
        int numberOfTimes = 2;
        var expected = Collections.emptyList();
        List<Integer> actual = valueUtils.getValuesNotAppearingNumberOfTimes(numberOfTimes, input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void highestValueOfSetOfSizeReturnedCorrectly() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 4, 4, 5, 5));
        int setSize = 2;
        var expected = 5;
        int actual = valueUtils.getHighestValueOfSetOfSize(setSize, input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void highestValueOfEmptyListReturnedCorrectly() {
        List<Integer> input = new ArrayList<>(Collections.emptyList());
        int setSize = 2;
        var expected = 0;
        int actual = valueUtils.getHighestValueOfSetOfSize(setSize, input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void setsOfTwoIdentifiedCorrectly() {
        List<Integer> twoPairs = Arrays.asList(2, 2, 1, 1, 4);
        Assertions.assertTrue(valueUtils.containsNumberOfSetsOfSize(2, 2, twoPairs));
    }

    @Test
    void setsOfTwoWithSpecialValuesIdentifiedCorrectly() {
        List<Integer> twoPairs = Arrays.asList(
            SpecialValue.A.value,
            SpecialValue.A.value,
            SpecialValue.Q.value,
            SpecialValue.Q.value,
            SpecialValue.K.value
        );
        Assertions.assertTrue(valueUtils.containsNumberOfSetsOfSize(2, 2, twoPairs));
    }

    @Test
    void setOfTwoAndThreeIdentifiedCorrectly() {
        List<Integer> fullHouse = Arrays.asList(2, 2, 1, 1, 1);
        Assertions.assertTrue(valueUtils.containsNumberOfSetsOfSize(1, 2, fullHouse) && valueUtils
            .containsNumberOfSetsOfSize(1, 3, fullHouse));
    }

    @Test
    void numberOfSetsOfSizeNotFalselyIdentified() {
        List<Integer> notFourOfAKind = Arrays.asList(2, 3, SpecialValue.T.value, 1, 1);
        Assertions.assertFalse(valueUtils.containsNumberOfSetsOfSize(1, 4, notFourOfAKind));
        notFourOfAKind = Arrays.asList(1, 1, 1, 1, 1);
        Assertions.assertFalse(valueUtils.containsNumberOfSetsOfSize(1, 4, notFourOfAKind));
    }
}
