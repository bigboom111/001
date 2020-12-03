package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.manager.WSManager;
import com.example.project.model.FavorteMovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListMyMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_my_movie);
        this.listMyMovie();
    }
    public void listMyMovie(){
        final WSManager manager = WSManager.getWsManager(this);
        final FavorteMovieModel favorteMovieModel = new FavorteMovieModel();
        Intent intent = getIntent();
        favorteMovieModel.getFavorteMovie().setUsername(intent.getStringExtra("id"));

        manager.doAllListMyMovie(favorteMovieModel, new WSManager.WSManagerListener() {
            @Override
            public void onComplete(Object response) {
                Log.d("data7777777 :  ", response.toString());
                Toast.makeText(ListMyMovie.this, response.toString(), Toast.LENGTH_SHORT).show();
                JSONArray json = null;
                try {
                    json = new JSONArray(response.toString());

                Toast.makeText(ListMyMovie.this, json.length()+"", Toast.LENGTH_SHORT).show();
                if(json != null) {
                    LinearLayout l = findViewById(R.id.l);
                    l.removeAllViews();
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsons = json.optJSONObject(i);
                        JSONObject jsonss = jsons.getJSONObject("pk");
                        JSONObject jsonsss = jsonss.getJSONObject("mo");
                        GridLayout gr = new GridLayout(ListMyMovie.this);
                        View v = getLayoutInflater().inflate(R.layout.listmy_movie, null);
                        TextView textView = v.findViewById(R.id.textView16);
                        textView.setText(""+jsonsss.get("movieTitle"));
                        ImageButton im = v.findViewById(R.id.imageButton2);

                        gr.addView(v);
                        l.addView(gr);
                    }
                    for (int i = 0; i < json.length(); i++) {
                        final JSONObject jsons = json.optJSONObject(i);
                        GridLayout g = (GridLayout) l.getChildAt(i);
                        LinearLayout li = (LinearLayout) g.getChildAt(0);
                        final TableRow tr = (TableRow) li.getChildAt(0);
                        ImageView imm = (ImageView) tr.getChildAt(1);
                        final JSONArray finalJson = json;
                        final int finalI = i;
                        imm.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                try {
                                    JSONObject jsons = finalJson.optJSONObject(finalI);
                                    JSONObject jsonss = jsons.getJSONObject("pk");
                                    JSONObject jsonsss = jsonss.getJSONObject("mo");
                                    JSONObject jsonssss = jsonss.getJSONObject("me");
                                    FavorteMovieModel favorteMovie = new FavorteMovieModel();
                                    favorteMovie.getFavorteMovie().setMovieID(""+jsonsss.get("movieID"));
                                    favorteMovie.getFavorteMovie().setUsername(""+jsonssss.get("username"));
                                    manager.doFaorteRemoves(favorteMovie, new WSManager.WSManagerListener() {
                                        @Override
                                        public void onComplete(Object response) {
                                            String result = response.toString();
                                            if("1".equals(result)){
                                                Toast.makeText(ListMyMovie.this, "ลบสำเร็จ", Toast.LENGTH_SHORT).show();
                                                finish();
                                                startActivity(getIntent());
                                            }else{
                                                Toast.makeText(ListMyMovie.this, "ไม่สามารถลบได้", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onError(String err) {
                                            Toast.makeText(ListMyMovie.this, err, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ListMyMovie.this, err, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onClickBack(View view){
        Intent intent = new Intent(this,Movie.class);
        Intent intents =getIntent();
        intent.putExtra("id",intents.getStringExtra("id"));
        startActivity(intent);
    }
    public void onClickLogout(View view){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);

    }
}
