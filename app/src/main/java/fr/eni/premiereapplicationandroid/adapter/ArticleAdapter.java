package fr.eni.premiereapplicationandroid.adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.eni.premiereapplicationandroid.MainActivity;
import fr.eni.premiereapplicationandroid.R;
import fr.eni.premiereapplicationandroid.bo.Article;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    public ArrayList<Article> articles;

    public ArticleAdapter(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titre;
        public RatingBar note;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            titre = itemView.findViewById(R.id.home_titre);
            note = itemView.findViewById(R.id.home_note);
            cardView = itemView.findViewById(R.id.home_cardview);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.card_article,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.titre.setText(article.getNom());
        holder.note.setRating(article.getDegreEnvie());
        holder.cardView.setOnClickListener(view -> {
            Intent intention = new Intent(view.getContext(), MainActivity.class);
            intention.putExtra("article", (Parcelable) article);
            view.getContext().startActivity(intention);
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
