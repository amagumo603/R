package repositoryS3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

public class s3Tool {

	public void s3Service() {
		AmazonS3 client = auth();

		ObjectListing objListing = client.listObjects(Const.S3_BUCKET_NAME); // バケット名を指定
		List<S3ObjectSummary> objList = objListing.getObjectSummaries();

		// ファイル一覧を出力
		for (S3ObjectSummary obj : objList) {
			// キー(ファイルパス)・サイズ・最終更新日
			System.out.println("Key [" + obj.getKey() + "] / Size [" + obj.getSize() + " B] / Last Modified ["
					+ obj.getLastModified() + "]");
		}
		try {
			upload(client);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		;
	}

	// 認証処理
	private static AmazonS3 auth() {
		System.out.println("auth start");

		// AWSの認証情報
		AWSCredentials credentials = new BasicAWSCredentials(Const.S3_ACCESS_KEY, Const.S3_SECRET_KEY);

		// クライアント設定
		ClientConfiguration clientConfig = new ClientConfiguration();
		//clientConfig.setProxyHost("[proxyHost]");
		//clientConfig.setProxyPort([portNo]);

		// エンドポイント設定
		EndpointConfiguration endpointConfiguration = new EndpointConfiguration(Const.S3_SERVICE_END_POINT,
				Const.S3_REGION);

		// S3アクセスクライアントの生成
		AmazonS3 client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withClientConfiguration(clientConfig)
				.withEndpointConfiguration(endpointConfiguration).build();

		System.out.println("auth end");
		return client;
	}

	// ｱｯﾌﾟﾛｰﾄﾞ処理
	private static void upload(AmazonS3 client) throws Exception {
		System.out.println("upload start");

		File file = new File("src/resources/java/memo.txt");
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

		System.out.println("upload end");
	}

	// ﾀﾞｳﾝﾛｰﾄﾞ処理
	private static void download() throws Exception {
		System.out.println("download start");

		// 認証処理
		AmazonS3 client = auth();

		final GetObjectRequest getRequest = new GetObjectRequest(Const.S3_BUCKET_NAME, "[ダウンロードファイル名");

		S3Object object = client.getObject(getRequest);

		FileOutputStream fos = new FileOutputStream(new File("[出力先パス]"));
		IOUtils.copy(object.getObjectContent(), fos);

		fos.close();

		System.out.println("download end");
	}

}