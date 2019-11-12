/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.utilities;

import application.partials.table.CustomColumn;
import application.partials.table.CustomSimpleColumn;
import constantes.Constantes;
import dbManager.Manager;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Caissier;
import model.Client;
import model.Facture;
import model.LigneFacture;
import model.Produit;

/**
 *
 * @author test
 */
public class TableViewManager {

    public static void addTableColumns(TableView table, CustomColumn... columns) {
        for (CustomColumn column : columns) {
            column.setResizable(false);
            table.getColumns().add(column);
            if (column.getWidthPercentage() != null) {
                column.minWidthProperty().bind(table.widthProperty().multiply(column.getWidthPercentage() * 0.01));

            }
        }

    }

    public static void permuteColumn(TableView table, int index1, int index2) {
        boolean invalidOperation = index1 >= table.getColumns().size() || index2 >= table.getColumns().size() || index1 < 0 || index2 < 0 || index1 == index2;
        if (invalidOperation) {
            return;
        }

        CustomSimpleColumn col1 = (CustomSimpleColumn) table.getColumns().get(index1);
        CustomSimpleColumn col2 = (CustomSimpleColumn) table.getColumns().get(index2);

        table.getColumns().set(index1, col2);
        table.getColumns().set(index2, col1);

    }

    public static void BindCol(TableColumn col, String propertyName) {

        col.setCellValueFactory(new PropertyValueFactory<>(propertyName));

    }

    public static void permuteColumn(TableView table, CustomSimpleColumn col1, CustomSimpleColumn col2) {
        
    }

    public static void enableProductSimpleFiltering(TableView table, ObservableList list, TextField searchBar) {

        FilteredList flListe = new FilteredList(list, p -> true);

        searchBar.textProperty().addListener(((observable, oldValue, newValue) -> {

            Predicate p0 = p -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                Produit prod = (Produit) p;
                if ((prod).getNom().toLowerCase().contains(lowerCaseFilter)
                        || (prod).getId().toLowerCase().contains(lowerCaseFilter)
                        || (prod).getCategorie().getId().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;

            };
            flListe.setPredicate(p0);

        }));

        SortedList<Produit> sortedData = new SortedList<>(flListe);
        table.setItems(sortedData);

    }

    public static void enableFactureFiltering(TableView table, ObservableList<Facture> list, Produit prod, Caissier ca, Client cli, String retard, String paye, Integer jours) {
        FilteredList flListe = new FilteredList(list, p -> true);
        Predicate pred = p -> true;
        if (prod != null) {
            Predicate p0 = p -> {
                Facture fact = (Facture) p;
                ObservableList<LigneFacture> ligne = fact.ligneFactures(Manager.em);
                for (LigneFacture lf : ligne) {
                    if (prod.getId().equals(lf.getLigneFactureId().getProduitId())) {
                        return true;
                    }
                }
                return false;

            };
            pred.and(p0);
        }
        if (ca != null) {
            Predicate p0 = p -> {
                Facture fact = (Facture) p;
                if (ca.getId().equals(fact.getEmployeId())) {
                    return true;
                }
                return false;
            };
            pred.and(p0);
        }
        if (cli != null) {
            Predicate p0 = p -> {
                Facture fact = (Facture) p;
                if (cli.getId().equals(fact.getClientId())) {
                    return true;
                }
                return false;
            };
            pred.and(p0);
        }
        if (!retard.equals(Constantes.TOUS)) {
            Predicate p0 = p -> {
                Facture fact = (Facture) p;
                switch (retard) {
                    case (Constantes.RETARD):
                        if (fact.getDateReglement().before(new Date())) {
                            return true;
                        }
                        return false;
                    case (Constantes.OK):
                        if (fact.getDateReglement().after(new Date())) {
                            return true;
                        }
                        return false;
                    default:
                        return false;
                }

            };
            pred.and(p0);
        }
        if (!paye.equals(Constantes.TOUS)) {
            Predicate p0 = p -> {
                Facture fact = (Facture) p;
                switch (paye) {
                    case (Constantes.PAYE):
                        if (fact.isPaye()) {
                            return true;
                        }
                        return false;
                    case (Constantes.IMPAYE):
                        if (!fact.isPaye()) {
                            return true;
                        }
                        return false;
                    default:
                        return false;
                }
            };
            pred.and(p0);
        }
        if (jours != null) {
            Predicate p0 = p -> {
                Facture fact = (Facture) p;
                Calendar date1 = Calendar.getInstance();
                Calendar date2 = Calendar.getInstance();
                date1.setTime(fact.getDateReglement());
                date2.setTime(new Date());
                date1.add(Calendar.DATE, jours);
                if(date1.getTimeInMillis() - date2.getTimeInMillis() > 0){
                    return true;
                }
                return false;
            };
            pred.and(p0);
        }
        flListe.setPredicate(pred);
        SortedList<Facture> sortedData = new SortedList<>(flListe);
        table.setItems(sortedData);

    }

    public static void showStage(String title, Boolean resize, FXMLLoader loader) {
        try {
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setResizable(resize);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TableViewManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
