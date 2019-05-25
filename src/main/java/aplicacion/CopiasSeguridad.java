/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import abonados.AbonadosVO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import plaza.PlazasVO;
import ticket.TicketVO;
import vehiculos.VehiculosVO;

/**
 *
 * @author Chiri
 */
public class CopiasSeguridad {

    public static void crearCopiaSeguridad(ArrayList<AbonadosVO> lista, String idFichero, ArrayList<PlazasVO> lista2, String idFichero2, ArrayList<TicketVO> lista3, String idFichero3, ArrayList<VehiculosVO> lista4, String idFichero4) {
        // escribimos un nuevo archivo PARA ABONADOS
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {
            // recoremos la lista 
            for (int i = 0; i < lista.size(); i++) {
                flujo.write(
                    lista.get(i).toString()
                );
                flujo.newLine();
           }
            // cuando termine de recorer la lista lo guardamos con flush
            flujo.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
        
        
        // escribimos un nuevo archivo PARA PLAZAS
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero2))) {
            // recoremos la lista 
            for (int i = 0; i < lista2.size(); i++) {
                flujo.write(
                        lista2.get(i).toString()
                );
                flujo.newLine();
            }
            // cuando termine de recorer la lista lo guardamos con flush
            flujo.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
        
        
        // escribimos un nuevo archivo PARA TICKET
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero3))) {
            // recoremos la lista 
            for (int i = 0; i < lista3.size(); i++) {
                flujo.write(
                    lista3.get(i).toString()
                );
                flujo.newLine();
            }
            // cuando termine de recorer la lista lo guardamos con flush
            flujo.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
        
        
        // escribimos un nuevo archivo PARA VEHICULOS
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero4))) {
            // recoremos la lista 
            for (int i = 0; i < lista4.size(); i++) {
                flujo.write(
                    lista4.get(i).toString()
                );
                flujo.newLine();
            }
            // cuando termine de recorer la lista lo guardamos con flush
            flujo.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }
}
