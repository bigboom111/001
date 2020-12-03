package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.manager.WSManager;
import com.example.project.model.MemberModel;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void doLogin(View view) {

        EditText username = findViewById(R.id.editText7);
        EditText password = findViewById(R.id.editText9);

        username.setError(null);

        if (username.getText().toString().trim().equals("")) {
            username.setError("กรุณากรอก id");
        } else {

            WSManager manager = WSManager.getWsManager(this);
            final MemberModel memberModel = new MemberModel();
            memberModel.getMember().setUsername(username.getText().toString().trim());
            memberModel.getMember().setPassword(password.getText().toString().trim());
            memberModel.getMember().setFullname("");
            memberModel.getMember().setAge("");
            memberModel.getMember().setGender("");
            memberModel.getMember().setEmail("");


            manager.doLogin(memberModel, new WSManager.WSManagerListener() {
                @Override
                public void onComplete(Object response) {
                    String result = response.toString();
                    if("1".equals(result)){
                        Intent intent = new Intent(Login.this,Movie.class);
                        intent.putExtra("id",memberModel.getMember().getUsername());
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Invalid Username & Password", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(String err) {
                    Toast.makeText(Login.this, err, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void onRegister(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
