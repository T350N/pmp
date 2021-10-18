/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicahiccpmp.cesarproyecto;


import com.unicahiccpmp.Layout.Layout;
import java.util.Scanner;

/**
 *
 * @author cahf2
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Layout.printHeader("PELICULA");
        String OpcionMenu = "";
        
        Scanner entradaTeclado = new Scanner(System.in);
        
        Aplicacion peliAPP = new Aplicacion(entradaTeclado);
        
        while (!(OpcionMenu.toUpperCase().equals("S"))) {
            Layout.printMenu();
            OpcionMenu = entradaTeclado.nextLine();

            System.out.println("Texto ingresado: " + OpcionMenu);
            peliAPP.activarEvento(OpcionMenu);
            
        }
    }
    
}
