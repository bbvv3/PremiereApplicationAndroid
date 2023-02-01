package fr.eni.premiereapplicationandroid.bo;

public class Contact {

    private long id;
    private String nom;
    private String telephone;

    public Contact() {
    }

    public Contact(String nom, String prenom, String telephone) {
        this.nom = nom;
        this.telephone = telephone;
    }

    public Contact(int id, String nom, String prenom, String telephone) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
