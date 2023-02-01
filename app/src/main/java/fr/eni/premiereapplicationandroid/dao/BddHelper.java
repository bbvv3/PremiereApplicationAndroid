package fr.eni.premiereapplicationandroid.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BddHelper extends SQLiteOpenHelper {
    public BddHelper(Context context) {
        super(context, "MaBdd.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ArticleContract.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(ArticleContract.DROP_TABLE);
        onCreate(db);
    }
}
