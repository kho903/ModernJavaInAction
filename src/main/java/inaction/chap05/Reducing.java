package inaction.chap05;

import static inaction.chap04.Dish.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Reducing {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(4, 5, 3, 9);
		// int sum = numbers.stream().reduce(0, (a, b) -> a + b);
		int sum = numbers.stream().reduce(0, Integer::sum);
		System.out.println(sum);
		int multi = numbers.stream().reduce(1, (a, b) -> a * b);
		System.out.println(multi);

		Optional<Integer> noInitial = numbers.stream().reduce((a, b) -> a + b);
		System.out.println(noInitial);

		Optional<Integer> max = numbers.stream().reduce(Integer::max);
		System.out.println(max);
		Optional<Integer> min = numbers.stream().reduce(Integer::min);
		System.out.println(min);

		int countMenu = menu.stream()
			.map(d -> 1)
			.reduce(0, Integer::sum);
		System.out.println(countMenu);

		long count = menu.stream().count();
		System.out.println(count);
	}
}
