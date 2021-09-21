import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.FormElement;

public class test {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://mahabhulekh.maharashtra.gov.in/Pune/Pune.html").get();

        // Find the form
        FormElement form = (FormElement) doc.getElementById("frmrd");
        if (form == null) {
            // Form not found...
        } else {
            doc = form.submit().execute().parse();
            System.out.println("Document base uri: " + doc.baseUri());
            System.out.println("Content: " + doc.outerHtml());
        }
    }
}
