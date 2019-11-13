/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.inputs;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author test
 */
public class AutoCompleteCombo<T> extends JFXComboBox {

    private ObservableList<T> data;

    private String targetProperty;

    public ObservableList<T> getData() {
        return data;
    }

    public void setData(ObservableList<T> data) {
        this.data = data;
    }

    public String getTargetProperty() {
        return targetProperty;
    }

    public void setTargetProperty(String targetProperty) {
        this.targetProperty = targetProperty;
    }

    
    public void configure() {
        this.setEditable(true);
        this.setItems(this.data);
        targetProperty = targetProperty.substring(0, 1).toUpperCase().concat(targetProperty.substring(1, targetProperty.length() ));
        String getter = "get" + targetProperty;
        String setter = "set" + targetProperty;
   this.setEditable(true);  
   
   
   
        System.out.println("Getter : "+getter +" - ----  -"+"Setter : "+setter);
     
        this.setOnKeyReleased(event-> this.handle(event));
        this.setTooltip(new Tooltip());
        
      

    }
    public AutoCompleteCombo(ObservableList<T> data){
        this("nom",data);
        
        
    }
    
    public AutoCompleteCombo(String targetProperty, ObservableList<T> data){
        
        this.targetProperty=targetProperty;
        this.data=data;
        this.configure();
        this.getStyleClass().add("defaultCombo");
        
    }
    
    
    
    public void handle(KeyEvent event) {
        if(this.getEditor().getText().isEmpty()){
            this.hide();
            return;
        }
      if (event.getCode() == KeyCode.DOWN){
        if(!this.isShowing())this.show();
        return;
      }

        if (event.getCode().isModifierKey()||event.getCode().isNavigationKey()||event.getCode().isFunctionKey()){
          return;
        }

       this.hide();

        if (event.getCode() == KeyCode.ENTER||event.getCode() == KeyCode.TAB){
            return;
        }
        if (event.getCode() ==KeyCode.ESCAPE||event.getCode() ==KeyCode.DELETE){
         this.getEditor().setText("");
         this.setValue(null);
         this.setItems(data);
          return;
        }
        if(event.getCode() == KeyCode.BACK_SPACE) {
            moveCaretToPos = true;
            caretPos =this.getEditor().getCaretPosition();
        }



        ObservableList<T> list = FXCollections.observableArrayList();
        if(data!=null){
          for (int i=0; i<data.size(); i++) {
            if(data.get(i).toString().toLowerCase().contains(
                this.getEditor().getText().toLowerCase())) {
              list.add(data.get(i));
            }
          }
        }
        String t =this.getEditor().getText();
       this.setItems(list);
       this.getEditor().setText(t);
        if(!moveCaretToPos) {
            caretPos = -1;
        }
        moveCaret(t.length());
        if(!list.isEmpty()) {
           this.show();
        }
        else{
            this.getEditor().clear();
        }
    }
     private boolean moveCaretToPos = false;
    private int caretPos;


    private void moveCaret(int textLength) {
        if(caretPos == -1) 
           this.getEditor().positionCaret(textLength);
        else 
           this.getEditor().positionCaret(caretPos);

        moveCaretToPos = false;
    }

}


    
    
    
    
    
    
    
    

    
    

    

