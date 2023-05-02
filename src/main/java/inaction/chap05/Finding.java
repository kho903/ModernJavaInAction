package inaction.chap05;

import static inaction.chap04.Dish.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import inaction.chap04.Dish;

public class Finding {
	public static void main(String[] args) {
		if (menu.stream().anyMatch(Dish::isVegetarian)) {
			System.out.println("The menu is (somewhat) vegetarian friendly!!");
		}

		boolean isHealthy1 = menu.stream()
			.allMatch(dish -> dish.getCalories() < 1000);
		System.out.println(isHealthy1);

		boolean isHealthy2 = menu.stream()
			.noneMatch(dish -> dish.getCalories() >= 1000);
		System.out.println(isHealthy2);

		Optional<Dish> dish = menu.stream()
			.filter(Dish::isVegetarian)
			.findAny();
		System.out.println(dish);

		menu.stream()
			.filter(Dish::isVegetarian)
			.findAny()
			.ifPresent(System.out::println);

		List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
			.map(n -> n * n)
			.filter(n -> n % 3 == 0)
			.findFirst();
		System.out.println(firstSquareDivisibleByThree);
	}
}
