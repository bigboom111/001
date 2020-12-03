package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project.manager.WSManager;
import com.example.project.model.MemberModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner gender = findViewById(R.id.spinner);
        String[] data = {"Male","Female"};
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        gender.setAdapter(dataAdapter);

    }
    public void doRegister(View view) {

        EditText username = findViewById(R.id.editText);
        EditText password = findViewById(R.id.editText2);
        EditText fullname = findViewById(R.id.editText3);
        EditText age = findViewById(R.id.editText4);
        Spinner gender = findViewById(R.id.spinner);
        EditText email = findViewById(R.id.editText6);

        username.setError(null);

        if (username.getText().toString().trim().equals("")) {
            username.setError("กรุณากรอก id");
        } else {

            WSManager manager = WSManager.getWsManager(this);
            MemberModel memberModel = new MemberModel();
            memberModel.getMember().setUsername(username.getText().toString());
            memberModel.getMember().setPassword(password.getText().toString());
            memberModel.getMember().setFullname(fullname.getText().toString());
            memberModel.getMember().setAge(age.getText().toString());
            memberModel.getMember().setGender(gender.getSelectedItem().toString());
            memberModel.getMember().setEmail(email.getText().toString());

            manager.doRegister(memberModel, new WSManager.WSManagerListener() {
                @Override
                public void onComplete(Object response) {
                    Toast.makeText(MainActivity.this, "ลงทะเบียนสำเร็จ", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                }

                @Override
                public void onError(String err) {
                    Toast.makeText(MainActivity.this, err, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
