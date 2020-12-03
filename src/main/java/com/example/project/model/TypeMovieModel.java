package com.example.project.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TypeMovieModel {

    private TypeMovie typeMovie;

    public TypeMovieModel(){
        typeMovie = new TypeMovie();
    }

    public TypeMovieModel(String jsonResponse) {
        Gson gson = new GsonBuilder().create();
        typeMovie = gson.fromJson(jsonResponse, TypeMovie.class);

    }

    public String toJSONString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this.typeMovie);
    }

    public TypeMovie getTypeMovie() {
        return typeMovie;
    }

    public class TypeMovie{
        private String typeName;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
    }
}
