package solution;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import resources.TreeNode;
import resources.Utility;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertSame;

class SolutionTest {
    @ParameterizedTest
    @MethodSource("isSymmetricProvider")
    void isSymmetric(String input, boolean expected) {
        TreeNode root = Utility.stringToTreeNode(input);
        boolean result = new Solution().isSymmetric(root);
        assertSame(expected, result);
    }

    private static Stream isSymmetricProvider() {
        return Stream.of(
                Arguments.of("[1, 2, 2, 3, 4, 4, 3]", true),
                Arguments.of("[1, 2, 2, null, 3, null, 3]", false)
        );
    }
}