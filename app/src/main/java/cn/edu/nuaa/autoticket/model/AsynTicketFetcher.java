package cn.edu.nuaa.autoticket.model;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import cn.edu.nuaa.autoticket.application.AutoTaskApplication;

/**
 * @author Meteor
 * @version V1.0
 * @ClassName: AsynTicketFetcher.java
 * @Description:
 * @Date 2018/4/14 20:39
 */
public class AsynTicketFetcher extends AsyncTask<QueryParameters, Integer, ArrayList<TicketInformation>> {
    private static final String     QUERY_WEB_SITE = "https://kyfw.12306.cn/otn/leftTicket/query";
    public static        Properties properties     = getCityCode();
    private QueryParameters queryParameter;
    private Activity        context;

    public AsynTicketFetcher(Activity context) {
        this.context = context;
    }

    private static Properties getCityCode() {
        Properties  properties  = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = AutoTaskApplication.getAppContext().getAssets().open("city_code.properties");
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @Override
    protected ArrayList<TicketInformation> doInBackground(QueryParameters... queryParameters) {
        this.queryParameter = queryParameters[0];
        String result = getTrainInformation();
        Log.d(this.getClass().getName(), result);
        return parseTrainInformation(result);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<TicketInformation> ticketInformations) {
        super.onPostExecute(ticketInformations);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(ArrayList<TicketInformation> ticketInformations) {
        super.onCancelled(ticketInformations);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    private String makeUrlString() {
//        Properties properties    = getCityCode();
        String startCityCode = properties.getProperty(queryParameter.getStartCity());
        String destCityCode  = properties.getProperty(queryParameter.getDestCity());
        String url = Uri.parse(QUERY_WEB_SITE).buildUpon().appendQueryParameter("leftTicketDTO.train_date", "2018-04-30"/*queryParameter.getDeliverDay()*/)
                .appendQueryParameter("leftTicketDTO.from_station", startCityCode).appendQueryParameter("leftTicketDTO.to_station", destCityCode)
                .appendQueryParameter("purpose_codes", "ADULT").build().toString();
        return url;
    }

    private String getTrainInformation() {
        HttpURLConnection urlConnection = null;
        InputStream       inputStream   = null;
        BufferedReader    reader        = null;
        StringBuilder     stringBuilder = null;
        try {
            //"https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2018-04-30&leftTicketDTO.from_station=NJH&leftTicketDTO.to_station=SHH&purpose_codes=ADULT"
            URL url = new URL(makeUrlString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(10000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }
            inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            stringBuilder = new StringBuilder();
            String content;
            while ((content = reader.readLine()) != null) {
                stringBuilder.append(content);
                stringBuilder.append('\n');
            }
            content = stringBuilder.toString();
            content = content.substring(0, content.length() - 1);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private ArrayList<TicketInformation> parseTrainInformation(String trainInfo) {
        JsonParser                   jsonParser    = new JsonParser();
        JsonObject                   dataObject    = jsonParser.parse(trainInfo).getAsJsonObject().getAsJsonObject("data");
        JsonObject                   addressObject = dataObject.getAsJsonObject("map");
        JsonArray                    ticketsArray  = dataObject.getAsJsonArray("result");
        ArrayList<TicketInformation> list          = new ArrayList<>();
        for (int i = 0; i < ticketsArray.size(); i++) {
            String ticket = ticketsArray.get(i).getAsString();
            list.add(resolveTicket(ticket));
        }
        return list;
    }

    private TicketInformation resolveTicket(String ticketInfo) {
        TicketInformation ticket = new TicketInformation();
        String[]          split  = ticketInfo.split("\\|");
        ticket.setTrainId(split[3]); // Train ID
        ticket.setStartTime(split[8]); // Start Time
        ticket.setEndTime(split[9]); // End Time
        ticket.setTotalTime(split[10]);// Total Time
        ticket.setStartDate(split[13]);
        ticket.setSeat_advance_soft_(split[20]);//高级软卧
        ticket.setSeat_other(split[22]);//其他
        ticket.setSeatSoft_(split[23]); // 软卧
        ticket.setSeatSoft(split[24]);  // 软座
        ticket.setSeat_null(split[26]);// 无座
        ticket.setSeatHard_(split[28]);  // 硬卧
        ticket.setSeatHard(split[29]);  // 硬座
        ticket.setSeat_normal(split[30]);// 二等座
        ticket.setSeat_advanced(split[31]); // 一等座
        ticket.setSeat_commercial(split[32]); // 商务座 / 特等座
        ticket.setSeat_high_(split[33]); // 动卧
        ticket.setEmptyInformation();
        return ticket;
    }
}
