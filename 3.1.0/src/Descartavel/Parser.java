/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package Descartavel;
import java.util.ArrayList;

/**
 *
 * @author Mateus
 */
public class Parser {
    ArrayList<String> variaveis_list = new ArrayList();
    public boolean Tamanho (String k){//Checa Se o Tamanho é maior que o mínimo possível
        if(k.length() > 7)
            return true;
        System.err.println("INVALIDO <ENTRADA.CURTA>");
        return false;
    }
    public ArrayList<String>  Instrucao(String k, ArrayList<String> lista_k){
        boolean valido_01 = false;
        for (String letter : lista_k) {
            if(k.startsWith(letter + " ")){
                valido_01 = true;//Significa que ele contêm os 4 principais instruções
                variaveis_list.add(letter);//Add Variavel
                k = k.substring(letter.length() + 1,k.length());//Vai remover a intrução achada
                System.out.println(k);
                    
                while (k.contains(",")) {
                    String aux = k.substring(0, k.indexOf(','));//Variavel auxiliar recebe temporariamente a string até ANTES do COMMA
                    variaveis_list.add(aux);
                    k = k.substring(aux.length(), k.length());// Redux até onde foi encontrado o COMMA
                    if(k.startsWith(", "))
                        k = k.substring(2, k.length());
                } 
                variaveis_list.add(k);
            }
        }
        if(!valido_01){
            System.err.println("INVALIDO <ENTRADA.MISS.TRUE-EXPRESSION>");
            variaveis_list.clear();
        }
        return variaveis_list;
    }
}
