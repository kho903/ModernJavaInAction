package inaction.chap05;

import static inaction.chap04.Dish.*;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import inaction.chap04.Dish;

public class NumericStreams {
	public static void main(String[] args) {

		int calories = menu.stream()
			.map(Dish::getCalories)
			.reduce(0, Integer::sum);

		// int calories = menu.stream()
		// 	.map(Dish:getCalories)
    	// 	.sum(); // 존재 X
		System.out.println(calories);

		int sumCalories = menu.stream()
			.mapToInt(Dish::getCalories)
			.sum();
		System.out.println(sumCalories);

		IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
		Stream<Integer> stream = intStream.boxed();

		OptionalInt maxCalories = menu.stream()
			.mapToInt(Dish::getCalories)
			.max();
		System.out.println(maxCalories);
		int max = maxCalories.orElse(1);
		System.out.println(max);

		IntStream evenNumbers = IntStream.rangeClosed(1, 100)
			.filter(n -> n % 2 == 0);
		IntStream evenNumbers2 = IntStream.range(1, 100)
			.filter(n -> n % 2 == 0);
		System.out.println(evenNumbers.count());
		System.out.println(evenNumbers2.count());
		IntStream.range(1, 100)
			.forEach(System.out::println);

		Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
			.flatMap(a ->
				IntStream.range(a, 100)
					.filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
					.mapToObj(b ->
						new int[] {a, b, (int)Math.sqrt(a * a + b * b)})
			);

		pythagoreanTriples.limit(5)
			.forEach(t ->
				System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

		Stream<double[]> pythagoreanTriples2 = IntStream.range(1, 100).boxed()
			.flatMap(a -> IntStream.range(a, 100)
				.mapToObj(
					b -> new double[] {a, b, Math.sqrt(a * a + b * b)})
				.filter(t -> t[2] % 1 == 0));


		pythagoreanTriples2.limit(5)
			.forEach(t ->
				System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

	}
}
