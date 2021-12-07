package com.jaweher.offreemploi;

public class societe {
    private String nom;
    private String password;
    private String description;
    private String localisation;
    private String num;
    private String email;

    public societe() {
    }

    public societe(String nom, String password, String description, String localisation, String num, String email) {
        this.nom = nom;
        this.password = password;
        this.description = description;
        this.localisation = localisation;
        this.num = num;
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public String getNum() {
        return num;
    }

    public String getEmail() {
        return email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "societe{" +
                "nom='" + nom + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", localisation='" + localisation + '\'' +
                ", num='" + num + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
