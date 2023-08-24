import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MiniTest_2 {
    public static void main(String[] args) {
//      Deklarasi Informasi API
        String API_KEY = "6b9b8303e02bd6d4b6ec0f20deef6fc6";
        String city = "Jakarta";
        String Url = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=" + API_KEY+"&units=metric";

        try {
            //Membangun koneksi dengan API
            URL obj = new URL(Url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            int responseCode = con.getResponseCode();
            //Menerima data JSON
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                //Mengolah data JSON menjadi Forecast
                forecast(response.toString());
            }else{
            System.out.println("GET request did not work.");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void forecast(String response){
        //Format tanggal output dan untuk filter tanggal
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy");
        //Set untuk filter hari
        Set<String> processedDates = new HashSet<>();
        //Memulai dari hari + 1
        processedDates.add(sdfDate.format(new Date()));

        JSONObject data = new JSONObject(response);
        JSONArray weathers = new JSONArray(data.getJSONArray("list"));

        for(int i = 0 ; i< weathers.length();i++) {
            JSONObject weatherItem = weathers.getJSONObject(i);
            //Get Time
            long dt = weatherItem.getLong("dt");
            String dateStr = sdfDate.format(new Date(dt * 1000L));

            //Pengecekan Filter hari
            if (!processedDates.contains(dateStr)) {
                //Get Temph
                JSONObject main = weatherItem.getJSONObject("main");
                double temp = main.getDouble("temp");

                //Convert Unix
                String formattedDate = sdf.format(new Date(dt * 1000L));
                System.out.println(formattedDate + ": " + temp + " °C");

                processedDates.add(dateStr);
            }
        }
    }
}
