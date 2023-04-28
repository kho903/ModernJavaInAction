package inaction.chap04;

import static inaction.chap04.Dish.*;

import java.util.List;
import java.util.stream.Collectors;

public class HighCaloriesNames {
	public static void main(String[] args) {
		List<String> threeHighCaloricDishNames =
			menu.stream()
				.filter(dish -> dish.getCalories() > 300)
				.map(Dish::getName)
				.limit(3)
				.collect(Collectors.toList());
		System.out.println(threeHighCaloricDishNames);
	}
}
