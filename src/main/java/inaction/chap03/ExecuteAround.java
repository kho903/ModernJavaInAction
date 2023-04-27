package inaction.chap03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class ExecuteAround {

	static final String FILE_NAME = "src/main/java/inaction/chap03/data.txt";

	public static void main(String[] args) throws IOException {
		System.out.println(processFile());
		String oneLine = processFile((BufferedReader br) -> br.readLine());
		System.out.println(oneLine);
		String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
		System.out.println(twoLines);
	}

	public static String processFile(BufferedReaderProcessor p) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
			return p.process(br);
		}
	}

	public static String processFile() throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
			return br.readLine();
		}
	}

	@FunctionalInterface
	public interface BufferedReaderProcessor {
		String process(BufferedReader b) throws IOException;
	}
}
