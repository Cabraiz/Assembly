/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.ArrayList;

/**
 *
 * @author Mateus
 */
public class BeansListaCPU {
    int cod;
    ArrayList<ArrayList<String>> listaListaCPU = new ArrayList<>();

    public BeansListaCPU() {
    }

    public BeansListaCPU(int cod, ArrayList< ArrayList<String>> listaListaCPU) {
        this.cod = cod;
        this.listaListaCPU =listaListaCPU;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public ArrayList<ArrayList<String>> getListaListaCPU() {
        return listaListaCPU;
    }

    public void setListaListaCPU(ArrayList<ArrayList<String>> listaListaCPU) {
        this.listaListaCPU = listaListaCPU;
    }

    
    
}
