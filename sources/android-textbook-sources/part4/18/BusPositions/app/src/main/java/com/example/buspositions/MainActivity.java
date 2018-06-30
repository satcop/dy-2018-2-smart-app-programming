package com.example.buspositions;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.data);

        String serviceUrl = "http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid";
        String serviceKey = ".....공공DB API 키.....";
        String busRouteId = "100100063";
        String strUrl = serviceUrl+"?ServiceKey="+serviceKey+"&busRouteId="+busRouteId;

        new DownloadWebpageTask().execute(strUrl);
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return (String)downloadUrl((String)urls[0]);
            } catch (IOException e) {
                return "다운로드 실패";
            }
        }

        protected void onPostExecute(String result) {

            String headerCd = "";
            String gpsX = "";
            String gpsY = "";
            String plainNo = "";

            boolean bSet_headerCd = false;
            boolean bSet_gpsX = false;
            boolean bSet_gpsY = false;
            boolean bSet_plainNo = false;

            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if(eventType == XmlPullParser.START_DOCUMENT) {
                        ;
                    } else if(eventType == XmlPullParser.START_TAG) {
                        String tag_name = xpp.getName();
                        if (tag_name.equals("headerCd"))
                            bSet_headerCd = true;
                        if (tag_name.equals("gpsX"))
                            bSet_gpsX = true;
                        if (tag_name.equals("gpsY"))
                            bSet_gpsY = true;
                        if (tag_name.equals("plainNo"))
                            bSet_plainNo = true;
                    } else if(eventType == XmlPullParser.TEXT) {
                        if (bSet_headerCd) {
                            headerCd = xpp.getText();
                            tv.append("headerCd: " + headerCd + "\n");
                            bSet_headerCd = false;
                        }

                        if (headerCd.equals("0")) {
                            if (bSet_gpsX) {
                                gpsX = xpp.getText();
                                tv.append("gpsX: " + gpsX + "\n");
                                bSet_gpsX = false;
                            }
                            if (bSet_gpsY) {
                                gpsY = xpp.getText();
                                tv.append("gpsY: " + gpsY + "\n");
                                bSet_gpsY = false;
                            }
                            if (bSet_plainNo) {
                                plainNo = xpp.getText();
                                tv.append("plainNo; " + plainNo + "\n");
                                bSet_plainNo = false;
                            }
                        }
                    } else if(eventType == XmlPullParser.END_TAG) {
                        ;
                    }
                    eventType = xpp.next();
                }
            } catch (Exception e) {
                tv.setText(e.getMessage());
            }

        }

        private String downloadUrl(String myurl) throws IOException {

            HttpURLConnection conn = null;
            try {
                URL url = new URL(myurl);
                conn = (HttpURLConnection) url.openConnection();
                BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
                BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8"));
                String line = null;
                String page = "";
                while((line = bufreader.readLine()) != null) {
                    page += line;
                }

                return page;
            } finally {
                conn.disconnect();
            }
        }
    }
}
