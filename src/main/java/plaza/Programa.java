/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plaza;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chiri
 */
public class Programa {

    public static void main(String[] args) {
        PlazasDAO daoPlaza = new PlazasDAO();
        List<PlazasVO> listaPlazas = new ArrayList<>();
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Turismo"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Caravana"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        listaPlazas.add(new PlazasVO("Motocicleta"));
        
        

        try {

            System.out.println("Nº plazas insertados " + daoPlaza.insertPlaza(listaPlazas));
            System.out.println("-----------------------------------------");
            System.out.println("Comprobamos en una nueva lista que se recogen los datos desde la tabla.");
            List<PlazasVO> nuevaLista = daoPlaza.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
            nuevaLista.forEach(System.out::println);
            System.out.println("-----------------------------------------");
            System.out.println("Plaza con primary key 45: ");
            System.out.println(daoPlaza.findByPk(42));
            System.out.println("-----------------------------------------");
            System.out.println("Se va a borrar la plaza con pk 32");
            System.out.println("Nº centros borradas "
                    + daoPlaza.deletePlaza(32));
            System.out.println("-----------------------------------------");
            nuevaLista = daoPlaza.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D despues de borrar un centro -------------");
            nuevaLista.forEach(System.out::println);
            System.out.println("-----------------------------------------");
            System.out.println("Modificación de la plaza con pk 10");
            System.out.println("Nº plazas modificados "
                    + daoPlaza.updatePlaza(10, new PlazasVO("Caravana")));
            System.out.println("-----------------------------------------");
            nuevaLista = daoPlaza.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D despues de modificar una persona -------------");
            nuevaLista.forEach(System.out::println);
        } catch (SQLException sqle) {
            System.out.println("No se ha podido realizar la operación:");
            System.out.println(sqle.getMessage());
        }
        
        
    }
}
