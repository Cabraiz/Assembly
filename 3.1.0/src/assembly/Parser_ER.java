
package assembly;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Mateus
 */

public class Parser_ER {
    public ArrayList<String> Check(String k, int PALAVRA, int BUFFER, int BARRA, int RAM){
        int montante = 0;//CHECA SE HÁ COMPATIBILIDADE COM O TAMANHO DO BUFFER
        ArrayList<String> lista =  new ArrayList<>();
        
        //lidoList.add("loop 1");
        //lidoList.add("inc 0x0001");
        
        
        //Fatores De Erro: Copiar do PDF e Colar(50 mins).
        String endereco    = "0x[A-F0-9]{" + Integer.toString(PALAVRA/8)+ "}";
        String registrador = "A|B|C|D";
        String inteiro     = "\\d+";
        String regisInt   = "(" + registrador + "|" + inteiro  + ")";//REGISTRADOR + INTEIRO
        String regisEnder = "(" + registrador + "|" + endereco + ")";//REGISTRADOR + ENDEREÇO
        String completo   = "(" + endereco    + "|" + registrador + "|" + inteiro + ")";
        String sinais     = "(>|<|==|!=|>=|<=)";
        
        String ploop1   = "(loop)\\s(\\d+)";
        //lidoList.add("A < 100 ? JMP 1 : 0");
        String ploop2   = regisEnder  + "\\s" + sinais + "\\s" +
                "(" + inteiro + ")"
                +"\\s" + "\\?" + "\\s" + "JMP" + "\\s" +
                "(" + inteiro + ")" + 
                "\\s" + ":" + "\\s" + "0";
        String pmov     = "(mov)\\s"  +   regisEnder + "\\s," + regisInt;
        String padd     = "(add)\\s"  +   regisEnder + "\\s," + regisInt;
        String pinc     = "(inc)\\s"  +   regisEnder;
        String piml     = "(imul)\\s" +   regisEnder + "\\s," + regisInt + "\\s," + regisInt;
        String[] strLista = {pmov,padd,pinc,piml,ploop1,ploop2};
        Pattern p;
        Matcher m = null;
        
        for (String st : strLista) {
            p = Pattern.compile(st);
            m = p.matcher(k);
            if(m.matches()){
                break;
            }
        }
        if(m.matches()){ 
            int cont = m.groupCount() + 1;
            for (int i = 1; i < cont; i++) {
                if(m.group(i).matches(endereco)){
                    if(PALAVRA != BARRA){
                        System.err.println("INVALID <ENTRADA.PALAVRA!=BARRA>"+ "." + "|" + lista + "|"+">");
                        return null;
                    }
                    lista.add(m.group(i));
                    montante = montante + PALAVRA/8;
                }else
                if(m.group(i).matches(registrador)){
                    lista.add(m.group(i));
                    montante++;
                }else
                if(m.group(i).matches(inteiro)){
                    try{
                        if(PALAVRA == 16){
                            short converterShort = Short.parseShort(m.group(i));
                        }
                        if(PALAVRA == 32){
                            int converterInt = Integer.parseInt(m.group(i));
                        }
                        if(PALAVRA == 64){
                            long converterLongS = Long.parseLong(m.group(i));
                        }
                    }catch(NumberFormatException e){
                        System.err.println("INVALIDO <ENTRADA.EXCEEDED.NUMBER"+ "." + "|" + lista + "|"+">");
                        return null;
                    }
                    lista.add(m.group(i));
                    montante = montante + PALAVRA/8;
                }else{
                    lista.add(m.group(i));
                    montante++;
                }
            }
            
        }
//        if(montante > RAM/2){
//            System.err.println("INVALID   <ENTRADA.OVERFLOW.RAM." + montante + "." + "|" + lista + "|" + ">");
//            return null;
        if(montante > RAM/2){
            System.err.println("INVALID   <ENTRADA.OVERFLOW.RAM." + montante + "." + "|" + lista + "|" + ">");
            return null;
        } else if(montante > BUFFER){
            System.err.println("INVALID   <ENTRADA.OVERFLOW.BUFFER." + montante + "." + "|" + lista + "|" + ">");
            return null;
        }else if(lista.isEmpty()){
            System.err.println("INVALID   <ENTRADA.MISS.TRUE-EXPRESSION."+ montante + "." + "|" + lista + "|" + ">");
            return null;
        }else{
            System.out.println("AVAIABLE  <ENTRADA."+ montante + "." + "|" + lista + "|" + ">");
        }
        
        return lista;
    }
    
    
        
    //PALAVRA -> unidade de transferência entre a CPU e memória principal.
        
    //Intrucao = 1 byte     || Endereco = Tamanho da palavra / 4 Bytes 
    //Registrador = 1 byte  || Inteiro =  Tamanho da palavra / 4 Bytes
        
        
    
    
}
