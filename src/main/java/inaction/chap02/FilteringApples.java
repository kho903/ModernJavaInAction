package inaction.chap02;

import static inaction.chap02.FilteringApples.Color.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringApples {

	public static void main(String[] args) {
		List<Apple> inventory = Arrays.asList(
			new Apple(80, Color.GREEN),
			new Apple(155, Color.GREEN),
			new Apple(120, Color.RED)
		);

		List<Apple> greenApples = filterGreenApples(inventory);
		System.out.println("greenApples = " + greenApples);

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
}
