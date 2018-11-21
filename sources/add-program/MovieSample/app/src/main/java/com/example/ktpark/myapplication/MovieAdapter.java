package com.example.ktpark.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends BaseAdapter {

    List<MovieData> items = new ArrayList<>();

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public MovieData getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.movie_listview, parent, false);
        }

        TextView movieNameKR = (TextView) convertView.findViewById(R.id.movieNameKR);
        //TextView posterURL = (TextView) convertView.findViewById(R.id.posterURL);
        TextView releaseDate = (TextView) convertView.findViewById(R.id.releaseDate);
        TextView bookingRate = (TextView) convertView.findViewById(R.id.bookingRate);

        MovieData movieData = getItem(position);

        movieNameKR.setText(movieData.getMovieNameKR());
        //posterURL.setText(movieData.getPosterURL());
        releaseDate.setText(movieData.getReleaseDate());
        bookingRate.setText(movieData.getBookingRate().toString());

        new DownloadImageTask((ImageView) convertView.findViewById(R.id.posterURL))
                .execute(movieData.getPosterURL());


        return convertView;
    }

    public void addItem(String movieNameKR, String posterURL, String releaseDate, Double bookingRate) {

        MovieData movieData = new MovieData();
        movieData.setMovieNameKR(movieNameKR);
        movieData.setPosterURL(posterURL);
        movieData.setReleaseDate(releaseDate);
        movieData.setBookingRate(bookingRate);

        items.add(movieData);
    }



    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}
