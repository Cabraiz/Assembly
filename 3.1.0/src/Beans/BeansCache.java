/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author Mateus
 */
public class BeansCache {
    Byte cacheByte;
    int  number;
    int localizacao;
    int localUpdate;
    boolean IsEmpty;

    public BeansCache(Byte cacheByte, int number, int localizacao, int localUpdate, boolean IsEmpty) {
        this.cacheByte = cacheByte;
        this.number = number;
        this.localizacao = localizacao;
        this.localUpdate = localUpdate;
        this.IsEmpty = IsEmpty;
    }

    

    public boolean IsEmpty() {
        return IsEmpty;
    }

    public void setIsEmpty(boolean IsEmpty) {
        this.IsEmpty = IsEmpty;
    }

    
    
    public int getLocalUpdate() {
        return localUpdate;
    }

    public void setLocalUpdate(int localUpdate) {
        this.localUpdate = localUpdate;
    }
    
    

    public Byte getCacheByte() {
        return cacheByte;
    }

    public void setCacheByte(Byte bytes) {
        this.cacheByte = bytes;
    }

    
    /**
    * @param parametro1
    * @param parametro2
    * @return 
    * @throws IOException
    */
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(int localizacao) {
        this.localizacao = localizacao;
    }
    
    
    

    
}
