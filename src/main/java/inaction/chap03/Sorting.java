package inaction.chap03;

import static java.util.Comparator.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Sorting {
	public static void main(String[] args) {
		List<Apple> inventory = newOrShuffleInventory();

		// 1단계 : 코드 전달
		inventory.sort(new AppleComparator());
		System.out.println(inventory);

		// 셔플
		inventory = newOrShuffleInventory();

		// 2단계 : 익명 클래스 사용
		inventory.sort(new Comparator<Apple>() {
			@Override
			public int compare(Apple a1, Apple a2) {
				return a1.getWeight() - a2.getWeight();
			}
		});
		System.out.println(inventory);

		// 셔플
		inventory = newOrShuffleInventory();

		// 3단계 : 람다 표현식 사용
		inventory.sort((Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight());
		System.out.println(inventory);

		// 셔플
		inventory = newOrShuffleInventory();

		// 3-1 : 람다 형식 추론
		inventory.sort((a1, a2) -> a1.getWeight() - a2.getWeight());
		System.out.println(inventory);

		// 셔플
		inventory = newOrShuffleInventory();

		// 3-2 : Comparator.comparing
		inventory.sort(comparing(apple -> apple.getWeight()));
		System.out.println(inventory);

		// 셔플
		inventory = newOrShuffleInventory();

		// 4단계 : 메서드 참조 사용
		inventory.sort(comparing(Apple::getWeight));
		System.out.println(inventory);
	}

	private static List<Apple> newOrShuffleInventory() {
		return Arrays.asList(
			new Apple(80, Color.GREEN),
			new Apple(155, Color.GREEN),
			new Apple(120, Color.RED),
			new Apple(130, Color.RED),
			new Apple(150, Color.RED)
		);
	}

	static class AppleComparator implements Comparator<Apple> {
		@Override
		public int compare(Apple a1, Apple a2) {
			return a1.getWeight() - a2.getWeight();
		}
	}

}
