
package assembly;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateus
 */
public class ZLeitor {
    public ArrayList<String> ler(String k){
        ArrayList<String> lista = new ArrayList<>();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(k));
        
        String str;

        while((str = in.readLine()) != null){
            lista.add(str);
        }

        String[] stringArr = lista.toArray(new String[0]);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ZLeitor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ZLeitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
