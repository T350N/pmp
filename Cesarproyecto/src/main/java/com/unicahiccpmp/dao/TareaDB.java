/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicahiccpmp.dao;

import com.unicahiccpmp.cesarproyecto.TareaItem;
import java.util.ArrayList;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author cahf2
 */
public class TareaDB {
    
    private ArrayList _peliItems;
    
    public TareaDB(){
        this._peliItems = new ArrayList<TareaItem>();
    }
    
    public ArrayList<TareaItem> getPeliculaItems(){
        return this.getPeliculaItems(false);
    }
    
    public void tableInitialize(){
        String sqlCreate = "CREATE TABLE IF NOT EXISTS PELICULA"
                        + " (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                        + " NOMBRE TEXT NOT NULL,"
                        + " AUTORES TEXT NOT NULL,"
                        + " PARTES INTEGER NOT NULL,"
                        + " AÑOS INTEGER NOT NULL"
                        + " )";
       
        try {
            Statement comando = Conexion.getConexion().createStatement();
            int resultado = comando.executeUpdate(sqlCreate);
            comando.close();
        } catch( Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public ArrayList<TareaItem> getPeliculaItems(boolean forceLoad){
        try {
           if (forceLoad) {
                Statement comando =  Conexion.getConexion().createStatement();
                ResultSet misRegistro = comando.executeQuery("SELECT * from PELICULA;");
                this._peliItems.clear();
                while (misRegistro.next()) {
                    TareaItem registro = new TareaItem();
                    registro.setId(misRegistro.getInt("ID"));
                    registro.setNombre(misRegistro.getString("NOMBRE"));
                    registro.setAutores(misRegistro.getString("AUTORES"));
                    registro.setParte(misRegistro.getInt("PARTES"));
                    registro.setYear(misRegistro.getInt("AÑOS"));
                    
                    this._peliItems.add(registro);
                }
                comando.close();
           }
           return this._peliItems;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
            return this._peliItems;
        }   
    }
    
    public TareaItem getPeliculaItemById(int id){
        try {
            String SQLGetByID = "SELECT * FROM PELICULA WHERE ID = ?;";
            PreparedStatement comando =  Conexion.getConexion().prepareStatement(SQLGetByID);
            comando.setInt(1, id);
            ResultSet misRegistro = comando.executeQuery();
            TareaItem registro = new TareaItem();
            while (misRegistro.next()) {
                registro.setId(misRegistro.getInt("ID"));
                registro.setNombre(misRegistro.getString("NOMBRE"));
                registro.setAutores(misRegistro.getString("AUTORES"));
                registro.setParte(misRegistro.getInt("PARTES"));
                registro.setYear(misRegistro.getInt("AÑOS"));
                
                break;
            }
            comando.close();

            return registro;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
            return null;
        }   
    }
    
    public int updatePeliculaItem(TareaItem ItemToUpdate) {
        try {
            String SQLUpdate = "UPDATE PELICULA set NOMBRE=?, AUTORES=?, PARTES=?, AÑOS=? where ID=?;";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLUpdate);
            
            comando.setString(1, ItemToUpdate.getNombre());
            comando.setString(2, ItemToUpdate.getAutores());
            comando.setInt(3, ItemToUpdate.getParte());
            comando.setInt(4, ItemToUpdate.getYear());
            comando.setInt(5, ItemToUpdate.getId());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
     public int insertPeliculaItem(TareaItem ItemToInsert) {
        try {
            String SQLInsert = "INSERT INTO PELICULA (NOMBRE, AUTORES, PARTES, AÑOS) values (?, ?, ?, ?);";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLInsert);
            
            comando.setString(1, ItemToInsert.getNombre());
            comando.setString(2, ItemToInsert.getAutores());
            comando.setInt(3, ItemToInsert.getParte());
            comando.setInt(4, ItemToInsert.getYear());
            
                    
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
     
    public int deletePeliculaItem(TareaItem ItemToDelete) {
        try {
            String SQLDelete = "DELETE FROM PELICULA WHERE ID = ?;";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLDelete);
            
            comando.setInt(1, ItemToDelete.getId());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
}
