package edu.ewubd.miniproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ToDo extends AppCompatActivity {

    private ListView lvEvents;
    private ArrayList<Event> events;
    private CustomEventAdapter adapter;

    LinearLayout stopwatchImgBtn,noteImagBtn,exitBtn,passwordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        //Checking is it protected or not
        SharedPreferences sp = getSharedPreferences("lock",MODE_PRIVATE);
        Authenticate grantAccess = new Authenticate();


//        if(grantAccess.grantAccess == false){
//            if(sp.getBoolean("isProtected",false)) {
//                Intent i = new Intent(ToDo.this, Authenticate.class);
//                startActivity(i);
//            }
//        }
//        if(grantAccess.grantAccess == false){
//                Intent i = new Intent(ToDo.this, Authenticate.class);
//                startActivity(i);
//
//        }




        stopwatchImgBtn = findViewById(R.id.stopwatchIcon);
        noteImagBtn = findViewById(R.id.noteIcon);
        exitBtn =  findViewById(R.id.exit);
        passwordBtn =  findViewById(R.id.password);



        noteImagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  i = new Intent(ToDo.this,NoteList.class);
                startActivity(i);
            }
        });

        stopwatchImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ToDo.this,MainActivity.class);
                startActivity(i);
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                finishAffinity();
            }
        });

        passwordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ToDo.this,SetPassword.class);
                startActivity(i);
            }
        });








    }

//    private void showDialog(String msg, String title, String position){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        builder.setMessage(msg);
//        builder.setTitle(title);
//
//        builder.setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
////                        Util.getInstance().deleteByKey(MainActivity.this, key);
//                        KeyValueDB db = new KeyValueDB(ToDo.this);
//                        db.deleteDataByKey(position);
//                        Toast.makeText(ToDo.this,"Note Deleted",Toast.LENGTH_LONG).show();
//                        loadData();
//                        dialog.cancel();
//                    }
//
//                })
//
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//        AlertDialog alert = builder.create();
//        alert.show();
//    }

//    private void loadData(){
//        events.clear();
//        KeyValueDB db = new KeyValueDB(this);
//        Cursor rows = db.execute("SELECT * FROM key_value_pairs");
//        if (rows.getCount() == 0) {
//            return;
//        }
//
////        events = new Event[rows.getCount()];
//        while (rows.moveToNext()) {
//            String key = rows.getString(0);
//            String eventData = rows.getString(1);
//            String[] fieldValues = eventData.split("---");
//
//            String title = fieldValues[0];
//            String notes = fieldValues[1];
//
//
//            Event e = new Event(key, title, notes);
//            events.add(e);
//        }
//        db.close();
//        adapter = new CustomEventAdapter(this, events);
//        lvEvents.setAdapter(adapter);
//
//        // handle the click on an event-list item
//        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
//                // String item = (String) parent.getItemAtPosition(position);
//                System.out.println(position);
//                Intent i = new Intent(ToDo.this, CreateNoteActivity.class);
//                i.putExtra("EVENT_KEY", events.get(position).key);
//                String values = events.get(position).title+"---"+events.get(position).notes;
//                i.putExtra("VALUE", values);
//                startActivity(i);
//            }
//        });
//        // handle the long-click on an event-list item
//        lvEvents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                String message = "Do you want to delete event - "+events.get(position).title +" ?";
//                System.out.println(message);
//                showDialog(message, "Delete Event", events.get(position).key);
//                return true;
//            }
//        });
//
//
//
//
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        loadData();

}
