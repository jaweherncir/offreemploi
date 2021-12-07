package com.jaweher.offreemploi;

public class OffreEmploi {
    private  String nom;
    private  String num;
    private  String addresse;
    private  String description;
    private  String categorie;

    public OffreEmploi() {
    }

    public OffreEmploi(String nom, String num, String addresse, String description, String categorie) {
        this.nom = nom;
        this.num = num;
        this.addresse = addresse;
        this.description = description;
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public String getNum() {
        return num;
    }

    public String getAddresse() {
        return addresse;
    }

    public String getDescription() {
        return description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
