
class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];
        int moves = 0;

        // Find minimum element
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }

        // Calculate minimum moves
        for (int num : nums) {
            moves += num - min;
        }

        return moves;
    }
}
