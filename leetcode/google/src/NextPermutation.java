import java.util.Arrays;

public class NextPermutation {

	public void next(int[] nums) {
		int n = nums.length, i;
		boolean exist = false;
		// find the rightmost pair: a[i] > a[i-1]
		for (i = n-1; i > 0; --i) {
			if (nums[i] > nums[i-1]) { exist = true; break; }
		}
		// if pair not found
		if (!exist) { reverse(nums, 0, n-1); return; }
		// find the element on the right just larger (>) a[i-1], swap(a[i-1], a[k])
		int k = i;
		while (k < n && nums[k] > nums[i-1]) ++k;
		System.out.printf("i = %d, k = %d\n", i, k);
		swap(nums, i-1, k-1);
		for (int x : nums) System.out.printf("%d ", x); System.out.println();
		// reverse a[i], a[i+1], ..., a[n-1]
		reverse(nums, i, n-1);
		for (int x : nums) System.out.printf("%d ", x); System.out.println();
	}

	public void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public void reverse(int[] nums, int i, int j) {
		while (i <= j) {
			swap(nums, i, j);
			++i; --j;
		}
	};

	public static void main(String[] args) {
		NextPermutation nextPermutation = new NextPermutation();
		// int[] nums = {1, 5, 8, 4, 7, 6, 5, 3, 1};
		int[] nums = {1, 5, 1};
		nextPermutation.next(nums);
		for (int x : nums) System.out.printf("%d ", x); System.out.println();
	}

}
