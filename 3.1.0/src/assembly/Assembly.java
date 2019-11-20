
package assembly;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateus
 */
public class Assembly {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        ZLeitor  leitor = new ZLeitor();
        Parser_ER pr    = new Parser_ER();
        Scanner scanner = new Scanner(System.in);
        Enconder enc    = new Enconder(); 
        Buffer buf      = new Buffer();
        CPU cpu         = new CPU();
        
        
        int PALAVRA = 32;
        int RAM     = 64;
        int BUFFER  = 16;
        int BARRA   = 32;
        int CACHE   = 32;
        
        boolean tr =  true;
        //String k = "C:\\Users\\Mateus\\Downloads\\txt.txt";
        ArrayList<String> lidoList = new ArrayList<>();
        
        
        /*
        lidoList.add("mov A ,0");
        lidoList.add("inc B");
        lidoList.add("add C ,2");
        lidoList.add("mov D ,3");
        lidoList.add("inc 0x0001");
        lidoList.add("add 0x0002 ,5");
        lidoList.add("mov 0x0003 ,6");
        lidoList.add("inc 0x0004");
        lidoList.add("add 0x0005 ,8");
        lidoList.add("mov 0x0006 ,9");
        lidoList.add("inc 0x0007");
        lidoList.add("add 0x0008 ,11");
        lidoList.add("inc A");
        lidoList.add("mov B ,12");
        lidoList.add("add C ,13");
        lidoList.add("inc D");
        lidoList.add("mov 0x0001 ,15");
        lidoList.add("add 0x0002 ,16");
        lidoList.add("inc 0x0003");
        lidoList.add("mov 0x0004 ,18");
        lidoList.add("add 0x0005 ,19");
        lidoList.add("inc 0x0006");
        lidoList.add("mov 0x0007 ,21");
        lidoList.add("add 0x0008 ,22");
        lidoList.add("add A ,22");
        lidoList.add("inc B");
        lidoList.add("mov C ,24");
        lidoList.add("add D ,25");
        lidoList.add("inc 0x0001");
        lidoList.add("mov 0x0002 ,27");
        lidoList.add("add 0x0003 ,28");
        lidoList.add("inc 0x0004");
        lidoList.add("mov 0x0005 ,30");
        lidoList.add("add 0x0006 ,31");
        lidoList.add("inc 0x0007");
        lidoList.add("mov 0x0008 ,33");
        lidoList.add("mov A ,34");
        lidoList.add("inc B");
        lidoList.add("add C ,36");
        lidoList.add("mov D ,37");
        lidoList.add("inc 0x0001");
        lidoList.add("add 0x0002 ,39");
        lidoList.add("mov 0x0003 ,40");
        lidoList.add("inc 0x0004");
        lidoList.add("add 0x0005 ,42");
        lidoList.add("mov 0x0006 ,43");
        lidoList.add("inc 0x0007");
        lidoList.add("add 0x0008 ,45");
        lidoList.add("inc A");
        lidoList.add("mov B ,46");
        lidoList.add("add C ,47");
        lidoList.add("inc D");
        lidoList.add("mov 0x0001 ,49");
        lidoList.add("add 0x0002 ,50");
        lidoList.add("inc 0x0003");
        lidoList.add("mov 0x0004 ,52");
        lidoList.add("add 0x0005 ,53");
        lidoList.add("inc 0x0006");
        lidoList.add("mov 0x0007 ,55");
        lidoList.add("add 0x0008 ,56");
        lidoList.add("add A ,56");
        lidoList.add("inc B");
        lidoList.add("mov C ,58");
        lidoList.add("add D ,59");
        lidoList.add("inc 0x0001");
        lidoList.add("mov 0x0002 ,61");
        lidoList.add("add 0x0003 ,62");
        lidoList.add("inc 0x0004");
        lidoList.add("mov 0x0005 ,64");
        lidoList.add("add 0x0006 ,65");
        lidoList.add("inc 0x0007");
        lidoList.add("mov 0x0008 ,67");
        lidoList.add("mov A ,68");
        lidoList.add("inc B");
        lidoList.add("add C ,70");
        lidoList.add("mov D ,71");
        lidoList.add("inc 0x0001");
        lidoList.add("add 0x0002 ,73");
        lidoList.add("mov 0x0003 ,74");
        lidoList.add("inc 0x0004");
        lidoList.add("add 0x0005 ,76");
        lidoList.add("mov 0x0006 ,77");
        lidoList.add("inc 0x0007");
        lidoList.add("add 0x0008 ,79");
        lidoList.add("inc A");
        lidoList.add("mov B ,80");
        lidoList.add("add C ,81");
        lidoList.add("inc D");
        lidoList.add("mov 0x0001 ,83");
        lidoList.add("add 0x0002 ,84");
        lidoList.add("inc 0x0003");
        lidoList.add("mov 0x0004 ,86");
        lidoList.add("add 0x0005 ,87");
        lidoList.add("inc 0x0006");
        lidoList.add("mov 0x0007 ,89");
        lidoList.add("add 0x0008 ,90");
        lidoList.add("add A ,90");
        lidoList.add("inc B");
        lidoList.add("mov C ,92");
        lidoList.add("add D ,93");
        lidoList.add("inc 0x0001");
        lidoList.add("mov 0x0002 ,95");
        lidoList.add("add 0x0003 ,96");
        lidoList.add("inc 0x0004");
        lidoList.add("mov 0x0005 ,98");
        lidoList.add("add 0x0006 ,99");
        lidoList.add("inc 0x0007");
        lidoList.add("mov 0x0008 ,101");
        lidoList.add("mov A ,102");
        lidoList.add("inc B");
        lidoList.add("add C ,104");
        lidoList.add("mov D ,105");
        lidoList.add("inc 0x0001");
        lidoList.add("add 0x0002 ,107");
        lidoList.add("mov 0x0003 ,108");
        lidoList.add("inc 0x0004");
        lidoList.add("add 0x0005 ,110");
        lidoList.add("mov 0x0006 ,111");
        lidoList.add("inc 0x0007");
        lidoList.add("add 0x0008 ,113");
        lidoList.add("inc A");
        lidoList.add("mov B ,114");
        lidoList.add("add C ,115");
        lidoList.add("inc D");
        lidoList.add("mov 0x0001 ,117");
        lidoList.add("add 0x0002 ,118");
        lidoList.add("inc 0x0003");
        lidoList.add("mov 0x0004 ,120");
        lidoList.add("add 0x0005 ,121");
        lidoList.add("inc 0x0006");
        lidoList.add("mov 0x0007 ,123");
        lidoList.add("add 0x0008 ,124");
        lidoList.add("add A ,124");
        lidoList.add("inc B");
        lidoList.add("mov C ,126");
        lidoList.add("add D ,127");
        lidoList.add("inc 0x0001");
        lidoList.add("mov 0x0002 ,129");
        lidoList.add("add 0x0003 ,130");
        lidoList.add("inc 0x0004");
        lidoList.add("mov 0x0005 ,132");
        lidoList.add("add 0x0006 ,133");
        lidoList.add("inc 0x0007");
        lidoList.add("mov 0x0008 ,135");
       
        //lidoList.add("loop 1");
        //lidoList.add("inc 0x0001");
        //lidoList.add("0x0001 < 10 ? JMP 1 : 0");
        
        lidoList.add("loop 1");
        lidoList.add("inc 0x0001");
        lidoList.add("0x0001 < 10 ? JMP 1 : 0");
        
        lidoList.add("loop 1");
        lidoList.add("inc 0x0002");
        lidoList.add("0x0002 < 10 ? JMP 1 : 0");
        
        lidoList.add("loop 1");
        lidoList.add("inc 0x0003");
        lidoList.add("0x0003 < 10 ? JMP 1 : 0");
        
        lidoList.add("loop 1");
        lidoList.add("inc 0x001A");
        lidoList.add("0x001A < 10 ? JMP 1 : 0");
        
        lidoList.add("loop 1");
        lidoList.add("inc 0x001B");
        lidoList.add("0x001B < 10 ? JMP 1 : 0");
                
        lidoList.add("loop 1");
        lidoList.add("inc 0x001C");
        lidoList.add("0x001C < 10 ? JMP 1 : 0");
        */
        
        //INFINITO LOOPING NO 1D
        /*
            lidoList.add("loop 1");
            lidoList.add("inc 0x001D");
            lidoList.add("0x001D < 10 ? JMP 1 : 0");
        */
        
        for (String string : lidoList) {
            System.out.println(string);
        }
        
        Bus bus = new Bus();
        bus.SIZE = BARRA/8;
        cpu.ram.FILL(RAM);
        cpu.ram.SIZE = RAM;
        cpu.RAMSIZE = RAM;
        cpu.PALAVRA = PALAVRA;
        cpu.FILL();
        cpu.BARRA = BARRA;
        cpu.MemoryCacheSize = RAM/2;
        cpu.SerializadorNumber = 3;
        cpu.MemoryCacheSize = CACHE;
        //5:1
        cpu.UpdateLimiteRAM = 5;
        
        //BYTES/SEG          HERTZ
        double speed = (BARRA * 100)/8;
        bus.TIME = (int) speed;
        
        //Bus frequency * width of data bus = bandwidth
        //RAM em bytes   [32, 64 ou 128]: ");
        //Cache em bytes [16, 32 ou 64]: ");
        
        do{ 
            System.out.println("INITIALIAZE <ASSEMBLY>");
            delay();
            for (String string : lidoList) {
                ArrayList<String> parserList       = pr.Check(string,PALAVRA,BUFFER,BARRA,RAM);
                delay();
                if(parserList != null){
                    ArrayList<Number> enconderList = enc.Get(parserList,PALAVRA,RAM);
                    delay();
                    if(enconderList != null){
                        bus = buf.Array(enconderList,PALAVRA, bus);
                        delay();
                        //BARRAMENTO PASSADO IRA PEGAR OS DADOS, PASSANDO-SE ELE PARA A RAM, QUANDO ISSO FOR ACONTENCER O BARRAMENTO  
                        //SERÁ COMPUTADO NA CPU QUE O BARRAMENTO ESTÁ SENDO PROCESSADO
                        cpu.RAMData(bus);
                    }else{
                        delay();
                         System.err.println("INVALID <ENTRADA.RAM.DENIED" + "." + "|" + parserList + "|" + ">");
                    }
                }
            } 
            System.out.println("HIT: " + cpu.hit);
            System.out.println("NO_HIT: " + cpu.Nohit);
            long endTime = System.nanoTime();
            System.out.println("Took "+(endTime - startTime) + " ns"); 
            break;
        }while(tr);
            
    }
    public static void delay(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Assembly.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
            
        /*
            System.out.println("Tamanho da palavra em bits [16, 32 ou 64]: ");
            parametros.add(scanner.nextLine());
            System.out.println("Tamanho da RAM em bytes [8, 16 ou 32]: ");
            System.out.println("Tamanho da RAM em bytes [32, 64 ou 128]: ");
            parametros.add(scanner.nextLine());
            System.out.println("Tamanho do buffer de entrada/saída em bytes [4, 8 ou 16]: ");
            parametros.add(scanner.nextLine());
            System.out.println(" Largura do barramento em bits [8, 16 ou 32]: ");
                                                          byte [1, 2 ou 4]: ");
            parametros.add(scanner.nextLine());
            
        */
            
        
        
        
        
        //<--------------ALPHA VERSION-------------ALPHA VERSION-------------ALPHA VERSION------------->
    /*    Parser pr   = new Parser();  
        
            System.out.println("Press 1 or 2 to choose ur Assembly: ");
            String selecao = scanner.nextLine();
            System.out.println("Comando: ");
            
            
            if(cv.INT(selecao) == 1){
                
               
            }
            
            if(cv.INT(selecao) == 2){
                String pr_ER_1;
                do {// Se Faz Para Se Checar O Tamanho
                    pr_ER_1 = scanner.nextLine();
                } while (!pr.Tamanho(pr_ER_1));



                ArrayList<String> vari= pr.Instrucao(pr_ER_1,enc.Lista_Instrucao());
                for (String string : vari) {
                    System.out.println(string);
                }
            }
            
            
            if(selecao.equalsIgnoreCase("esc"))
                tr = false;
        }*/
