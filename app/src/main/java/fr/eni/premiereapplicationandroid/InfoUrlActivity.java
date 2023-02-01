package fr.eni.premiereapplicationandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import fr.eni.premiereapplicationandroid.bo.Article;

public class InfoUrlActivity extends AppCompatActivity {

    private static final String TAG = "InfoUrlActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_url);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intention = getIntent();
        if (intention != null){
            Article article = intention.getParcelableExtra("article");
            if(article != null){
                TextView title = findViewById(R.id.titreInfoUrl);
                title.setText(article.getUrl());
            }
        }
    }
}