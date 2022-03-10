package me.mahakagg.recipesrecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeMethodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_method);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String item_name = null;
        if (extras != null) {
           item_name = extras.getString(RecipeListAdapter.EXTRA_NAME);
        }
        TextView heading = findViewById(R.id.food_name);
        ImageView imageView = findViewById(R.id.food_image);
        heading.setText(item_name);
        if (item_name != null) {
            switch (item_name){
                case "ЗУЛ САРЫН БАЯРЫН ПУДИНГ (ИХ БРИТАНИ)":
                    imageView.setImageResource(R.drawable.chocolate_mint_bar);
                    break;
                case "ДУЛЬСЕ ДЕ ЛЕЧЕ (АРГЕНТИН)":
                    imageView.setImageResource(R.drawable.blueberry_cupcakes);
                    break;
                case "БОЛУ РЕЙ (ПОРТУГАЛИ)":
                    imageView.setImageResource(R.drawable.fudge_brownies);
                    break;
                case "МАЗАРИНЕР (ШВЕД)":
                    imageView.setImageResource(R.drawable.lemon_cake);
                    break;
                case "CHERRY PIE (ГОЛЛАНД)":
                    imageView.setImageResource(R.drawable.cobbler);
                    break;
                case "ГУЛАБЖАМУН (ЭНЭТХЭГ)":
                    imageView.setImageResource(R.drawable.sheet_cake);
                    break;
                case "ВИНАРТЕРТА (ИСЛАНД)":
                    imageView.setImageResource(R.drawable.espresso_crinkles);
                    break;
                case "БАНОФИ БЯЛУУ (АНГЛИ)":
                    imageView.setImageResource(R.drawable.chocolate_cherry_cookies);
                    break;
                case "КНАФЕ (ОЙРХИ ДОРНОД)":
                    imageView.setImageResource(R.drawable.cheesecake);
                    break;
                case "ТИРАМИСУ (ИТАЛИ)":
                    imageView.setImageResource(R.drawable.tiramisu);
                    break;
                case "КРАНАХАН (ШОТЛАНД)":
                    imageView.setImageResource(R.drawable.carrot_cake);
                    break;
                case "ROCKY ROAD CAKES (АВСТРАЛИ)":
                    imageView.setImageResource(R.drawable.blueberry_ice_cream);
                    break;
                default: break;
           }
        }
    }
}
