/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;

import abonados.AbonadosVO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rosa
 */
public class ProgramaTicket {

    public static void main(String[] args) {
        TicketDAO ticket=new TicketDAO();
        TicketVO prueba = new TicketVO("789775", 5);
        TicketVO prueba2 = new TicketVO("789885", 6);
        TicketVO prueba3 = new TicketVO("789776", 7);
        TicketVO prueba4 = new TicketVO("789887", 8);
        
        prueba.setPin("000000");
        prueba2.setPin("111111");
        ArrayList<TicketVO> lista= new ArrayList<>();
        lista.add(prueba);
        lista.add(prueba2);
        lista.add(prueba3);
        lista.add(prueba4);
        
        for (TicketVO tickett : lista) {
            System.out.println(tickett.toString());
        }

        try {
            System.out.println("Nº tickets insertadas " + ticket.insertListaTicket(lista));
            System.out.println("-----------------------------------------");
            System.out.println("Comprobamos en una nueva lista que se recogen los datos desde la tabla.");
            List<TicketVO> nuevaLista = ticket.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
            nuevaLista.forEach(System.out::println);
//            System.out.println("-----------------------------------------");
//            System.out.println("Ticket con primary key 1: ");
//            System.out.println(ticket.findByPk("000000"));
//            System.out.println("-----------------------------------------");
//            System.out.println("Se va a borrar el ticket con pk 000000");
//            System.out.println("Nº personas borradas " + ticket.delete_Tickets(prueba));
//            System.out.println("-----------------------------------------");
//            nuevaLista = ticket.getAll();
//            System.out.println("-------- Lista con datos recogidos desde la B.D despues de borrar un ticket-------------");
//            nuevaLista.forEach(System.out::println);
//            System.out.println("-----------------------------------------");
//            System.out.println("Modificación del ticket  con pk 111111");
//            System.out.println("Nº Personas modificadas " + ticket.updateTicket("111111", prueba2));
//            System.out.println("-----------------------------------------");
//            nuevaLista = ticket.getAll();
//            System.out.println("-------- Lista con datos recogidos desde la B.D despues de modificar un ticket -------------");
//            nuevaLista.forEach(System.out::println);
//            System.out.println("-----------------------------------------");
//            System.out.println("Ejecución del procedimiento almacenado");
//            System.out.println("Se cambia Juanillo dinamita por Hola holita");
//            System.out.println("Nombres cambiados " + abo.cambiarNombres("Hola holita", "Juanillo dinamita"));
//            System.out.println("-----------------------------------------");
//            nuevaLista = abo.getAll();
//            System.out.println("-------- Lista con datos recogidos desde la B.D despues de ejecutar proced. -------------");
//            nuevaLista.forEach(System.out::println);
//            System.out.println("-----------------------------------------");

        } catch (SQLException sqle) {
            System.out.println("No se ha podido realizar la operación:");
            System.out.println(sqle.getMessage());
        }

    }
}
