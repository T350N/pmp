/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicahiccpmp.cesarproyecto;

import com.unicahiccpmp.Layout.Layout;
import java.util.Scanner;
import java.util.ArrayList;
import com.unicahiccpmp.dao.TareaDB;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author cahf2
 */
public class Aplicacion {

     private Scanner _EntradaTeclado;
    private ArrayList _MiPelicula;
    private int _MiPeliculaIdCounter;
    private TareaDB _PeliculaModel;
    public Aplicacion(Scanner EntradaTeclado) {
        this._EntradaTeclado = EntradaTeclado;
        this._MiPelicula = new ArrayList<TareaItem>();  
        this._MiPeliculaIdCounter = 0;
        this._PeliculaModel = new TareaDB();
        this._PeliculaModel.tableInitialize();
        this._MiPelicula = this._PeliculaModel.getPeliculaItems(true);
    }
    
    public void activarEvento(String opcionMenu){
        switch(opcionMenu.toUpperCase()){
            case "M":
                this.mostrarRegistros();
                break;
            case "I":
                this.ingresarNuevoRegistro();
                break;
            case "A":
                this.actualizarRegistro();
                break;
            case "E":   
                this.eliminarRegistro();
                break;
            case "S":
                break;
            default:
                System.out.println("Opción no reconocida!!!");
                break;
        }
    }
    
    private void ingresarNuevoRegistro(){
        Layout.printHeader("Nuevo Registro de Pelicula");
        TareaItem nuevopeliItem =  new TareaItem();
        nuevopeliItem.setNombre(Layout.obtenerValorParaCampo("Nombre de la Pelicula", "Pelicula X", this._EntradaTeclado));
        nuevopeliItem.setAutores(Layout.obtenerValorParaCampo("Nombre del Director", "Director X", this._EntradaTeclado));
        nuevopeliItem.setParte(Integer.parseInt(Layout.obtenerValorParaCampo("partes de la pelicula", "Partes X", this._EntradaTeclado)));
        nuevopeliItem.setYear(Integer.parseInt(Layout.obtenerValorParaCampo("Año de Lanzamiento yyyy", "Año X", this._EntradaTeclado)));
        
        this._PeliculaModel.insertPeliculaItem(nuevopeliItem);
        this._MiPelicula = this._PeliculaModel.getPeliculaItems(true);
        
        Layout.printSeparator();
        System.out.println(this._MiPelicula.size());
    }
    
    private void mostrarRegistros(){
        Layout.printSeparator();
        ArrayList<String> columnas = new ArrayList<String>();
        columnas.add("Codigo");
        columnas.add("Nombre");
        columnas.add("Autor");
        columnas.add("Partes");
        columnas.add("Año");
       
        
        ArrayList<Integer> anchos = new ArrayList<Integer>();
        anchos.add(9);
        anchos.add(20);
        anchos.add(22);
        anchos.add(11);
        anchos.add(11);
        
        
        try {
            
            Layout.imprimirEnColumna(columnas, anchos, "|");
            Layout.printSeparator();
            for(int i = 0; i< this._MiPelicula.size(); i++){
                columnas = ((TareaItem) this._MiPelicula.get(i)).obtenerCampos();
                Layout.imprimirEnColumna(columnas, anchos, "|");
            }
            
        } catch(Exception ex) {
            System.err.println("Error al imprimir " + ex.getMessage());
        }
    }
    
    private void actualizarRegistro(){
        Layout.printHeader("Actualizar Registro");
        int selectedId = Integer.valueOf(Layout.obtenerValorParaCampo("Ingrese Codigo Registro", "0", this._EntradaTeclado));
        TareaItem selectprograma = null;

        selectprograma = this._PeliculaModel.getPeliculaItemById(selectedId);
        if (selectprograma == null ) {
            System.out.println("Registro solicitado no existe!!!");
        } else {
            selectprograma.setNombre(Layout.obtenerValorParaCampo("Nombre de la pelicula", selectprograma.getNombre(), this._EntradaTeclado));
            selectprograma.setAutores(Layout.obtenerValorParaCampo("Nombre del Director", selectprograma.getAutores(), this._EntradaTeclado));
            selectprograma.setParte(Integer.parseInt(Layout.obtenerValorParaCampo("partes de la pelicula", "Partes X", this._EntradaTeclado)));
            selectprograma.setYear(Integer.parseInt(Layout.obtenerValorParaCampo("Año de Lanzamiento yyyy", "Año X", this._EntradaTeclado)));
        
           
            this._PeliculaModel.updatePeliculaItem(selectprograma);
            this._MiPelicula = this._PeliculaModel.getPeliculaItems(true);
            Layout.printSeparator();
            System.out.println("Registro Actualizado Satisfactoriamente!!!");
        }
        
    }
    
    private void eliminarRegistro(){
        Layout.printHeader("Eliminar Registro");
        int selectedId = Integer.valueOf(Layout.obtenerValorParaCampo("Ingrese Codigo Registro", "0", this._EntradaTeclado));

        TareaItem selectedMusic = this._PeliculaModel.getPeliculaItemById(selectedId);
        if (selectedMusic != null){
            Layout.printSeparator();
            String confirmado = Layout.obtenerValorParaCampo("¿Desea Eliminar este Registro? S - N", "N", this._EntradaTeclado);
            if (confirmado.toUpperCase().equals("S")){
               
                this._PeliculaModel.deletePeliculaItem(selectedMusic);
                 this._MiPelicula = this._PeliculaModel.getPeliculaItems(true);
                Layout.printSeparator();
                System.out.println("Registro Eliminado Satisfactoriamente!!!");
            }
        } else {
            System.out.println("Registro solicitado no existe!!!");
            
           
        }
    
    }
    
}
