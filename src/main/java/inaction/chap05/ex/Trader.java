package inaction.chap05.ex;

public class Trader {
	private final String name;
	private final String city;

	public Trader(String name, String city) {
		this.name = name;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Trader trader = (Trader)o;

		if (getName() != null ? !getName().equals(trader.getName()) : trader.getName() != null)
			return false;
		return getCity() != null ? getCity().equals(trader.getCity()) : trader.getCity() == null;
	}

	@Override
	public int hashCode() {
		int result = getName() != null ? getName().hashCode() : 0;
		result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Trader{" +
			"name='" + name + '\'' +
			", city='" + city + '\'' +
			'}';
	}
}
