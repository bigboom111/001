package com.example.project.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FavorteMovieModel {

    private FavorteMovie favorteMovie;

    public FavorteMovieModel(){
        favorteMovie = new FavorteMovie();
    }

    public FavorteMovieModel(String jsonResponse) {
        Gson gson = new GsonBuilder().create();
        favorteMovie = gson.fromJson(jsonResponse, FavorteMovie.class);

    }

    public String toJSONString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this.favorteMovie);
    }

    public FavorteMovie getFavorteMovie() {
        return favorteMovie;
    }
    public class FavorteMovie{
        private String username;
        private String movieID;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMovieID() {
            return movieID;
        }

        public void setMovieID(String movieID) {
            this.movieID = movieID;
        }
    }
}
