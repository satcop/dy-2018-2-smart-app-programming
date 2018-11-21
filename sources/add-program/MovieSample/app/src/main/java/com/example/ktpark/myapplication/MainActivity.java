package com.example.ktpark.myapplication;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MovieAdapter movieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        movieAdapter = new MovieAdapter();

        // 어댑터 설정
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(movieAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MovieData movieData = movieAdapter.getItem(position);

                String msg = movieData.getMovieNameKR() + ", " + movieData.getReleaseDate();

                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

            }
        });

        movieAdapter.notifyDataSetChanged();

        // URL 설정.
        String url = "http://webtattoo.run.goorm.io/movies.jsp";

        url = "http://www.lottecinema.co.kr/LCWS/Movie/MovieData.aspx?nocashe=0.21550290810399564";

        //String v = "{\"MethodName\":\"GetMovies\",\"channelType\":\"HO\",\"osType\":\"Chrome\",\"osVersion\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36\",\"multiLanguageID\":\"KR\",\"division\":1,\"moviePlayYN\":\"Y\",\"orderType\":\"1\",\"blockSize\":100,\"pageNo\":1}";
        String v = "{\"MethodName\":\"GetMovies\",\"channelType\":\"HO\",\"osType\":\"Chrome\",\"osVersion\":\"Mozilla/5.\",\"multiLanguageID\":\"KR\",\"division\":1,\"moviePlayYN\":\"Y\",\"orderType\":\"1\",\"blockSize\":100,\"pageNo\":1}";

        ContentValues contentValues = new ContentValues();
        contentValues.put("paramList", v);

        // AsyncTask를 통해 HttpURLConnection 수행.
        NetworkTask networkTask = new NetworkTask(url, contentValues);
        networkTask.execute();
    }


    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            System.out.println(s);


            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(s).getAsJsonObject();

            Log.d("JSON", jsonObj.toString());

            JsonObject mainItems = jsonObj.getAsJsonObject("Movies");
            JsonArray jsonArray = mainItems.getAsJsonArray("Items");

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject x = (JsonObject) jsonArray.get(i);

                //Log.d("DATA", x.toString());

                String movieNameKR = x.get("MovieNameKR").getAsString();
                String posterURL = x.get("PosterURL").getAsString();

                String releaseDate = "";
                if (x.has("ReleaseDate")) {
                    try {
                        releaseDate = x.get("ReleaseDate") != null ? x.get("ReleaseDate").getAsString() : "";
                    } catch (Exception e) {
                        Log.d("D", e.getMessage());
                    }
                }

                Double bookingRate = x.get("BookingRate") != null ? x.get("BookingRate").getAsDouble() : 0;

                movieAdapter.addItem(movieNameKR, posterURL, releaseDate, bookingRate);

            }

            //listView.clearChoices();                 // 선택 해제
            movieAdapter.notifyDataSetChanged();


            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
            //tv_outPut.setText(s);
        }
    }


}

