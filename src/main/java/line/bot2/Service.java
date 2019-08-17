package line.bot2;

import java.util.ArrayList;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;


public class Service {

	/**
	 * googleスプレッドシートに接続する窓口
	 *
	 */
	List<String> service() {
		try {
			SpreadsheetService SPsheet= getService();

			  List<SpreadsheetEntry> tes = findAllSpreadsheets(SPsheet);
			  List<String> returnList = new ArrayList<String>();

			  for (SpreadsheetEntry sheetNM : tes) {
				  returnList.add(sheetNM.getTitle().getPlainText());
				  }

			  return returnList;
		} catch (Exception e) {
			System.out.println("接続に失敗しました");
			e.printStackTrace();
			return null;
		}


	}

	SpreadsheetService getService() throws Exception {
		  System.out.println("service in");

		  SpreadsheetService service = new SpreadsheetService(Constant.APPLICATION_NAME);
		  service.setProtocolVersion(SpreadsheetService.Versions.V3);

		  Credential credential = Authorize.authorize();
		  service.setOAuth2Credentials(credential);

		  // debug dump
		  System.out.println("Schema: " + service.getSchema().toString());
		  System.out.println("Protocol: " + service.getProtocolVersion().getVersionString());
		  System.out.println("ServiceVersion: " + service.getServiceVersion());

		  System.out.println("service out");

		  return service;
		}

	private static List<SpreadsheetEntry> findAllSpreadsheets(SpreadsheetService service) throws Exception {
		  System.out.println("findAllSpreadsheets in");

		  SpreadsheetFeed feed = service.getFeed(Constant.SPREADSHEET_FEED_URL, SpreadsheetFeed.class);

		  List<SpreadsheetEntry> spreadsheets = feed.getEntries();

		  // debug dump
		  for (SpreadsheetEntry spreadsheet : spreadsheets) {
		    System.out.println("title: " + spreadsheet.getTitle().getPlainText());
		  }

		  System.out.println("findAllSpreadsheets out");
		  return spreadsheets;
		}
}