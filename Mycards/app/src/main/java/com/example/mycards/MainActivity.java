package com.example.mycards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button add_button;
    Button next_button;
    Button pre_button;
    Button update_button;

    int wordcounter = 0;
    MyDatabaseHelper myDB;
    ArrayList<String> word_id, word_eng, word_mon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_button = findViewById(R.id.button);
        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        myDB = new MyDatabaseHelper(MainActivity.this);
        word_id = new ArrayList<>();
        word_eng = new ArrayList<>();
        word_mon = new ArrayList<>();

        storeDataInArrays();
        int getWordcountermax = word_id.size()-1;
        update_button = findViewById(R.id.button2);
        update_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, activity_update.class);

                intent.putExtra("id", String.valueOf(word_id.get(wordcounter))); //id
                intent.putExtra("title", String.valueOf(word_eng.get(wordcounter))); //eng
                intent.putExtra("author", String.valueOf(word_mon.get(wordcounter))); //mon
                startActivity(intent);
            }
        });

        next_button = findViewById(R.id.button4);
        next_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                TextView tv = (TextView)findViewById(R.id.textView);
                tv.setText(word_eng.get(wordcounter));
                TextView tv1 = (TextView)findViewById(R.id.textView2);
                tv1.setText(word_mon.get(wordcounter));
                wordcounter++;
                if(wordcounter>getWordcountermax){
                    wordcounter=0;
                }
            }
        });
        pre_button = findViewById(R.id.button5);
        pre_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                TextView tv = (TextView)findViewById(R.id.textView);
                tv.setText(word_eng.get(wordcounter));
                TextView tv1 = (TextView)findViewById(R.id.textView2);
                tv1.setText(word_mon.get(wordcounter));
                wordcounter--;
                if(wordcounter==-1){
                    wordcounter=getWordcountermax;
                }
            }
        });



    }


    void storeDataInArrays(){

        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
//            empty_imageview.setVisibility(View.VISIBLE);
//            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                word_id.add(cursor.getString(0));
                word_eng.add(cursor.getString(1));
                word_mon.add(cursor.getString(2));

            }
//            empty_imageview.setVisibility(View.GONE);
//            no_data.setVisibility(View.GONE);
        }
    }

//    void DisplayData
//String cuntovich = "centric";
//    public void buttonClick(View view) {
//        TextView tv = (TextView)findViewById(R.id.textView);
//        tv.setText("Welcome to android");
//        TextView tv1 = (TextView)findViewById(R.id.textView2);
//        tv1.setText(cuntovich);
//    }

}