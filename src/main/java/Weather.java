import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    //b2f014bd0168b8a2def4b8f96880583e
    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("http://openweathermap.org/data/2.5/weather?q="+message+"&units=metric&appid=b6907d289e10d714a6e88b30761fae22");
        Scanner in = new Scanner((InputStream) url.getContent());

        String result="";
        while (in.hasNext()){
            result+=in.nextLine();
        }
        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHuminity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("weather");
        for(int i=0;i<getArray.length();i++){
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setMain((String) obj.get("main"));
        }
return "Город: " + model.getName() + "\n"
        + "Температура: " + model.getTemp() + "\n"
        + "Влажность: " + model.getHuminity() + "%" +"\n"
        + "http://openweathermap.org/img/w/" + model.getIcon() + ".png";
    }
}
