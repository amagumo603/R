package repositoryS3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
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
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;

public class S3Service {

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
				download();
				Path file = Paths.get("/tmp/memo.txt");
				List<String> text = Files.readAllLines(file); // UTF-8

				return text.get(0);
			} else {
				download();
				inputText(messageText);
				upload(client);
				return "登録完了٩(ˊᗜˋ*)و";
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

	/**
	 * ユーザが入力した文字をテキストに登録する
	 * @param messageText ユーザが入力した文字列
	 */
	private void inputText(String messageText) {

		try {
			FileWriter file = new FileWriter("/tmp/memo.txt", true);
			BufferedWriter bw = new BufferedWriter(file);

			//ファイルに書き込む
			bw.newLine();
			bw.write(messageText);

			bw.close();
			file.close();

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	// ｱｯﾌﾟﾛｰﾄﾞ処理
	private void upload(AmazonS3 client) throws Exception {
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