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
    public static Comando menu(){
        System.out.println("------BIENVENIDO AL PARKING------");
        
        System.out.println("¿Como desea acceder?");
        Scanner teclado = new Scanner(System.in);
        System.out.println("1) Como Admin");
        System.out.println("2) Como Cliente");
        int opcion = teclado.nextInt();
        while (!(opcion == 1 || opcion == 2)) {
            System.out.println("ERROR: Vuelva a introducir la opción: ");
            opcion = teclado.nextInt();
        }
        
        switch (opcion) {
            case 1:
                System.out.println("------------------------");
                System.out.println("¿Que acción desea realizar?");
                System.out.println("--------------------------");
                System.out.println("4.- Ver estado parking");
                System.out.println("5.- Facturar entre fechas");
                System.out.println("7.- Dar de alta");
                System.out.println("8.- Modificar abono");
                System.out.println("9.- Dar de baja");
                System.out.println("10.- Consultar caducidad abonados");
    //            System.out.println("11.- Consultar caducidad abonados ultimos 10 dias");
                System.out.println("12.- Crear copia de seguridad");
                System.out.println("13.- Restaurar copia de seguridad");
                System.out.println("14.-Salir");
                int opcion2 = teclado.nextInt();
                while (opcion2 < 4 || opcion2 > 14) {
                    System.out.println("ERROR: Vuelve a introducir una accion corecta: ");
                    opcion2 = teclado.nextInt();
                }
                switch (opcion2) {
                    case 4:
                        return Comando.VER_ESTADO_PARKING;
                    case 5:
                        return Comando.FACTURAR_ENTRE_FECHAS;
                    case 7:    
                        return Comando.DAR_DE_ALTA;
                    case 8:
                        return Comando.MODIFICAR_ABONO;
                    case 9:
                        return Comando.DAR_DE_BAJA;
                    case 10:
                        return Comando.CONSULTAR_CADUCIDAD_ABONADOS;
                    case 11:
                        return Comando.CONSULTAR_CADUCIDAD_ABONADOS_10DIAS;
                    case 12:
                        return Comando.CREAR_COPIA_SEGURIDAD;
                    case 13:
                        return Comando.RESTAURAR_COPIA_SEGURIDAD;
                    case 14:
                        return Comando.SALIR;
                    default:
                        throw new AssertionError();
                }
            case 2:
                System.out.println("------------------------");
                System.out.println("¿Que acción desea realizar?");
                System.out.println("--------------------------");
                System.out.println("0.- Depositar vehiculo");
                System.out.println("1.- Retirar vehiculo");
                System.out.println("3.- Depositar abonos");
                System.out.println("4.- Retirar abonos");
                System.out.println("5.-Salir");
                int opcion3 = teclado.nextInt();
                while (opcion3 < 0 || opcion3 > 5) {
                    System.out.println("ERROR: Vuelve a introducir una accion corecta: ");
                    opcion2 = teclado.nextInt();
                }
                switch (opcion3) {
                    case 0:
                        return Comando.DEPOSITAR_VEHICULO;
                    case 1:
                        return Comando.RETIRAR_VEHICULO;
                    case 3:
                        return Comando.DEPOSITAR_ABONOS;
                    case 4: 
                        return Comando.RETIRAR_ABONOS;
                    case 5:
                        return Comando.SALIR;
                    default:
                        throw new AssertionError();
                }
            default:
                throw new AssertionError();
        }
    }
}
