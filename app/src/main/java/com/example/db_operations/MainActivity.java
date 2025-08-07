package com.example.db_operations;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.database.sqlite.SQLiteDatabase;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button button;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Name = findViewById(R.id.tb1);
        Password = findViewById(R.id.tb2);
        button = findViewById(R.id.b1);
        db = openOrCreateDatabase("studentdb", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, password TEXT)");

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String name = Name.getText().toString();
                String password = Password.getText().toString();
                String Query = "INSERT INTO student(name,password) VALUES('"+name+"','"+password+"')";
                db.execSQL(Query);

                Toast.makeText(MainActivity.this,"Inserted", Toast.LENGTH_SHORT).show();

            }


        });
    }
}