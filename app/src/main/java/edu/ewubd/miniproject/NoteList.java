package edu.ewubd.miniproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NoteList extends AppCompatActivity {

    private ListView lvEvents;
    private ArrayList<Event> events;
    private CustomEventAdapter adapter;
    Button todoBtn, stopwatchBtn,addNoteBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        todoBtn = findViewById(R.id.todo);
        stopwatchBtn = findViewById(R.id.stopwatch);
        addNoteBtn = findViewById(R.id.addNote);

        lvEvents = findViewById(R.id.listNotes);
        events = new ArrayList<>();

//        loadData();

        todoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NoteList.this,ToDo.class);
                startActivity(i);
            }
        });

        stopwatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NoteList.this,MainActivity.class);
                startActivity(i);
            }
        });

        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NoteList.this,CreateNoteActivity.class);
                startActivity(i);
            }
        });


    }

    private void showDialog(String msg, String title, String position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(msg);
        builder.setTitle(title);

        builder.setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Util.getInstance().deleteByKey(MainActivity.this, key);
                        KeyValueDB db = new KeyValueDB(NoteList.this);
                        db.deleteDataByKey(position);
                        Toast.makeText(NoteList.this,"Note Deleted",Toast.LENGTH_LONG).show();
                        loadData();
                        dialog.cancel();
                    }

                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void loadData() {
        events.clear();
        KeyValueDB db = new KeyValueDB(this);
        Cursor rows = db.execute("SELECT * FROM key_value_pairs");
        if (rows.getCount() == 0) {
            return;
        }

//        events = new Event[rows.getCount()];
        while (rows.moveToNext()) {
            String key = rows.getString(0);
            String eventData = rows.getString(1);
            String[] fieldValues = eventData.split("---");

            String title = fieldValues[0];
            String notes = fieldValues[1];


            Event e = new Event(key, title, notes);
            events.add(e);
        }
        db.close();
        adapter = new CustomEventAdapter(this, events);
        lvEvents.setAdapter(adapter);

        // handle the click on an event-list item
        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                // String item = (String) parent.getItemAtPosition(position);
                System.out.println(position);
                Intent i = new Intent(NoteList.this, CreateNoteActivity.class);
                i.putExtra("EVENT_KEY", events.get(position).key);
                String values = events.get(position).title + "---" + events.get(position).notes;
                i.putExtra("VALUE", values);
                startActivity(i);
            }
        });
        // handle the long-click on an event-list item
        lvEvents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String message = "Do you want to delete this note " +"'" + events.get(position).title + "' ?";
                System.out.println(message);
                showDialog(message, "Delete Note", events.get(position).key);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}