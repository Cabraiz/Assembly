
package assembly;

import Beans.BeansMaterial;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 *
 * @author Mateus
 */
public class Buffer {
    int MONTANTE;
    Bus bus = new Bus();
    //byte[] bytes = ByteBuffer.allocate(4).putInt(listaBuf).array();
    public Bus Array(ArrayList<Number> enconderList, int PALAVRA, Bus bus){
        //Todo Numero Vem Em Long
        //Nome Diz O tipo
        //Outro é o Valor
        ArrayList<Byte> bytesList = new ArrayList<>();
        //Se Não For Long Na Hora De Converter Um Numero Int Para Long Irá Dar Erro
        
        for (Number lista : enconderList) {
            if (lista instanceof Byte){
                bytesList.add((byte)lista);
            }else
            if (lista instanceof Short){
                byte[] bytes1 = ByteBuffer.allocate(2).putShort((short)lista).array();
                for (byte aByte : bytes1) {
                    bytesList.add(aByte);
                }
            }else
            if (lista instanceof Integer){
                byte[] bytes2 = ByteBuffer.allocate(4).putInt((int)lista).array();
                for (byte aByte : bytes2) {
                    bytesList.add(aByte);
                }
            }else
            if (lista instanceof Long){
               byte[] bytes3 = ByteBuffer.allocate(8).putLong((long)lista).array();
                for (byte aByte : bytes3) {
                    bytesList.add(aByte);
                } 
            }
        }
       MONTANTE = bytesList.size();
       for (Byte b : bytesList) {
           System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1));
       }
       BeansMaterial bm = new BeansMaterial(bytesList,bus);
       bus.Transport("BUFFER", "Ram", bm);
       return bus;
    }
}


            /*switch (PALAVRA) {
                case 16:
                    byte[] bytes1 = ByteBuffer.allocate(2).putShort((short)lista).array();
                    for (byte aByte : bytes1) {
                        bytesList.add(aByte);
                    }
                    break;
                case 32:
                    byte[] bytes2 = ByteBuffer.allocate(4).putInt((int)lista).array();
                    for (byte aByte : bytes2) {
                        bytesList.add(aByte);
                    }
                    break;
                case 64:
                    long longNumber;
                    byte[] bytes3 = ByteBuffer.allocate(8).putLong((long)lista).array();
                    for (byte aByte : bytes3) {
                        bytesList.add(aByte);
                    }
                    break;
                default:
                    break;
            }  
*/