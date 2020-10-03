package solution;

import resources.ListNode;
import resources.Utility;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    @ParameterizedTest
    @MethodSource("hasCycleProvider")
    void hasCycle(String input, int pos, boolean expect) {
        Utility utility = new Utility();
        Solution sol = new Solution();
        ListNode inputLinkedList = utility.stringToCircularList(input,pos);
        assertEquals(expect,sol.hasCycle(inputLinkedList));
    }
    private static Stream hasCycleProvider() {
        return Stream.of(
                Arguments.of("[3,2,0,-4,7,8]", 2, true),
                Arguments.of("[1,2]",0, true),
                Arguments.of("[2,3,0,4]",3,true),
                Arguments.of("[2,3,0,4]",-1,false)
        );
    }
}