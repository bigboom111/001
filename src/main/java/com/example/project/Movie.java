package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.manager.WSManager;
import com.example.project.model.FavorteMovieModel;
import com.example.project.model.MovieModel;
import com.example.project.model.TypeMovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Movie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        this.getAllmovie();
    }
    public void getAllmovie(){
        final WSManager manager = WSManager.getWsManager(this);

        TypeMovieModel typemovieModel = new TypeMovieModel();
        manager.doAllTypeMovie(typemovieModel, new WSManager.WSManagerListener() {
            @Override
            public void onComplete(Object response) {

                try {
                    JSONArray json =   new JSONArray(response.toString());
                    Toast.makeText(Movie.this, json.length()+"", Toast.LENGTH_SHORT).show();
                    String[] spin = new String[json.length()];
                    if(json != null){
//                        spin[0] ="ประเภทหนัง";
                        for (int i = 0; i<json.length(); i++) {
                            JSONObject jsons = json.optJSONObject(i);
                            spin[i] = ""+jsons.get("typeName");
                        }

                    }
                    Spinner spinner = findViewById(R.id.spinner2);
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Movie.this, android.R.layout.simple_spinner_item, spin);
                    spinner.setAdapter(dataAdapter);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                            MovieModel movieModel = new MovieModel();
                            Spinner spinnerMenu = (Spinner) findViewById(R.id.spinner2);

                            movieModel.getMovie().setTypeName(spinnerMenu.getSelectedItem().toString());
                            movieModel.getMovie().setMovieID("");
                            movieModel.getMovie().setMovieTitle("");
                            movieModel.getMovie().setMovieRatings("");
                            movieModel.getMovie().setMovieDetail("");
                            manager.doAllMovie(movieModel, new WSManager.WSManagerListener() {
                                @Override
                                public void onComplete(Object response) {

                                    try {
                                        JSONArray json =   new JSONArray(response.toString());
                                        Toast.makeText(Movie.this, json.length()+"", Toast.LENGTH_SHORT).show();
                                        LinearLayout l = findViewById(R.id.l);
                                        l.removeAllViews();
                                        if(json != null){

                                            for (int i = 0; i<json.length(); i++) {
                                                JSONObject jsons = json.optJSONObject(i);

                                                GridLayout gr = new GridLayout(Movie.this);
                                                View v = getLayoutInflater().inflate(R.layout.movie_laout, null);
                                                TextView textView = v.findViewById(R.id.textView12);
                                                textView.setText(""+jsons.getString("movieTitle"));
                                                ImageButton im = v.findViewById(R.id.imageButton);

                                                gr.addView(v);
                                                l.addView(gr);

                                            }

                                            for (int i = 0; i<json.length(); i++) {

                                                final JSONObject jsons = json.optJSONObject(i);
                                                GridLayout g = (GridLayout) l.getChildAt(i);
                                                LinearLayout li = (LinearLayout) g.getChildAt(0);
                                                final TableRow tr = (TableRow) li.getChildAt(0);
                                                ImageView imm = (ImageView) tr.getChildAt(1);
                                                imm.setOnClickListener(new View.OnClickListener() {

                                                    @Override
                                                    public void onClick(View v) {
                                                        try {
                                                            Toast.makeText(Movie.this, ""+jsons.getString("movieID"), Toast.LENGTH_LONG).show();
                                                            FavorteMovieModel favorteMovie = new FavorteMovieModel();
                                                            favorteMovie.getFavorteMovie().setMovieID(jsons.getString("movieID"));
                                                            Intent intent =getIntent();
                                                            favorteMovie.getFavorteMovie().setUsername(intent.getStringExtra("id"));
                                                            manager.doAddMyMovie(favorteMovie, new WSManager.WSManagerListener() {
                                                                @Override
                                                                public void onComplete(Object response) {
                                                                    String result = response.toString();
                                                                    if("1".equals(result)){
                                                                        Toast.makeText(Movie.this, "บันทึกสำเร็จ", Toast.LENGTH_SHORT).show();
                                                                    }else{
                                                                        Toast.makeText(Movie.this, "คุณมีหนังเรื่องนี้เเล้ว", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }

                                                                @Override
                                                                public void onError(String err) {
                                                                    Toast.makeText(Movie.this, err, Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                });

                                            }
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(String err) {
                                    Toast.makeText(Movie.this, err, Toast.LENGTH_SHORT).show();
                                }
                            });


                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            // TODO Auto-generated method stub
                            //
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String err) {
                Toast.makeText(Movie.this, err, Toast.LENGTH_SHORT).show();
            }
        });






    }
    public void onClickMyMovie(View view){
        Intent intent = new Intent(this,ListMyMovie.class);
        Intent intents =getIntent();
        intent.putExtra("id",intents.getStringExtra("id"));
        startActivity(intent);

    }
    public void onClickLogout(View view){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);

    }


}
