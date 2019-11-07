/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.table;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 *
 * @author test
 */
public class CustomActionFactories {
    
    
    
    public static <T> Callback< TableColumn<T, Void>, TableCell<T,Void>> removeActionCallback(TableView table)
    {;
   Callback<TableColumn<T, Void>, TableCell<T, Void>> cellFactory=new Callback<TableColumn<T, Void>, TableCell<T, Void>>() {
           
       

       @Override
       public TableCell<T,Void> call(final TableColumn param) {
           
                final TableCell<T,Void> cell = new TableCell<T,Void>() {
                    
                    final JFXButton btn = new JFXButton("Retirer");
                    
                     @Override
                    public void updateItem(Void item, boolean empty) {
                        
                        
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Object current = table.getItems().get(getIndex());
                                table.getItems().remove(current);
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
    return cellFactory;
    }
    
    
    
    
   

    
    

      















}
