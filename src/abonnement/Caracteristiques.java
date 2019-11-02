/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnement;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ESDRAS
 */
public class Caracteristiques implements Serializable{
    private Date dateEnregistrement;
    private Date dateFin;
    private int numeroAbonnement;
    public Caracteristiques(Date dateEnregistrement, Date dateFin, int numeroAbonnement) {
        this.dateEnregistrement = dateEnregistrement;
        this.dateFin = dateFin;
        this.numeroAbonnement = numeroAbonnement;
    }

    public Date getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(Date dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNumeroAbonnement() {
        return numeroAbonnement;
    }

    public void setNumeroAbonnement(int numeroAbonnement) {
        this.numeroAbonnement = numeroAbonnement;
    }
    
    
}
