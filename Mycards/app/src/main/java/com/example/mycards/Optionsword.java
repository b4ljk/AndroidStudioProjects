package com.example.mycards;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class Optionsword extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_word);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
                String text = checkedRadioButton.getText().toString();
                Log.d("mymessage1", String.valueOf(text));
                switch (text){
                    case "Монгол":
                        Intent intent = new Intent(Optionsword.this, MainActivity.class);

                        intent.putExtra("languj","1" ); //id
                        startActivity(intent);
                        break;
                    case "Англи":
                        Intent intent1 = new Intent(Optionsword.this, MainActivity.class);

                        intent1.putExtra("languj","2" ); //id
                        startActivity(intent1);
                        break;
                    case "Цуг":
                        Intent intent2 = new Intent(Optionsword.this, MainActivity.class);

                        intent2.putExtra("languj","3" ); //id
                        startActivity(intent2);
                        break;
                }
                Log.d("mymessage", String.valueOf(checkedId));
            }
        });


    }
}