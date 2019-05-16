/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rosa
 */
public class Programa {
    
     public static void main(String[] args) {
        AbonadosDAO abo= new AbonadosDAO();
        ArrayList <AbonadosVO> lista2= new ArrayList <>();
        
        lista2.add(new AbonadosVO("Juanillo dinamita", "0000000000000000", 1, 14));
        lista2.add(new AbonadosVO("Sergy del valle", "1111111111111111", 1, 16));
         lista2.add(new AbonadosVO("Rosa Caracuel", "0000000000000000", 1, 13));
        AbonadosVO uno=new AbonadosVO("Isi García", "1111111111111111", 1, 18);
        lista2.add(uno);
        AbonadosVO paraUpdate=new AbonadosVO("María Fernández", "1111111113561111", 2, 10);
        
        for (AbonadosVO abonadosVO : lista2) {
            System.out.println(abonadosVO.toString());
        }
        
        try{
         System.out.println("Nº personas insertadas " + abo.insertListAbonado(lista2));
            System.out.println("-----------------------------------------");
            System.out.println("Comprobamos en una nueva lista que se recogen los datos desde la tabla.");
            List<AbonadosVO> nuevaLista = abo.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
            nuevaLista.forEach(System.out::println);
             System.out.println("-----------------------------------------");
            System.out.println("Persona con primary key 1: ");
            System.out.println(abo.findByPk(1));
            System.out.println("-----------------------------------------");
            System.out.println("Se va a borrar la persona con pk 3");
            System.out.println("Nº personas borradas " + abo.deleteAbonados(uno));
            System.out.println("-----------------------------------------");
            nuevaLista = abo.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D despues de borrar una persona -------------");
            nuevaLista.forEach(System.out::println);
            System.out.println("-----------------------------------------");
            System.out.println("Modificación de la persona con pk 1");
            System.out.println("Nº Personas modificadas " + abo.updateAbono(1, paraUpdate));
            System.out.println("-----------------------------------------");
            nuevaLista = abo.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D despues de modificar una persona -------------");
            nuevaLista.forEach(System.out::println);
            System.out.println("-----------------------------------------");
            System.out.println("Ejecución del procedimiento almacenado");
            System.out.println("Se cambia Juanillo dinamita por Hola holita");
            System.out.println("Nombres cambiados " + abo.cambiarNombres("Hola holita", "Juanillo dinamita"));
            System.out.println("-----------------------------------------");
            nuevaLista = abo.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D despues de ejecutar proced. -------------");
            nuevaLista.forEach(System.out::println);
            System.out.println("-----------------------------------------");
            
        
        }catch (SQLException sqle) {
            System.out.println("No se ha podido realizar la operación:");
            System.out.println(sqle.getMessage());
        }
        
    }
}
