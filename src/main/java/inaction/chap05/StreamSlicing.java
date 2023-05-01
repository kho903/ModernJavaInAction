package inaction.chap05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import inaction.chap04.Dish;

public class StreamSlicing {
	public static void main(String[] args) {
		List<Dish> specialMenu = Arrays.asList(
			new Dish("season fruit", true, 120, Dish.Type.OTHER),
			new Dish("prawns", false, 300, Dish.Type.FISH),
			new Dish("rice", true, 350, Dish.Type.OTHER),
			new Dish("chicken", false, 400, Dish.Type.MEAT),
			new Dish("french fries", true, 530, Dish.Type.OTHER));

		System.out.println("Filtered sorted menu:");
		List<Dish> filteredMenu = specialMenu.stream()
			.filter(dish -> dish.getCalories() < 320)
			.collect(Collectors.toList());
		filteredMenu.forEach(System.out::println);

		System.out.println("Sorted menu sliced with takeWhile():");
		List<Dish> slicedMenu1 = specialMenu.stream()
			.takeWhile(dish -> dish.getCalories() < 320)
			.collect(Collectors.toList());
		slicedMenu1.forEach(System.out::println);

		System.out.println("Sorted menu sliced with dropWhile():");
		List<Dish> slicedMenu2 = specialMenu.stream()
			.dropWhile(dish -> dish.getCalories() < 320)
			.collect(Collectors.toList());
		slicedMenu2.forEach(System.out::println);

		System.out.println("Sorted menu sliced with limit():");
		List<Dish> limitMenu = specialMenu.stream()
			.filter(dish -> dish.getCalories() > 300)
			.limit(2)
			.collect(Collectors.toList());
		limitMenu.forEach(System.out::println);

		System.out.println("Sorted menu sliced with skip():");
		List<Dish> skipDishes = specialMenu.stream()
			.filter(d -> d.getCalories() > 300)
			.skip(2)
			.collect(Collectors.toList());
		skipDishes.forEach(System.out::println);

		List<Dish> meatSkip = specialMenu.stream()
			.filter(d -> d.getType() == Dish.Type.MEAT)
			.skip(2)
			.collect(Collectors.toList());
		meatSkip.forEach(System.out::println);
	}
}
