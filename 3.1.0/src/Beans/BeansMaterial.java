/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import assembly.Bus;
import assembly.RAM;
import java.util.ArrayList;

/**
 *
 * @author Mateus
 */
public class BeansMaterial {
    int PONTEIRO;
    int TAMANHOINSTRUCAO;
    
    int localizacaoUndRam;
    RAM ram;
    
    
    ArrayList<Byte> bufferList;
    Bus bus;
    
    //Endereco, bytesAlocados, ram
    int endereco;
    ArrayList<Byte> bytesAlocados;
    
    
    public BeansMaterial() {
    }

    public BeansMaterial(int endereco, ArrayList<Byte> bytesAlocados , RAM ram) {
        this.ram = ram;
        this.endereco = endereco;
        this.bytesAlocados = bytesAlocados;
    }

    public BeansMaterial(ArrayList<Byte> bufferList,Bus bus) {
        this.bus = bus;
        this.bufferList = bufferList;
    }

    public BeansMaterial(int localizacaoRam, RAM ram) {
        this.localizacaoUndRam = localizacaoRam;
        this.ram = ram;
    }

    public BeansMaterial(int PONTEIRO, int TAMANHOINSTRUCAO, RAM ram) {
        this.PONTEIRO = PONTEIRO;
        this.TAMANHOINSTRUCAO = TAMANHOINSTRUCAO;
        this.ram = ram;
    }

    public int getEndereco() {
        return endereco;
    }

    public void setEndereco(int endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Byte> getBytesAlocados() {
        return bytesAlocados;
    }

    public void setBytesAlocados(ArrayList<Byte> bytesAlocados) {
        this.bytesAlocados = bytesAlocados;
    }

    
    
    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public ArrayList<Byte> getBufferList() {
        return bufferList;
    }

    public void setBufferList(ArrayList<Byte> bufferList) {
        this.bufferList = bufferList;
    }

    public int getLocalizacaoUndRam() {
        return localizacaoUndRam;
    }

    public void setLocalizacaoUndRam(int localizacaoRam) {
        this.localizacaoUndRam = localizacaoRam;
    }

    
    public int getPONTEIRO() {
        return PONTEIRO;
    }

    public void setPONTEIRO(int PONTEIRO) {
        this.PONTEIRO = PONTEIRO;
    }

    public int getTAMANHOINSTRUCAO() {
        return TAMANHOINSTRUCAO;
    }

    public void setTAMANHOINSTRUCAO(int TAMANHOINSTRUCAO) {
        this.TAMANHOINSTRUCAO = TAMANHOINSTRUCAO;
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }
    
    
    
}
