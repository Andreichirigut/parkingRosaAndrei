/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import abonados.AbonadosDAO;
import abonados.AbonadosVO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
            for (int i = 31; i < 45; i++) {
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
            for (int i = 0; i < 15; i++) {
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
            for (int i = 16; i < 30; i++) {
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

                    TicketVO ticketVO = new TicketVO(respuesta, i);
                    TicketDAO ticketDAO = new TicketDAO();
                    ticketDAO.insertTicket(ticketVO);
                    System.out.println("Ticket creado: " + ticketVO);

                    break;
                }
            }

        }
    }

    public static void retirarVehiculo() throws SQLException {

        VehiculosDAO vehiculo = new VehiculosDAO();
        TicketDAO ticket = new TicketDAO();
        PlazasDAO plazas = new PlazasDAO();
//        ArrayList<VehiculosVO> listaVehiculo = new ArrayList<>();
//        ArrayList<TicketVO> listaTicket = new ArrayList<>();
//
//        ArrayList<PlazasVO> listaPlaza = new ArrayLi
        Boolean[] plazasEstado = new Boolean[45];
        ArrayList<VehiculosVO> listaVehiculo = new ArrayList<>();
        ArrayList<TicketVO> listaTicket = new ArrayList<>();
        ArrayList<PlazasVO> listaPlaza = new ArrayList<>();

        listaTicket = (ArrayList<TicketVO>) ticket.getAll();
        listaPlaza = (ArrayList<PlazasVO>) plazas.getAll();

        System.out.println("-------Numero de plazas libres-------");
        PlazasDAO daoPlaza = new PlazasDAO();
        daoPlaza.getEstados();
        System.out.println("-----------------------");
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce tu matricula: ");
        String matricula = teclado.nextLine();
//        for (TicketVO ticketVO : listaTicket) {
//            while (!(matricula.equalsIgnoreCase(ticketVO.getMatricula()))) {
//                System.out.println("ERROR: No se encuentra su matricula ----- Vuelve a introducirla: ");
//                matricula = teclado.nextLine();
//            }
//        }
        System.out.println("Dime tu tipo de vehiculo: ");
        String tipo = teclado.nextLine();
        while (!(tipo.equalsIgnoreCase("Turismo") || tipo.equalsIgnoreCase("Motocicleta") || tipo.equalsIgnoreCase("Caravana"))) {
            System.out.println("ERROR: Vuelve a introducir el tipo de vehiculo");
            tipo = teclado.nextLine();
        }

        System.out.println("Introduce el pin de tu ticket: ");
        String pin = teclado.nextLine();
//        for (TicketVO ticketVO : listaTicket) {
//            while (!(pin.equalsIgnoreCase(ticketVO.getPin()))) {
//                System.out.println("ERROR: No se encuentra su pin ------ Vuelve a introducirlo: ");
//                pin = teclado.nextLine();
//            }
//        }

        System.out.println("Introduce tu numero de Plaza: ");
        int numPlaza = teclado.nextInt();
//        for (TicketVO ticketVO : listaTicket) {
//            while (!(numPlaza == ticketVO.getNumeroPlaza())) {
//                System.out.println("ERROR: No se encuentra su numero de plaza ------ Vuelve a introducirla: ");
//                numPlaza = teclado.nextInt();
//            }
//        }

        System.out.println("--------------");

        //Antes se debe calcular el importe
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
        for (TicketVO ticketVO : listaTicket) {
            if (matricula.equalsIgnoreCase(ticketVO.getMatricula()) && pin.equalsIgnoreCase(ticketVO.getPin()) && numPlaza == ticketVO.getNumeroPlaza()) {
                if (tipo.equalsIgnoreCase("Motocicleta")) {
                    int contador = 1;
                    for (int i = 0; i < 15; i++) {
                        //Hacemos k si la plaza esta ocupada la salte
                        if (plazasEstado[i] == true) {
                            i = i + contador;
                            contador++;
                        }

                        if (plazasEstado[i] == false) {
                            //Se actualiza la plaza
                            PlazasVO plazaModificada = listaPlaza.get(i);
                            plazaModificada.setEstadoPlaza(true);
                            //Cambiamos el estado de la plaza a ocupada
                            plazas.updatePlaza(listaPlaza.get(i).getNumPlaza(), plazaModificada);
                            System.out.println("Plaza actualizada");
                            break;

                        }

                    }
                }

                if (tipo.equalsIgnoreCase("Turismo")) {
                    int contador = 1;
                    for (int i = 31; i < 45; i++) {
                        //Hacemos k si la plaza esta ocupada la salte
                        if (plazasEstado[i] == true) {
                            i = i + contador;
                            contador++;
                        }

                        if (plazasEstado[i] == false) {
                            //Se actualiza la plaza
                            PlazasVO plazaModificada = listaPlaza.get(i);
                            plazaModificada.setEstadoPlaza(true);
                            //Cambiamos el estado de la plaza a ocupada
                            plazas.updatePlaza(listaPlaza.get(i).getNumPlaza(), plazaModificada);
                            System.out.println("Plaza actualizada");
                            break;

                        }

                    }
                }

                if (tipo.equalsIgnoreCase("Caravana")) {
                    int contador = 1;
                    for (int i = 16; i < 30; i++) {
                        //Hacemos k si la plaza esta ocupada la salte
                        if (plazasEstado[i] == true) {
                            i = i + contador;
                            contador++;
                        }

                        if (plazasEstado[i] == false) {
                            //Se actualiza la plaza
                            PlazasVO plazaModificada = listaPlaza.get(i);
                            plazaModificada.setEstadoPlaza(true);
                            //Cambiamos el estado de la plaza a ocupada
                            plazas.updatePlaza(listaPlaza.get(i).getNumPlaza(), plazaModificada);
                            System.out.println("Plaza actualizada");
                            break;

                        }

                    }
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
        daoPlaza.getEstados();

    }

    public static void depositarVehiculoAbonado() {
        //Se declaran distintos objetos generales necesarios
        Boolean[] plazasEstado = new Boolean[45];
        ArrayList<PlazasVO> listaPlaza = new ArrayList<>();
        PlazasDAO plazas = new PlazasDAO();
        VehiculosDAO vehiculos = new VehiculosDAO();
        Scanner teclado = new Scanner(System.in);

        System.out.println("Dime tu matricula: ");
        String matricula = teclado.nextLine();
        while (matricula.length() > 8 || matricula.length() < 8) {
            System.out.println("ERROR: Vuelve a introducir la matricula");
            matricula = teclado.nextLine();

        }

        System.out.println("Dime tu DNI");
        String dni = teclado.nextLine();
        while (dni.length() > 9 || dni.length() < 9) {
            System.out.println("ERROR: Vuelve a introducir la matricula");
        }

    }

    public static void retirarVehiculoAbonado() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce tu matricula: ");
        String matricula = teclado.nextLine();
//        for (TicketVO ticketVO : listaTicket) {
//            while (!(matricula.equalsIgnoreCase(ticketVO.getMatricula()))) {
//                System.out.println("ERROR: No se encuentra su matricula ----- Vuelve a introducirla: ");
//                matricula = teclado.nextLine();
//            }
//        }
        System.out.println("Dime tu tipo de vehiculo: ");
        String tipo = teclado.nextLine();
        while (!(tipo.equalsIgnoreCase("Turismo") || tipo.equalsIgnoreCase("Motocicleta") || tipo.equalsIgnoreCase("Caravana"))) {
            System.out.println("ERROR: Vuelve a introducir el tipo de vehiculo");
            tipo = teclado.nextLine();
        }

        System.out.println("Introduce el pin de tu ticket: ");
        String pin = teclado.nextLine();
//        for (TicketVO ticketVO : listaTicket) {
//            while (!(pin.equalsIgnoreCase(ticketVO.getPin()))) {
//                System.out.println("ERROR: No se encuentra su pin ------ Vuelve a introducirlo: ");
//                pin = teclado.nextLine();
//            }
//        }

        System.out.println("Introduce tu numero de Plaza: ");
        int numPlaza = teclado.nextInt();
//        for (TicketVO ticketVO : listaTicket) {
//            while (!(numPlaza == ticketVO.getNumeroPlaza())) {
//                System.out.println("ERROR: No se encuentra su numero de plaza ------ Vuelve a introducirla: ");
//                numPlaza = teclado.nextInt();
//            }
//        }
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
                aux.setNumTarjeta(num);
                abo.updateAbono(cod, aux);
                System.out.println("Modificado con éxito");
                break;
            case 3:
                System.out.println("Introduzca meses a ampliar (1,3,6,12)");
                int mes = teclado.nextInt();
                LocalDate vieja = aux.getFechaFin();
                aux.setFechaFin(vieja.plusMonths(mes));
                abo.updateAbono(cod, aux);
                System.out.println("Modificado con éxito");
                break;
            default:
                throw new AssertionError();
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
        AbonadosVO aux = new AbonadosVO(nom, tarjeta, 1, 0);
        System.out.println("Introduzca tipo abono (1,3,6,12)");
        int tipoAbono = teclado.nextInt();
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
        System.out.println("Introduzca tipo de vehículo");
        String tipo = tec2.nextLine();
        VehiculosVO vehi = new VehiculosVO(matri, tipo);
        vehi.setCodAbono(aux.getPk());
        VehiculosDAO veDao = new VehiculosDAO();
        TicketDAO tic = new TicketDAO();
        veDao.insertVehiculo(vehi);
        PlazasDAO plaza = new PlazasDAO();
        int num = plazaVacia(tipo);
        if (num != -1) {
            PlazasVO auxi = plaza.findByPk(num);
            auxi.setEstadoPlaza(false);
            plaza.updatePlaza(num, auxi);
            System.out.println("Vehículo estacionado correctamente");
            TicketVO ticket = new TicketVO(matri, num);
            tic.insertTicket(ticket);
            imprimirTicket(ticket);
        } else {
            System.out.println("No hemos podido estacionar el vehículo");
        }

    }

    //Método para dar de baja a un abonado, solicitamos pk del abono y si existe
    // hacemos un update de ese abonado borrándole sus datos personales
    public static void bajaAbonado() throws SQLException {
        AbonadosDAO abo = new AbonadosDAO();
        System.out.println("Introduzca el código de abonado que desea eliminar:");
        Scanner teclado = new Scanner(System.in);
        int codigo = teclado.nextInt();
        AbonadosVO auxiliar = abo.findByPk(codigo);
        auxiliar.setFechaActiva(LocalDate.now());
        auxiliar.setFechaFin(LocalDate.now());
        auxiliar.setNombre(" ");
        auxiliar.setTipoABono(0);
        auxiliar.setNumTarjeta("----------------");
        auxiliar.setImporte(-1);
        abo.updateAbono(codigo, auxiliar);
        System.out.println("Abono eliminado con éxito");

    }

    //Método que nos indica el número de abonados que caducan en el mes indicado
    public static void caducidad() throws SQLException {
        //El sistema solicita un mes y nos informa de los abonos que caducan en ese mes.
        AbonadosDAO abo = new AbonadosDAO();
        System.out.println("Introduzca un mes (número)");
        Scanner teclado = new Scanner(System.in);
        int mes = teclado.nextInt();

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

        System.out.println("Abonos que caducan en los próximos 10 días");
        for (AbonadosVO abon : lista) {
            if ((abon.getFechaFin().isEqual(LocalDate.now())) || (abon.getFechaFin().isAfter(LocalDate.now()) && abon.getFechaFin().isBefore(LocalDate.now().plusDays(11)))) {

                System.out.println("->  " + abon.getPk());

            }
        }

    }

    //Método que nos devuelve el número de la primera plaza vacía en función del tipo de plaza que le pasemos
    public static int plazaVacia(String tipoPlaza) throws SQLException {
        PlazasDAO plazas = new PlazasDAO();
        List<PlazasVO> lista = plazas.getAll();

        for (PlazasVO pla : lista) {

            if ((pla.isEstadoPlaza() == true) & (pla.getTipoPlaza().equalsIgnoreCase(tipoPlaza))) {

                System.out.println("Plaza libre número " + pla.getNumPlaza());
                return pla.getNumPlaza();

            }

        }
        System.out.println("Ninguna plaza libre para este tipo");
        return -1;
    }
    
    public static void imprimirTicket(TicketVO ticket){
        
        String idfichero=ticket.getPin()+"-"+ticket.getMatricula()+".txt";
        
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idfichero))){
         flujo.write(ticket.toString());
         flujo.flush();	
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} 
        
    }

    public static void main(String[] args) throws SQLException {

//        Menu.menu();
//        GestionVehiculos.depositarVehiculo();
        // GestionVehiculos.retirarVehiculo();
        // Menu.menu();
        // GestionVehiculos.depositarVehiculo();
//        GestionVehiculos.altaAbonado();
        //  GestionVehiculos.modificarAbonado();
        GestionVehiculos.altaAbonado();
        //  GestionVehiculos.modificarAbonado();
        // GestionVehiculos.plazaVacia("Motocicleta");
        //GestionVehiculos.bajaAbonado();
        //GestionVehiculos.caducidad();
       // GestionVehiculos.ultimosDias();
    }
}
