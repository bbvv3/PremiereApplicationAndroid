package fr.eni.premiereapplicationandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class ConfigurationActivity extends AppCompatActivity {

    public static final String NOM_FICHIER = "configuration";
    public static final String CLE1 = "tri";
    public static final String CLE2 = "prixParDefaut";

    SwitchCompat switchCompat;
    EditText editText = null;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        SharedPreferences sharedPreferences = getSharedPreferences(NOM_FICHIER,MODE_PRIVATE);
        boolean triParPrix = sharedPreferences.getBoolean(CLE1,false);
        String prixParDefaut = sharedPreferences.getString(CLE2, "");

        switchCompat = findViewById(R.id.input_triPrix);
        editText = findViewById(R.id.input_prixParDefaut);

        switchCompat.setChecked(triParPrix);
        editText.setText(prixParDefaut);
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPreferences = getSharedPreferences(NOM_FICHIER,MODE_PRIVATE);
        SharedPreferences.Editor editeur = sharedPreferences.edit();
        editeur.putBoolean(CLE1,switchCompat.isChecked());
        editeur.putString(CLE2,editText.getText().toString());
        editeur.commit();
    }
}
