package fr.univamu.iut.platsutilisateurs.domain;

/**
 * Entite metier representant un plat propose par l'entreprise
 * Couche : Enterprise Business Rules (centre de l'architecture propre)
 */
public class Plat {

    protected int id;
    protected String nom;
    protected String description;
    protected double prix;

    public Plat() {}

    public Plat(int id, String nom, String description, double prix) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    @Override
    public String toString() {
        return "Plat{id=" + id + ", nom='" + nom + "', description='" + description + "', prix=" + prix + '}';
    }
}
