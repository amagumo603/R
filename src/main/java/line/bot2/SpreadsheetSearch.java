package line.bot2;

public class SpreadsheetSearch {
    public void  spreadSearch()  {
        System.out.println("spreadSearchMethodIN");
        /*
        // このアプリケーションの名称。任意の名前を設定
        String applicationName = "spreadsheet-application";
        // Google AppsもしくはGoogleアカウントのメールアドレスとパスワードを設定
        String username = "hard.rain.frog@gmail.com";
        String password = "79827913Tz";


        // Spreadsheetsサービスへの認証を行う
        SpreadsheetService service = new SpreadsheetService(applicationName);
        service.setUserCredentials(username, password);

        System.out.println("グーぐるスプレッドシートにログイン成功");

        // 検索対象のスプレッドシートを取得
        FeedURLFactory urlFactory = FeedURLFactory.getDefault();
        SpreadsheetQuery spreadsheetQuery = new SpreadsheetQuery(urlFactory
                .getSpreadsheetsFeedUrl());
        spreadsheetQuery.setTitleQuery("買物表"); // 検索対象のスプレッドシート名を指定している
        SpreadsheetFeed spreadsheetFeed = service.query(spreadsheetQuery,
                SpreadsheetFeed.class);
        SpreadsheetEntry spreadsheetEntry = spreadsheetFeed.getEntries().get(0);
        System.out.println("名前：" + spreadsheetEntry.getTitle().getPlainText());

        // 検索対象のワークシートを取得
        WorksheetEntry worksheetEntry = spreadsheetEntry.getDefaultWorksheet();

        System.out.println("ワークシート取得成功");
        // ワークシート内を検索
        ListQuery listQuery = new ListQuery(worksheetEntry.getListFeedUrl());
        listQuery.setSpreadsheetQuery("名称 = りんご");
        ListFeed listFeed = service.query(listQuery, ListFeed.class);
        ListEntry listEntry = listFeed.getEntries().get(0);
        CustomElementCollection elements = listEntry.getCustomElements();
        System.out.println("名称：" + elements.getValue("名称"));
        System.out.println("数量：" + elements.getValue("数量"));
        System.out.println("価格：" + elements.getValue("価格"));

        */
    }
}
