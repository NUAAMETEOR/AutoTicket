package cn.edu.nuaa.autoticket.model;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author Meteor
 * @version V1.0
 * @ClassName: AsynTicketFetcher.java
 * @Description:
 * @Date 2018/4/14 20:39
 */
public class AsynTicketFetcher extends AsyncTask<QueryParameters, Integer, ArrayList<TicketInformation>> {
    private static final String QUERY_WEB_SITE = "https://kyfw.12306.cn/otn/leftTicket/query";
    private QueryParameters queryParameter;
    private Activity        context;

    public AsynTicketFetcher(Activity context) {
        this.context = context;
    }

    @Override
    protected ArrayList<TicketInformation> doInBackground(QueryParameters... queryParameters) {
        this.queryParameter = queryParameters[0];
        String result = getTrainInformation();
        Log.d(this.getClass().getName(), result);
        return null;
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

    private String makeUrl() {
        Properties properties = getCityCode();
//        ResourceBundle resourceBundle = getCityCode();
//        String         startCityCode  = resourceBundle.getString(queryParameter.getStartCity());
//        String         destCityCode   = resourceBundle.getString(queryParameter.getDestCity());
        String startCityCode = properties.getProperty(queryParameter.getStartCity());
        String destCityCode  = properties.getProperty(queryParameter.getDestCity());
        String url = Uri.parse(QUERY_WEB_SITE).buildUpon().appendQueryParameter("leftTicketDTO.train_date", "2018-04-30"/*queryParameter.getDeliverDay()*/)
                .appendQueryParameter("leftTicketDTO.from_station", startCityCode).appendQueryParameter("leftTicketDTO.to_station", destCityCode)
                .appendQueryParameter("purpose_codes", "ADULT").build().toString();
        //String url = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=$s&leftTicketDTO.from_station=%s&leftTicketDTO.to_station=%s&purpose_codes=ADULT";
//        String.format(url, queryParameters.getDeliverDay(), startCityCode, destCityCode);
        return url;
    }

    private String getTrainInformation() {
        HttpURLConnection     urlConnection         = null;
        InputStream           inputStream           = null;
        BufferedReader        reader                = null;
        StringBuilder         stringBuilder         = null;
        try {
            //"https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2018-04-30&leftTicketDTO.from_station=NJH&leftTicketDTO.to_station=SHH&purpose_codes=ADULT"
            URL url = new URL(makeUrl());
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

    private Properties getCityCode() {
        Properties  properties  = new Properties();
        InputStream inputStream = null;
//        ResourceBundle resourceBundle = null;
        try {
            inputStream = context.getAssets().open("city_code.properties");
//            resourceBundle = new PropertyResourceBundle(inputStream);
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private void parseTrainInformation(String trainInfo) {
        Gson gson=new Gson();
        gson.fromJson(trainInfo, TicketHolder.class);
        /*var trainObject = JSON.parse(trainStr);
        if (!trainObject.status) {
            console.log("train information invalid");
            return;
        }
        var data = trainObject.data;
        var result = data.result;
        var flag = data.flag;
        var map = data.map;
        var trainnum = result.length;
        var table = document.getElementById("tb");
        console.log("receive " + trainnum + " train information");
        for (var i = 1; i <= trainnum; i++) {
            var tickect = {};
            var temp = result[i].split("|");

            tickect.trainId = temp[3];// Train ID
            tickect.startTime = temp[8];// Start Time
            tickect.endTime = temp[9];// End Time
            tickect.totalTime = temp[10];// Total Time
            tickect.date = temp[13];
            tickect.ruanwo = temp[23];// 软卧
            tickect.ruanzuo = temp[24]; // 软座
            tickect.wuzuo = temp[26];// 无座
            tickect.yingwo = temp[28]; // 硬卧
            tickect.yingzuo = temp[29]; // 硬座
            tickect.scSeat = temp[30];// 二等座
            tickect.fcSeat = temp[31];// 一等座
            tickect.bcSeat = temp[32];// 商务座 / 特等座
            tickect.dongwo = temp[33];// 动卧

            for(var prop in tickect){
                if(tickect[prop].length==0) {
                    tickect[prop] = "--";
                }
            }

            console.log(tickect);
            var row=table.insertRow(i);
            for(var j=0;j<14;j++) {
                row.insertCell(j);
            }
            row.cells[0].innerHTML=tickect.trainId;
            row.cells[1].innerHTML=tickect.startTime;
            row.cells[2].innerHTML=tickect.endTime;
            row.cells[3].innerHTML=tickect.totalTime;
            row.cells[4].innerHTML=tickect.date;
            row.cells[5].innerHTML=tickect.ruanwo;
            row.cells[6].innerHTML=tickect.ruanzuo;
            row.cells[7].innerHTML=tickect.wuzuo;
            row.cells[8].innerHTML=tickect.yingwo;
            row.cells[9].innerHTML=tickect.yingzuo;
            row.cells[10].innerHTML=tickect.scSeat;
            row.cells[11].innerHTML=tickect.fcSeat;
            row.cells[12].innerHTML=tickect.bcSeat;
            row.cells[13].innerHTML=tickect.dongwo;
        }
    }*/
    }
}
