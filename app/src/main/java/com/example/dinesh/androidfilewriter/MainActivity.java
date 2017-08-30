package com.example.dinesh.androidfilewriter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    EditText et2;
    EditText et3;

    Button write, read;

    TextView tv1;
    TextView tv2;
    TextView tv3;

    String message1 = "file1.txt";
    String message2 = "file2.txt";
    String message3 = "file3.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.e3);
        write = (Button) findViewById(R.id.write);
        read = (Button) findViewById(R.id.read);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv1.setText(readMessage(message1));
                tv2.setText(readMessage(message2));
                tv3.setText(readMessage(message3));
            }
        });
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMessage(message1, et1.getText().toString());
                saveMessage(message2, et2.getText().toString());
                saveMessage(message3, et3.getText().toString());
            }
        });
    }

    public void saveMessage(String file, String text) {
        try{
            FileOutputStream fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());
            fileOutputStream.close();
            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
        }catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error saving file", Toast.LENGTH_SHORT).show();
        }
    }

    public String readMessage(String file) {
        String text = "";

        try {
            FileInputStream fileInputStream  = openFileInput(file);
            int sizeOf = fileInputStream.available();
            byte[] buffer = new byte[sizeOf];
            fileInputStream.read(buffer);
            fileInputStream.close();
            text = new String(buffer);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error reading file", Toast.LENGTH_SHORT).show();
        }

        return text;
    }

}
