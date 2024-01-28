package edu.ewubd.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateNoteActivity extends AppCompatActivity {

    Button saveBtn,backBtn;
    EditText notes,noteTitle;
    private String existingKey="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        saveBtn = findViewById(R.id.saveNote);
        backBtn = findViewById(R.id.back);
        notes = findViewById(R.id.notes);
        noteTitle = findViewById(R.id.noteTitle);



        Intent i = getIntent();
        if(i.hasExtra("VALUE")){

            String value = i.getStringExtra("VALUE");
            KeyValueDB db = new KeyValueDB(CreateNoteActivity.this);
            String values[] = value.split("---");
            noteTitle.setText(values[0]);
            notes.setText(values[1]);

            System.out.println(values[0]);


        }

        if(i.hasExtra("EVENT_KEY")){
            existingKey = i.getStringExtra("EVENT_KEY");
        }




        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = noteTitle.getText().toString();
                String note = notes.getText().toString();

                if(title.length()>0 && note.length()>0){

                    String value = title+"---"+note+"---";

                    KeyValueDB db = new KeyValueDB(CreateNoteActivity.this);

                    if (existingKey.length() == 0) {
                        String key = title + System.currentTimeMillis();
                        existingKey = title;
                        db.insertKeyValue(key,value);

                    } else {
                        db.updateValueByKey(existingKey, value);
                    }
                    db.close();

                    Intent  i = new Intent(CreateNoteActivity.this,NoteList.class);
                    startActivity(i);
                    finish();

                }

            }
        });






    }
}