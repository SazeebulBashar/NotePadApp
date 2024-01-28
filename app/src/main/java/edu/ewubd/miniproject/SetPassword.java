package edu.ewubd.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class SetPassword extends AppCompatActivity {
    public boolean isProtected = false;
    private Button setPasswordBtn,setPasswordBackBtn;
    private EditText password,repassword,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.rePassword);
        setPasswordBtn = findViewById(R.id.setPasswordBtn);
        setPasswordBackBtn = findViewById(R.id.setPasswordBackBtn);



        setPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailID = email.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(Integer.parseInt(pass) == Integer.parseInt(repass) && emailID.length() >0){
                    makeToast("App Protected");
                    isProtected = true;
                    SharedPreferences sp = getSharedPreferences("lock", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("email",emailID);
                    editor.putInt("pass",Integer.parseInt(pass));
                    editor.putBoolean("isProtected",true);
                    editor.apply();


                    Intent i =  new Intent(SetPassword.this,ToDo.class);
                    startActivity(i);
                }
                else
                    makeToast("Passwords are not Same");

            }
        });

        setPasswordBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(SetPassword.this,ToDo.class);
                startActivity(i);
            }
        });




    }
    private void makeToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}