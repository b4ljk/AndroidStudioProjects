package com.example.mycards;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button add_button;
    Button next_button;
    Button pre_button;
    Button update_button,deleteButton;
    TextView english, mongolian;
    private static final int Filelocation = 100;

    int wordcounter = 0;
    MyDatabaseHelper myDB;
    ArrayList<String> word_id, word_eng, word_mon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);

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
        int getWordcountermax = word_eng.size()-1;
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
                wordcounter++;
                if(wordcounter>getWordcountermax){
                    wordcounter=0;
                }
                TextView tv = (TextView)findViewById(R.id.textView);
                tv.setText(word_eng.get(wordcounter));
                TextView tv1 = (TextView)findViewById(R.id.textView2);
                tv1.setText(word_mon.get(wordcounter));


            }
        });
        deleteButton = findViewById(R.id.button3);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                confirmDialogfortext();
            }
        });
        pre_button = findViewById(R.id.button5);

        pre_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                wordcounter--;
                if(wordcounter<0){
                    wordcounter=getWordcountermax;
                }
                TextView tv = (TextView)findViewById(R.id.textView);
                tv.setText(word_eng.get(wordcounter));
                TextView tv1 = (TextView)findViewById(R.id.textView2);
                tv1.setText(word_mon.get(wordcounter));


            }
        });
        english = findViewById(R.id.textView);
        english.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_update.class);
                intent.putExtra("id", String.valueOf(word_id.get(wordcounter))); //id
                intent.putExtra("title", String.valueOf(word_eng.get(wordcounter))); //eng
                intent.putExtra("author", String.valueOf(word_mon.get(wordcounter))); //mon
                startActivity(intent);
                return false;
            }
        });
        english.setOnClickListener(textviewhider);
 mongolian = findViewById(R.id.textView2);
        mongolian.setOnClickListener(textviewhider2);
        mongolian.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_update.class);
                intent.putExtra("id", String.valueOf(word_id.get(wordcounter))); //id
                intent.putExtra("title", String.valueOf(word_eng.get(wordcounter))); //eng
                intent.putExtra("author", String.valueOf(word_mon.get(wordcounter))); //mon
                startActivity(intent);
                return false;
            }
        });



    hide();
    }

    private View.OnClickListener textviewhider = new View.OnClickListener() {
        public void onClick(View v) {

            english.setVisibility((english.getVisibility() == View.VISIBLE)
                    ? View.INVISIBLE : View.VISIBLE);


        }
    };
    private View.OnClickListener textviewhider2 = new View.OnClickListener() {
        public void onClick(View v) {

           if(mongolian.getVisibility()==View.VISIBLE){
               mongolian.setVisibility(View.GONE);
           }else{
               mongolian.setVisibility(View.VISIBLE);
           }

        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //hi testing git
        //test2





        switch (item.getItemId()) {
            case R.id.action_contact:
//                displayToast(getString(R.string.action_order_message));
                Intent intent = new Intent(MainActivity.this, Optionsword.class);

                startActivity(intent);
                return true;
            case R.id.addword:
                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.setType("*/*");
                intent1.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent1, Filelocation);
                return true;
            default:
                // Do nothing
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("testMain","resume") ;
//        recreate();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
        if (requestCode == Filelocation) {

            Uri urlforfile=data.getData();
            Log.d("aibisda", urlforfile.toString());
            BufferedReader reader;
            InputStream is = null;
            try {
                is = getContentResolver().openInputStream(urlforfile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            try {
                String line = reader.readLine();
                while((line = reader.readLine()) != null){String[] ugs = line.split(",");
                    MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                    myDB.addBook(ugs[0].toString().trim(),
                            ugs[1].toString().trim());
//                    Log.d("aibisda2", ugs[1]); RECREATE hiihgui bol screen dahij zurahgui bsnaas aldaa ugj bn
                    recreate();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return;
        }
    }

    void storeDataInArrays(){
        next_button = findViewById(R.id.button4);
        pre_button = findViewById(R.id.button5);
        deleteButton = findViewById(R.id.button3);
        update_button = findViewById(R.id.button2);
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();

            next_button.setEnabled(false);
            pre_button.setEnabled(false);
            deleteButton.setEnabled(false);
            update_button.setEnabled(false);

//            empty_imageview.setVisibility(View.VISIBLE);
//            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                word_id.add(cursor.getString(0));
                word_eng.add(cursor.getString(1));
                word_mon.add(cursor.getString(2));

            }
            next_button.setEnabled(true);
            pre_button.setEnabled(true);
            deleteButton.setEnabled(true);
            update_button.setEnabled(true);
            //hamgiin suuld button nemegdev aldaagui
//            empty_imageview.setVisibility(View.GONE);
//            no_data.setVisibility(View.GONE);
        }
    }


void confirmDialogfortext(){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Delete "  + " ?");
    builder.setMessage("Are you sure you want to delete "  + " ?");
    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
            myDB.deleteOneRow(word_id.get(wordcounter));
            recreate();
        }
    });
    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

        }
    });
    builder.create().show();
}
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
    // das 1
    public void hide() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        TextView txtView = (TextView)findViewById(R.id.textView);
        TextView txtView2 = (TextView)findViewById(R.id.textView2);
        String lang = getIntent().getStringExtra("languj");
        if(lang==null) {
            lang = preferences.getString("myhelper", "");
        }
        Log.d("sdaymaa", String.valueOf(lang));
        if(lang!=null){
            editor.putString("myhelper",lang);
            editor.apply();
            switch(lang){
                case "1":
                    txtView.setVisibility(View.INVISIBLE);
                    txtView2.setVisibility(View.VISIBLE);
                    break;
                case "2":
                    txtView.setVisibility(View.VISIBLE);
                    txtView2.setVisibility(View.INVISIBLE);
                    break;
                case "3":
                    txtView.setVisibility(View.VISIBLE);
                    txtView2.setVisibility(View.VISIBLE);
                    break;
                default:
                    return;
            }
        }


    }



}