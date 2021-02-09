package com.madhumankatha.mychat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private EditText edId,edName,edSurname;

    Button add,delete,update,view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);

        edId = findViewById(R.id.edID);
        edName = findViewById(R.id.edName);
        edSurname = findViewById(R.id.edSurName);

        add = findViewById(R.id.btnAdd);
        delete = findViewById(R.id.btnDelete);
        update = findViewById(R.id.btnUpdate);
        view = findViewById(R.id.btnView);

        add.setOnClickListener(v -> {
            if (dbHelper.insert(edId.getText().toString(),edName.getText().toString(),edSurname.getText().toString())){
                Toast.makeText(this, "Inserted Successful!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(v -> {
            if (dbHelper.delete(edId.getText().toString())){
                Toast.makeText(this, "Deleted Successful !!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Error occured", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(v -> {
            if (dbHelper.update(edId.getText().toString(),edName.getText().toString(),edSurname.getText().toString())){
                Toast.makeText(this, "Updated Successful!!", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(v -> {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                    .create();

            alertDialog.setMessage(dbHelper.getAllData());
            alertDialog.setTitle("Student Details");
            alertDialog.show();
        });

    }
}