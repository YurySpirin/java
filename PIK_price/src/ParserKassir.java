import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserKassir implements IParser {



    private String sector;

    private Document kassirPage;

    public Document getKassirPage() {
        kassirPage = null;
        try {
            kassirPage = Jsoup.connect("https://msk.kassir.ru/frame/event/128741?key=47fff075-4762-d3b1-95e6-95c7fe20d2b8").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return kassirPage;
    }

    public String getSector() {
        sector = String.valueOf(getKassirPage().select("span").text());
        Pattern str = Pattern.compile("ФАН-ЗОНА .*? ", Pattern.DOTALL);
        Matcher mat = str.matcher(sector);
        while (mat.find()) {
            sector = String.valueOf(mat.group());
            break;
        }
        return sector;
    }
}