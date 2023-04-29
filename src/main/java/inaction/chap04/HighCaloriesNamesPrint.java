package inaction.chap04;

import static inaction.chap04.Dish.*;

import java.util.List;
import java.util.stream.Collectors;

public class HighCaloriesNamesPrint {
	public static void main(String[] args) {
		List<String> names =
			menu.stream()
				.filter(dish -> {
					System.out.println("filtering : " + dish.getName());
					return dish.getCalories() > 300;
				}) // 필터링한 요리명을 출력함
				.map(dish -> {
					System.out.println("mapping : " + dish.getName());
					return dish.getName();
				}) // 추출한 요리명을 출력함
				.limit(3)
				.collect(Collectors.toList());
		System.out.println(names);
		/*
			filtering : pork
			mapping : pork
			filtering : beef
			mapping : beef
			filtering : chicken
			mapping : chicken
			[pork, beef, chicken]
		 */
	}
}
