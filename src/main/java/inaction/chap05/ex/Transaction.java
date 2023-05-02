package inaction.chap05.ex;

public class Transaction {
	private final Trader trader;
	private final int year;
	private final int value;

	public Transaction(Trader trader, int year, int value) {
		this.trader = trader;
		this.year = year;
		this.value = value;
	}

	public Trader getTrader() {
		return trader;
	}

	public int getYear() {
		return year;
	}

	public int getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Transaction that = (Transaction)o;

		if (getYear() != that.getYear())
			return false;
		if (getValue() != that.getValue())
			return false;
		return getTrader() != null ? getTrader().equals(that.getTrader()) : that.getTrader() == null;
	}

	@Override
	public int hashCode() {
		int result = getTrader() != null ? getTrader().hashCode() : 0;
		result = 31 * result + getYear();
		result = 31 * result + getValue();
		return result;
	}

	@Override
	public String toString() {
		return "Transaction{" +
			"trader=" + trader +
			", year=" + year +
			", value=" + value +
			'}';
	}
}
