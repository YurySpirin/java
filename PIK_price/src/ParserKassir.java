import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ParserKassir implements IParser {

    private String price;

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
        sector = String.valueOf(getKassirPage().select("span.name").text());
        String sectors[] = sector.split(" ");
        sector = sectors[sectors.length-1];
        return sector;
    }


    public String getPrice() {
        price = String.valueOf(getKassirPage().select("span.price*").text());
        String prices [] = price.split(" ");
        price = prices[prices.length-1];
        return price;
    }


}