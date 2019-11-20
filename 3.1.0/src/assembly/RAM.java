
package assembly;

import java.util.ArrayList;

/**
 *
 * @author Mateus
 */
public class RAM {
    int SIZE;
    ArrayList<Byte> RAMList = new ArrayList<>();
    int inicial;
    public void WriteMemory(ArrayList<Byte> escreverList,int PONTEIRO){
        /*
        inicial = PONTEIRO + SIZE/2;
        //inicial = PONTEIRO;
        for (int i = 0; i < escreverList.size(); i++) {
            if(inicial == SIZE){
                inicial = SIZE/2;
                //inicial = 0;
            }
            RAMList.set(inicial,escreverList.get(i));
            inicial++;
        }
        */
        if(PONTEIRO < SIZE/2){
            inicial = PONTEIRO;
            for (int i = 0; i < escreverList.size(); i++) {
                if(inicial == SIZE/2){
                    inicial = 0;
                }
                RAMList.set(inicial,escreverList.get(i));
                inicial++;
            }
        }else{
            inicial = PONTEIRO;
            for (int i = 0; i < escreverList.size(); i++) {
                if(inicial == SIZE){
                    inicial = SIZE/2;
                }
                RAMList.set(inicial,escreverList.get(i));
                inicial++;
            }
        }
    }
    
    public ArrayList<Byte> Read(int PONTEIRO,int TOTAL){
        /*
        ArrayList<Byte> busList = new ArrayList<>();
        inicial = PONTEIRO + SIZE/2;
        //inicial = PONTEIRO;S
        for (int i = 0; i < TOTAL; i++) {
            if(inicial == SIZE){
                inicial = SIZE/2;
                //inicial = 0;
            }
            busList.add(RAMList.get(inicial));
            inicial++;
        }
        */
        ArrayList<Byte> busList = new ArrayList<>();
        if(PONTEIRO < SIZE/2){
            inicial = PONTEIRO;
            for (int i = 0; i < TOTAL; i++) {
                if(inicial == SIZE/2){;
                    inicial = 0;
                }
                busList.add(RAMList.get(inicial));
                inicial++;
            }
        }else{
            inicial = PONTEIRO;
            for (int i = 0; i < TOTAL; i++) {
                if(inicial == SIZE){
                    inicial = SIZE/2;
                }
                busList.add(RAMList.get(inicial));
                inicial++;
            }
        }
        
        
        return busList;
    }
    
    public void FILL(int RAM){
        while(RAMList.size() < RAM){
            RAMList.add((byte) 0);
        }
    }
    /*
    public void Write(BeansRam br, boolean sobrescrever){
        if(sobrescrever){
            for (BeansRam beansRam : enderecoList) {
                if(beansRam.getNome() == br.getNome()){
                    System.out.println("assembly.RAM.Write()VDD");
                    beansRam = br;
                }
            }
        }else{
            System.out.println("assembly.RAM.Write()FALSO");
            enderecoList.add(br);
        }
    }
*/
    
    

   
    
}

