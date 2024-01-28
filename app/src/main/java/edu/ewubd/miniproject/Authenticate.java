package edu.ewubd.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Authenticate extends AppCompatActivity {

    private EditText pass;
    private Button submit,exit;
    public boolean grantAccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        submit = findViewById(R.id.PasswordSubmitBtn);
        exit = findViewById(R.id.exitBtn);
        pass = findViewById(R.id.password);


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                finishAffinity();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int password = Integer.parseInt(pass.getText().toString());
                SharedPreferences sp = getSharedPreferences("lock",MODE_PRIVATE);
                int p = sp.getInt("pass",0);
                if(p==password){
//                    Toast.makeText(getApplicationContext(),""+p,Toast.LENGTH_SHORT).show();
                    grantAccess = true;
                    Intent i = new Intent(Authenticate.this,ToDo.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(Authenticate.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    pass.setText("");
                }

            }
        });


    }
}