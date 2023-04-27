package inaction.chap02;

import static inaction.chap02.FilteringApples.Color.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FilteringApples {

	public static void main(String[] args) {
		List<Apple> inventory = Arrays.asList(
			new Apple(80, Color.GREEN),
			new Apple(155, Color.GREEN),
			new Apple(120, Color.RED),
			new Apple(160, Color.RED)
		);

		List<Apple> greenApples = filterGreenApples(inventory);
		System.out.println("greenApples = " + greenApples);

		List<Apple> greenApples2 = filterApplesByColor(inventory, GREEN);
		List<Apple> redApples = filterApplesByColor(inventory, RED);
		System.out.println("greenApples2 = " + greenApples2);
		System.out.println("redApples = " + redApples);

		List<Apple> over100Apples = filterApplesByWeight(inventory, 100);
		System.out.println("over100Apples = " + over100Apples);

		List<Apple> apples = filterApples(inventory, new AppleRedAndHeavyPredicate());
		System.out.println("apples = " + apples);

		prettyPrintApple(inventory, new AppleFancyFormatter());
		prettyPrintApple(inventory, new AppleSimpleFormatter());

		List<Apple> filterRedApples = filterApples(inventory, new ApplePredicate() {
			@Override
			public boolean test(Apple apple) {
				return RED.equals(apple.getColor());
			}
		});
		System.out.println("filterRedApples = " + filterRedApples);

		List<Apple> result = filterApples(inventory, (Apple apple) -> RED.equals(apple.getColor()));
		System.out.println("result = " + result);

		List<Apple> redAppleList = filter(inventory, (Apple apple) -> RED.equals(apple.getColor()));
		System.out.println("redAppleList = " + redAppleList);

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
		System.out.println("evenNumbers = " + evenNumbers);

		inventory.sort(new Comparator<Apple>() {
			@Override
			public int compare(Apple o1, Apple o2) {
				return Integer.compare(o1.getWeight(), o2.getWeight());
			}
		});
		System.out.println(inventory);

		inventory.sort(
			(Apple a1, Apple a2) -> Integer.compare(a1.getWeight(), a2.getWeight()));
		System.out.println(inventory);
	}

	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (GREEN.equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getColor().equals(color)) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	enum Color {
		RED,
		GREEN
	}

	public static class Apple {
		private int weight = 0;
		private Color color;

		public Apple(int weight, Color color) {
			this.weight = weight;
			this.color = color;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		@Override
		public String toString() {
			return "Apple{" +
				"weight=" + weight +
				", color='" + color + '\'' +
				'}';
		}
	}

	interface ApplePredicate {
		boolean test(Apple apple);
	}

	static class AppleHeavyWeightPredicate implements ApplePredicate {
		@Override
		public boolean test(Apple apple) {
			return apple.getWeight() > 150;
		}
	}

	static class AppleGreenColorPredicate implements ApplePredicate {

		@Override
		public boolean test(Apple apple) {
			return GREEN.equals(apple.getColor());
		}
	}

	static class AppleRedAndHeavyPredicate implements ApplePredicate {

		@Override
		public boolean test(Apple apple) {
			return RED.equals(apple.getColor())
				&& apple.getWeight() > 150;
		}
	}

	public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
		for (Apple apple : inventory) {
			String output = formatter.accept(apple);
			System.out.println(output);
		}
	}

	interface AppleFormatter {
		String accept(Apple apple);
	}

	public static class AppleFancyFormatter implements AppleFormatter {
		@Override
		public String accept(Apple apple) {
			String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
			return "A " + characteristic + " " + apple.getColor() + " apple";
		}
	}

	public static class AppleSimpleFormatter implements AppleFormatter {
		@Override
		public String accept(Apple apple) {
			return "An apple of " + apple.getWeight() + "g";
		}
	}

	public interface Predicate<T> {
		boolean test(T t);
	}

	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> result = new ArrayList<>();
		for (T e : list) {
			if (p.test(e)) {
				result.add(e);
			}
		}
		return result;
	}

}
