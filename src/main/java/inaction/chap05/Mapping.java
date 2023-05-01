package inaction.chap05;

import static inaction.chap04.Dish.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import inaction.chap04.Dish;

public class Mapping {
	public static void main(String[] args) {
		System.out.println("======== map ========");
		System.out.println("==> dish name :");
		List<String> dishNames = menu.stream()
			.map(Dish::getName)
			.collect(Collectors.toList());
		System.out.println(dishNames);

		System.out.println("==> word list");
		List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
		List<Integer> wordLength = words.stream()
			.map(String::length)
			.collect(Collectors.toList());
		System.out.println(wordLength);

		System.out.println("==> dish name length :");
		List<Integer> dishNameLength = menu.stream()
			.map(Dish::getName)
			.map(String::length)
			.collect(Collectors.toList());
		System.out.println(dishNameLength);

		System.out.println("======= 고유 문자 변환 실패");
		words = Arrays.asList("Hello", "World");
		List<String[]> collect = words.stream()
			.map(word -> word.split(""))
			.distinct()
			.collect(Collectors.toList());
		for (String[] str : collect) {
			for (String s : str) {
				System.out.print(s + " ");
			}
			System.out.println();
		}

		System.out.println("======= 고유 문자 변환 실패2");
		List<Stream<String>> collect1 = words.stream()
			.map(word -> word.split(""))
			.map(Arrays::stream)
			.distinct()
			.collect(Collectors.toList());
		for (Stream<String> stringStream : collect1) {
			stringStream.forEach(System.out::print);
		}
		System.out.println();

		System.out.println("====== flatMap으로 고유 문자 변환하기");
		List<String> uniqueCharacters = words.stream()
			.map(word -> word.split(""))
			.flatMap(Arrays::stream)
			.distinct()
			.collect(Collectors.toList());
		System.out.println(uniqueCharacters);
	}
}
