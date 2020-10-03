package solution;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int N = nums.length;
        for(int i=0; i<N; ++i){
            int secondVal = target - nums[i];
            for(int j=0; j<N; ++j){
                if(secondVal == nums[j] && secondVal != nums[i]) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[]{};
    }
}
