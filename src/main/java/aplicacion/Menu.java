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
        
        switch (opcion) {
            case 1:
                System.out.println("------------------------");
                System.out.println("¿Que acción desea realizar?");
                System.out.println("--------------------------");
                System.out.println("4.- Ver estado parking");
                System.out.println("5.- Facturar entre fechas");
                System.out.println("6.- Facturar los abonados");
                System.out.println("7.- Dar de alta");
                System.out.println("8.- Modificar abono");
                System.out.println("9.- Dar de baja");
                System.out.println("10.- Consultar caducidad abonados");
                System.out.println("11.- Consultar caducidad abonados ultimos 10 dias");
                System.out.println("12.- Crear copia de seguridad");
                System.out.println("13.- Restaurar copia de seguridad");
                int opcion2 = teclado.nextInt();
                switch (opcion2) {
                    case 4:
                        return Comando.VER_ESTADO_PARKING;
                    case 5:
                        return Comando.FACTURAR_ENTRE_FECHAS;
                    case 6:
                        return Comando.FACTURACION_ABONADOS;
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
                    default:
                        throw new AssertionError();
                }
                
            default:
                throw new AssertionError();
        }
    }
}
