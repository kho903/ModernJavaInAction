package inaction.chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class CombinationLambda {
	public static void main(String[] args) {
		List<Apple> inventory = newOrShuffleInventory();
		// 1. Comparator 조합
		System.out.println("=========== Comparator =============");
		// 먼저, Comparator.comparing 을 이용해 비교에 사용할 키를 추출하는 Function 기반의 Comparator 반환.
		Comparator<Apple> c = Comparator.comparing(Apple::getWeight);

		// 역정렬 : 무게를 내림차순으로 정렬
		inventory.sort(c.reversed());
		System.out.println(inventory);

		// Comparator 연결
		inventory.sort(c.reversed() // 무게를 내림차순으로 정렬
			.thenComparing(Apple::getColor)); // 두 사과의 무게가 같으면 색깔별로 정렬
		System.out.println(inventory);

		// 2. Predicate 조합
		System.out.println("=========== Predicate =============");
		Predicate<Apple> redApple = apple -> apple.getColor().equals(Color.RED);
		List<Apple> red = filterApples(inventory, redApple);
		System.out.println(red);

		// negate() - 반전시키기
		Predicate<Apple> notRedApple = redApple.negate();
		List<Apple> notRed = filterApples(inventory, notRedApple);
		System.out.println(notRed);

		// and 메서드
		Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() >= 150);
		List<Apple> redHeavy = filterApples(inventory, redAndHeavyApple);
		System.out.println(redHeavy);

		// and + or 메서드
		Predicate<Apple> redAndHeavyOrGreen = redApple.and(apple -> apple.getWeight() >= 150)
			.or(apple -> apple.getColor().equals(Color.GREEN));
		List<Apple> redHeavyOrGreen = filterApples(inventory, redAndHeavyOrGreen);
		System.out.println(redHeavyOrGreen);

		// 3. Function 조합
		System.out.println("=========== Function =============");
		Function<Integer, Integer> f = x -> x + 1;
		Function<Integer, Integer> g = x -> x * 2;
		Function<Integer, Integer> h = f.andThen(g);
		System.out.println(h.apply(1)); // 4
		Function<Integer, Integer> h2 = f.compose(g);
		System.out.println(h2.apply(1)); // 3

		// Letter 예제
		// 헤더 추가 -> 철자 검사 -> 푸터 추가
		Function<String, String> addHeader = Letter::addHeader;
		Function<String, String> transformationPipeline =
			addHeader
				.andThen(Letter::checkSpelling)
				.andThen(Letter::addFooter);
		// 철자 검사 제외하고 헤더/푸터만 추가하는 경우
		addHeader = Letter::addHeader;
		transformationPipeline = addHeader.andThen(Letter::addFooter);
	}

	static class Letter {
		public static String addHeader(String text) {
			return "From Raoul, Mario and Alan : " + text;
		}

		public static String addFooter(String text) {
			return text + " Kind regards";
		}

		public static String checkSpelling(String text) {
			return text.replaceAll("labda", "lambda");
		}
	}

	public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}
	private static List<Apple> newOrShuffleInventory() {
		return Arrays.asList(
			new Apple(80, Color.GREEN),
			new Apple(155, Color.GREEN),
			new Apple(155, Color.RED),
			new Apple(120, Color.RED),
			new Apple(130, Color.RED),
			new Apple(150, Color.RED)
		);
	}
}
