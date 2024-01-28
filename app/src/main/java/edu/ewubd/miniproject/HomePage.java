package edu.ewubd.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Checking is it protected or not
        SharedPreferences sp = getSharedPreferences("lock",MODE_PRIVATE);
        Authenticate grantAccess = new Authenticate();

//        if(grantAccess.grantAccess == false){
//            if(sp.getBoolean("isProtected",false)) {
//                Intent i = new Intent(HomePage.this, Authenticate.class);
//                startActivity(i);
//            }
//        }
        if(sp.getBoolean("isProtected",false)) {
                Intent i = new Intent(HomePage.this, Authenticate.class);
                startActivity(i);
            }
        else{
            Intent i = new Intent(HomePage.this, ToDo.class);
            startActivity(i);

        }


    }
}