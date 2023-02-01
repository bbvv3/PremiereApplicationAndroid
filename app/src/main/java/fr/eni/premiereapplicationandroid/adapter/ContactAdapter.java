package fr.eni.premiereapplicationandroid.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import fr.eni.premiereapplicationandroid.R;
import fr.eni.premiereapplicationandroid.bo.Contact;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<Contact> contacts = null;
    private View.OnClickListener listener;

    public ContactAdapter(ArrayList<Contact> contacts, View.OnClickListener listener) {

        this.contacts = contacts;
        this.listener=listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
       TextView tv_nom;
       TextView tv_tel;
       CardView cv_contact;
        public ViewHolder(View itemView, View.OnClickListener listener) {
            super(itemView);
            tv_nom = itemView.findViewById(R.id.contact_nom);
            tv_tel = itemView.findViewById(R.id.contact_tel);
            cv_contact = itemView.findViewById(R.id.contact_cardview);
            itemView.setOnClickListener(listener);
        }
    }
    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.card_contact,parent,false);
        return new ContactAdapter.ViewHolder(view, listener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.tv_nom.setText(contact.getNom());
        holder.tv_tel.setText(contact.getTelephone());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
