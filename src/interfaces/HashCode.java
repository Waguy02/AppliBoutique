/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import outils.Outils;
/**
 *
 * @author LOIC KWATE DASSI
 */
public interface HashCode {
    default public String hash(String prefix, int index) {
        String ret = String.valueOf(index);
        int n = Outils.LONGUEURCODE - prefix.length() - ret.length(); 
        for(int i=0;i<n;i++) ret="0"+ret;
        String x = prefix + ret;
        x = x.substring(0, 3)+"-"+x.substring(3, x.length());
        return x;
    }
    public void genererCode();
}
