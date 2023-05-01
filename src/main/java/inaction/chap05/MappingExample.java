package inaction.chap05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MappingExample {
	public static void main(String[] args) {
		System.out.println("제곱근 반환");
		List<Integer> numbers = Arrays.asList(1,2,3,4,5);
		List<Integer> squares = numbers.stream()
			.map(n -> n * n)
			.collect(Collectors.toList());
		System.out.println(squares);

		System.out.println("모든 숫자 쌍 리스트");
		List<Integer> nums1 = Arrays.asList(1, 2, 3);
		List<Integer> nums2 = Arrays.asList(3, 4);
		List<int[]> pairs = nums1.stream()
			.flatMap(i -> nums2.stream()
				.map(j -> new int[] {i, j}))
			.collect(Collectors.toList());
		pairs.stream().forEach(arr -> System.out.println(Arrays.toString(arr)));

		System.out.println("모든 숫자 쌍 리스트 내에서 합이 3으로 나누어 떨어지는 쌍만 조회");
		List<int[]> pairs2 = nums1.stream()
			.flatMap(i -> nums2.stream()
				.filter(j -> (i + j) % 3 == 0)
				.map(j -> new int[] {i, j}))
			.collect(Collectors.toList());
		pairs2.stream().forEach(arr -> System.out.println(Arrays.toString(arr)));
	}
}
