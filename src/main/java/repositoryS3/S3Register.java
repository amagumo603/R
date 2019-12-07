package repositoryS3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3Register {

	/**
	 *
	 */
	public void update(AmazonS3 client, String messageText) {

		try {
			// 入力文字列をテキストに書き込み
			inputText(messageText);

			// s3にアップロード
			upload(client);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/**String messageText
	 * ユーザが入力した文字をテキストに登録する
	 * @param messageText ユーザが入力した文字列
	 */
	public void inputText(String messageText) {

		try {
			FileWriter file = new FileWriter("/tmp/memo.txt", true);
			BufferedWriter bw = new BufferedWriter(file);

			//ファイルに書き込む
			bw.write(messageText.substring(3));
			bw.newLine();

			bw.close();
			file.close();

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	/**String messageText
	 * ユーザが入力した文字をテキストから削除する
	 * @param messageText ユーザが入力した文字列
	 */
	public void deleteText(String messageText) {

		try {
			// "、"以下の文字を切り出し
			String delTarget = messageText.substring(messageText.indexOf("、") + 1);
			List<String> lines = Files.readAllLines(Paths.get("/tmp/memo.txt"), StandardCharsets.UTF_8);

			if (lines.indexOf(delTarget) != -1) {
				lines.remove(lines.indexOf(delTarget));
			} else {
				lines.remove(Integer.parseInt(delTarget) - 1);
			}

			FileWriter file = new FileWriter("/tmp/memo.txt");
			BufferedWriter bw = new BufferedWriter(file);

			//ファイルに書き込む
			for (String tmpLine : lines) {
				bw.write(tmpLine);
				bw.newLine();
			}
			bw.close();
			file.close();

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	// ｱｯﾌﾟﾛｰﾄﾞ処理
	public void upload(AmazonS3 client) throws Exception {
		System.out.println("upload 開始");

		File file = new File("/tmp/memo.txt");
		FileInputStream fis = new FileInputStream(file);

		ObjectMetadata om = new ObjectMetadata();
		om.setContentLength(file.length());

		final PutObjectRequest putRequest = new PutObjectRequest(Const.S3_BUCKET_NAME, file.getName(), fis, om);

		// 権限の設定
		putRequest.setCannedAcl(CannedAccessControlList.PublicRead);
		try {
			// アップロード
			client.putObject(putRequest);
		} catch (NoClassDefFoundError e) {
			// TODO とりあえずエラーを握りつぶし
		}
		fis.close();

		System.out.println("upload 終わり");
	}

}