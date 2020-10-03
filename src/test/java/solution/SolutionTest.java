package solution;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import solution.Solution;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    @ParameterizedTest
    @MethodSource("twoSumProvider")
    void twoSum(int[] nums, int target, int[] expected) {
        Solution sol1 = new Solution();
        assertArrayEquals(expected, sol1.twoSum(nums,target));
    }
    private static Stream twoSumProvider() {
        return Stream.of(
                Arguments.of(new int[]{2,7,11,15},9, new int[]{0,1}),
                Arguments.of(new int[]{2,7,11,15},13, new int[]{0,2}),
                Arguments.of(new int[]{1,4,5,7},11, new int[]{1,3})
        );
    }
}