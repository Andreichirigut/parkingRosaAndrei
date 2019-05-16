/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiculos;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chiri
 */
public class Programa {

    public static void main(String[] args) {
        VehiculosDAO daoVehiculo = new VehiculosDAO();
        List<VehiculosVO> listaVehiculos = new ArrayList<>();
        listaVehiculos.add(new VehiculosVO(100, "23456XCP", "Turismo"));
        listaVehiculos.add(new VehiculosVO(101, "23456XCX", "Motocicleta"));
        listaVehiculos.add(new VehiculosVO(102, "23456XCR", "Caravana"));

        try {

            System.out.println("Nº plazas insertados " + daoVehiculo.insertVehiculo(listaVehiculos));
            System.out.println("-----------------------------------------");
            System.out.println("Comprobamos en una nueva lista que se recogen los datos desde la tabla.");
            List<VehiculosVO> nuevaLista = daoVehiculo.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
            nuevaLista.forEach(System.out::println);
//            System.out.println("-----------------------------------------");
//            System.out.println("Plaza con primary key 101: ");
//            System.out.println(daoPlaza.findByPk(101));
//            System.out.println("-----------------------------------------");
//            System.out.println("Se va a borrar la plaza con pk 104");
//            System.out.println("Nº centros borradas "
//                    + daoPlaza.deletePlaza(new VehiculosVO(104, "Turismo", false, 0.12)));
//            System.out.println("-----------------------------------------");
//            nuevaLista = daoPlaza.getAll();
//            System.out.println("-------- Lista con datos recogidos desde la B.D despues de borrar un centro -------------");
//            nuevaLista.forEach(System.out::println);
//            System.out.println("-----------------------------------------");
//            System.out.println("Modificación de la plaza con pk 105");
//            System.out.println("Nº plazas modificados "
//                    + daoPlaza.updatePlaza(105, new VehiculosVO(105, "Caravana", false, 0.45)));
//            System.out.println("-----------------------------------------");
//            nuevaLista = daoPlaza.getAll();
//            System.out.println("-------- Lista con datos recogidos desde la B.D despues de modificar una persona -------------");
//            nuevaLista.forEach(System.out::println);
        } catch (SQLException sqle) {
            System.out.println("No se ha podido realizar la operación:");
            System.out.println(sqle.getMessage());
        }
        
    }
}
