
package assembly;

import Beans.BeansMaterial;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateus
 */
public class Bus{
    int SIZE;
    ArrayList<Byte>  bufferTransferring = new ArrayList<>();
    int TIME;
    public Object Transport(String acess, String go,Object obj){
        BeansMaterial bm;
        RAM ram;
        switch (acess){
            case "BUFFER": 
                //Buffer vai passar a lista para a RAM
                bm = (BeansMaterial) obj;
                ArrayList bufferList   = bm.getBufferList();
                bufferTransferring = bufferList;
                Bus bus = bm.getBus();
                for (int i = 0; i < bufferTransferring.size(); ) {
                    i = i + SIZE;
                    Delay();
                }
                return bus;
            case "RAM":
                bm = (BeansMaterial) obj;
                ram = bm.getRam();
                ArrayList<Byte> ramList = new ArrayList<>();
                //LENDO A RAM
                ramList = ram.Read(bm.getPONTEIRO(), bm.getTAMANHOINSTRUCAO());
                for (int i = 0; i < ramList.size(); ) {
                    i = i + SIZE;
                    Delay();
                }
                return ramList;
            case "CPU":
                switch (go){
                    case "AcessarRam":
                        bm = (BeansMaterial) obj;
                        ram = bm.getRam();
                        ArrayList<Byte> ramUnd = ram.Read(bm.getLocalizacaoUndRam(), 1);
                        return ramUnd;
                    case "EscreverRam":  
                        //(Endereco, bytesAlocados, ram)
                        bm = (BeansMaterial) obj;
                        ram = bm.getRam();
                        ram.WriteMemory(bm.getBytesAlocados(), bm.getEndereco());
                        return ram;
                }
                
        }
        return null;
    }
    public void Delay(){
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
    
    
    
    
    
    //0xff represents the value -1
    
    /*public void BusSend() {
        byte bytes = 0;
        short shorts = 0;
        int inteiro = 0;
        //Checa Tamanho Do Barramento Para Saber Quanto Ele Suporta
        if(BARRA/8 == 1){
            //bytes = bufferList.get(0);
            //bufferList.remove(0);
        }
        if(BARRA/8 == 2){
            byte hi;
            byte lo;
            hi = GetFirst();
            lo = GetFirst();
            shorts = (short)(((hi&0xFF)<<8) | (lo&0xFF));
        }
        if(BARRA/8 == 4){
            byte al = GetFirst();
            byte be = GetFirst();
            byte ga = GetFirst();
            byte de = GetFirst();
            inteiro = (0x000000ff & (al << 0)) |
            (0x0000ff00 & (be << 8)) |
            (0x00ff0000 & (ga << 16)) |
            (0xff000000 & (de << 24));
        }
    }*/
    
    
    
    
    /*
       1.read the byte:
        xxxxxxxx
       2.expand the byte into an int:
        000000000000000000000000xxxxxxxx        
       3.shift the bits to the left by 16 slots:
        00000000xxxxxxxx0000000000000000
       4.finally, push this through an AND mask which lets only the x bits through. 
         This is needed because byte is signed, so the conversion to int may actually result in the following:
        111111111111111111111111xxxxxxxx
    */
    
    /*
                                11111111 (first byte read)
                        22222222         (second byte read)
                33333333                 (third byte read)
        44444444                         (fourth byte read)
    */
    
    
//    public static void main(String args[]) {
//      int a = 60;	/* 60 = 0011 1100 */
//      int b = 13;	/* 13 = 0000 1101 */
//      int c = 0;
//
//      c = a & b;        /* 12 = 0000 1100 */
//      System.out.println("a & b = " + c );
//
//      c = a | b;        /* 61 = 0011 1101 */
//      System.out.println("a | b = " + c );
//
//      c = a ^ b;        /* 49 = 0011 0001 */
//      System.out.println("a ^ b = " + c );
//
//      c = ~a;           /*-61 = 1100 0011 */
//      System.out.println("~a = " + c );
//
//      c = a << 2;       /* 240 = 1111 0000 */
//      System.out.println("a << 2 = " + c );
//
//      c = a >> 2;       /* 15 = 1111 */
//      System.out.println("a >> 2  = " + c );
//
//      c = a >>> 2;      /* 15 = 0000 1111 */
//      System.out.println("a >>> 2 = " + c );
//   }

