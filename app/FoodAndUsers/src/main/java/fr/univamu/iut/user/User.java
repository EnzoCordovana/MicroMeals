package fr.univamu.iut.user;

/**
 * Classe representant un abonne (utilisateur)
 */
public class User {

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
     * Adresse email de l'utilisateur (unique)
     */
    protected String email;

    /**
     * Adresse postale de l'utilisateur
     */
    protected String adresse;

    /**
     * Constructeur par defaut
     */
    public User() {
    }

    /**
     * Constructeur d'un utilisateur
     * @param nom nom de l'utilisateur
     * @param prenom prenom de l'utilisateur
     * @param email adresse email de l'utilisateur
     * @param adresse adresse postale de l'utilisateur
     */
    public User(String nom, String prenom, String email, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getAdresse() { return adresse; }

    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}
