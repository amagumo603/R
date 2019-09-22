package line.bot2;

public class SpreadsheetSearch {



    public static void searchUnit(String arg) {

    	//SpreadsheetTool aut = new SpreadsheetTool();
    	/*
    	try {
    	    System.out.println("main start");

    	    SpreadsheetService service = aut.getService();

    	    String ssName = "買物表";
    	    String wsName = "必要なもの";

    	    SpreadsheetEntry ssEntry = aut.findSpreadsheetByName(service, ssName);

    	    // 作業Sheetを検索する
    	    WorksheetEntry wsEntry = aut.findWorksheetByName(service, ssEntry, wsName);

    	    // 日付情報の取得
    	    Date date = new Date();
    	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    	    // insert
    	    Map<String, Object> insertValues1 = new HashMap<>();
    	    insertValues1.put("品目", arg);
    	    insertValues1.put("登録日時", sdf.format(date));
    	    insertValues1.put("削除日時", "未実装"); //TODO いずれ実装する

    	 // データを書き込む
    	    aut.insertDataRow(service, wsEntry, insertValues1);

    	    List<ListEntry> retValue = aut.allGetCell(service,wsEntry);
    	    for(ListEntry ent : retValue){


    	    System.out.println(ent.getCustomElements().getValue("品目"));

    	    }

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
*/
    }
}
