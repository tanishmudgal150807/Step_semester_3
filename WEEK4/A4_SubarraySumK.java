import java.util.HashMap;

public class A4_SubarraySumK {

    static int subarraySum(int[] nums, int k) {

        HashMap<Integer, Integer> prefixSum = new HashMap<>();

        // Empty prefix
        prefixSum.put(0, 1);

        int currentSum = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {

            currentSum += nums[i];

            // Check if currentSum - k appeared before
            int requiredSum = currentSum - k;

            if (prefixSum.containsKey(requiredSum)) {
                count += prefixSum.get(requiredSum);
            }

            // Store/update current prefix sum
            prefixSum.put(
                currentSum,
                prefixSum.getOrDefault(currentSum, 0) + 1
            );
        }

        return count;
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1};
        int k = 2;

        System.out.println(subarraySum(nums, k));
    }
}