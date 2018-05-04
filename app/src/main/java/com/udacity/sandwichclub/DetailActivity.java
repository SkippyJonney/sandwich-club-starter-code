package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            //What is being sent to JasonUtils
            Toast.makeText(this,json,Toast.LENGTH_LONG).show();
            //https://stackoverflow.com/questions/43551974/java-how-to-access-json-inner-child-array
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        // Text View References
        TextView tv_AKA = findViewById(R.id.also_known_tv);
        TextView tv_Origin = findViewById(R.id.origin_tv);
        TextView tv_Ingredients = findViewById(R.id.ingredients_tv);
        TextView tv_Description = findViewById(R.id.description_tv);

        List<String> listAKA = sandwich.getAlsoKnownAs();
        if(listAKA.size() > 0) {
            for(int i = 0; i < listAKA.size(); i++) {
                tv_AKA.append(" " + listAKA.get(i));
                if(i != listAKA.size() - 1) {
                    tv_AKA.append(",");
                }
            }
        }

        tv_Origin.setText("  " + sandwich.getPlaceOfOrigin());

        List<String> listIngredients = sandwich.getIngredients();
        if(listIngredients.size() > 0) {
            for(int i = 0; i < listIngredients.size(); i++) {
                tv_Ingredients.append(" " + listIngredients.get(i));
                if(i != listIngredients.size() - 1) {
                    tv_Ingredients.append(",");
                }
            }
        }

        tv_Description.setText(sandwich.getDescription());

    }
}
