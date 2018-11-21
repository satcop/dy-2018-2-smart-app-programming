package com.example.ktpark.myapplication;


public class MovieData {

    String movieNameKR;
    String posterURL;
    String releaseDate;
    Double bookingRate;

    public String getMovieNameKR() {
        return movieNameKR;
    }

    public void setMovieNameKR(String movieNameKR) {
        this.movieNameKR = movieNameKR;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getBookingRate() {
        return bookingRate;
    }

    public void setBookingRate(Double bookingRate) {
        this.bookingRate = bookingRate;
    }
}
