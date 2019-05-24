/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.sql.SQLException;
import java.util.Scanner;
import plaza.PlazasDAO;
import plaza.PlazasVO;
import ticket.TicketDAO;
import ticket.TicketVO;

/**
 *
 * @author andrei
 */
public class GestionVehiculos {
     public static void depositarVehiculo() throws SQLException{
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
         
         if (respuesta2.equalsIgnoreCase("turismo")) {
             TicketVO ticketVO = new TicketVO(respuesta);
             TicketDAO ticketDAO = new TicketDAO();
             ticketDAO.insertTicket(ticketVO);
         }
     }
     
     public static void main(String[] args) throws SQLException {
         Menu.menu();
        GestionVehiculos.depositarVehiculo();
    }
}
