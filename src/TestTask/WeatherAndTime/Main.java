package TestTask.WeatherAndTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static String getWeatherData(String location) throws IOException, ParseException{

        String API_KEY = "5bd5bf72accd8f96c8b9e34075ba2055";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + location  +"&appid=" + API_KEY;

        URL url = new URL(urlString);

        URLConnection connection = url.openConnection();

        InputStream inputStream = connection.getInputStream();

        JSONParser jsonParser = new JSONParser();

        JSONObject uglyJSONString = (JSONObject)jsonParser.parse(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(uglyJSONString.toString());
        String prettyJsonString = gson.toJson(je);

        return prettyJsonString;
    }

    public static void main(String[] args) throws IOException, ParseException {
        Scanner in = new Scanner(System.in);

        while (true){
            System.out.println("Press [1] to get weather");
            System.out.println("Press [0] to stop");
            String operation = in.next();
            if (operation.equals("1")){
                System.out.println("Insert name of city (start with upper case): ");// start with upper case
                String location = in.next();
                System.out.println(getWeatherData(location));
            }else if (operation.equals("0")){
                break;
            }else {
                System.out.println("Unknown operation!");
            }
        }



    }
}
