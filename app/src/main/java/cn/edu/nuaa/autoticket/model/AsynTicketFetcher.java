package cn.edu.nuaa.autoticket.model;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        ByteArrayOutputStream byteArrayOutputStream = null;
        InputStream           inputStream           = null;
        try {
            //"https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2018-04-30&leftTicketDTO.from_station=NJH&leftTicketDTO.to_station=SHH&purpose_codes=ADULT"
            URL url = new URL(makeUrl());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(10000);
            byteArrayOutputStream = new ByteArrayOutputStream();
            urlConnection.connect();
            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }
            inputStream = urlConnection.getInputStream();
            int    readCount = 0;
            byte[] content   = new byte[1024];
            while ((readCount = inputStream.read(content, 0, 1024)) > 0) {
                byteArrayOutputStream.write(content, 0, readCount);
            }
            return new String(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
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
            inputStream = context.getAssets().open("city_code_zh.properties");
//            resourceBundle = new PropertyResourceBundle(inputStream);
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
