import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserPik implements IParser {
    private int price;
    private int floor;
    private double area;
    private JSONObject jsonObject;
    private String jsonString;

    public JSONObject getJsonObject() {
        Document pik1 = null;

        try {
            pik1 = Jsoup.connect("https://www.pik.ru/odin/flats/391046").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pik1 != null;
        String body = String.valueOf(pik1.body());
        Pattern str = Pattern.compile("window.REDUX_INITIAL_STATE = (.*?}});", Pattern.DOTALL);
        Matcher mat = str.matcher(body);
        while (mat.find()) {
            jsonString = String.valueOf(mat.group(1));
            break;
        }
        jsonObject = new JSONObject(jsonString);
        return jsonObject;
    }

    public int getPrice() {
        price = (int) getJsonObject().getJSONObject("flats").getJSONObject("currentFlat").get("price");
        return price;
    }

    public int getFloor() {
        floor = (int) getJsonObject().getJSONObject("flats").getJSONObject("currentFlat").get("floor");
        return floor;
    }

    public double getArea() {
        area = (double) getJsonObject().getJSONObject("flats").getJSONObject("currentFlat").get("area");
        return area;
    }
}