/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erreurs;

/**
 *
 * @author LOIC KWATE DASSI
 */
public class QuantiteInsuffisanceException extends Exception{
    
    public QuantiteInsuffisanceException() {
        super("quantite insuffisante en stock");
    }
}
