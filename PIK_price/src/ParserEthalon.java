import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ParserEthalon implements IParser {

  private String price;
  private String floor;
  private String area;
  private Document ethalonPage;

  public Document getEthalonPage() {
      ethalonPage = null;
      try {
          ethalonPage = Jsoup.connect("https://www.izumrudnie-holmi.ru/choose/flat-17-2-457").get();
      } catch (IOException e) {
          e.printStackTrace();
      }
      return ethalonPage;
    }

    public String getPrice() {
        price = String.valueOf(getEthalonPage().select("span.Flat_price").text());
        return price;
    }

    public String getFloor() {
        floor = String.valueOf(getEthalonPage().select("div.Flat_generalCell:nth-child(2)").text());
        return floor;
    }

    public String getArea() {
        area = String.valueOf(getEthalonPage().select("div.Flat_generalCell:first-child").text());
        return area;
    }
}