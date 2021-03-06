/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import abonados.AbonadosDAO;
import abonados.AbonadosVO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;
import plaza.Conexion;
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
        getEstados();
        System.out.println("------------------");
        Scanner teclado = new Scanner(System.in);

        System.out.println("Introduce la matricula: ");
        String respuesta = teclado.nextLine();
        while (respuesta.length() > 7 || respuesta.length() < 7) {
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
        int[] plazasEstado = new int[45];
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
            for (int i = 30; i < 44; i++) {

                if (plazasEstado[i] == 1) {
                    //Insertamos un nuevo vehiculo
                    vehiculos.insertVehiculo(vehiculo);
                    //Se actualiza la plaza
                    PlazasVO plazaModificada = listaPlaza.get(i);
                    plazaModificada.setEstadoPlaza(2);
                    //Cambiamos el estado de la plaza a ocupada
                    plazas.updatePlaza(listaPlaza.get(i).getNumPlaza(), plazaModificada);
                    System.out.println("Plaza actualizada");

                    //Generamos un ticket
                    TicketVO ticketVO = new TicketVO(respuesta, i);
                    TicketDAO ticketDAO = new TicketDAO();
                    ticketDAO.insertTicket(ticketVO);
                    System.out.println("Ticket creado: " + ticketVO);

                    break;

                }

            }

        }

        if (respuesta2.equalsIgnoreCase("Motocicleta")) {
            int contador = 1;
            for (int i = 0; i < 14; i++) {

                if (plazasEstado[i] == 1) {
                    //Insertamos un nuevo vehiculo
                    vehiculos.insertVehiculo(vehiculo);
                    //Se actualiza la plaza
                    PlazasVO plazaModificada = listaPlaza.get(i);
                    plazaModificada.setEstadoPlaza(2);
                    //Cambiamos el estado de la plaza a ocupada
                    plazas.updatePlaza(listaPlaza.get(i).getNumPlaza(), plazaModificada);
                    System.out.println("Plaza actualizada");

                    TicketVO ticketVO = new TicketVO(respuesta, i);
                    TicketDAO ticketDAO = new TicketDAO();
                    ticketDAO.insertTicket(ticketVO);
                    System.out.println("Ticket creado: " + ticketVO);

                    break;
                }
            }

        }

        if (respuesta2.equalsIgnoreCase("Caravana")) {
            int contador = 1;
            for (int i = 16; i < 29; i++) {

                if (plazasEstado[i] == 1) {
                    //Insertamos un nuevo vehiculo
                    vehiculos.insertVehiculo(vehiculo);
                    //Se actualiza la plaza
                    PlazasVO plazaModificada = listaPlaza.get(i);
                    plazaModificada.setEstadoPlaza(2);
                    //Cambiamos el estado de la plaza a ocupada
                    plazas.updatePlaza(listaPlaza.get(i).getNumPlaza(), plazaModificada);
                    System.out.println("Plaza actualizada");

                    TicketVO ticketVO = new TicketVO(respuesta, i);
                    TicketDAO ticketDAO = new TicketDAO();
                    ticketDAO.insertTicket(ticketVO);
                    System.out.println("Ticket creado: " + ticketVO);

                    break;
                }
            }

        }
    }

    public static void retirarVehiculo() throws SQLException, ParseException {

        VehiculosDAO vehiculo = new VehiculosDAO();
        TicketDAO ticket = new TicketDAO();
        PlazasDAO plazas = new PlazasDAO();
//        ArrayList<VehiculosVO> listaVehiculo = new ArrayList<>();
//        ArrayList<TicketVO> listaTicket = new ArrayList<>();
//
//        ArrayList<PlazasVO> listaPlaza = new ArrayLi
        int[] plazasEstado = new int[45];
        ArrayList<VehiculosVO> listaVehiculo = new ArrayList<>();
        ArrayList<TicketVO> listaTicket = new ArrayList<>();
        ArrayList<PlazasVO> listaPlaza = new ArrayList<>();

        listaTicket = (ArrayList<TicketVO>) ticket.getAll();
        listaPlaza = (ArrayList<PlazasVO>) plazas.getAll();

        System.out.println("-------Numero de plazas libres-------");
        PlazasDAO daoPlaza = new PlazasDAO();
        getEstados();
        System.out.println("-----------------------");
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce tu matricula: ");
        String matricula = teclado.nextLine();

        System.out.println("Dime tu tipo de vehiculo: ");
        String tipo = teclado.nextLine();
        while (!(tipo.equalsIgnoreCase("Turismo") || tipo.equalsIgnoreCase("Motocicleta") || tipo.equalsIgnoreCase("Caravana"))) {
            System.out.println("ERROR: Vuelve a introducir el tipo de vehiculo");
            tipo = teclado.nextLine();
        }

        System.out.println("Introduce el pin de tu ticket: ");
        String pin = teclado.nextLine();

        System.out.println("Introduce tu numero de Plaza: ");
        int numPlaza = teclado.nextInt();

        System.out.println("--------------");

        VehiculosVO vehiculoVO = new VehiculosVO(matricula, tipo);
//
//        ArrayList<VehiculosVO> listaVehiculo = new ArrayList<>();
//        ArrayList<TicketVO> listaTicket = new ArrayList<>();
//
//        ArrayList<PlazasVO> listaPlaza = new ArrayList<>();

        listaTicket = (ArrayList<TicketVO>) ticket.getAll();
        listaPlaza = (ArrayList<PlazasVO>) plazas.getAll();

        for (int i = 0; i < listaPlaza.size(); i++) {
            plazasEstado[i] = listaPlaza.get(i).isEstadoPlaza();
        }

        double importe = GestionVehiculos.calcularTarifa(pin);
        System.out.println("Realice el pago por favor: ");
        double pago = teclado.nextDouble();
        while (pago != importe) {
            System.out.println("Error al pagar: Vuelva introducir el importe correcto");
            pago = teclado.nextDouble();
        }

        if (tipo.equalsIgnoreCase("Motocicleta")) {
            int contador = 1;
            for (int i = 0; i < 14; i++) {

                if (plazasEstado[i] == 2) {
                    //Se actualiza la plaza
                    PlazasVO plazaModificada = listaPlaza.get(i);
                    plazaModificada.setEstadoPlaza(1);
                    //Cambiamos el estado de la plaza a ocupada
                    plazas.updatePlaza(listaPlaza.get(i).getNumPlaza(), plazaModificada);
                    System.out.println("Plaza actualizada");
                    break;

                }

            }
        }

        if (tipo.equalsIgnoreCase("Turismo")) {
            int contador = 1;
            for (int i = 30; i < 44; i++) {
//                        

                if (plazasEstado[i] == 2) {
                    //Se actualiza la plaza
                    PlazasVO plazaModificada = listaPlaza.get(i);
                    plazaModificada.setEstadoPlaza(1);
                    //Cambiamos el estado de la plaza a ocupada
                    plazas.updatePlaza(listaPlaza.get(i).getNumPlaza(), plazaModificada);
                    System.out.println("Plaza actualizada");
                    break;

                }

            }
        }

        if (tipo.equalsIgnoreCase("Caravana")) {
            int contador = 1;
            for (int i = 16; i < 29; i++) {
//                        

                if (plazasEstado[i] == 2) {
                    //Se actualiza la plaza
                    PlazasVO plazaModificada = listaPlaza.get(i);
                    plazaModificada.setEstadoPlaza(1);
                    //Cambiamos el estado de la plaza a ocupada
                    plazas.updatePlaza(listaPlaza.get(i).getNumPlaza(), plazaModificada);
                    System.out.println("Plaza actualizada");
                    break;

                }

            }
        }

        for (TicketVO ticketVO : listaTicket) {
            if (matricula.equalsIgnoreCase(ticketVO.getMatricula()) && pin.equalsIgnoreCase(ticketVO.getPin()) && numPlaza == ticketVO.getNumeroPlaza()) {
                vehiculo.deleteVehiculo(vehiculoVO);
                System.out.println("Vehiculo retirado");

            }
        }

        System.out.println("-------Numero de plazas libres-------");
        daoPlaza = new PlazasDAO();
        getEstadosClientes();

    }

    public static void depositarVehiculoAbonado() throws SQLException {
        //Se declaran distintos objetos generales necesarios
        Boolean[] plazasEstado = new Boolean[45];
        ArrayList<PlazasVO> listaPlaza = new ArrayList<>();
        PlazasDAO plazas = new PlazasDAO();
        VehiculosDAO vehiculos = new VehiculosDAO();
        Scanner teclado = new Scanner(System.in);

        System.out.println("Dime tu matricula: ");
        String matricula = teclado.nextLine();
        while (matricula.length() > 7 || matricula.length() < 7) {
            System.out.println("ERROR: Vuelve a introducir la matricula");
            matricula = teclado.nextLine();

        }

        System.out.println("Dime tu DNI");
        String dni = teclado.nextLine();
        while (dni.length() > 9 || dni.length() < 9) {
            System.out.println("ERROR: Vuelve a introducir tu DNI");
            dni = teclado.nextLine();
        }

        System.out.println("Dime tu tipo de vehiculo: ");
        String tipo = teclado.nextLine();
        while (!(tipo.equalsIgnoreCase("Turismo") || tipo.equalsIgnoreCase("Motocicleta") || tipo.equalsIgnoreCase("Caravana"))) {
            System.out.println("ERROR: Vuelve a introducir el tipo de vehiculo");
            tipo = teclado.nextLine();
        }

        VehiculosVO vehi = new VehiculosVO(matricula, tipo);
        VehiculosDAO veDao = new VehiculosDAO();
        TicketDAO tic = new TicketDAO();
        veDao.insertVehiculo(vehi);
        PlazasDAO plaza = new PlazasDAO();
        int num = plazaVacia(tipo);
        if (num != -1) {
            PlazasVO auxi = plaza.findByPk(num);
            auxi.setEstadoPlaza(4);
            plaza.updatePlaza(num, auxi);
            System.out.println("Vehículo estacionado correctamente");
            TicketVO ticket = new TicketVO(matricula, num);
            tic.insertTicket(ticket);
            imprimirTicket(ticket);
            System.out.println(ticket.toString());
        } else {
            System.out.println("No hemos podido estacionar el vehículo");
        }

    }

    public static void retirarVehiculoAbonado() throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce tu matricula: ");
        String matricula = teclado.nextLine();

        System.out.println("Dime tu tipo de vehiculo: ");
        String tipo = teclado.nextLine();
        while (!(tipo.equalsIgnoreCase("Turismo") || tipo.equalsIgnoreCase("Motocicleta") || tipo.equalsIgnoreCase("Caravana"))) {
            System.out.println("ERROR: Vuelve a introducir el tipo de vehiculo");
            tipo = teclado.nextLine();
        }

        System.out.println("Introduce el pin de tu ticket: ");
        String pin = teclado.nextLine();

        System.out.println("Introduce tu numero de Plaza: ");
        int numPlaza = teclado.nextInt();

        VehiculosDAO vehiculo = new VehiculosDAO();
        TicketDAO ticket = new TicketDAO();
        PlazasDAO plazas = new PlazasDAO();
        PlazasDAO daoPlaza = new PlazasDAO();
        ArrayList<PlazasVO> listaPlaza = new ArrayList<>();

        int[] plazasEstado = new int[45];
        ArrayList<VehiculosVO> listaVehiculo = new ArrayList<>();
        ArrayList<TicketVO> listaTicket = new ArrayList<>();

        VehiculosVO vehi = new VehiculosVO(matricula, tipo);
        listaTicket = (ArrayList<TicketVO>) ticket.getAll();
        listaPlaza = (ArrayList<PlazasVO>) plazas.getAll();

        for (int i = 0; i < listaPlaza.size(); i++) {
            plazasEstado[i] = listaPlaza.get(i).isEstadoPlaza();
        }

        VehiculosVO vehiculoVO = new VehiculosVO(matricula, tipo);

        if (tipo.equalsIgnoreCase("Motocicleta")) {
            int contador = 1;
            for (int i = 0; i < 14; i++) {
                for (TicketVO ticketVO : listaTicket) {
                    if (plazasEstado[i] == 4 && pin.equalsIgnoreCase(ticketVO.getPin())) {
                        //Se actualiza la plaza
                        PlazasVO plazaModificada = listaPlaza.get(i);
                        plazaModificada.setEstadoPlaza(3);
                        //Cambiamos el estado de la plaza a ocupada
                        plazas.updatePlaza(listaPlaza.get(i).getNumPlaza(), plazaModificada);
                        System.out.println("Plaza actualizada");
                        break;

                    }
                }

            }
        }

        if (tipo.equalsIgnoreCase("Turismo")) {
            int contador = 1;
            for (int i = 30; i < 44; i++) {
//                       

                for (TicketVO ticketVO : listaTicket) {
                    if (plazasEstado[i] == 4 && pin.equalsIgnoreCase(ticketVO.getPin())) {
                        //Se actualiza la plaza
                        PlazasVO plazaModificada = listaPlaza.get(i);
                        plazaModificada.setEstadoPlaza(3);
                        //Cambiamos el estado de la plaza a ocupada
                        plazas.updatePlaza(listaPlaza.get(i).getNumPlaza(), plazaModificada);
                        System.out.println("Plaza actualizada");
                        break;

                    }
                }

            }
        }

        if (tipo.equalsIgnoreCase("Caravana")) {
            int contador = 1;
            for (int i = 16; i < 29; i++) {
//                       

                for (TicketVO ticketVO : listaTicket) {
                    if (plazasEstado[i] == 4 && pin.equalsIgnoreCase(ticketVO.getPin())) {
                        //Se actualiza la plaza
                        PlazasVO plazaModificada = listaPlaza.get(i);
                        plazaModificada.setEstadoPlaza(3);
                        //Cambiamos el estado de la plaza a ocupada
                        plazas.updatePlaza(listaPlaza.get(i).getNumPlaza(), plazaModificada);
                        System.out.println("Plaza actualizada");
                        break;

                    }
                }

            }
        }
        for (TicketVO ticketVO : listaTicket) {
            if (matricula.equalsIgnoreCase(ticketVO.getMatricula()) && pin.equalsIgnoreCase(ticketVO.getPin()) && numPlaza == ticketVO.getNumeroPlaza()) {
             //   vehiculo.deleteVehiculo(vehiculoVO);
                System.out.println("Vehiculo retirado");

            }
        }

        System.out.println("-------Numero de plazas libres-------");
        daoPlaza = new PlazasDAO();
        getEstados();

    }

    public static void modificarAbonado() throws SQLException {

        AbonadosDAO abo = new AbonadosDAO();
        /*
         Modificación. Existirá la opción de cambiar los datos personales del abonado 
         o bien cambiar la fecha de cancelación del abono, porque el abono ha sido renovado.
         */
        Scanner teclado = new Scanner(System.in);
        Scanner tec2 = new Scanner(System.in);
        System.out.println("Código a modificar");
        int cod = teclado.nextInt();
        teclado.nextLine();

        AbonadosVO aux = new AbonadosVO();
        aux = abo.findByPk(cod);
        System.out.println("-----------------------------------");
        System.out.println("Menú Modificar");
        System.out.println("--1.Modificar nombre");
        System.out.println("--2.Modificar número de tarjeta");
        System.out.println("--3.Ampliar fecha de cancelación");
        System.out.println("-----------------------------------");
        int opcion = teclado.nextInt();
        while (!(opcion == 1 || opcion == 2 || opcion == 3)) {
            System.out.println("ERROR: Vuelva a introducir la opción: ");
            opcion = teclado.nextInt();
        }
        switch (opcion) {
            case 1:
                System.out.println("Introduzca nuevo nombre");
                String nom = tec2.nextLine();
                aux.setNombre(nom);
                abo.updateAbono(cod, aux);
                System.out.println("Modificado con éxito");
                break;
            case 2:
                System.out.println("Introduzca nuevo número de tarjeta");
                String num = tec2.nextLine();
                while (num.length() > 16 || num.length() < 16) {
                    System.out.println("ERROR: Vuelva a introducir el número de tarjeta");
                    num = teclado.nextLine();

                }
                aux.setNumTarjeta(num);
                abo.updateAbono(cod, aux);
                System.out.println("Modificado con éxito");
                break;
            case 3:
                System.out.println("Introduzca meses a ampliar (1,3,6,12)");
                int mes = teclado.nextInt();
                while (!(mes == 1 || mes == 3 || mes == 6 || mes == 12)) {
                    System.out.println("ERROR: Vuelva a introducir el tipo de abono");
                    mes = teclado.nextInt();
                }
                LocalDate vieja = aux.getFechaFin();
                aux.setFechaFin(vieja.plusMonths(mes));
                abo.updateAbono(cod, aux);
                System.out.println("Modificado con éxito");
                break;
            default:
                throw new AssertionError();
        }

    }

    //Método para introducir clientes NO abonados, se genera un fichero txt con el ticket pero no se almacena en la BBDD
    public static void altaCliente() throws SQLException {
        getEstadosClientes();
        AbonadosDAO abo = new AbonadosDAO();
        VehiculosDAO ve = new VehiculosDAO();
        PlazasDAO pla = new PlazasDAO();
        TicketDAO tic = new TicketDAO();
        AbonadosVO cliente = new AbonadosVO();
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca matrícula");
        String matri = teclado.nextLine();
        while (matri.length() > 7 || matri.length() < 7) {
            System.out.println("ERROR: Vuelve a introducir la matricula");
            matri = teclado.nextLine();

        }
        System.out.println("Introduzca el tipo de vehículo");
        String tipo = teclado.nextLine();
        while (!(tipo.equalsIgnoreCase("Turismo") || tipo.equalsIgnoreCase("Motocicleta") || tipo.equalsIgnoreCase("Caravana"))) {
            System.out.println("ERROR: Vuelve a introducir el tipo de vehiculo");
            tipo = teclado.nextLine();
        }
        VehiculosVO aux = new VehiculosVO(matri, tipo);
        aux.setCodAbono(cliente.getPk());
        abo.insertAbonado(cliente);
        ve.insertVehiculo(aux);

        //Aquí llamaremos al método que nos indica el número de plazas libres
        int num = plazaVacia(tipo);

        if (num != -1) {
            PlazasVO auxi = pla.findByPk(num);
            auxi.setEstadoPlaza(2);
            pla.updatePlaza(num, auxi);
            System.out.println("Vehículo estacionado correctamente");
            TicketVO ticket = new TicketVO(matri, num);
            imprimirTicket(ticket);
            tic.insertTicket(ticket);
            System.out.println(ticket.impreso());
        } else {
            System.out.println("No hemos podido estacionar el vehículo");
        }

    }

    //Método para dar de alta a un abonado junto con su vehículo y generamos un ticket
    //que también se almacena en la BBDD
    public static void altaAbonado() throws SQLException {

        AbonadosDAO abo = new AbonadosDAO();

        Scanner teclado = new Scanner(System.in);
        Scanner tec2 = new Scanner(System.in);

        System.out.println("Introduzca su nombre");
        String nom = teclado.nextLine();
        System.out.println("Introduzca número de tarjeta");
        String tarjeta = teclado.nextLine();
        while (tarjeta.length() > 16 || tarjeta.length() < 16) {
            System.out.println("ERROR: Vuelva a introducir el número de tarjeta");
            tarjeta = teclado.nextLine();

        }
        AbonadosVO aux = new AbonadosVO(nom, tarjeta, 1, 0);
        System.out.println("Introduzca tipo abono (1,3,6,12)");
        int tipoAbono = teclado.nextInt();
        while (tipoAbono != 1 && tipoAbono != 3 && tipoAbono != 6 && tipoAbono != 12) {
            System.out.println("ERROR: Vuelva a introducir el tipo de abono");
            tipoAbono = teclado.nextInt();
        }
        aux.setTipoABono(tipoAbono);
        switch (tipoAbono) {
            case 1:
                aux.setImporte(25);
                aux.setFechaFin(LocalDate.now().plusMonths(tipoAbono));
                break;
            case 3:
                aux.setImporte(70);
                aux.setFechaFin(LocalDate.now().plusMonths(tipoAbono));
                break;
            case 6:
                aux.setImporte(130);
                aux.setFechaFin(LocalDate.now().plusMonths(tipoAbono));
                break;
            case 12:
                aux.setImporte(200);
                aux.setFechaFin(LocalDate.now().plusMonths(tipoAbono));
                break;
            default:
                throw new AssertionError();
        }

        abo.insertAbonado(aux);
        System.out.println("-----------------------------");
        System.out.println("Datos del Vehículo: Introduzca matrícula");
        String matri = tec2.nextLine();
        while (matri.length() > 7 || matri.length() < 7) {
            System.out.println("ERROR: Vuelve a introducir la matricula");
            matri = teclado.nextLine();

        }
        System.out.println("Introduzca tipo de vehículo");
        String tipo = tec2.nextLine();
        while (!(tipo.equalsIgnoreCase("Turismo") || tipo.equalsIgnoreCase("Motocicleta") || tipo.equalsIgnoreCase("Caravana"))) {
            System.out.println("ERROR: Vuelve a introducir el tipo de vehiculo");
            tipo = teclado.nextLine();
        }
        VehiculosVO vehi = new VehiculosVO(matri, tipo);
        vehi.setCodAbono(aux.getPk());
        VehiculosDAO veDao = new VehiculosDAO();
        TicketDAO tic = new TicketDAO();
        veDao.insertVehiculo(vehi);
        PlazasDAO plaza = new PlazasDAO();
        int num = plazaVacia(tipo);
        if (num != -1) {
            PlazasVO auxi = plaza.findByPk(num);
            auxi.setEstadoPlaza(4);
            plaza.updatePlaza(num, auxi);
            System.out.println("Vehículo estacionado correctamente");
            TicketVO ticket = new TicketVO(matri, num);
            tic.insertTicket(ticket);
            imprimirTicket(ticket);
            System.out.println(ticket.impreso());
        } else {
            System.out.println("No hemos podido estacionar el vehículo");
        }

    }

    //Método para dar de baja a un abonado, solicitamos pk del abono y si existe
    // hacemos un update de ese abonado borrándole sus datos personales
    public static void bajaAbonado() throws SQLException {
        AbonadosDAO abo = new AbonadosDAO();
        TicketDAO tic = new TicketDAO();
        PlazasDAO pla = new PlazasDAO();
        System.out.println("Introduzca el código de abonado que desea eliminar:");
        Scanner teclado = new Scanner(System.in);
        Scanner teclado_ = new Scanner(System.in);
        int codigo = teclado.nextInt();
        System.out.println("Introduzca el pin de su abono:");
        String pin = teclado_.nextLine();
        TicketVO ticket = tic.findByPk(pin);
        AbonadosVO auxiliar = abo.findByPk(codigo);
        auxiliar.setFechaActiva(LocalDate.now());
        auxiliar.setFechaFin(LocalDate.now());
        auxiliar.setNombre(" ");
        auxiliar.setTipoABono(0);
        auxiliar.setNumTarjeta("----------------");
        abo.updateAbono(codigo, auxiliar);
        int plaza = ticket.getNumeroPlaza();
        PlazasVO plaza_aux = pla.findByPk(plaza);
        plaza_aux.setEstadoPlaza(1);
        pla.updatePlaza(plaza, plaza_aux);

        System.out.println("Abono eliminado con éxito");

    }

    //Método que nos indica el número de abonados que caducan en el mes indicado
    public static void caducidad() throws SQLException {
        //El sistema solicita un mes y nos informa de los abonos que caducan en ese mes.
        AbonadosDAO abo = new AbonadosDAO();
        System.out.println("Introduzca un mes (número)");
        Scanner teclado = new Scanner(System.in);
        int mes = teclado.nextInt();
        while (mes < 1 || mes > 12) {
            System.out.println("ERROR: Vuelva a introducir el mes: ");
            mes = teclado.nextInt();
        }

        List<AbonadosVO> lista = abo.getAll();
        System.out.println("Número de abono/s que caduca/n en el mes " + mes);
        for (AbonadosVO abona : lista) {
            if (abona.getFechaFin().getMonthValue() == mes) {
                System.out.println("" + abona.getPk());
            }
        }

    }

    //Consultar últimos 10 días. El programa informa por consola de los abonos que caducan en los siguientes 10 días a
    //la fecha actual. Posibilidad de envío de un email al abonado recordando que su abono va a caducar.
    public static void ultimosDias() throws SQLException {

        AbonadosDAO abo = new AbonadosDAO();
        List<AbonadosVO> lista = abo.getAll();
        List<AbonadosVO> mail = new ArrayList<>();

        System.out.println("Abonos que caducan en los próximos 10 días");
        for (AbonadosVO abon : lista) {
            if ((abon.getFechaFin().isEqual(LocalDate.now())) || (abon.getFechaFin().isAfter(LocalDate.now()) && abon.getFechaFin().isBefore(LocalDate.now().plusDays(11)))) {

                System.out.println("->  " + abon.getPk());
                mail.add(abon);

            }
        }

        System.out.println("¿Desea enviar un e-mail a los abonados que están próximos a caducar?   S/N");
        Scanner teclado = new Scanner(System.in);
        String respuesta = teclado.nextLine();
        while (!(respuesta.equalsIgnoreCase("s") || respuesta.equalsIgnoreCase("n"))) {
            System.out.println("ERROR: Vuelva a responder: ");
            respuesta = teclado.nextLine();
        }
        if (respuesta.equalsIgnoreCase("S")) {

            for (AbonadosVO vo : mail) {
                System.out.println("Introduzca e-mail del abonado número " + vo.getPk());
                String em = teclado.nextLine();
                String idfichero = em + ".txt";

                try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idfichero))) {
                    flujo.write("Su abono está próximo a caducar. Fecha de fin de abono: " + vo.getFechaFin());
                    flujo.flush();

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("Emails enviados correctamente");

        } else if (respuesta.equalsIgnoreCase("N")) {

        } else {
            System.out.println("No se ha reconocido la respuesta. No se ha enviado ningún email.");
        }

    }

    //Método que nos devuelve el número de la primera plaza vacía en función del tipo de plaza que le pasemos
    public static int plazaVacia(String tipoPlaza) throws SQLException {
        PlazasDAO plazas = new PlazasDAO();
        List<PlazasVO> lista = plazas.getAll();

        for (PlazasVO pla : lista) {

            if ((pla.isEstadoPlaza() == 1 || pla.isEstadoPlaza() == 3) & (pla.getTipoPlaza().equalsIgnoreCase(tipoPlaza))) {

                System.out.println("Plaza libre número " + pla.getNumPlaza());
                return pla.getNumPlaza();

            }

        }
        System.out.println("Ninguna plaza libre para este tipo");
        return -1;
    }

    public static void imprimirTicket(TicketVO ticket) {

        String idfichero = "./Tickets/" + ticket.getPin() + "-" + ticket.getMatricula() + ".txt";

        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idfichero))) {
            flujo.write(ticket.toString());
            flujo.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static double calcularTarifa(String pin) throws ParseException, SQLException {
        TicketDAO tic = new TicketDAO();
        TicketVO ticket = tic.findByPk(pin);
        ticket.setFechaSalida(LocalDate.now());
        ticket.setHora_Salida(LocalTime.now());
        int num = ticket.getNumeroPlaza();
        PlazasDAO pla = new PlazasDAO();
        PlazasVO plaza = pla.findByPk(num);
        double tari = plaza.getTarifa();

        int dia = ticket.getFechaEntrada().getDayOfMonth();
        int mes = ticket.getFechaEntrada().getMonthValue();
        int anio = ticket.getFechaEntrada().getYear();

        int hora = ticket.getHora_Entrada().getHour();
        int min = ticket.getHora_Entrada().getMinute();
        int seg = ticket.getHora_Entrada().getSecond();

        LocalDateTime ini = LocalDateTime.of(anio, mes, dia, hora, min, seg);

        dia = ticket.getFechaSalida().getDayOfMonth();
        mes = ticket.getFechaSalida().getMonthValue();
        anio = ticket.getFechaSalida().getYear();

        hora = ticket.getHora_Salida().getHour();
        min = ticket.getHora_Salida().getMinute();
        seg = ticket.getHora_Salida().getSecond();

        LocalDateTime fin = LocalDateTime.of(anio, mes, dia, hora, min, seg);

        long minutos = ChronoUnit.MINUTES.between(ini, fin);

        double to = minutos * tari;
        ticket.setCosteFinal(to);

        tic.updateTicket(pin, ticket);
        System.out.println("Total a pagar:" + to);
        return to;
    }

    public static void consultarEntreFechas() throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca fecha inicial");
        String fecIni = teclado.nextLine();
        LocalDate ini = LocalDate.parse(fecIni);
        System.out.println("Introduzca fecha final");
        String fecfin = teclado.nextLine();
        LocalDate fin = LocalDate.parse(fecfin);

        TicketDAO tic = new TicketDAO();
        ArrayList<TicketVO> ticket = (ArrayList<TicketVO>) tic.getAll();

        double total = 0;
        for (TicketVO ticketVO : ticket) {
            if (ticketVO.getFechaSalida().isAfter(ini) && ticketVO.getFechaSalida().isBefore(fin)) {
                total += ticketVO.getCosteFinal();
            }
        }

        System.out.println("Total facturación: " + total);

    }

    public static void restaurarVehiculos(String directorio) throws FileNotFoundException, UnsupportedEncodingException, SQLException {

        String linea = "hola";
        VehiculosDAO vehi = new VehiculosDAO();
        Scanner teclado = new Scanner(System.in);

        ArrayList<VehiculosVO> lista = new ArrayList<>();
        String idFichero = "./Backups/" + directorio + "/Vehiculos.txt";

        try (Scanner datosFichero = new Scanner(new InputStreamReader(new FileInputStream(idFichero), "ISO-8859-1"))) {

            while (datosFichero.hasNextLine()) {

                linea = datosFichero.nextLine();
                VehiculosVO aux = new VehiculosVO();

                //Abonados_2019-05-29_1
                String[] cortarString = linea.split("\t");
                String[] cortarPuntos = cortarString[0].split(":");
                String pk = cortarPuntos[1];
                aux.setCodAbono(Integer.parseInt(pk.trim()));
                cortarPuntos = cortarString[1].split(":");
                pk = cortarPuntos[1];
                aux.setMatricula(pk);
                cortarPuntos = cortarString[2].split(":");
                pk = cortarPuntos[1];
                aux.setTipoVehiculo(pk);
                lista.add(aux);

            }

            vehi.insertVehiculo(lista);
            System.out.println("Restauración completada");

        }
    }

    public static void restaurarTickets(String directorio) throws FileNotFoundException, UnsupportedEncodingException, SQLException {
        String linea = "hola";
        TicketDAO tic = new TicketDAO();
        Scanner teclado = new Scanner(System.in);

        ArrayList<TicketVO> lista = new ArrayList<>();
        String idFichero = "./Backups/" + directorio + "/Tickets.txt";

        try (Scanner datosFichero = new Scanner(new InputStreamReader(new FileInputStream(idFichero), "ISO-8859-1"))) {

            while (datosFichero.hasNextLine()) {
                int contador = 1;

                linea = datosFichero.nextLine();
                if (contador % 2 != 0) {

                    TicketVO aux = new TicketVO();

                    //Abonados_2019-05-29_1
                    String[] cortarString = linea.split("\\|");
                    String[] cortarPuntos = cortarString[0].split(":");
                    String pk = cortarPuntos[1];
                    aux.setPin(pk);

                    cortarPuntos = cortarString[1].split(":");
                    pk = cortarPuntos[1];
                    aux.setMatricula(pk);
                    cortarPuntos = cortarString[2].split(":");
                    pk = cortarPuntos[1];
                    aux.setFechaEntrada(LocalDate.parse(pk));
                    cortarPuntos = cortarString[3].split(":");
                    pk = cortarPuntos[1];
                    aux.setFechaSalida(LocalDate.parse(pk));
                    cortarPuntos = cortarString[4].split(":");
                    pk = cortarPuntos[1] + ":" + cortarPuntos[2] + ":" + cortarPuntos[3];
                    aux.setHora_Entrada(LocalTime.parse(pk));
                    cortarPuntos = cortarString[5].split(":");
                    pk = cortarPuntos[1] + ":" + cortarPuntos[2] + ":" + cortarPuntos[3];
                    aux.setHora_Salida(LocalTime.parse(pk));
                    cortarPuntos = cortarString[6].split(":");
                    pk = cortarPuntos[1];
                    aux.setNumeroPlaza(Integer.parseInt(pk));
                    cortarPuntos = cortarString[7].split(":");
                    pk = cortarPuntos[1];
                    aux.setCosteFinal(Double.parseDouble(pk));
                    lista.add(aux);

                }

                tic.insertListaTicket(lista);
                System.out.println("Restauración completada");
            }

        }
    }

    public static void restaurarPlazas(String directorio) throws FileNotFoundException, UnsupportedEncodingException, SQLException {
        String linea = "hola";
        PlazasDAO plaza = new PlazasDAO();
        Scanner teclado = new Scanner(System.in);

        ArrayList<PlazasVO> lista = new ArrayList<>();
        String idFichero = "./Backups/" + directorio + "/Plazas.txt";
        try (Scanner datosFichero = new Scanner(new InputStreamReader(new FileInputStream(idFichero), "ISO-8859-1"))) {

            while (datosFichero.hasNextLine()) {

                linea = datosFichero.nextLine();
                PlazasVO aux = new PlazasVO();

                //Plazas_8_46_27
                String[] cortarString = linea.split("\t");
                String[] cortarPuntos = cortarString[0].split(":");
                String pk = cortarPuntos[1];
                aux.setNumPlaza(Integer.parseInt(pk.trim()));
                cortarPuntos = cortarString[1].split(":");
                pk = cortarPuntos[1];
                aux.setTipoPlaza(pk);
                cortarPuntos = cortarString[2].split(":");
                pk = cortarPuntos[1];
                aux.setEstadoPlaza(Integer.parseInt(pk));
                cortarPuntos = cortarString[3].split(":");
                pk = cortarPuntos[1];
                aux.setTarifa(Double.parseDouble(pk.trim()));
                lista.add(aux);

            }

            plaza.insertPlaza(lista);
            System.out.println("Restauración completada");

        }
    }

//Método que restaura una copia de seguridad en la BBDD, para ello se solicita por teclado el nombre del fichero
    public static void restaurarAbonados(String directorio) throws UnsupportedEncodingException, IOException, SQLException {
        String linea = "hola";
        AbonadosDAO abo = new AbonadosDAO();
        Scanner teclado = new Scanner(System.in);

        ArrayList<AbonadosVO> lista = new ArrayList<>();
        String idFichero = "./Backups/" + directorio + "/Abonados.txt";

        try (Scanner datosFichero = new Scanner(new InputStreamReader(new FileInputStream(idFichero), "ISO-8859-1"))) {

            while (datosFichero.hasNextLine()) {

                linea = datosFichero.nextLine();
                AbonadosVO aux = new AbonadosVO();

                String[] cortarString = linea.split("\t");
                String[] cortarPuntos = cortarString[0].split(":");
                String pk = cortarPuntos[1];
                aux.setPk(Integer.parseInt(pk.trim()));
                cortarPuntos = cortarString[1].split(":");
                pk = cortarPuntos[1];
                aux.setNombre(pk);
                cortarPuntos = cortarString[2].split(":");
                pk = cortarPuntos[1];
                aux.setNumTarjeta(pk);
                cortarPuntos = cortarString[3].split(":");
                pk = cortarPuntos[1];
                aux.setTipoABono(Integer.parseInt(pk.trim()));
                cortarPuntos = cortarString[4].split(":");
                pk = cortarPuntos[1];
                aux.setImporte(Integer.parseInt(pk.trim()));
                cortarPuntos = cortarString[5].split(":");
                pk = cortarPuntos[1];
                aux.setFechaActiva(LocalDate.parse(pk));
                cortarPuntos = cortarString[6].split(":");
                pk = cortarPuntos[1];
                aux.setFechaFin(LocalDate.parse(pk));
                lista.add(aux);

            }
            abo.insertListAbonado(lista);
            System.out.println("Restauración completada");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    //Método que genera un fichero-Copia de seguridad- de todas las plazas
    public static void copiasPlazas(String directorio) throws SQLException {

        PlazasDAO pla = new PlazasDAO();

        List<PlazasVO> lista = pla.getAll();

        String idfichero = directorio + "/Plazas.txt";

        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idfichero))) {

            for (PlazasVO plaza : lista) {
                flujo.write(plaza.toString());
                flujo.newLine();
            }

            flujo.flush();
            System.out.println("Copia de seguridad completada");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void getEstadosClientes() throws SQLException {

        Connection con = null;

        con = Conexion.getInstance();

        String sql = "select count(*) from Plaza where estadoPlaza='1' and tipoPlaza='Turismo'";
        String sql2 = "select count(*) from Plaza where estadoPlaza='1'and tipoPlaza='Caravana'";
        String sql3 = "select count(*) from Plaza where estadoPlaza='1'and tipoPlaza='Motocicleta'";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet

            ResultSet res = null;
            res = prest.executeQuery();
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            if (res.next()) {

                int aux = res.getInt(1);

                System.out.println("Plazas de turismo libres :" + aux);
            }
        }
        try (PreparedStatement prest = con.prepareStatement(sql2)) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet

            ResultSet res = null;
            res = prest.executeQuery();
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            if (res.next()) {

                int aux = res.getInt(1);

                System.out.println("Plazas de caravana libres :" + aux);
            }
        }
        try (PreparedStatement prest = con.prepareStatement(sql3)) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet

            ResultSet res = null;
            res = prest.executeQuery();
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            if (res.next()) {

                int aux = res.getInt(1);

                System.out.println("Plazas de motocicleta libres :" + aux);
            }
        }

    }

    public static void getEstados() throws SQLException {
        // El sistema informa en todo momento del número de plazas libres que existen de cada tipo.

        Connection con = null;

        con = Conexion.getInstance();

        String sql = "select count(*) from Plaza where estadoPlaza='1' and tipoPlaza='Turismo'";
        String sql2 = "select count(*) from Plaza where estadoPlaza='1'and tipoPlaza='Caravana'";
        String sql3 = "select count(*) from Plaza where estadoPlaza='1'and tipoPlaza='Motocicleta'";
        String sql4 = "select count(*) from Plaza where estadoPlaza='2' and tipoPlaza='Turismo'";
        String sql5 = "select count(*) from Plaza where estadoPlaza='2'and tipoPlaza='Caravana'";
        String sql6 = "select count(*) from Plaza where estadoPlaza='2'and tipoPlaza='Motocicleta'";
        String sql7 = "select count(*) from Plaza where estadoPlaza='3' and tipoPlaza='Turismo'";
        String sql8 = "select count(*) from Plaza where estadoPlaza='3'and tipoPlaza='Caravana'";
        String sql9 = "select count(*) from Plaza where estadoPlaza='3'and tipoPlaza='Motocicleta'";
        String sql10 = "select count(*) from Plaza where estadoPlaza='4' and tipoPlaza='Turismo'";
        String sql11 = "select count(*) from Plaza where estadoPlaza='4'and tipoPlaza='Caravana'";
        String sql12 = "select count(*) from Plaza where estadoPlaza='4'and tipoPlaza='Motocicleta'";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet

            ResultSet res = null;
            res = prest.executeQuery();
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            if (res.next()) {

                int aux = res.getInt(1);

                System.out.println("Plazas de turismo libres :" + aux);
            }
        }
        try (PreparedStatement prest = con.prepareStatement(sql2)) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet

            ResultSet res = null;
            res = prest.executeQuery();
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            if (res.next()) {

                int aux = res.getInt(1);

                System.out.println("Plazas de caravana libres :" + aux);
            }
        }
        try (PreparedStatement prest = con.prepareStatement(sql3)) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet

            ResultSet res = null;
            res = prest.executeQuery();
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            if (res.next()) {

                int aux = res.getInt(1);

                System.out.println("Plazas de motocicleta libres :" + aux);
            }
        }

        System.out.println("---------------------------------------------");
        try (PreparedStatement prest = con.prepareStatement(sql4)) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet

            ResultSet res = null;
            res = prest.executeQuery();
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            if (res.next()) {

                int aux = res.getInt(1);

                System.out.println("Plazas de Turismo ocupadas por clientes :" + aux);
            }
        }
        try (PreparedStatement prest = con.prepareStatement(sql5)) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet

            ResultSet res = null;
            res = prest.executeQuery();
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            if (res.next()) {

                int aux = res.getInt(1);

                System.out.println("Plazas de Caravanas ocupadas por clientes :" + aux);
            }
        }

        try (PreparedStatement prest = con.prepareStatement(sql6)) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet

            ResultSet res = null;
            res = prest.executeQuery();
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            if (res.next()) {

                int aux = res.getInt(1);

                System.out.println("Plazas de Motocicletas ocupadas por clientes :" + aux);
            }
        }

        System.out.println("----------------------------------------");

        try (PreparedStatement prest = con.prepareStatement(sql7)) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet

            ResultSet res = null;
            res = prest.executeQuery();
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            if (res.next()) {

                int aux = res.getInt(1);

                System.out.println("Plazas de Turismo libres(ABONADO) :" + aux);
            }
        }
        try (PreparedStatement prest = con.prepareStatement(sql8)) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet

            ResultSet res = null;
            res = prest.executeQuery();
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            if (res.next()) {

                int aux = res.getInt(1);

                System.out.println("Plazas de Caravana libres(ABONADO) :" + aux);
            }
        }

        try (PreparedStatement prest = con.prepareStatement(sql9)) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet

            ResultSet res = null;
            res = prest.executeQuery();
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            if (res.next()) {

                int aux = res.getInt(1);

                System.out.println("Plazas de Motocicletas libres(ABONADO) :" + aux);
            }
        }

        System.out.println("---------------------------------------------");

        try (PreparedStatement prest = con.prepareStatement(sql10)) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet

            ResultSet res = null;
            res = prest.executeQuery();
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            if (res.next()) {

                int aux = res.getInt(1);

                System.out.println("Plazas de Turismo ocupadas(ABONADO) :" + aux);
            }
        }
        try (PreparedStatement prest = con.prepareStatement(sql11)) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet

            ResultSet res = null;
            res = prest.executeQuery();
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            if (res.next()) {

                int aux = res.getInt(1);

                System.out.println("Plazas de Caravana ocupadas(ABONADO) :" + aux);
            }
        }

        try (PreparedStatement prest = con.prepareStatement(sql12)) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet

            ResultSet res = null;
            res = prest.executeQuery();
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            if (res.next()) {

                int aux = res.getInt(1);

                System.out.println("Plazas de Motocicletas ocupadas(ABONADO) :" + aux);
            }
        }
    }

    //Método que genera un fichero-Copia de seguridad- de todos los Vehiculos
    public static void copiasVehiculos(String directorio) throws SQLException {

        VehiculosDAO ve = new VehiculosDAO();

        List<VehiculosVO> lista = ve.getAll();

        String idfichero = directorio + "/Vehiculos.txt";

        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idfichero))) {

            for (VehiculosVO vehiculo : lista) {
                flujo.write(vehiculo.toString());
                flujo.newLine();
            }

            flujo.flush();
            System.out.println("Copia de seguridad completada");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    //Método que genera un fichero-Copia de seguridad- de todos los Tickets
    public static void copiasTickets(String directorio) throws SQLException {

        TicketDAO tic = new TicketDAO();

        List<TicketVO> lista = tic.getAll();

        String idfichero = directorio + "/Tickets.txt";

        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idfichero))) {

            for (TicketVO ticket : lista) {
                flujo.write(ticket.toString());
                flujo.newLine();
            }

            flujo.flush();
            System.out.println("Copia de seguridad completada");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    static void restaurar() throws IOException, UnsupportedEncodingException, SQLException {
        Scanner teclado = new Scanner(System.in);
        //Primero listamos los diferentes directorios que hay
        File f = new File("./Backups");
        if (f.exists()) {
            System.out.println("Directorios:");
            File[] ficheros = f.listFiles();
            for (File file2 : ficheros) {
                System.out.println(file2.getName());
            }
        } else {
            System.out.println("El directorio a listar no existe");
        }

        System.out.println("Escriba el nombre del directorio que desea restaurar");
        String directorio = teclado.nextLine();

        restaurarAbonados(directorio);
        restaurarPlazas(directorio);
        restaurarVehiculos(directorio);
        restaurarTickets(directorio);

    }

    //Método para hacer las copias de seguridad todas a la vez
    static void copias() throws SQLException {

        String directorio = "./Backups/" + LocalTime.now().getHour() + "_" + LocalTime.now().getMinute() + "_" + LocalTime.now().getSecond();
        Path directory = Paths.get(directorio);
        try {
            Files.createDirectory(directory);
        } catch (IOException e) {
            System.out.println("Problema creando el directorio.");
            System.out.println(e.toString());
        }

        copiaAbonados(directorio);
        copiasPlazas(directorio);
        copiasVehiculos(directorio);
        copiasTickets(directorio);
        System.out.println("Copia de seguridad realizada con éxito");

    }

    //Método que genera un fichero-Copia de seguridad- de todos los abonados
    static void copiaAbonados(String directorio) throws SQLException {
        AbonadosDAO abo = new AbonadosDAO();

        List<AbonadosVO> lista = abo.getAll();

        String idfichero = directorio + "/Abonados.txt";

        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idfichero))) {

            for (AbonadosVO abonados : lista) {
                flujo.write(abonados.toString());
                flujo.newLine();
            }

            flujo.flush();
            System.out.println("Copia de seguridad completada");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public static void main(String[] args) throws SQLException {
        //GestionVehiculos.depositarVehiculoAbonado();
        GestionVehiculos.retirarVehiculoAbonado();
    }

}
