package line.bot2;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Constant {
// アプリケーション名 (任意)
static final String APPLICATION_NAME = "spreadsheet-application";

// アカウント
static final String ACCOUNT_P12_ID = "hamsyatyo@amagumo.iam.gserviceaccount.com";
static final File P12FILE = new File("src/main/resources/Amagumo-13c936f72a8a.p12");

// 認証スコープ
static final List<String> SCOPES = Arrays.asList(
  "https://docs.google.com/feeds",
  "https://spreadsheets.google.com/feeds");

// Spreadsheet API URL
static final String SPREADSHEET_URL = "https://spreadsheets.google.com/feeds/spreadsheets/private/full";

static final URL SPREADSHEET_FEED_URL;

static {
  try {
    SPREADSHEET_FEED_URL = new URL(SPREADSHEET_URL);
  } catch (MalformedURLException e) {
    throw new RuntimeException(e);
  }
}

}