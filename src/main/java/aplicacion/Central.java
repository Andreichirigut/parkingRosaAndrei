/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author andrei
 */
public class Central {

    public static void ejecutarOrden(Comando comando) throws SQLException, IOException {
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;
        switch (comando) {
            case DEPOSITAR_VEHICULO:
                GestionVehiculos.altaCliente();
                break;
            case RETIRAR_VEHICULO:
                System.out.println("");
                break;
            case DEPOSITAR_ABONOS:
                System.out.println("");
                break;
            case RETIRAR_ABONOS:
                System.out.println("");
                break;
            case VER_ESTADO_PARKING:
                System.out.println("");
                break;
            case FACTURAR_ENTRE_FECHAS:
                System.out.println("");
                break;
            case FACTURACION_ABONADOS:
                System.out.println("");
                break;
            case DAR_DE_ALTA:
                GestionVehiculos.altaAbonado();
                break;
            case MODIFICAR_ABONO:
                GestionVehiculos.modificarAbonado();
                break;
            case DAR_DE_BAJA:
                GestionVehiculos.bajaAbonado();
                break;
            case CONSULTAR_CADUCIDAD_ABONADOS:
                System.out.println("1.-Consultar caducidad según mes");
                System.out.println("2.-Consultar próximos 10 días");
                opcion=teclado.nextInt();
                while (!(opcion == 1 || opcion ==2)) {
                    System.out.println("ERROR: Vuelva a introducir la opcion");
                    opcion=teclado.nextInt();
                }
                switch (opcion) {
                    case 1:
                        GestionVehiculos.caducidad();
                        break;
                    case 2: GestionVehiculos.ultimosDias();
                    default:
                        throw new AssertionError();
                }

                break;
            case CONSULTAR_CADUCIDAD_ABONADOS_10DIAS:
                GestionVehiculos.altaAbonado();
                break;
            case CREAR_COPIA_SEGURIDAD:
                System.out.println("CREAR COPIA DE SEGURIDAD");
                System.out.println("1.-De Clientes");
                System.out.println("2.-De Vehiculos");
                System.out.println("3.-De Plazas");
                System.out.println("4.-De tickets");
                opcion = teclado.nextInt();
                while (opcion < 1 || opcion > 4) {
                    System.out.println("ERROR: Vuelva a introducir la opcion: ");
                    opcion = teclado.nextInt();
                }
                switch (opcion) {
                    case 1:
                        GestionVehiculos.copiaAbonados();
                        break;
                    case 2:
                        GestionVehiculos.copiasVehiculos();
                        break;
                    case 3:
                        GestionVehiculos.copiasPlazas();
                        break;
                    case 4:
                        GestionVehiculos.copiasTickets();
                        break;
                    default:
                        throw new AssertionError();
                }

                break;
            case RESTAURAR_COPIA_SEGURIDAD:
                System.out.println("1.-Restaurar abonados");
                System.out.println("2.-Restaurar Vehiculos");
                System.out.println("3.-Restaurar plazas");
                System.out.println("4.-Restaurar tickets");
                opcion=teclado.nextInt();
                while (opcion < 1 || opcion > 4) {
                    System.out.println("ERROR: Vuelva a introducir la opcion: ");
                    opcion = teclado.nextInt();
                }
                switch (opcion) {
                    case 1:
                        GestionVehiculos.restaurarAbonados();
                        break;
                    case 2:
                        GestionVehiculos.restaurarVehiculos();
                        break;
                    case 3:
                        GestionVehiculos.restaurarPlazas();
                        break;
                    case 4:
                        GestionVehiculos.restaurarTickets();
                        break;
                    default:
                        throw new AssertionError();
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    public static void main(String[] args) throws SQLException, IOException {
        ejecutarOrden(Menu.menu());
    }
}
