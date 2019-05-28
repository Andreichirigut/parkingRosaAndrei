/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import plaza.PlazasDAO;
import plaza.PlazasVO;
import ticket.TicketDAO;
import ticket.TicketVO;
import vehiculos.VehiculosDAO;
import vehiculos.VehiculosVO;

/**
 *
 * @author andrei
 */
public class GestionVehiculos {

    public static void depositarVehiculo() throws SQLException {
        System.out.println("-------Numero de plazas libres-------");
        PlazasDAO daoPlaza = new PlazasDAO();
        daoPlaza.getEstados();
        System.out.println("------------------");
        Scanner teclado = new Scanner(System.in);

        System.out.println("Introduce la matricula: ");
        String respuesta = teclado.nextLine();
        while (respuesta.length() > 8 || respuesta.length() < 8) {
            System.out.println("ERROR: Vuelve a introducir la matricula");
            respuesta = teclado.nextLine();

        }

        System.out.println("Introduce el tipo: ");
        System.out.println("---Turismo" + "\n" + "---Motocicleta" + "\n" + "---Caravana");
        String respuesta2 = teclado.nextLine();
        while (!(respuesta2.equalsIgnoreCase("Turismo") || respuesta2.equalsIgnoreCase("Motocicleta") || respuesta2.equalsIgnoreCase("Caravana"))) {
            System.out.println("ERROR: Vuelve a introducir el tipo de vehiculo");
            respuesta2 = teclado.nextLine();
        }

        //Se declaran distintos objetos generales necesarios
        Boolean[] plazasEstado = new Boolean[45];
        ArrayList<PlazasVO> listaPlaza = new ArrayList<>();
        PlazasDAO plazas = new PlazasDAO();
        VehiculosDAO vehiculos = new VehiculosDAO();
        VehiculosVO vehiculo = new VehiculosVO(respuesta, respuesta2);

        //Se guarda en la lista las distintas plazas
        listaPlaza = (ArrayList<PlazasVO>) plazas.getAll();
        for (int i = 0; i < listaPlaza.size(); i++) {
            plazasEstado[i] = listaPlaza.get(i).isEstadoPlaza();
        }

        if (respuesta2.equalsIgnoreCase("Turismo")) {
            int contador = 1;
            for (int i = 30; i < 45; i++) {
                //Hacemos k si la plaza esta ocupada la salte
                if (plazasEstado[i] == false) {
                    i = i + contador;
                    contador++;
                }

                if (plazasEstado[i] == true) {
                    //Insertamos un nuevo vehiculo
                    vehiculos.insertVehiculo(vehiculo);
                    //Se actualiza la plaza
                    PlazasVO plazaModificada = listaPlaza.get(i);
                    plazaModificada.setEstadoPlaza(false);
                    //Cambiamos el estado de la plaza a ocupada
                    plazas.updatePlaza(listaPlaza.get(i).getNumPlaza(), plazaModificada);
                    System.out.println("Plaza actualizada");
                    break;

                } 
                

            }
            //Generamos un ticket
            TicketVO ticketVO = new TicketVO(respuesta);
            TicketDAO ticketDAO = new TicketDAO();
            ticketDAO.insertTicket(ticketVO);
            System.out.println("Ticket creado: " + ticketVO);
        }

        if (respuesta2.equalsIgnoreCase("Motocicleta")) {
            int contador = 1; 
            for (int i = 0; i < 14; i++) {                        
                //Hacemos k si la plaza esta ocupada la salte
                if (plazasEstado[i] == false) {
                    i = i + contador;
                    contador++;
                }

                if (plazasEstado[i] == true) {
                    //Insertamos un nuevo vehiculo
                    vehiculos.insertVehiculo(vehiculo);
                    //Se actualiza la plaza
                    PlazasVO plazaModificada = listaPlaza.get(i);
                    plazaModificada.setEstadoPlaza(false);
                    //Cambiamos el estado de la plaza a ocupada
                    plazas.updatePlaza(listaPlaza.get(i).getNumPlaza(), plazaModificada);
                    System.out.println("Plaza actualizada");
                    break;
                } 
            }

            TicketVO ticketVO = new TicketVO(respuesta);
            TicketDAO ticketDAO = new TicketDAO();
            ticketDAO.insertTicket(ticketVO);
            System.out.println("Ticket creado: " + ticketVO);
        }

        if (respuesta2.equalsIgnoreCase("Caravana")) {
            int contador = 1;
            for (int i = 15; i < 29; i++) {
                //Hacemos k si la plaza esta ocupada la salte
                if (plazasEstado[i] == false) {
                    i = i + contador;
                    contador++;
                }

                if (plazasEstado[i] == true) {
                    //Insertamos un nuevo vehiculo
                    vehiculos.insertVehiculo(vehiculo);
                    //Se actualiza la plaza
                    PlazasVO plazaModificada = listaPlaza.get(i);
                    plazaModificada.setEstadoPlaza(false);
                    //Cambiamos el estado de la plaza a ocupada
                    plazas.updatePlaza(listaPlaza.get(i).getNumPlaza(), plazaModificada);
                    System.out.println("Plaza actualizada");
                    break;
                } 
            }

            TicketVO ticketVO = new TicketVO(respuesta);
            TicketDAO ticketDAO = new TicketDAO();
            ticketDAO.insertTicket(ticketVO);
            System.out.println("Ticket creado: " + ticketVO);
        }
    }

    public static void retirarVehiculo() throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce tu matricula: ");
        String matricula = teclado.nextLine();
        System.out.println("Introduce tu numero de Plaza: ");
        int numPlaza = teclado.nextInt();
        System.out.println("Introduce el pin de tu ticket: ");
        String pin = teclado.nextLine();

        //Antes se debe calcular el importe
        
        
        VehiculosDAO vehiculo = new VehiculosDAO();
        TicketDAO ticket = new TicketDAO();
        VehiculosVO vehiculoVO = new VehiculosVO();
        ArrayList<VehiculosVO> listaVehiculo = new ArrayList<>();
        ArrayList<TicketVO> listaTicket = new ArrayList<>();
       
        listaTicket = (ArrayList<TicketVO>) ticket.getAll();
        
//        for (TicketVO ticketVO : listaTicket) {
//            for (VehiculosVO vehiculos : listaVehiculo) {
//                if (vehiculos.getMatricula().equalsIgnoreCase(ticketVO.getMatricula())) {
//                        vehiculoVO = vehiculos;
//                    }
//            }
//            
//        }

    }

    public static void main(String[] args) throws SQLException {
        Menu.menu();
        GestionVehiculos.depositarVehiculo();
    }
}
