package fr.univamu.iut.platsutilisateurs.plat;

/**
 * Classe representant un plat propose par l'entreprise
 */
public class Plat {

    /**
     * Identifiant du plat
     */
    protected int id;

    /**
     * Nom du plat
     */
    protected String nom;

    /**
     * Description du plat
     */
    protected String description;

    /**
     * Prix du plat en euros
     */
    protected double prix;

    /**
     * Constructeur par defaut
     */
    public Plat() {
    }

    /**
     * Constructeur de plat
     * @param id identifiant du plat
     * @param nom nom du plat
     * @param description description du plat
     * @param prix prix du plat
     */
    public Plat(int id, String nom, String description, double prix) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }

    /**
     * Methode permettant d'acceder a l'identifiant du plat
     * @return l'identifiant du plat
     */
    public int getId() {
        return id;
    }

    /**
     * Methode permettant de modifier l'identifiant du plat
     * @param id l'identifiant a utiliser
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Methode permettant d'acceder au nom du plat
     * @return une chaine de caracteres avec le nom du plat
     */
    public String getNom() {
        return nom;
    }

    /**
     * Methode permettant de modifier le nom du plat
     * @param nom le nom a utiliser
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Methode permettant d'acceder a la description du plat
     * @return une chaine de caracteres avec la description du plat
     */
    public String getDescription() {
        return description;
    }

    /**
     * Methode permettant de modifier la description du plat
     * @param description la description a utiliser
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Methode permettant d'acceder au prix du plat
     * @return le prix du plat en euros
     */
    public double getPrix() {
        return prix;
    }

    /**
     * Methode permettant de modifier le prix du plat
     * @param prix le prix a utiliser
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Plat{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                '}';
    }
}
