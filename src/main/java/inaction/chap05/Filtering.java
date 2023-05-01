package inaction.chap05;

import static inaction.chap04.Dish.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import inaction.chap04.Dish;

public class Filtering {
	public static void main(String[] args) {
		System.out.println("==== Filtering with a predicate ====");
		List<Dish> vegetarianMenu = menu.stream()
			.filter(Dish::isVegetarian)
			.collect(Collectors.toList());
		vegetarianMenu.forEach(System.out::println);

		System.out.println("==== Filtering unique elements ====");
		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
		numbers.stream()
			.filter(i -> i % 2 == 0)
			.distinct()
			.forEach(System.out::println);
	}
}
