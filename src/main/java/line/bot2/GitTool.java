package line.bot2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class GitTool {

	public void memoTool(String[] args) {
		try {
			File file = new File("src/resources/java/memo.txt");
			FileReader filereader = new FileReader(file);

		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}