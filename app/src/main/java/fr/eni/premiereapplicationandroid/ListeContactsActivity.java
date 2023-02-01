package fr.eni.premiereapplicationandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.eni.premiereapplicationandroid.adapter.ContactAdapter;
import fr.eni.premiereapplicationandroid.bo.Contact;

public class ListeContactsActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager rlm;
    private ArrayList<Contact> contacts;
    private static final String tel = "06 46 28 67 00";

    private Contact clickedContact = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_contacts);

        ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS},
                1422
        );
    }

    private View.OnClickListener contactClickListener = view -> {
        int position = Integer.parseInt(view.getTag().toString());
        clickedContact = contacts.get(position);
        ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.SEND_SMS,Manifest.permission.READ_PHONE_NUMBERS},
                2522
        );
    };

    private void envoiSMS() {
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(tel, null, "Bonjour " + clickedContact.getNom(), null, null);
        Toast.makeText(this, "Message envoyé à " + clickedContact.getNom(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 1422:{
                if(grantResults.length>0 &&
                        grantResults[0]== PackageManager.PERMISSION_GRANTED &&
                        grantResults[1]== PackageManager.PERMISSION_GRANTED){
                    chargerDonnees();
                    chargerRecycler();
                }
                break;
            }
            case 2522:{
                if(grantResults.length>0 &&
                        grantResults[0]== PackageManager.PERMISSION_GRANTED &&
                        grantResults[1]== PackageManager.PERMISSION_GRANTED){
                    envoiSMS();
                }
                break;
            }
        }
    }

    @SuppressLint("Range")
    private void chargerDonnees() {
        contacts = new ArrayList<>();
        ContentResolver cr = getContentResolver();

        Cursor cursorNom = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null,
                null);

        Cursor cursorTel = null;
        while(cursorNom.moveToNext()){
            Contact contact = new Contact();
            long id = cursorNom.getInt(cursorNom.getColumnIndex(ContactsContract.Contacts._ID));
            contact.setId(id);
            contact.setNom(cursorNom.getString(cursorNom.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
            int hasPhone = (cursorNom.getInt(cursorNom.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
            if( hasPhone > 0){
                cursorTel = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{String.valueOf(contact.getId())},
                        null,
                        null);
                if(cursorTel.getCount() > 0 && cursorTel.moveToFirst()){
                    contact.setTelephone(cursorTel.getString(cursorTel.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                }

            }
            contacts.add(contact);
        }
        cursorTel.close();
        cursorNom.close();
    }

    private void chargerRecycler() {
        rv = findViewById(R.id.contact_recycler);
        rv.setHasFixedSize(true);
        rlm = new LinearLayoutManager(this);
        rv.setLayoutManager(rlm);

        adapter = new ContactAdapter(contacts, contactClickListener);
        rv.setAdapter(adapter);
    }

}