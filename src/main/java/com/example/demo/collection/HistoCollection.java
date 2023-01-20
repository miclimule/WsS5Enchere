package com.example.demo.collection;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "histo")
public class HistoCollection {
    @Id
    private ObjectId _id;

    private MaterielleCollection materielle;

    private JoueurCollection joueur;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public MaterielleCollection getMaterielle() {
        return materielle;
    }

    public void setMaterielle(MaterielleCollection materielle) {
        this.materielle = materielle;
    }

    public JoueurCollection getJoueur() {
        return joueur;
    }

    public void setJoueur(JoueurCollection joueur) {
        this.joueur = joueur;
    }
}