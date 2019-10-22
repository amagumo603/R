package repositoryS3;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;

public class S3Service {

	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	// S3にテキストを登録するクラス
	S3Register s3register;

	/**
	 * S3と連携するサービス
	 * @param messageText
	 */
	public String s3Service(String messageText) {
		AmazonS3 client = auth();

		ObjectListing objListing = client.listObjects(Const.S3_BUCKET_NAME); // バケット名を指定
		List<S3ObjectSummary> objList = objListing.getObjectSummaries();

		try {
			if (messageText.equals("一覧")) {
				String reText = "";
				download();
				Path file = Paths.get("/tmp/memo.txt");
				List<String> textList = Files.readAllLines(file); // UTF-8
				for (String text : textList) {
					reText = reText + LINE_SEPARATOR + text;
				}
				return reText;
			} else if (messageText.startsWith("登録、")) {
				download();
				s3register.inputText(messageText);
				s3register.upload(client);
				//s3register.update(client, messageText);
				return "登録完了٩(ˊᗜˋ*)و";
			} else if (messageText.startsWith("削除、")) {
				download();
				s3register.deleteText(messageText);
				s3register.upload(client);
				//s3register.update(client, messageText);
				return "削除完了 :)";
			}

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		;
		return "";
	}

	// 認証処理
	private AmazonS3 auth() {
		System.out.println("auth 開始");

		// AWSの認証情報
		AWSCredentials credentials = new BasicAWSCredentials(Const.S3_ACCESS_KEY, Const.S3_SECRET_KEY);

		// クライアント設定
		ClientConfiguration clientConfig = new ClientConfiguration();

		// エンドポイント設定
		EndpointConfiguration endpointConfiguration = new EndpointConfiguration(Const.S3_SERVICE_END_POINT,
				Const.S3_REGION);

		// S3アクセスクライアントの生成
		AmazonS3 client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withClientConfiguration(clientConfig)
				.withEndpointConfiguration(endpointConfiguration).build();

		System.out.println("auth 終わり");
		return client;
	}

	// ﾀﾞｳﾝﾛｰﾄﾞ処理
	private void download() throws Exception {
		System.out.println("download 開始");

		// 認証処理
		AmazonS3 client = auth();

		final GetObjectRequest getRequest = new GetObjectRequest(Const.S3_BUCKET_NAME, "memo.txt");

		S3Object object = client.getObject(getRequest);

		FileOutputStream fos = new FileOutputStream(new File("/tmp/memo.txt"));
		IOUtils.copy(object.getObjectContent(), fos);

		fos.close();

		System.out.println("download 終わり");
	}

}