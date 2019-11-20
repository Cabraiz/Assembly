/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Descartavel;

/**
 *
 * @author Mateus
 */
public class CPU_ALPHA {
    //bus.Transport(acess, go, bm);
        
        
        /*
        //Se adiciona o que precisa no barramento, então ele separa pelo seu tamanho para passar para o que se pede
        int BARRA;   
        int MONTANTE;
        int PALAVRA;
        RAM ram = new RAM();
        CPU cpu = new CPU();
        public void BUFFER_RAM(ArrayList<Byte> bufferList, int montante){
            MONTANTE = montante;
            int inicio = cpu.PONTEIRO;
            System.out.println("INITIALIAZE <BUFFER_RAM>");
            double porcentagem = bufferList.size();
            while(!bufferList.isEmpty()){
                for (int i = 0; i < BARRA/8; i++) {
                    Delay();
                    if(!bufferList.isEmpty()){
                        if(inicio == ram.RAMList.size()){
                            inicio = 0;
                        }
                        ram.RAMList.set(inicio,bufferList.get(0));
                        bufferList.remove(0);
                        inicio++;
                    }
                }
            }
            System.out.println();
            RAM_CPU();
        }
        public void RAM_CPU(){
            //BEGIN.RAM.READ()
            int inicio = cpu.PONTEIRO;
            ArrayList<Byte> temporary = new ArrayList<>();
            System.out.println("INITIALIAZE <RAM_CPU>");
            System.out.println("");
            //O Começo Convém Do Ponteiro e O Tamanho Dito Convém Do Buffer
            for (int j = 0; j < MONTANTE; j++) {
                if(inicio == ram.RAMList.size()){
                    inicio = 0;
                }
                temporary.add(ram.RAMList.get(inicio));
                inicio++;
            }
            //Array Temporario Para Levar Até O Decoder
            System.out.println("Read: " + temporary);
            while(!temporary.isEmpty()){
                for (int i = 0; i < BARRA/8; i++) {
                    Delay();
                    if(!temporary.isEmpty()){
                        cpu.listaDecoder.add(temporary.get(0));
                        temporary.remove(0);
                    }
                }
            }
            //END.RAM,READ()
            cpu.Decoder(MONTANTE , ram.RAMList.size());
            BeansRam br = cpu.CPU(ram.enderecoList);
            //BEGIN.RAM.GET()
            Number n = 0;
            switch(PALAVRA){
                case 16:
                    n     = (short) 0;
                    break;
                case 32:
                    n     =  0;
                    break;
                case 64:
                    n     = (long) 0;
                    break; 
            }
            byte[] writeList;
            boolean existe = false;
            if(br.isValid()){
                for (BeansRam brList : ram.enderecoList) {
                    if (brList.getNome().equalsIgnoreCase(br.getNome())) {
                        byte[] b = brList.getValor();
                        n = cpu.ByteToNumber(b);
            //END.RAM.GET()

            //BEGIN.RAM.WRITE()
                        writeList = cpu.RAM(n); 
                        existe = true;
                        ram.Write(new BeansRam(br.getNome(), writeList, writeList.length + 1), true);
                    }
                }
                //Checa Se Já Existe Ou Não 
                if(!existe){
                    writeList = cpu.RAM(n); 
                    ram.Write(new BeansRam(br.getNome(), writeList,     writeList.length + 1), false);
                }else{
                    existe = false;
                }
            //END.RAM.WRITE()    
                System.out.println("RegistradorList: " + cpu.registradorList);
                int totalsize = 0;
                for (BeansRam bear : ram.enderecoList) {
                    System.out.println("Ram: " + bear.getNome() + ": " + cpu.ByteToNumber(bear.getValor()) + " Size: " + bear.getSize());
                    totalsize = totalsize + bear.getSize();
                }
                System.out.println("TotalSize: " + totalsize);
                cpu.MaximumAmountOfMemory(totalsize);
            }

        }
        */
    
    
    
    
    
    
    
    
    
    
    /*
    
    public void Registrador_Registrador(String nome, String valor){
        switch (nome){
            case "A":
                Registrador_Registrador_02(Numero_Referencia(nome),Numero_Referencia(nome));
                break;
            case "B":
                Registrador_Registrador_02(Numero_Referencia(nome),Numero_Referencia(nome));
                break;
            case "C":
                Registrador_Registrador_02(Numero_Referencia(nome),Numero_Referencia(nome));
                break;
            case "D":
                Registrador_Registrador_02(Numero_Referencia(nome),Numero_Referencia(nome));
                break;
        }
    }
    
     public void Registrador_Registrador_02(int R1 , int R2){
         switch (PALAVRA){            
            case 16:
                registradorShort[R1] = registradorShort[R2];
                break;
            case 32:
                registradorInt[R1]   =  registradorShort[R2];
                break;
            case 64:
                registradorLong[R1]  = registradorShort[R2];
                break;
        }
     }
    
    public void Registrador_Inteiro(String nome, String valor){
        switch (nome){
            case "A":
                Registrador_Inteiro_02(0,Long.parseLong(valor));
                break;
            case "B":
                Registrador_Inteiro_02(1,Long.parseLong(valor));
                break;
            case "C":
                Registrador_Inteiro_02(2,Long.parseLong(valor));
                break;
            case "D":
                Registrador_Inteiro_02(3,Long.parseLong(valor));
                break;
        }
    }
    
    public void Registrador_Inteiro_02(int nome , long valor){
        switch (PALAVRA){            
            case 16:
                registradorShort[nome] = (short)valor;
                break;
            case 32:
                registradorInt[nome]   =  (int)valor;
                break;
            case 64:
                registradorLong[nome]  = (long)valor;
                break;
        }
    }
    
    public void RegistradorMaisUnd(String nome){
        switch (nome){
            case "A":
                RegistradorMaisUnd_02(0);
                break;
            case "B":
                RegistradorMaisUnd_02(1);
                break;
            case "C":
                RegistradorMaisUnd_02(2);
                break;
            case "D":
                RegistradorMaisUnd_02(3);
                break;
        }
    }
    
    public void RegistradorMaisUnd_02(int nome){
        switch (PALAVRA){
            case 16:
                registradorShort[nome]++;
                break;
            case 32:
                registradorInt[nome]++;
                break;
            case 64:
                registradorLong[nome]++;
                break;
        }
    }
    
    public void RegistradorMultiplicador(String x, String y ,String z){
        switch (x){
            case "A":
                RegistradorMultiplicador_02(0, Integer.parseInt(y), Integer.parseInt(z));
                break;
            case "B":
                RegistradorMultiplicador_02(1, Integer.parseInt(y), Integer.parseInt(z));
                break;
            case "C":
                RegistradorMultiplicador_02(2, Integer.parseInt(y), Integer.parseInt(z));
                break;
            case "D":
                RegistradorMultiplicador_02(3, Integer.parseInt(y), Integer.parseInt(z));
                break;
        }
    }
    
    public void RegistradorMultiplicador_02(int x, int y ,int z){
        switch (PALAVRA){
            case 16:
                registradorShort[x] = (short) (registradorShort[y] * registradorShort[z]);
                break;
            case 32:
                registradorInt[x]   = (int)   (registradorInt[y]   * registradorInt[z]);
                break;
            case 64:
                registradorLong[x]  = (long)  (registradorLong[y]  * registradorLong[z]);
                break;
        }
    }
    */
    
    
    
    
    
    
    /*
    ArrayList<String> hexaList = new ArrayList<>();
    ArrayList<Byte> intList = new ArrayList<>();
    boolean hexAct = true;
    public String Hexa_Int(byte b){
        String hex = "";
        if(hexAct){
            if(!hexaList.isEmpty()){
                if(b == 0){
                    hexAct = false;
                    for (String str : hexaList) {
                        hex = hex + str;
                    }
                    while(hex.length() < PALAVRA/4){
                        hex = "0" + hex;
                    }
                    
                    hex = "0x" + hex;
                    return hex;
                }else{
                    hex =  String.format("%02x", b);
                    hexaList.add(hex);
                }
            }
            if(b != 0 && hexaList.isEmpty()){
                hex =  String.format("%02x", b);
                hexaList.add(hex);
            }
        }
        else{
            if(!intList.isEmpty()){
                if(b == 0){
                    int Int = 0;
                    if(PALAVRA == 16){
                        int shortNumber = 0;
                        int cont = intList.size()-1;
                        for (byte byte1 : intList) {
                            shortNumber += ((short) byte1 & 0xff) << (8 * cont);
                            cont--;
                        }
                        return String.valueOf(shortNumber);
                    }
                    if(PALAVRA == 32){
                        int intNumber = 0;
                        int cont = intList.size()-1;
                        for (byte byte1 : intList) {
                            intNumber += ((int) byte1 & 0xff) << (8 * cont);
                            cont--;
                        }
                        return String.valueOf(intNumber);
                    }
                    if(PALAVRA == 64){
                        //Reverter Operação (5 Hrs.)
                        long longNumber = 0;
                        int cont = intList.size()-1;
                        for (byte byte1 : intList) {
                            longNumber += ((long) byte1 & 0xff) << (8 * cont);
                            cont--;
                        }
                        return String.valueOf(longNumber);
                    }
                    return hex;
                }else{
                    intList.add(b);
                }
            }
            if(b != 0 && intList.isEmpty()){
                intList.add(b);
            }
        }
        return ""; 
    }
    */


}
