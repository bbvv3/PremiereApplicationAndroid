package fr.eni.premiereapplicationandroid.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.eni.premiereapplicationandroid.bo.Article;

public class ArticleDao {
    private SQLiteDatabase db;
    private BddHelper helper;

    private static final String TAG = "DAO";

    private final String[] ALL_COLUMN = new String[]{
            ArticleContract.COL_ID,
            ArticleContract.COL_NOM,
            ArticleContract.COL_DESCRIPTION,
            ArticleContract.COL_URL,
            ArticleContract.COL_PRIX,
            ArticleContract.COL_DEGRE_ENVIE,
            ArticleContract.COL_IS_ACHETE
    };

    public ArticleDao(Context ctx) {
        helper = new BddHelper(ctx);
        db = helper.getWritableDatabase();
    }

    public void insert(Article article) {
        ContentValues cv = new ContentValues();
        cv.put(ArticleContract.COL_NOM, article.getNom());
        cv.put(ArticleContract.COL_DESCRIPTION, article.getDescription());
        cv.put(ArticleContract.COL_URL, article.getUrl());
        cv.put(ArticleContract.COL_PRIX, article.getPrix());
        cv.put(ArticleContract.COL_DEGRE_ENVIE, article.getDegreEnvie());
        cv.put(ArticleContract.COL_IS_ACHETE, (article.isAchete()?1:0));

        long id = db.insert(ArticleContract.TABLE_NAME, null, cv);

        if (id == -1) {
            Log.w(TAG, "l'article n'a pas été inseré");
        } else {
            Log.i(TAG, "l'article ID = " + id + " a été inseré");
        }
    }

    @SuppressLint("Range")
    public Article get(int id) {

        Article resultat = null;

        Cursor cursor = db.query(ArticleContract.TABLE_NAME,
                ALL_COLUMN,
                ArticleContract.COL_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        if (cursor.moveToNext()) {
            resultat = new Article();
            resultat.setId(cursor.getInt(0));
            resultat.setNom(cursor.getString(1));
            resultat.setDescription(cursor.getString(2));
            resultat.setUrl(cursor.getString(3));
            resultat.setPrix(cursor.getFloat(4));
            resultat.setDegreEnvie(cursor.getFloat(5));
            resultat.setAchete(cursor.getInt(6) == 1);
        }

        if (resultat == null) {
            Log.w(TAG, "L'article n'a pas été trouvé");
        }
        return resultat;
    }


    public ArrayList<Article> get(boolean triPrix) {

        ArrayList<Article> resultat = new ArrayList<>();

        String orderBy = (triPrix?ArticleContract.COL_PRIX + " ASC":null);

        Cursor cursor = db.query(ArticleContract.TABLE_NAME,
                ALL_COLUMN,
                null,
                null,
                null,
                null,
                orderBy,
                null
        );

        while (cursor.moveToNext()) {
            Article article = new Article();
            article.setId(cursor.getInt(0));
            article.setNom(cursor.getString(1));
            article.setDescription(cursor.getString(2));
            article.setUrl(cursor.getString(3));
            article.setPrix(cursor.getFloat(4));
            article.setDegreEnvie(cursor.getFloat(5));
            article.setAchete(cursor.getInt(6) == 1);

            resultat.add(article);
        }
        if (resultat.size() == 0) {
            Log.w(TAG, "Aucun article n'a été trouvé");
        }
        return resultat;
    }

    public void update(Article article){
        ContentValues valeurs = new ContentValues();
        valeurs.put(ArticleContract.COL_NOM, article.getNom());
        valeurs.put(ArticleContract.COL_DESCRIPTION, article.getDescription());
        valeurs.put(ArticleContract.COL_URL, article.getUrl());
        valeurs.put(ArticleContract.COL_PRIX, article.getPrix());
        valeurs.put(ArticleContract.COL_DEGRE_ENVIE, article.getDegreEnvie());
        valeurs.put(ArticleContract.COL_IS_ACHETE, article.isAchete());

        boolean updated = db.update(
                ArticleContract.TABLE_NAME,
                valeurs,
                ArticleContract.COL_ID + "= ?",
                new String[]{String.valueOf(article.getId())}
        )> 0;

        if(updated){
            Log.i(TAG, "Update de l'article d'ID = "+ article.getId());
        }else{
            Log.w(TAG, "Aucune update réalisé");
        }
    }

    public void delete(int id){
        db.delete(ArticleContract.TABLE_NAME,
                ArticleContract.COL_ID + "= ?",
                new String[]{String.valueOf(id)});
    }


    public void reset(){
        helper.onUpgrade(db, 1,1);
    }

}
