package inaction.chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionExample {
	public static void main(String[] args) {
		// Predicate
		Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
		List<String> listOfStrings = Arrays.asList("", "a", "b", "", "", "c");
		List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
		System.out.println(nonEmpty);

		// Consumer
		forEach(
			Arrays.asList(1, 2, 3, 4, 5),
			(Integer i) -> System.out.println(i)
		);

		// Function
		List<Integer> l = map(
			Arrays.asList("lambdas", "in", "action"),
			(String s) -> s.length()
		);
		System.out.println(l);
	}

	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> results = new ArrayList<>();
		for (T t : list) {
			if (p.test(t)) {
				results.add(t);
			}
		}
		return results;
	}

	public static <T> void forEach(List<T> list, Consumer<T> c) {
		for (T t : list) {
			c.accept(t);
		}
	}

	public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
		List<R> result = new ArrayList<>();
		for (T t : list) {
			result.add(f.apply(t));
		}
		return result;
	}
}
