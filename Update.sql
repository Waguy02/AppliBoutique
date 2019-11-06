USE supermarche;
CREATE TABLE remise(idClient varchar(15) NOT NULL, idProduit varchar(15) NOT NULL, remise float, dateDebut date, dateFin date,
  PRIMARY KEY(idClient, idProduit), FOREIGN KEY (idClient) REFERENCES Client(id), FOREIGN KEY (idProduit) REFERENCES Produit(id));
CREATE TABLE versement(idVersement varchar(15) NOT NULL, idProduit varchar(15) NOT NULL, idFacture varchar(15) NOT NULL,
  montant int(11), PRIMARY KEY(idFacture, idProduit), FOREIGN KEY (idProduit) REFERENCES Produit(id), FOREIGN KEY (idFacture) REFERENCES Facture(id));
ALTER TABLE produitFournisseur ADD prixAchat int(10);
ALTER TABLE produitFournisseur ADD quantite int(10);
ALTER TABLE versement ADD dateVersement date;
