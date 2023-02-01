package fr.eni.premiereapplicationandroid.bo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Article implements Serializable, Parcelable {

    private int id;
    private String nom;
    private String description;
    private String url;
    private float prix;
    private float degreEnvie;
    private byte isAchete;

    public Article() {
    }

    public Article(String nom, String description, String url, float prix, float degreEnvie, Boolean isAchete) {
        this.nom = nom;
        this.description = description;
        this.url = url;
        this.prix = prix;
        this.degreEnvie = degreEnvie;
        this.isAchete = (byte) (isAchete?1:0);
    }

    public Article(int id, String nom, String description, String url, float prix, float degreEnvie, Boolean isAchete) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.url = url;
        this.prix = prix;
        this.degreEnvie = degreEnvie;
        this.isAchete = (byte) (isAchete?1:0);
    }

    protected Article(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        description = in.readString();
        url = in.readString();
        prix = in.readFloat();
        degreEnvie = in.readFloat();
        isAchete = in.readByte();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nom);
        parcel.writeString(description);
        parcel.writeString(url);
        parcel.writeFloat(prix);
        parcel.writeFloat(degreEnvie);
        parcel.writeByte(isAchete);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getDegreEnvie() {
        return degreEnvie;
    }

    public void setDegreEnvie(float degreEnvie) {
        this.degreEnvie = degreEnvie;
    }

    public Boolean isAchete() {
        return (isAchete==1);
    }

    public void setAchete(Boolean isAchete) {
        this.isAchete = (byte) (isAchete?1:0);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", prix=" + prix +
                ", degreEnvie=" + degreEnvie +
                ", isAchete=" + isAchete +
                '}';
    }
}
