
package assembly;


import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 *
 * @author Mateus
 */
public class Enconder {
    public ArrayList<Number> Get(ArrayList<String> parserList, int PALAVRA, int RAM){//lista_parser
        //Nome -> INSTRUCAO/REGISTRADOR/INTEIRO(PRIMITIVO)/ENDEREÇO
        //Tipo -> O Filho Do Nome
        //Subtipo -> Se é Int ou Long
        ArrayList<Number> listaEnc = new ArrayList<>();
        for (String lista : parserList) {
            switch (lista) {
                case "mov":
                    listaEnc.add((byte)-50);
                    break;
                case "inc":
                    listaEnc.add((byte)-51);
                    break;
                case "imul":
                    listaEnc.add((byte)-52);
                    break;
                case "add":
                    listaEnc.add((byte)-53);
                    break;    
                case "A":
                    listaEnc.add((byte)-54);
                    break;
                case "B":
                    listaEnc.add((byte)-55);
                    break;
                case "C":
                    listaEnc.add((byte)-56);
                    break;
                case "D":
                    listaEnc.add((byte)-57);
                    break; 
                case "loop":
                    listaEnc.add((byte)-58);
                    break; 
                //>|<|==|!=|>=|<=   
                case ">":
                    listaEnc.add((byte)-59);
                    break;
                case "<":
                    listaEnc.add((byte)-60);
                    break;
                case "==":
                    listaEnc.add((byte)-61);
                    break;
                case ">=":
                    listaEnc.add((byte)-62);
                    break;
                case "<=":
                    listaEnc.add((byte)-63);
                    break;
                case "!=":
                    listaEnc.add((byte)-64);
                    break; 
                default:
                    switch (PALAVRA) {
                        case 16:
                            if(lista.startsWith("0x")){
                                short test = Short.parseShort(lista.substring(2,lista.length()), 16);
                                listaEnc.add(test);
                                if(test >= RAM/2){
                                    return null;
                                }
                                
                            }else{
                                listaEnc.add(Short.parseShort(lista));
                            }
                            break;
                        case 32:
                            if(lista.startsWith("0x")){
                                int test = Integer.parseInt(lista.substring(2,lista.length()), 16);
                                listaEnc.add(test);
                                if(test >= RAM/2){
                                    return null;
                                }
                            }else{
                                listaEnc.add(Integer.parseInt(lista));
                            }
                            break;
                        case 64:
                            if(lista.startsWith("0x")){
                                long test = Long.parseLong(lista.substring(2,lista.length()), 16);
                                listaEnc.add(test);
                                if(test >= RAM/2){
                                    return null;
                                }
                            }else{
                                listaEnc.add(Long.parseLong(lista));
                            }
                            break;
                    }  
            }
        }
        return listaEnc;
    }
    
}
