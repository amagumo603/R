package line.bot2;

public class SpreadsheetTool {
//
//	public static Credential authorize() throws Exception {
//		  System.out.println("authorize in");
//
//		  HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//		  JsonFactory jsonFactory = new JacksonFactory();
//
//		  GoogleCredential credential = new GoogleCredential.Builder()
//		    .setTransport(httpTransport)
//		    .setJsonFactory(jsonFactory)
//		    .setServiceAccountId(Const.ACCOUNT_P12_ID)
//		    .setServiceAccountPrivateKeyFromP12File(Const.P12FILE)
//		    .setServiceAccountScopes(Const.SCOPES)
//		    .build();
//
//		  boolean ret = credential.refreshToken();
//		  // debug dump
//		  System.out.println("refreshToken:" + ret);
//
//		  // debug dump
//		  if (credential != null) {
//		    System.out.println("AccessToken:" + credential.getAccessToken());
//		  }
//
//		  System.out.println("authorize out");
//
//		  return credential;
//		}
//
///**
// * サービスを取得する
// * @return
// * @throws Exception
// */
//
//	public static SpreadsheetService getService() throws Exception {
//		  System.out.println("service in");
//
//		  SpreadsheetService service = new SpreadsheetService(Const.APPLICATION_NAME);
//		  service.setProtocolVersion(SpreadsheetService.Versions.V3);
//
//		  Credential credential = authorize();
//		  service.setOAuth2Credentials(credential);
//
//		  // debug dump
//		  System.out.println("Schema: " + service.getSchema().toString());
//		  System.out.println("Protocol: " + service.getProtocolVersion().getVersionString());
//		  System.out.println("ServiceVersion: " + service.getServiceVersion());
//
//		  System.out.println("service out");
//
//		  return service;
//		}
//
//	/**
//	* 利用可能なシートを取得する
// 	* @param service
// 	* @return
//	* @throws Exception
// 	*/
//	public static List<SpreadsheetEntry> findAllSpreadsheets(SpreadsheetService service) throws Exception {
//		  System.out.println("findAllSpreadsheets in");
//
//		  SpreadsheetFeed feed = service.getFeed(Const.SPREADSHEET_FEED_URL, SpreadsheetFeed.class);
//
//		  List<SpreadsheetEntry> spreadsheets = feed.getEntries();
//
//		  // debug dump
//		  for (SpreadsheetEntry spreadsheet : spreadsheets) {
//		    System.out.println("title: " + spreadsheet.getTitle().getPlainText());
//		  }
//
//		  System.out.println("findAllSpreadsheets out");
//		  return spreadsheets;
//		}
//
//	/**
//	 * スプレッドシート名を指定して検索
//	 * @param service
//	 * @param spreadsheetName
//	 * @return
//	 * @throws Exception
//	 */
//	public static SpreadsheetEntry findSpreadsheetByName(SpreadsheetService service, String spreadsheetName) throws Exception {
//		  System.out.println("findSpreadsheetByName in");
//		  SpreadsheetQuery sheetQuery = new SpreadsheetQuery(Const.SPREADSHEET_FEED_URL);
//		  sheetQuery.setTitleQuery(spreadsheetName);
//		  SpreadsheetFeed feed = service.query(sheetQuery, SpreadsheetFeed.class);
//		  SpreadsheetEntry ssEntry = null;
//		  if (feed.getEntries().size() > 0) {
//		    ssEntry = feed.getEntries().get(0);
//		  }
//		  System.out.println("findSpreadsheetByName out");
//		  return ssEntry;
//		}
//
//	/**
//	 * ワークシート名で検索
//	 * @param service
//	 * @param ssEntry
//	 * @param sheetName
//	 * @return
//	 * @throws Exception
//	 */
//	public static WorksheetEntry findWorksheetByName(SpreadsheetService service, SpreadsheetEntry ssEntry, String sheetName) throws Exception {
//		  System.out.println("findWorksheetByName in");
//		  WorksheetQuery worksheetQuery = new WorksheetQuery(ssEntry.getWorksheetFeedUrl());
//		  worksheetQuery.setTitleQuery(sheetName);
//		  WorksheetFeed feed = service.query(worksheetQuery, WorksheetFeed.class);
//		  WorksheetEntry wsEntry = null;
//		  if (feed.getEntries().size() > 0){
//		    wsEntry = feed.getEntries().get(0);
//		  }
//		  System.out.println("findWorksheetByName out");
//		  return wsEntry;
//		}
//
//	/**
//	 * ワークシートのタイトル行を追加.
//	 * 引数のqueryでタイトル行(1行目)を指定し、その行のセルへタイトル名を書き込み
//	 * @param service
//	 * @param wsEntry
//	 * @param header
//	 * @param query
//	 * @throws Exception
//	 */
//	public static void insertHeadRow(SpreadsheetService service, WorksheetEntry wsEntry, List<String> header, String query) throws Exception {
//		  System.out.println("insertHeadRow in");
//
//		  URL cellFeedUrl = new URI(wsEntry.getCellFeedUrl().toString() + query).toURL();
//		  CellFeed cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);
//
//		  for (int i=0; i<header.size(); i++) {
//		    cellFeed.insert(new CellEntry(1, i+1, header.get(i)));
//		  }
//
//		  System.out.println("insertHeadRow out");
//		}
//
//	/**
//	 * クエリーストリングでセルの範囲指定を行う
//	 * @param minrow
//	 * @param maxrow
//	 * @param mincol
//	 * @param maxcol
//	 * @return
//	 */
//	public static String makeQuery(int minrow, int maxrow, int mincol, int maxcol) {
//		  String base = "?min-row=MINROW&max-row=MAXROW&min-col=MINCOL&max-col=MAXCOL";
//		  return base.replaceAll("MINROW", String.valueOf(minrow))
//		             .replaceAll("MAXROW", String.valueOf(maxrow))
//		             .replaceAll("MINCOL", String.valueOf(mincol))
//		             .replaceAll("MAXCOL", String.valueOf(maxcol));
//		}
//
//	/**
//	 * 行を追加する
//	 * @param service
//	 * @param wsEntry
//	 * @param values
//	 * @throws Exception
//	 */
//	public  static void insertDataRow(SpreadsheetService service, WorksheetEntry wsEntry, Map<String, Object> values) throws Exception {
//		  System.out.println("insertDataRow in");
//
//		  ListEntry dataRow = new ListEntry();
//
//		  values.forEach((title,value)->{
//		    dataRow.getCustomElements().setValueLocal(title, value.toString());
//		  });
//
//		  URL listFeedUrl = wsEntry.getListFeedUrl();
//		  service.insert(listFeedUrl, dataRow);
//
//		  System.out.println("insertDataRow out");
//		}
//
//	/**
//	 * 登録されたものを取得する
//	 */
//	public static List<ListEntry> allGetCell(SpreadsheetService service, WorksheetEntry wsEntry) {
//		try {
//        URL listFeedUrl = wsEntry.getListFeedUrl();
//        ListFeed listFeed;
//
//		listFeed = service.getFeed(listFeedUrl, ListFeed.class);
//
//        List<ListEntry> entries = listFeed.getEntries();
//		return entries;
//		} catch (IOException | ServiceException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//			return null;
//		}
//
//	}
}