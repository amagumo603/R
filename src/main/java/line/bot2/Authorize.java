
package line.bot2;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

public class Authorize{
static Credential authorize() throws Exception {
  System.out.println("authorize in");

  HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
  JsonFactory jsonFactory = new JacksonFactory();

  GoogleCredential credential = new GoogleCredential.Builder()
    .setTransport(httpTransport)
    .setJsonFactory(jsonFactory)
    .setServiceAccountId(Constant.ACCOUNT_P12_ID)
    .setServiceAccountPrivateKeyFromP12File(Constant.P12FILE)
    .setServiceAccountScopes(Constant.SCOPES)
    .build();

  boolean ret = credential.refreshToken();
  // debug dump
  System.out.println("refreshToken:" + ret);

  // debug dump
  if (credential != null) {
    System.out.println("AccessToken:" + credential.getAccessToken());
  }

  System.out.println("authorize out");

  return credential;
}
}