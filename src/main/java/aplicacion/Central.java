/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

/**
 *
 * @author andrei
 */
public class Central {
    public static void ejecutarOrden(Comando comando){
        switch (comando) {
            case DEPOSITAR_VEHICULO:
                System.out.println("");
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
                System.out.println("");
                break;
            case MODIFICAR_ABONO:
                System.out.println("");
                break;
            case DAR_DE_BAJA:
                System.out.println("");
                break;
            case CONSULTAR_CADUCIDAD_ABONADOS:
                System.out.println("");
                break;
            case CONSULTAR_CADUCIDAD_ABONADOS_10DIAS:
                System.out.println("");
                break;
            case CREAR_COPIA_SEGURIDAD:
                System.out.println("");
                break;
            case RESTAURAR_COPIA_SEGURIDAD:
                System.out.println("");
                break;
            default:
                throw new AssertionError();
        }
    }
}
