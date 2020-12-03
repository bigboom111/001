package com.example.project.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MovieModel {
    private Movie movie;

    public MovieModel(){
        movie = new Movie();
    }

    public MovieModel(String jsonResponse) {
        Gson gson = new GsonBuilder().create();
        movie = gson.fromJson(jsonResponse, Movie.class);

    }

    public String toJSONString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this.movie);
    }

    public Movie getMovie() {
        return movie;
    }
    public class Movie{
        private String movieID;
        private String movieTitle;
        private String movieRatings;
        private String movieDetail;
        private String TypeName;

        public String getMovieID() {
            return movieID;
        }

        public void setMovieID(String movieID) {
            this.movieID = movieID;
        }

        public String getMovieTitle() {
            return movieTitle;
        }
        public void setMovieTitle(String movieTitle) {
            this.movieTitle = movieTitle;
        }

        public String getMovieRatings() {
            return movieRatings;
        }

        public void setMovieRatings(String movieRatings) {
            this.movieRatings = movieRatings;
        }

        public String getMovieDetail() {
            return movieDetail;
        }

        public void setMovieDetail(String movieDetail) {
            this.movieDetail = movieDetail;
        }

        public String getTypeName() {
            return TypeName;
        }

        public void setTypeName(String typeName) {
            TypeName = typeName;
        }
    }

}
