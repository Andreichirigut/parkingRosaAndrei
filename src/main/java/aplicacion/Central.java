/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author andrei
 */
public class Central {

    public static void ejecutarOrden(Comando comando) throws SQLException, IOException, ParseException {
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;
        switch (comando) {
            case DEPOSITAR_VEHICULO:
                GestionVehiculos.altaCliente();
                ejecutarOrden(Menu.menu());

                break;
            case RETIRAR_VEHICULO:
                GestionVehiculos.retirarVehiculo();
                break;
            case DEPOSITAR_ABONOS:
                GestionVehiculos.depositarVehiculoAbonado();
                break;
            case RETIRAR_ABONOS:
                GestionVehiculos.retirarVehiculoAbonado();
                break;
            case VER_ESTADO_PARKING:
                GestionVehiculos.getEstados();
                ejecutarOrden(Menu.menu());
                break;
            case FACTURAR_ENTRE_FECHAS:
                GestionVehiculos.consultarEntreFechas();
                ejecutarOrden(Menu.menu());
                break;
            case FACTURACION_ABONADOS:
                //   GestionVehiculos.calcularTarifa();
                ejecutarOrden(Menu.menu());
                break;
            case DAR_DE_ALTA:
                GestionVehiculos.altaAbonado();
                ejecutarOrden(Menu.menu());
                break;
            case MODIFICAR_ABONO:
                GestionVehiculos.modificarAbonado();
                ejecutarOrden(Menu.menu());
                break;
            case DAR_DE_BAJA:
                GestionVehiculos.bajaAbonado();
                ejecutarOrden(Menu.menu());
                break;
            case CONSULTAR_CADUCIDAD_ABONADOS:
                System.out.println("1.-Consultar caducidad según mes");
                System.out.println("2.-Consultar próximos 10 días");
                opcion = teclado.nextInt();
                while (!(opcion == 1 || opcion == 2)) {
                    System.out.println("ERROR: Vuelva a introducir la opcion");
                    opcion = teclado.nextInt();
                }
                switch (opcion) {
                    case 1:
                        GestionVehiculos.caducidad();
                        ejecutarOrden(Menu.menu());
                        break;
                    case 2:
                        GestionVehiculos.ultimosDias();
                        ejecutarOrden(Menu.menu());
                        break;
                    default:
                        throw new AssertionError();
                }

                break;
//            case CONSULTAR_CADUCIDAD_ABONADOS_10DIAS:
//                GestionVehiculos.altaAbonado();
//                ejecutarOrden(Menu.menu());
//                break;
            case CREAR_COPIA_SEGURIDAD:
                System.out.println("CREAR COPIA DE SEGURIDAD");

                GestionVehiculos.copias();

                break;
            case RESTAURAR_COPIA_SEGURIDAD:
                System.out.println("RESTAURAR COPIA DE SEGURIDAD");
                GestionVehiculos.restaurar();
                break;
            case SALIR:
                System.out.println("Cerrando ");
                break;
            default:
                throw new AssertionError();
        }
    }

    public static void main(String[] args) throws SQLException, IOException, ParseException {
        ejecutarOrden(Menu.menu());
    }
}
