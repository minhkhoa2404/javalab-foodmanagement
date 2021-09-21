import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.parser.Tag;

public class HTMLParser {

    public static void main(String[] args) throws IOException {
        Connection.Response resp = Jsoup.connect(
                "https://www.oxfordreference.com/view/10.1093/acref/9780192803511.001.0001/acref-9780192803511?btog=chap&hide=true&jumpTo=fish&page=25&pageSize=20&skipEditions=true&sort=titlesort&source=%2F10.1093%2Facref%2F9780192803511.001.0001%2Facref-9780192803511")
                .timeout(30000).method(Connection.Method.GET).execute();
        // FormElement f3 = doc.select("[name=jumpToSubmit]").forms().get(-1);
        // System.out.println(f3);
        Document respDoc = resp.parse();
        Element form = respDoc.select("form#jumpToForm").first();
        FormElement f = (FormElement) form;
        Element foodSearch = form.select("[value=fish]").first();
        foodSearch.val("pork");
        System.out.println(foodSearch);

        Document searchRes = f.submit().cookies(resp.cookies()).post();
        Element firstRes = searchRes.selectFirst("h2.itemTitle > a");
        System.out.println(firstRes);
        Tag finalRes = Tag.valueOf(firstRes.text());
        System.out.println(finalRes.toString());
        // Element form2 = respDoc.select("h2.itemTitle > a").first();
        // System.out.println(form2);
        // for (Element entityNumber : searchResults
        // .select("table[id$=enitityTable] > tbody > tr >
        // td:first-of-type:not(td[colspan=5])")) {
        // System.out.println(entityNumber.text());
        // }
    }

}
