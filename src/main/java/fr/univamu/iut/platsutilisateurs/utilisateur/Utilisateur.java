package fr.univamu.iut.platsutilisateurs.utilisateur;

/**
 * Classe representant un utilisateur abonne au service
 */
public class Utilisateur {

    /**
     * Identifiant de l'utilisateur
     */
    protected int id;

    /**
     * Nom de l'utilisateur
     */
    protected String nom;

    /**
     * Prenom de l'utilisateur
     */
    protected String prenom;

    /**
     * Email de l'utilisateur
     */
    protected String email;

    /**
     * Adresse de l'utilisateur
     */
    protected String adresse;

    /**
     * Constructeur par defaut
     */
    public Utilisateur() {
    }

    /**
     * Constructeur d'utilisateur
     * @param id identifiant de l'utilisateur
     * @param nom nom de l'utilisateur
     * @param prenom prenom de l'utilisateur
     * @param email email de l'utilisateur
     * @param adresse adresse de l'utilisateur
     */
    public Utilisateur(int id, String nom, String prenom, String email, String adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
    }

    /**
     * Methode permettant d'acceder a l'identifiant de l'utilisateur
     * @return l'identifiant de l'utilisateur
     */
    public int getId() {
        return id;
    }

    /**
     * Methode permettant de modifier l'identifiant de l'utilisateur
     * @param id l'identifiant a utiliser
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Methode permettant d'acceder au nom de l'utilisateur
     * @return le nom de l'utilisateur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Methode permettant de modifier le nom de l'utilisateur
     * @param nom le nom a utiliser
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Methode permettant d'acceder au prenom de l'utilisateur
     * @return le prenom de l'utilisateur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Methode permettant de modifier le prenom de l'utilisateur
     * @param prenom le prenom a utiliser
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Methode permettant d'acceder a l'email de l'utilisateur
     * @return l'email de l'utilisateur
     */
    public String getEmail() {
        return email;
    }

    /**
     * Methode permettant de modifier l'email de l'utilisateur
     * @param email l'email a utiliser
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Methode permettant d'acceder a l'adresse de l'utilisateur
     * @return l'adresse de l'utilisateur
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Methode permettant de modifier l'adresse de l'utilisateur
     * @param adresse l'adresse a utiliser
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}
