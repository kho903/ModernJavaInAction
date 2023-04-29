package inaction.chap04;

import static inaction.chap04.Dish.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ExternalInternalIteration {
	public static void main(String[] args) {
		List<String> names = new ArrayList<>();
		for (Dish dish : menu) { // 메뉴 리스트를 명시적으로 순차 반복.
			names.add(dish.getName()); // 이름을 추출해서 리스트에 추가함.
		}

		names = new ArrayList<>();
		Iterator<Dish> iterator = menu.iterator();
		while (iterator.hasNext()) { // 명시적 반복
			Dish dish = iterator.next();
			names.add(dish.getName());
		}
		names = menu.stream()
			.map(Dish::getName) // map 메서드를 getName 메서드로 파라미터화해서 요리명 추출함.
			.collect(Collectors.toList()); // 파이프라인을 실행함. 반복자는 필요 없음.


		// 내부 반복 vs 외부 반복 퀴즈
		List<String> highCaloricDishes = new ArrayList<>();
		Iterator<Dish> iter = menu.iterator();
		while (iter.hasNext()) {
			Dish dish = iter.next();
			if (dish.getCalories() > 300) {
				highCaloricDishes.add(dish.getName());
			}
		}

		highCaloricDishes = menu.stream()
			.filter(dish -> dish.getCalories() > 300)
			.map(Dish::getName)
			.collect(Collectors.toList());
	}
}
