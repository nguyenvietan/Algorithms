public class ContainerWithMostWater {

	public int maxArea(int[] height) {
		int curArea, area = 0, l = 0, r = height.length-1;
		while (l <= r) {
			curArea = Math.min(height[r], height[l]) * (r - l);
			area = Math.max(area, curArea);
			if (height[l] <= height[r]) l++; else r--;
		}
		return area;
	}

	public static void main(String[] args) {
		ContainerWithMostWater obj = new ContainerWithMostWater();
		int[] height = { 1,8,6,2,5,4,8,3,7 };
		System.out.println(obj.maxArea(height));
	}
}
