package inaction.chap05.ex;

import static java.util.Comparator.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {

		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(
			new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000),
			new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2012, 710),
			new Transaction(mario, 2012, 700),
			new Transaction(alan, 2012, 950)
		);

		// 1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정렬
		List<Transaction> transaction2011 = transactions.stream()
			.filter(t -> t.getYear() == 2011)
			.sorted(comparing(Transaction::getValue))
			.collect(Collectors.toList());
		System.out.println(transaction2011);

		// 2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.
		List<String> traderCity = transactions.stream()
			.map(transaction -> transaction.getTrader().getCity())
			.distinct()
			.collect(Collectors.toList());
		System.out.println(traderCity);

		// 3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
		List<Trader> cambridgeTrader = transactions.stream()
			.map(Transaction::getTrader)
			.filter(trader -> trader.getCity().equals("Cambridge"))
			.distinct()
			.sorted(comparing(Trader::getName))
			.collect(Collectors.toList());
		System.out.println(cambridgeTrader);

		// 4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.
		List<String> traderNames = transactions.stream()
			.map(t -> t.getTrader().getName())
			.distinct()
			.sorted()
			.collect(Collectors.toList());
		System.out.println(traderNames);

		// 5. 밀라노에 거래자가 있는가?
		boolean isMilan = transactions.stream()
			.anyMatch(t -> t.getTrader().getCity().equals("Milan"));
		System.out.println(isMilan);

		// 6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오.
		List<Integer> cambridgeValue = transactions.stream()
			.filter(t -> t.getTrader().getCity().equals("Cambridge"))
			.map(Transaction::getValue)
			.collect(Collectors.toList());
		System.out.println(cambridgeValue);

		// 7. 전체 트랜잭션 중 최댓값은 얼마인가?
		int max = transactions.stream()
			.map(Transaction::getValue)
			.reduce(0, Integer::max);
		System.out.println(max);

		// 8. 전체 트랜잭션 중 최솟값은 얼마인가?
		Optional<Transaction> min = transactions.stream()
			.min(comparing(Transaction::getValue));
		System.out.println(min);
	}

}
