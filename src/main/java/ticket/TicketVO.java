/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;

import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author rosa
 */
public class TicketVO {
    
    //Atributos
    private int pin;
    private String matricula;
    private LocalDate fecha;
    private int numeroPlaza;
    
    //Constructores

    public TicketVO(String matricula) {
        Random rnd= new Random();
        
        this.pin = pin;
        this.matricula = matricula;
        this.fecha = fecha;
        this.numeroPlaza = numeroPlaza;
    }

    public TicketVO() {
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getNumeroPlaza() {
        return numeroPlaza;
    }

    public void setNumeroPlaza(int numeroPlaza) {
        this.numeroPlaza = numeroPlaza;
    }

    @Override
    public String toString() {
        return "TicketVO{" + "pin=" + pin + ", matricula=" + matricula + ", fecha=" + fecha + ", numeroPlaza=" + numeroPlaza + '}';
    }
    
    
}
