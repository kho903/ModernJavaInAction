package inaction.chap04;

import static java.util.Comparator.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamBasic {
	public static void main(String[] args) {
		getLowCaloricDishesNameInJava7(Dish.menu).forEach(System.out::println);

		System.out.println("=======================================");

		getLowCaloricDishesNameInJava8(Dish.menu).forEach(System.out::println);
	}

	private static List<String> getLowCaloricDishesNameInJava7(List<Dish> dishes) {
		List<Dish> lowCaloricDishes = new ArrayList<>();
		for (Dish dish : dishes) {
			if (dish.getCalories() < 400) {
				lowCaloricDishes.add(dish);
			}
		}
		Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
			@Override
			public int compare(Dish d1, Dish d2) {
				return Integer.compare(d1.getCalories(), d2.getCalories());
			}
		});
		List<String> lowCaloricDishesName = new ArrayList<>();
		for (Dish dish : lowCaloricDishes) {
			lowCaloricDishesName.add(dish.getName());
		}
		return lowCaloricDishesName;
	}

	private static List<String> getLowCaloricDishesNameInJava8(List<Dish> dishes) {
		return dishes.stream()
			.filter(d -> d.getCalories() < 400)
			.sorted(comparing(Dish::getCalories))
			.map(Dish::getName)
			.collect(Collectors.toList());
	}
}
