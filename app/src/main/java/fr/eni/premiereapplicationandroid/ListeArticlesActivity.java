package fr.eni.premiereapplicationandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import fr.eni.premiereapplicationandroid.adapter.ArticleAdapter;
import fr.eni.premiereapplicationandroid.bo.Article;
import fr.eni.premiereapplicationandroid.dao.ArticleDao;

public class ListeArticlesActivity extends AppCompatActivity {



    private ArrayList<Article> articles = new ArrayList<>();

     @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_liste_articles);

        Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences(ConfigurationActivity.NOM_FICHIER,MODE_PRIVATE);
        boolean triParPrix = sharedPreferences.getBoolean(ConfigurationActivity.CLE1,false);

        ArticleDao dao = new ArticleDao(this);
        articles = dao.get(triParPrix);

        RecyclerView rv = findViewById(R.id.recycler);
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager rlm = new LinearLayoutManager(this);
        rv.setLayoutManager(rlm);

        ArticleAdapter adapter = new ArticleAdapter(articles);
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         Intent intent;
         switch (item.getItemId()){
             case R.id.menu_config :
                 intent = new Intent(this, ConfigurationActivity.class);
                 startActivity(intent);
                 break;
             case R.id.icon_plus :
                 intent = new Intent(this, NouvelArticleActivity.class);
                 startActivity(intent);
                 break;
         }

        return super.onOptionsItemSelected(item);
    }
}