package inaction.chap05;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStream {
	public static void main(String[] args) {
		System.out.println("====== Stream.of ======");
		Stream<String> streamOf = Stream.of("Modern", "Java", "In", "Action");
		streamOf
			.map(String::toUpperCase)
			.forEach(System.out::println);

		System.out.println("====== Stream.empty ======");
		Stream<String> emptyStream = Stream.empty();

		// Steam null 명시적 확인
		String homeValue = System.getProperty("home");
		Stream<String> homeValueStream = homeValue == null ? Stream.empty() : Stream.of(homeValue);

		// Stream.ofNullable
		Stream<String> homeValueStream2 = Stream.ofNullable(System.getProperty("home"));

		Stream<String> values = Stream.of("config", "home", "user")
			.flatMap(key -> Stream.ofNullable(System.getProperty(key)));

		System.out.println("====== Arrays Stream ======");
		int[] nums = {2, 3, 5, 7, 11, 13};
		int sum = Arrays.stream(nums).sum();
		System.out.println(sum);

		System.out.println("====== File Stream ======");
		long uniqueWords = 0;
		try (Stream<String> lines =
				 Files.lines(Paths.get("src/main/java/inaction/chap05/data.txt"), Charset.defaultCharset())) {
			uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
				.distinct()
				.count();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println("uniqueWords = " + uniqueWords); // 9

		System.out.println("====== iterate method ======");
		Stream.iterate(0, n -> n + 2)
			.limit(10)
			.forEach(System.out::println);

		System.out.println("====== fibonacci ======");
		Stream.iterate(new int[] {0, 1}, (arr) -> new int[] {arr[1], arr[0] + arr[1]})
			.limit(20)
			.forEach(arr -> System.out.print(Arrays.toString(arr) + " "));

		System.out.println();

		Stream.iterate(new int[] {0, 1}, (arr) -> new int[] {arr[1], arr[0] + arr[1]})
			.limit(10)
			.map(t -> t[0])
			.forEach(t -> System.out.print(t + " "));
		System.out.println();

		System.out.println("====== iterate Predicate ======");
		IntStream.iterate(0, n -> n < 100, n -> n + 4)
			.forEach(t -> System.out.print(t + " "));

		// overflow
		// IntStream.iterate(0, n -> n + 4)
		// 	.filter(n -> n < 100)
		// 	.forEach(System.out::println);
		System.out.println();

		System.out.println("====== iterate takeWhile ======");
		IntStream.iterate(0, n -> n + 4)
			.takeWhile(n -> n < 100)
			.forEach(t -> System.out.print(t + " "));

		System.out.println();
		System.out.println("====== generate method ======");
		Stream.generate(Math::random)
			.limit(5)
			.forEach(t -> System.out.print(t + " "));

		System.out.println("====== Infinite Stream ======");
		IntStream ones = IntStream.generate(() -> 1);
		IntStream tows = IntStream.generate(new IntSupplier() {
			@Override
			public int getAsInt() {
				return 2;
			}
		});

		System.out.println("====== fibonacci IntSupplier ======");
		IntSupplier fib = new IntSupplier() {
			private int previous = 0;
			private int current = 1;
			@Override
			public int getAsInt() {
				int oldPrevious = this.previous;
				int nextValue = this.previous + this.current;
				this.previous = this.current;
				this.current = nextValue;
				return oldPrevious;
			}
		};
		IntStream.generate(fib).limit(10).forEach(t -> System.out.print(t + " "));
	}
}
