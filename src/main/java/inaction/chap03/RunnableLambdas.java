package inaction.chap03;

public class RunnableLambdas {

	public static void main(String[] args) {
		Runnable r1 = () -> System.out.println("Hello World 1");
		Runnable r2 = new Runnable() { // 익명 클래스
			@Override
			public void run() {
				System.out.println("Hello World 2");
			}
		};
		process(r1);
		process(r2);
		process(() -> System.out.println("Hello World 3"));
	}

	public static void process(Runnable r) {
		r.run();
	}
}
