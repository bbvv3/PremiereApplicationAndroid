package fr.eni.premiereapplicationandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import java.util.Objects;

import fr.eni.premiereapplicationandroid.bo.Article;
import fr.eni.premiereapplicationandroid.dao.ArticleDao;

public class NouvelArticleActivity extends AppCompatActivity {

    Article article;
    EditText et_nom;
    EditText et_desc;
    EditText et_url;
    EditText et_prix;
    RatingBar et_envie;
    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouvel_article);
        et_nom = findViewById(R.id.et_nom);
        et_desc = findViewById(R.id.et_desc);
        et_url = findViewById(R.id.et_url);
        et_prix = findViewById(R.id.et_prix);
        et_envie = findViewById(R.id.et_envie);
        Button btnAjouter = findViewById(R.id.btn_ajouter);

        btnAjouter.setOnClickListener(view -> {
            if (et_nom.getText() != null && et_desc.getText() != null && et_url.getText() != null && et_prix.getText() != null){
                article = new Article();
                article.setNom(et_nom.getText().toString());
                article.setDescription(et_desc.getText().toString());
                article.setUrl(et_url.getText().toString());
                article.setPrix(Float.parseFloat(et_prix.getText().toString()));
                article.setDegreEnvie(et_envie.getRating());
                article.setAchete(false);

                ArticleDao dao = new ArticleDao(this);
                dao.insert(article);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        sharedPreferences = getSharedPreferences(ConfigurationActivity.NOM_FICHIER, MODE_PRIVATE);
        String prixParDefaut = sharedPreferences.getString(ConfigurationActivity.CLE2, "");
        if (!Objects.equals(prixParDefaut, "")) {
            et_prix.setText(prixParDefaut);
        }
    }
}