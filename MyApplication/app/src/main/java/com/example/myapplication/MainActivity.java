package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String mymessage ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
//    public void itemClicked(View v) {
//        //code to check if this checkbox is checked!
//        if(((CheckBox)v).isChecked()){
//            toastmessage=toastmessage+" "+;
//        }
//    }
//    public String onClick(View view) {
//        // Get the text on the pressed button
//        return ((Button)view).getText().toString();
//    }


    public void toastMessage(View view) {
        selectToppings((CheckBox) findViewById(R.id.checkBox2), getString(R.string.choco));
        selectToppings((CheckBox) findViewById(R.id.checkBox3), getString(R.string.cherries));
        selectToppings((CheckBox) findViewById(R.id.checkBox4), getString(R.string.never_let_you_down));
        selectToppings((CheckBox) findViewById(R.id.checkBox5), getString(R.string.nuts));
        selectToppings((CheckBox) findViewById(R.id.checkBox6), getString(R.string.sprinkles));
        Toast.makeText(this, mymessage, Toast.LENGTH_SHORT).show();
    }

    private void selectToppings(CheckBox checkBox, String topping){
        if (checkBox.isChecked()){
            if (!mymessage.contains(topping)){
                mymessage = mymessage + " " + topping;
            }
        }
        else{
            if (mymessage.contains(topping)){
                mymessage = mymessage.replace(" " + topping, "");
            }
        }
    }

//public void onCheckboxClicked(View view) {
//    // Is the view now checked?
//    boolean checked = ((CheckBox) view).isChecked();
//
//    // Check which checkbox was clicked
//    switch(view.getId()) {
//        case R.id.checkBox2:
//            if (checked){
//                toastmessage=toastmessage + " " + "choco";
//                break;
//            }
//        case R.id.checkBox3:
//            if (checked){
//                toastmessage=toastmessage + " " + "cherries";
//                break;
//            }
//        case R.id.checkBox4:
//            if (checked){
//                toastmessage=toastmessage + " " + "never let you down";
//                break;
//            }
//        case R.id.checkBox5:
//            if (checked){
//                toastmessage=toastmessage + " " + "nuts";
//                break;
//            }
//        case R.id.checkBox6:
//            if (checked){
//                toastmessage=toastmessage + " " + "sprinkles";
//                break;
//            }
//        default:
//            toastmessage = "";
//
//
//
//
//
//
//            // TODO: Veggie sandwich
//    }
//}

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked.
//        switch (view.getId()) {
//            case R.id.sameday:
//                if (checked)
//                    // Same day service
//                    displayToast(getString(R.string.same_day_messenger_service));
//                break;
//            case R.id.nextday:
//                if (checked)
//                    // Next day delivery
//                    displayToast(getString(R.string.next_day_ground_delivery));
//                break;
//            case R.id.pickup:
//                if (checked)
//                    // Pick up
//                    displayToast(getString(R.string.pick_up));
//                break;
//            default:
//                // Do nothing.
//                break;
//        }
    }
}