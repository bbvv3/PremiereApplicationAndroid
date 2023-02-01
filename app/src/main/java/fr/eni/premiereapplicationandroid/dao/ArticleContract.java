package fr.eni.premiereapplicationandroid.dao;

public class ArticleContract {
    public static final String TABLE_NAME = "Articles";

    public static final String COL_ID = "id";
    public static final String COL_NOM = "nom";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_URL = "url";
    public static final String COL_PRIX = "prix";
    public static final String COL_DEGRE_ENVIE = "degreEnvie";
    public static final String COL_IS_ACHETE = "isAchete";

    public static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME +
            " ( " +
            COL_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NOM + " TEXT, " +
            COL_DESCRIPTION + " TEXT, " +
            COL_URL + " TEXT, " +
            COL_PRIX + " REAL, " +
            COL_DEGRE_ENVIE + " REAL, " +
            COL_IS_ACHETE + " INTEGER); ";

    public static final String DROP_TABLE = "DROP TABLE " + TABLE_NAME;
}
