    
package assembly;

import Beans.BeansCache;
import Beans.BeansListaCPU;
import Beans.BeansMaterial;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateus
 */
public class CPU {
    int PALAVRA;
    int BARRA;
    int PONTEIRO = 0;
    int RAMSIZE;
    int TAMANHOINSTRUCAO;
    ArrayList<String> listaCPU        = new ArrayList<>();
    ArrayList<Number> registradorList = new ArrayList<>();
    Bus bus = new Bus();
    RAM ram = new RAM();
   
    ArrayList<Byte>   listaDecoder = new ArrayList<>();
    ArrayList<Byte>   cacheList    = new ArrayList<>();
    
    //MemoryCacheAuxiliadores
    int SerializadorNumber;
    ArrayList<BeansCache> listaCache = new ArrayList<>();
    ArrayList<BeansCache> listaCacheShowAux = new ArrayList<>();
    ArrayList<Byte>       Auxiliador     = new ArrayList<>();
    int MemoryCacheSize;
    int hit   = 0;
    int Nohit = 0;
    
    //LoopAxiliadores
    int codJump;
    ArrayList<BeansListaCPU> BLLCPU      = new ArrayList<>();
    boolean Isloop  = false;
    boolean IsloopHappen  = false;
    
    //5:1 Function
    int UpdateLimiteRAM = 0;
    
    
    public void CPU(){
        String posicao01;
        String posicao02;
        String posicao03;
        //RAM
        
        switch (listaCPU.get(0)) {
            case "mov":
                posicao01 = Switch(listaCPU.get(1));
                posicao02 = Switch(listaCPU.get(2));
                //Nulo Significa Que Não É Um Registrador
                if(posicao01 != null){
                    //Registrador
                    Atualizar_Registrador(Numero_Referencia(listaCPU.get(1)),RegistradorOuLiteral(listaCPU.get(2)));
                }else{
                    //Endereço 
                    AcessEndereco();
                }
                break;
            case "inc":
                posicao01 = Switch(listaCPU.get(1));
                if(posicao01 != null){
                    switch(PALAVRA){
                    case 16:
                        short sh = (short)RegistradorOuLiteral(listaCPU.get(1));
                        sh = (short)(sh + 1);
                        Atualizar_Registrador(Numero_Referencia(listaCPU.get(1)),sh);
                        break;
                    case 32:
                        Atualizar_Registrador(Numero_Referencia(listaCPU.get(1)),((int)RegistradorOuLiteral(listaCPU.get(1))) +1);
                        break;
                    case 64:
                        Atualizar_Registrador(Numero_Referencia(listaCPU.get(1)),((long)RegistradorOuLiteral(listaCPU.get(1))) +1);
                        break;
                    }
                }else{
                    //Endereço
                    AcessEndereco();
                }
                break;
            case "imul":
                posicao01 = Switch(listaCPU.get(1));
                if(posicao01 != null){
                    switch(PALAVRA){
                        case 16:
                            short sh1 = (short)RegistradorOuLiteral(listaCPU.get(2));
                            short sh2 = (short)RegistradorOuLiteral(listaCPU.get(3));
                            sh1 = (short)(sh1 * sh2);
                            Atualizar_Registrador(Numero_Referencia(listaCPU.get(1)),(sh1));
                            break;
                        case 32:
                            Atualizar_Registrador(Numero_Referencia(listaCPU.get(1)),((int)RegistradorOuLiteral(listaCPU.get(2))) * ((int)RegistradorOuLiteral(listaCPU.get(3))));
                            break;
                        case 64:
                            Atualizar_Registrador(Numero_Referencia(listaCPU.get(1)),((long)RegistradorOuLiteral(listaCPU.get(2))) * ((long)RegistradorOuLiteral(listaCPU.get(3))));
                            break;
                    }
                }else{
                   //Endereço
                   AcessEndereco();
                }
                break;
            case "add":
                posicao01 = Switch(listaCPU.get(1));
                if(posicao01 != null){
                    switch(PALAVRA){
                        case 16:
                            short sh1 = (short)RegistradorOuLiteral(listaCPU.get(1));
                            short sh2 = (short)RegistradorOuLiteral(listaCPU.get(2));
                            sh1 = (short)(sh1 + sh2);
                            Atualizar_Registrador(Numero_Referencia(listaCPU.get(1)),(sh1));
                            break;
                        case 32:
                            Atualizar_Registrador(Numero_Referencia(listaCPU.get(1)),((int)RegistradorOuLiteral(listaCPU.get(1))) + ((int)RegistradorOuLiteral(listaCPU.get(2))));
                            break;
                        case 64:
                            Atualizar_Registrador(Numero_Referencia(listaCPU.get(1)),((long)RegistradorOuLiteral(listaCPU.get(1))) + ((long)RegistradorOuLiteral(listaCPU.get(2))));
                            break;
                    }
                }else{
                    //Endereço
                    AcessEndereco();
                }
                break;
            case "loop":
                Isloop = true;
                codJump = Integer.parseInt(listaCPU.get(1));
                boolean replace = false;
                ArrayList<ArrayList<String>> clearListaListaCPU = new ArrayList<>();
                for (BeansListaCPU beansListaCPU : BLLCPU) {
                    if(beansListaCPU.getCod() == codJump){
                        replace = true;
                        beansListaCPU.setListaListaCPU(clearListaListaCPU);
                    }
                }
                if(!replace){
                    BLLCPU.add(new BeansListaCPU(codJump,clearListaListaCPU));
                }
                break;
            default:
                System.out.println("Registrador: " + registradorList);
                Isloop = false;
                //lidoList.add("A < 100 ? JMP 1 : 0");
                posicao01 = Switch(listaCPU.get(0));
                if(posicao01 != null){
                    IsLoopHappen(registradorList.get(Numero_Referencia(listaCPU.get(0))));
                }else{
                    int enderecoNumber = Integer.parseInt(listaCPU.get(0).substring(2,listaCPU.get(0).length()), 16); 

                    boolean IsUndList = false;
                    ArrayList<Byte> undList = new ArrayList<>();
                    for (BeansCache bc : listaCache) {
                        if(bc.getLocalizacao()  == enderecoNumber + 3){
                            IsUndList = true;
                            undList.add(bc.getCacheByte());
                        }
                    }

                    if(!IsUndList){
                        undList = (ArrayList <Byte>) bus.Transport("CPU", "AcessarRam", new BeansMaterial(ram.SIZE/2 + enderecoNumber, ram));
                    }

                    //AQUI SE FAZ OS MATHS
                    System.out.println("RAMList:" + ram.RAMList);
                    Number n = ByteToTypeToNumber(undList.get(0));
                    IsLoopHappen(n);
                }
        }
        
    }
    
    public void IsLoopHappen(Number n){
        for (BeansCache bc : listaCache) {
            if(!bc.IsEmpty()){
                if(bc.getLocalizacao() == 0){
                    System.out.print("Byte: " + Integer.toBinaryString(bc.getCacheByte() & 255 | 256).substring(1));
                    System.out.println(" || Acesso: " + bc.getNumber());
                }else{
                    System.out.print("Byte: " + Integer.toBinaryString(bc.getCacheByte() & 255 | 256).substring(1));
                    System.out.println(" || Acesso: " + bc.getNumber() + " || Localização: " + bc.getLocalizacao());
                }
            }
        }
        System.out.println();
        
        if(listaCPU.get(1).equalsIgnoreCase("<")){
            switch(PALAVRA){
                case 16:
                    short numShort  = (short) n;
                    if(numShort < Short.parseShort(listaCPU.get(2))){
                        JumpHappen();
                    }
                    break;
                case 32:
                    int   numInt    = (int)  n;
                    if(numInt   < Integer.parseInt(listaCPU.get(2))){
                        JumpHappen();
                    }
                    break;
                case 64:
                    long  numLong   = (long) n;
                    if(numLong  < Long.parseLong(listaCPU.get(2))){
                        JumpHappen();
                    }
                    break;
            }
        }
        if(listaCPU.get(1).equalsIgnoreCase(">")){
            switch(PALAVRA){
                case 16:
                    short numShort  = (short)n;
                    if(numShort > Short.parseShort(listaCPU.get(2))){
                        JumpHappen();
                    }
                    break;
                case 32:
                    int   numInt    = (int)  n;
                    if(numInt   > Integer.parseInt(listaCPU.get(2))){
                        JumpHappen();
                    }
                    break;
                case 64:
                    long  numLong   = (long) n;
                    if(numLong  > Long.parseLong(listaCPU.get(2))){
                        JumpHappen();
                    }
                    break;
            }
        }
        if(listaCPU.get(1).equalsIgnoreCase("==")){
            switch(PALAVRA){
                case 16:
                    short numShort  = (short)n;
                    if(numShort == Short.parseShort(listaCPU.get(2))){
                        JumpHappen();
                    }
                    break;
                case 32:
                    int   numInt    = (int)  n;
                    if(numInt   == Integer.parseInt(listaCPU.get(2))){
                        JumpHappen();
                    }
                    break;
                case 64:
                    long  numLong   = (long) n;
                    if(numLong  == Long.parseLong(listaCPU.get(2))){
                        JumpHappen();
                    }
                    break;
            }
        }
        if(listaCPU.get(1).equalsIgnoreCase("!=")){
            switch(PALAVRA){
                case 16:
                    short numShort  = (short) n;
                    if(numShort != Short.parseShort(listaCPU.get(2))){
                        JumpHappen();
                    }
                    break;
                case 32:
                    int   numInt    = (int)   n;
                    if(numInt   != Integer.parseInt(listaCPU.get(2))){
                        JumpHappen();
                    }
                    break;
                case 64:
                    long  numLong   = (long)  n;
                    if(numLong  != Long.parseLong(listaCPU.get(2))){
                        JumpHappen();
                    }
                    break;
            }
        }
        if(listaCPU.get(1).equalsIgnoreCase(">=")){
            switch(PALAVRA){
                case 16:
                    short numShort  = (short) n;
                    if(numShort >= Short.parseShort(listaCPU.get(2))){
                        JumpHappen();
                    }
                    break;
                case 32:
                    int   numInt    = (int)   n;
                    if(numInt   >= Integer.parseInt(listaCPU.get(2))){
                        JumpHappen();
                    }
                    break;
                case 64:
                    long  numLong   = (long)  n;
                    if(numLong  >= Long.parseLong(listaCPU.get(2))){
                        JumpHappen();
                    }
                    break;
            }
        }
        if(listaCPU.get(1).equalsIgnoreCase("<=")){
            switch(PALAVRA){
                case 16:
                    short numShort  = (short) n;
                    if(numShort <= Short.parseShort(listaCPU.get(2))){
                        JumpHappen();
                    }
                    break;
                case 32:
                    int   numInt    = (int)   n;
                    if(numInt   <= Integer.parseInt(listaCPU.get(2))){
                        JumpHappen(); 
                    }
                    break;
                case 64:
                    long  numLong   = (long)  n;
                    if(numLong  <= Long.parseLong(listaCPU.get(2))){
                        JumpHappen();
                    }
                    break;
            }
        }
    }
    
    boolean booleanTemporario = false;
    public void JumpHappen(){
        for (BeansListaCPU beansListaCPU : BLLCPU) {
            if(beansListaCPU.getCod()== codJump){
                ArrayList<ArrayList<String>> auxListaListaCPU = beansListaCPU.getListaListaCPU();  
                for (ArrayList<String> arrayList : auxListaListaCPU) {
                    System.out.println("Jump: "+ arrayList);
                }
                for (ArrayList<String> arrayList : auxListaListaCPU) {
                    IsloopHappen = true;
                    listaCPU = arrayList;
                    System.out.println("DecoderList: " + listaCPU);
                    MemoryCache();
                    delay();
                    CPU();
                    delay();
                    booleanTemporario = true;
                }
            }
        }
        IsloopHappen = false;
    }
    
    boolean IsStartTime = false;
    boolean time = false;
    public void MemoryCache(){
        //Ultimo Recentemente Usado
        //Temporizador
        //Menor  Frequencia
        if(SerializadorNumber == 1){
            for (Byte auxByte : Auxiliador) {
                boolean boo = false;
                for (BeansCache bc : listaCache) {
                    if(Objects.equals(bc.getCacheByte(), auxByte)){
                        boo = true;
                    }
                }
                if(!boo){
                    Nohit++;
                    AddOrderCacheMemory(new BeansCache(auxByte,UltimoAdicionado(),0,0,false));
                }else{
                    hit++;
                }
            }
        }
        
        if(SerializadorNumber == 2){
             for (Byte auxByte : Auxiliador) {
                boolean boo = false;
                for (BeansCache bc : listaCache) {
                    if(Objects.equals(bc.getCacheByte(), auxByte)){
                        //hit++;
                        bc.setNumber(bc.getNumber() + 1);
                        boo = true;
                    }
                }
                if(!boo){
                    Nohit++;
                    AddOrderCacheMemory(new BeansCache(auxByte,0,0,0,false));
                }else{
                    hit++;
                }
            }
        }
        
        if(SerializadorNumber == 3){
            if(!IsStartTime){
                IsStartTime = true;
                TimerCount();
            }
            for (Byte auxByte : Auxiliador) {
                boolean boo = false;
                for (BeansCache bc : listaCache) {
                    if(Objects.equals(bc.getCacheByte(), auxByte)){
                        bc.setNumber(10);
                        boo = true;
                    }
                }
                if(!boo){
                    Nohit++;
                    AddOrderCacheMemory(new BeansCache(auxByte,10,0,0,false));
                }else{
                    hit++;
                }
            }
        }
    }
    int interval;
    Timer timer;
    
    public void TimerCount(){
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
               for (BeansCache bc : listaCache) {
                   if(!bc.IsEmpty()){
                        bc.setNumber(bc.getNumber() - 1);
                        if(bc.getNumber() == 0){
                            System.err.println("TIMEOUT: "+ Integer.toBinaryString(bc.getCacheByte() & 255 | 256).substring(1));
                            bc.setIsEmpty(true);
                        }
                   }
                }
            }
        }, delay, period);
    }
    
    
    public void ShowCacheMemory(){
        System.out.println();
            System.out.println("MemoryCache:");
            for (BeansCache bc : listaCache) {
                if(!bc.IsEmpty()){
                    if(bc.getNumber() != 0 && SerializadorNumber == 3){
                        if(bc.getLocalizacao() == 0){
                            System.out.print("Byte: " + Integer.toBinaryString(bc.getCacheByte() & 255 | 256).substring(1));
                            System.out.println(" || Acesso: " + bc.getNumber());
                        }else{
                            System.out.print("Byte: " + Integer.toBinaryString(bc.getCacheByte() & 255 | 256).substring(1));
                            System.out.println(" || Acesso: " + bc.getNumber() + " || Localização: " + bc.getLocalizacao());
                        }
                    }
                }
            }
            System.out.println();
    }
    
    
    public Number ByteToNumber(ArrayList<Byte> Auxiliador){
        long longNumber = 0;
        Number n = 0;
        switch(PALAVRA){
            case 16:
                for (int i = 1; i > -1; i--) {
                    longNumber += ((short) Auxiliador.get(0) & 0xff) << (8 * i);
                    Auxiliador.remove(0);
                }
                n = (short) longNumber;
            break;

            case 32:
                for (int i = 3; i > -1; i--) {
                    longNumber += ((int)    Auxiliador.get(0) & 0xff) << (8 * i);
                    Auxiliador.remove(0);
                }
                n = (int) longNumber;
            break;

            case 64:
                for (int i = 7; i > -1; i--) {
                    longNumber +=  ((long)   Auxiliador.get(0)  & 0xff) << (8 * i);
                    Auxiliador.remove(0); 
                }
            break;  
        }
        return n;
    }   
    
    
    
    public void AcessEndereco(){
        Number n;
        byte[] arrayConverter;
        ArrayList<Byte> bytesAlocados = new ArrayList<>();
        //Endereço
        //Pegar Valor Daquele Endereço
        
        int enderecoNumber = Integer.parseInt(listaCPU.get(1).substring(2,listaCPU.get(1).length()), 16);  

        boolean IsUndList = false;
        ArrayList<Byte> undList = new ArrayList<>();
        for (BeansCache bc : listaCache) {
            if(bc.getLocalizacao()  == enderecoNumber){
                IsUndList = true;
                undList.add(bc.getCacheByte());
                bc.setLocalUpdate(bc.getLocalUpdate() + 1);
            }
        }
        
        if(!IsUndList)
            undList = (ArrayList <Byte>) bus.Transport("CPU", "AcessarRam", new BeansMaterial(ram.SIZE/2 + enderecoNumber, ram));
        
        n = ByteToTypeToNumber(undList.get(0));
        int AuxiliadorLoopError = (PALAVRA/8 + enderecoNumber) - 1;
        
        if(IsloopHappen){
            
            undList = (ArrayList <Byte>) bus.Transport("CPU", "AcessarRam", new BeansMaterial(ram.SIZE/2 + enderecoNumber, ram));
            
            IsUndList = false;
            
            if(undList.get(0) == 0){
                for (BeansCache bc : listaCache) {
                    if(bc.getLocalizacao()  == AuxiliadorLoopError){
                        IsUndList = true;
                        undList.remove(0);
                        undList.add(bc.getCacheByte());
                        bc.setLocalUpdate(bc.getLocalUpdate() + 1);
                    }
                }
            }else{
                IsUndList = true;
            } 
            if(!IsUndList){
                //se não ele busca na memoria
                undList = (ArrayList <Byte>) bus.Transport("CPU", "AcessarRam", new BeansMaterial(ram.SIZE/2 + AuxiliadorLoopError, ram));
            }
            /*
            for (Integer integer : AuxiliadorLoopError) {
                IsUndList = false;
                for (BeansCache bc : listaCache) {
                    if(bc.getLocalizacao()  == integer){
                        //se o numero contem na Cache
                        IsUndList = true;
                        AuxiliadorLoopErrorByte.add(bc.getCacheByte());
                    }
                }

                if(!IsUndList){
                    //se não ele busca na memoria
                    
                    AuxiliadorLoopErrorByte.add(undList.get(0));
                }
            }
            */
            n = ByteToTypeToNumber(undList.get(0));
            
        }
        
        //AQUI SE FAZ OS MATHS
        System.out.println("RAMList:" + ram.RAMList);
        
        arrayConverter = RAMMaths(n);
        for (byte b : arrayConverter) {
            bytesAlocados.add(b);
        }
        
        System.out.println();
        System.out.println("Locomover: " + bytesAlocados);
        
        if(bytesAlocados.size() > RAMSIZE/2 || bytesAlocados.size() + enderecoNumber > ram.SIZE/2){
            System.err.println("INVALID <SYSTEM.RAM.DENIED.OVERFLOW>");
        }else{
            //Byte bytes;
            //int  number;
            //int localizacao;
            int value = enderecoNumber;
            boolean IsValue;
            System.out.println();
            System.out.println("ByteAlocado: " + bytesAlocados);
            System.out.println();
            for (Byte byteAlocado : bytesAlocados) {
                IsValue = false;
                for (BeansCache bc : listaCache) {
                    if(bc.getLocalizacao() == value && Objects.equals(byteAlocado, bc.getCacheByte())){
                        IsValue = true;
                    }
                    if(bc.getLocalizacao() == value && !Objects.equals(byteAlocado, bc.getCacheByte())){
                        IsValue = true;
                        //Atualizar
                        if(SerializadorNumber == 2){
                            bc.setLocalUpdate(bc.getLocalUpdate() + 1);
                        }
                            bc.setCacheByte(byteAlocado);
                        
                        if(SerializadorNumber == 3){
                            bc.setNumber(10);
                        }
                        
                        UpdateCacheMemory();
                    }
                }
                if(!IsValue && byteAlocado != (byte)0){
                    Nohit++;
                    //Criando
                    if(SerializadorNumber == 3){
                        AddOrderCacheMemory(new BeansCache(byteAlocado,10,value,0,false));
                    }
                    
                    if(SerializadorNumber == 2){
                        AddOrderCacheMemory(new BeansCache(byteAlocado,0,value,0,false));
                    }
                    
                    if(SerializadorNumber == 1){
                        AddOrderCacheMemory(new BeansCache(byteAlocado,UltimoAdicionado(),value,0,false));
                    }
                }else{
                    hit++;
                }
                value++;
            }
        }
    }
    
    public int UltimoAdicionado(){
        int time = 0;
        for (BeansCache bc : listaCache) {
            if(time < bc.getNumber()){
                time = bc.getNumber();
            }
        }
        return time + 1;
    }
    
    public void AddOrderCacheMemory(BeansCache bc){
        VerifyAndRemoveCacheMemory();
        boolean bol = false;
        int acumulador = 0;
        boolean resultado = false;
        for (BeansCache bcLista : listaCache) {
            if(bcLista.IsEmpty()){
                bcLista = bc;
                bol = true;
                resultado = true;
            }
            if(!resultado){
                acumulador++;
            }
        }
        if(!bol){
            listaCache.add(bc);
        }else{
            listaCache.set(acumulador, bc);
        }
        VerifyAndRemoveCacheMemory();
    }
    
    public int SizeListaCache(){
        int size = 0;
        for (BeansCache bc : listaCache) {
            if(!bc.IsEmpty()){
                size++;
            }
        }
        return size;
    }
    
    public void VerifyAndRemoveCacheMemory(){
        double doubleNumber = (MemoryCacheSize * 80)/100;
        while(SizeListaCache() > doubleNumber){
            if(SerializadorNumber != 1){
                long menor = Long.MAX_VALUE;
                BeansCache bcStart = null;
                for (BeansCache bc : listaCache) {
                    if(menor > bc.getNumber() && !bc.IsEmpty()){
                        menor = bc.getNumber();
                        bcStart = bc;
                    }
                }
                for (BeansCache bc : listaCache) {
                    if(bcStart == bc && !bc.IsEmpty()){
                       if(bc.getLocalizacao() != 0){
                            ArrayList<Byte> auxiliadorCache = new ArrayList<>();
                            auxiliadorCache.add(bc.getCacheByte());
                            ram = (RAM) bus.Transport("CPU","EscreverRam", new BeansMaterial(bc.getLocalizacao() + ram.SIZE/2, auxiliadorCache, ram));
                       }
                       bc.setIsEmpty(true); 
                    }
                }
                
            }else{
                int menor = 0;
                BeansCache bcStart = null;
                while(bcStart == null){
                    for (BeansCache bc : listaCache) {
                        if(menor == bc.getNumber() && !bc.IsEmpty()){
                            bcStart = bc;
                        }
                    }
                    menor++;
                }
                for (BeansCache bc : listaCache) {
                    if(bcStart == bc && !bc.IsEmpty()){
                       if(bc.getLocalizacao() != 0){
                            ArrayList<Byte> auxiliadorCache = new ArrayList<>();
                            auxiliadorCache.add(bc.getCacheByte());
                            ram = (RAM) bus.Transport("CPU","EscreverRam", new BeansMaterial(bc.getLocalizacao() + ram.SIZE/2, auxiliadorCache, ram));
                       }
                       bc.setIsEmpty(true); 
                    }
                }
            }
        }
    }
    
    public void UpdateCacheMemory(){
        for (BeansCache bc : listaCache) {
            if(bc.getLocalUpdate() == UpdateLimiteRAM){
                System.out.println("RAM Pré-Atualizada: " + ram.RAMList);
                
                ArrayList<Byte> auxiliadorCache = new ArrayList<>();
                auxiliadorCache.add(bc.getCacheByte());
                ram = (RAM) bus.Transport("CPU","EscreverRam", new BeansMaterial(bc.getLocalizacao() + ram.SIZE/2, auxiliadorCache, ram));
                bc.setLocalUpdate(0);
                
                System.out.println("RAM Atualizada: " + ram.RAMList);
            }
        }
    }
    
    public Number RegistradorOuLiteral(String k){
        if(Switch(k) != null){
            //É UM REGISTRADOR
            int i = Numero_Referencia(k);
            //QUAL SEU ENDEREÇO NO ARRAY
            return registradorList.get(i);
        }else{
            Number num = null;
            switch(PALAVRA){
            case 16:
                num = Short.parseShort(k);
                break;
            case 32:
                num = Integer.parseInt(k);
                break;
            case 64:
                num = Long.parseLong(k);
                break;
            }
             return num;
        }
    } 
    
    public void Atualizar_Registrador(int pos, Number valor){
        switch (PALAVRA){            
            case 16:
                registradorList.set(pos, (short)valor);
                break;
            case 32:
                registradorList.set(pos, (int)valor);
                break;
            case 64:
                registradorList.set(pos, (long)valor);
                break;
        }
    }
    
    public int Numero_Referencia(String k){
        switch (k){
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
        }
        return 4;
    }
    
    public String Switch(String k){
        switch (k){
            case "A":
                return "A";
            case "B":
                return "B";
            case "C":
                return "C";
            case "D":
                return "D";
            default:
                return null;
        }
    }
    
    public Number ByteToTypeToNumber(byte b){
        Number n;
        switch (PALAVRA){
            case 16:
                short sh = (short) (b & 0xFF);
                return n = sh;
            case 32:
                int   in = b & 0xFF;
                return n = in;
            case 64:
                long  ln = (long)  (b & 0xFF);
                return n = ln;
        }
        return null;
   }
    
    public void Decoder(){
        boolean diferenciador       = false;
        boolean Loop_diferenciador  = false;
        switch (listaDecoder.get(0)) {  
            case -50:
                listaCPU.add("mov");
                listaDecoder.remove(0);
                Loop_diferenciador = true;
                break;
            case -51:
                listaCPU.add("inc");
                listaDecoder.remove(0);
                Loop_diferenciador = true;
                break;
            case -52:
                listaCPU.add("imul");
                listaDecoder.remove(0);
                Loop_diferenciador = true;
                break;
            case -53:
                listaCPU.add("add");
                listaDecoder.remove(0);
                Loop_diferenciador = true;
                break; 
            case -58:
                listaCPU.add("loop");
                listaDecoder.remove(0);
                diferenciador  = true;
                Loop_diferenciador = true;
                break;     
        }
        if(!Loop_diferenciador){
          switch (listaDecoder.get(0)){
            case -54:
                listaCPU.add("A");
                listaDecoder.remove(0);
                diferenciador = true;
            break;
            case -55:
                listaCPU.add("B");
                listaDecoder.remove(0);
                diferenciador = true;
            break;
            case -56:
                listaCPU.add("C");
                listaDecoder.remove(0);
                diferenciador = true;
            break;
            case -57:
                listaCPU.add("D");
                listaDecoder.remove(0);
                diferenciador = true;
            break;
            default:
                diferenciador = true;
                long longNumber = 0;
                String endereco = ""; 
                switch (PALAVRA) {
                    case 16:
                        for (int i = 1; i > -1; i--) {
                            longNumber += ((short) listaDecoder.get(0) & 0xff) << (8 * i);
                            listaDecoder.remove(0);
                        }
                    break;

                    case 32:
                        for (int i = 3; i > -1; i--) {
                            longNumber += ((int)    listaDecoder.get(0) & 0xff) << (8 * i);
                            listaDecoder.remove(0);
                        }
                    break;

                    case 64:
                    for (int i = 7; i > -1; i--) {
                        longNumber +=  ((long)   listaDecoder.get(0)  & 0xff) << (8 * i);
                        listaDecoder.remove(0); 
                    }
                    break;  
                }
                
                switch (BARRA){
                    case 8:
                        endereco = Long.toHexString(longNumber);
                        while(endereco.length() < BARRA/8){
                            endereco = "0" + endereco;
                        }
                        break;
                    case 16:
                        endereco = Long.toHexString(longNumber);
                        while(endereco.length() < BARRA/8){
                            endereco = "0" + endereco;
                        }
                        break;
                    case 32:
                        endereco = Long.toHexString(longNumber);
                        while(endereco.length() < BARRA/8){
                            endereco = "0" + endereco;
                        }
                        break;    
                }
                listaCPU.add("0x" + endereco);
            break;    
          }
        }
        for (int i = 0; i < 3; i++) {
            if(!listaDecoder.isEmpty()){
                switch (listaDecoder.get(0)){
                    case -54:
                        listaCPU.add("A");
                        listaDecoder.remove(0);
                        diferenciador = true;
                        break;
                    case -55:
                        listaCPU.add("B");
                        listaDecoder.remove(0);
                        diferenciador = true;
                        break;
                    case -56:
                        listaCPU.add("C");
                        listaDecoder.remove(0);
                        diferenciador = true;
                        break;
                    case -57:
                        listaCPU.add("D");
                        listaDecoder.remove(0);
                        diferenciador = true;
                        break;
                    case -59:
                        listaCPU.add(">");
                        listaDecoder.remove(0);
                        break;
                    case -60:
                        listaCPU.add("<");
                        listaDecoder.remove(0);
                        break;
                    case -61:
                        listaCPU.add("==");
                        listaDecoder.remove(0);
                        break;
                    case -62:
                        listaCPU.add(">=");
                        listaDecoder.remove(0);
                        break;
                    case -63:
                        listaCPU.add("<=");
                        listaDecoder.remove(0);
                        break;
                    case -64:
                        listaCPU.add("!=");
                        listaDecoder.remove(0);
                        break;         
                }
            }
        }
        String endereco = "";   
        while(listaDecoder.size() >= PALAVRA/8){
            long longNumber = 0;
            if(listaDecoder.get(0) < 0){
                findLetter();
            }else{
                switch (PALAVRA) {
                    case 16:
                        for (int i = 1; i > -1; i--) {
                            longNumber += ((short) listaDecoder.get(0) & 0xff) << (8 * i);
                            listaDecoder.remove(0);
                        }
                        break;
                    case 32:
                        for (int i = 3; i > -1; i--) {
                            longNumber += ((int)    listaDecoder.get(0) & 0xff) << (8 * i);
                            listaDecoder.remove(0);
                        }
                        break;
                    case 64:
                        for (int i = 7; i > -1; i--) {
                            longNumber +=  ((long)   listaDecoder.get(0)  & 0xff) << (8 * i);
                            listaDecoder.remove(0); 
                        }
                        break;  
                }
                if(!diferenciador){
                    diferenciador = true;
                    switch (BARRA){
                        case 8:
                            endereco = Long.toHexString(longNumber);
                            while(endereco.length() < BARRA/8){
                                endereco = "0" + endereco;
                            }
                            break;
                        case 16:
                            endereco = Long.toHexString(longNumber);
                            while(endereco.length() < BARRA/8){
                                endereco = "0" + endereco;
                            }
                            break;
                        case 32:
                            endereco = Long.toHexString(longNumber);
                            while(endereco.length() < BARRA/8){
                                endereco = "0" + endereco;
                            }
                            break;    
                    }
                    listaCPU.add("0x" + endereco);
                }else{
                    if(PALAVRA == 16){
                        short temp = (short) 32767;
                        if(longNumber > temp){
                            byte[] bytes1 = ByteBuffer.allocate(2).putShort((short)longNumber).array();
                            for (byte b : bytes1) {
                                listaDecoder.add(b);
                            }
                            break;
                        }               
                    }
                    listaCPU.add(String.valueOf(longNumber));
                }
            }
        }
        while(!listaDecoder.isEmpty()){
            findLetter();
        }
        System.out.println();
        System.out.println("DecoderList: " + listaCPU);
    }
    
    
    public void findLetter(){
        switch (listaDecoder.get(0)){
                case -54:
                    listaCPU.add("A");
                    listaDecoder.remove(0);
                    break;
                case -55:
                    listaCPU.add("B");
                    listaDecoder.remove(0);
                    break;
                case -56:
                    listaCPU.add("C");
                    listaDecoder.remove(0);
                    break;
                case -57:
                    listaCPU.add("D");
                    listaDecoder.remove(0);
                    break;
            }
    }
    
    public byte[] RAMMaths(Number ene) {
        //Irá vir 0, se não houver nada.
        byte[] b = null;
        Number n = ene;
        switch (listaCPU.get(0)) {
            case "mov":
                n = RegistradorOuLiteral(listaCPU.get(2));   
                b = NumberToByte(n);    
                break;
            case "inc":
                switch(PALAVRA){
                    case 16:
                        short s =  (short) n;
                        s = (short)(s + 1);
                        n = s;
                        break;
                    case 32:
                        int i   =  (int) n;
                        i = i + (int) 1;
                        n = i;
                        break;
                    case 64:
                        long l  =  (long) n;
                        l = l + (long) 1;
                        n = l;
                        break;
                }
                b = NumberToByte(n); 
                break;
            case "imul":
                switch(PALAVRA){
                    case 16:
                        short sh1 = (short) RegistradorOuLiteral(listaCPU.get(2));
                        short sh2 = (short)RegistradorOuLiteral(listaCPU.get(3));
                        sh1 = (short)(sh1 * sh2);
                        n = sh1;
                        break;
                    case 32:
                        n = ((int)RegistradorOuLiteral(listaCPU.get(2))) * ((int)RegistradorOuLiteral(listaCPU.get(3)));
                        break;
                    case 64:
                        n = ((long)RegistradorOuLiteral(listaCPU.get(2))) * ((long)RegistradorOuLiteral(listaCPU.get(3)));
                        break;
                }
                b = NumberToByte(n);
            break;
                
            case "add":
                switch(PALAVRA){
                    case 16:
                        short sh1 = (short) n;
                        short sh2 = (short)RegistradorOuLiteral(listaCPU.get(2));
                        sh1 = (short)(sh1 + sh2);
                        n = sh1;
                        break;
                    case 32:
                        n = (int) n   + (int) RegistradorOuLiteral(listaCPU.get(2));
                        break;
                    case 64:
                        n = (long) n + (long) RegistradorOuLiteral(listaCPU.get(2));
                        break;
                }
                b = NumberToByte(n);
            break;    
            
        }
        return b;
    }
    
    public void FILL(){
        switch(PALAVRA){
            case 16:
                while(registradorList.size() != 4){
                    registradorList.add((short)0);
                }
                break;
            case 32:
                while(registradorList.size() != 4){
                    registradorList.add(0);
                }
                break;
            case 64:
                while(registradorList.size() != 4){
                    registradorList.add((long)0);
                }
                break;
        }
    }
    
    public byte[] NumberToByte(Number n){
        byte[] b = null;
        switch(PALAVRA){
            case 16:
                b = ByteBuffer.allocate(2).putShort((short) n).array();
                break;
            case 32:
                b = ByteBuffer.allocate(4).putInt((int) n).array();
                break;
            case 64:
                b = ByteBuffer.allocate(8).putLong((long) n).array();
                break;
        }
        return b;
    }
    
    public Number ByteToNumber(byte[] b){
        long longNumber = 0;
        Number n = 0;
        ArrayList<Byte> temporaryList = new ArrayList<>(); 
            for (byte c : b) {
                temporaryList.add(c);
            }
            switch(PALAVRA){
                case 16:
                    for (int i = 1; i > -1; i--) {
                        longNumber += ((short) temporaryList.get(0) & 0xff) << (8 * i);
                        temporaryList.remove(0);
                    }
                    n = (short) longNumber;
                    break;
                case 32:
                    for (int i = 3; i > -1; i--) {
                        longNumber += ((int) temporaryList.get(0) & 0xff) << (8 * i);
                        temporaryList.remove(0);
                    }
                    n = (int) longNumber;
                    break;
                case 64:
                    for (int i = 7; i > -1; i--) {
                        longNumber += ((long) temporaryList.get(0) & 0xff) << (8 * i);
                        temporaryList.remove(0);
                    }
                    n = longNumber;
                    break;
            }
        return n;
    }
    
    public void RAMData(Bus bus){
        //delayCheck();
        //DIZ PARA O BARRAMENTO PARA ESCREVER NA RAM
        ram.WriteMemory(bus.bufferTransferring, PONTEIRO); 
        TAMANHOINSTRUCAO = bus.bufferTransferring.size();
        //A RAM NECESSITA DO PONTEIRO PARA SABER ONDE ELA DEVE PEGAR A INSTRUÇÃO
        listaDecoder = (ArrayList<Byte>) bus.Transport("RAM", "CPU", new BeansMaterial(PONTEIRO, TAMANHOINSTRUCAO, ram));
        if(!Auxiliador.isEmpty()){
            Auxiliador.clear();
        }
        for (Byte byte1 : listaDecoder) {
            Auxiliador.add(byte1);
        }
        Decoder();
        MemoryCache();
        delay();
        //INICIARÁ A LÓGICA DENTRO DA CPU
        if(Isloop){
            for (BeansListaCPU beansListaCPU : BLLCPU) {
                if(beansListaCPU.getCod()== codJump){
                    ArrayList<ArrayList<String>> auxListaListaCPU = beansListaCPU.getListaListaCPU();
                    ArrayList<String> listaCPUCopy = new ArrayList<>(listaCPU);
                    auxListaListaCPU.add(listaCPUCopy);
                }
            }
        }
        CPU();
        ShowCacheMemory();
        if(!booleanTemporario){
            System.out.println("Registrador: " + registradorList);
        }
        System.out.println();
        listaCPU.clear();
        //ATUALIZARÁ O PONTEIRO
        PONTEIRO = PONTEIRO + TAMANHOINSTRUCAO;
        if(PONTEIRO > RAMSIZE/2){
            PONTEIRO = PONTEIRO - RAMSIZE/2;
        }
    }
    public void delay(){
        try {
            Thread.sleep(20);
        } catch (InterruptedException ex) {
            Logger.getLogger(Assembly.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delayCheck(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Assembly.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
        