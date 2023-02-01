package fr.eni.premiereapplicationandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.eni.premiereapplicationandroid.bo.Article;
import fr.eni.premiereapplicationandroid.bo.Contact;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Article article = null;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        setSupportActionBar(toolbar);

        Intent intention = getIntent();
        if (intention != null){
            article = intention.getParcelableExtra("article");
            if(article != null){
                TextView titre = findViewById(R.id.titreAliment);
                titre.setText(article.getNom());
                TextView prix = findViewById(R.id.prixAliment);
                prix.setText((article.getPrix() + "€"));
                TextView description = findViewById(R.id.descriptionAliment);
                description.setText(article.getDescription());
                RatingBar note = findViewById(R.id.note);
                note.setRating(article.getDegreEnvie());
                Button bouton2 = findViewById(R.id.button2);
                bouton2.setText((article.isAchete()?"Acheté":"A acheter"));
            }else{
                Toast.makeText(this, "L'article est introuvable", Toast.LENGTH_LONG).show();
            }
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details,menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.menu_share :
                intent = new Intent(this, ListeContactsActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_edit :
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickButton1(View view){
        Toast.makeText(this, article.getUrl(), Toast.LENGTH_LONG).show();
        Intent intention = new Intent(this, InfoUrlActivity.class);
        intention.putExtra("article", (Parcelable) article);
        startActivity(intention);
    }

    @SuppressLint("SetTextI18n")
    public void onClickButton2(View view){
        Button bouton2 = findViewById(R.id.button2);
        if(bouton2.getText() == "Acheté") {
            article.setAchete(false);
            bouton2.setText("A acheter");
        }else{
            article.setAchete(true);
            bouton2.setText("Acheté");
        }
    }
}