/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.util.Scanner;

/**
 *
 * @author andrei
 */
public class Menu {
    public static void Menu(){
        System.out.println("------BIENVENIDO AL PARKING------");
        
        System.out.println("¿Como desea acceder?");
        Scanner teclado = new Scanner(System.in);
        System.out.println("1) Como Admin");
        System.out.println("2) Como Cliente");
    }
}
