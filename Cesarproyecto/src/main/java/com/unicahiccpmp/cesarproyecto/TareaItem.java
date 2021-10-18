/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicahiccpmp.cesarproyecto;


import java.util.ArrayList;
/**
 *
 * @author cahf2
 */
public class TareaItem {

    /**
     * @return the _id
     */
    public int getId() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void setId(int _id) {
        this._id = _id;
    }

    /**
     * @return the _nombre
     */
    public String getNombre() {
        return _nombre;
    }

    /**
     * @param _nombre the _nombre to set
     */
    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    /**
     * @return the _autores
     */
    public String getAutores() {
        return _autores;
    }

    /**
     * @param _autores the _autores to set
     */
    public void setAutores(String _autores) {
        this._autores = _autores;
    }

    /**
     * @return the _parte
     */
    public int getParte() {
        return _parte;
    }

    /**
     * @param _parte the _parte to set
     */
    public void setParte(int _parte) {
        this._parte = _parte;
    }

    /**
     * @return the _year
     */
    public int getYear() {
        return _year;
    }

    /**
     * @param _year the _year to set
     */
    public void setYear(int _year) {
        this._year = _year;
    }
    
    private int _id;
    private String _nombre;
    private String _autores;
    private int _parte;
    private int _year;
    
    
    public TareaItem(){
        this.setId(0);
        this.setNombre("");
        this.setAutores("");
        this.setParte(0);
        this.setYear(0);
       
    }
    
    public TareaItem(int id, String nombre, String autores, int parte, int year) {
        this.setId(id);
        this.setNombre(nombre);
        this.setAutores(autores);
        this.setParte(parte);
        this.setYear(year);
        
    }
    
    public ArrayList<String> obtenerCampos(){
        ArrayList<String> campos = new ArrayList<String>();
        campos.add(String.valueOf(this.getId()));
        campos.add(this.getNombre());
        campos.add(this.getAutores());
        campos.add(String.valueOf(this.getParte()));
        campos.add(String.valueOf(this.getYear()));
       
        
        return campos;
        
    }
}
