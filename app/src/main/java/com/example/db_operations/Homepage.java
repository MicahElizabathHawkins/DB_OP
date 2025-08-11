package com.example.db_operations;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class Homepage extends AppCompatActivity{

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        Private TextView text1;
//        Private Button b2;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db = openOrCreateDatabase("studentdb", MODE_PRIVATE, null);
        TextView tv1 = findViewById(R.id.tv1);
        Button b2 = findViewById(R.id.b2);
        EditText text = findViewById(R.id.text1);
        Button Btn = findViewById(R.id.b3);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder result = new StringBuilder();
                Cursor cursor = db.rawQuery("SELECT * FROM student", null);
                if (cursor.getCount()==0) {
                    tv1.setText("No data found");
                    return;
                }
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    String password = cursor.getString(2);
                    result.append("ID: " + id + " ");
                    result.append("Name: " + name + " ");
                    result.append("Password: " + password + " \n");
                }
                cursor.close();
                tv1.setText(result.toString());

            }
        });
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = text.getText().toString();
                String query = "DELETE FROM student WHERE name = ?";
                db.execSQL(query, new String[]{name});

                Toast.makeText(Homepage.this,"Deleted", Toast.LENGTH_SHORT).show();
            }
        });

            }

    }
