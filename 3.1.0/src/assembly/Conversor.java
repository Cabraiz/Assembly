/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assembly;

/**
 *
 * @author Mateus
 */
public class Conversor {
    public int INT (String k){
        int i = Integer.parseInt(k);
        return i;
    }
    public String STRING (int k){
        String i = Integer.toString(k);
        return i;
    }
}
