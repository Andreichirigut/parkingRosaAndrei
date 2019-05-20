/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;


import java.time.LocalDateTime;
import java.util.Random;

/**
 *
 * @author rosa
 */
public class TicketVO {
    
    //Atributos
    private String pin="";
    private String matricula;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private int numeroPlaza;
    private double costeFinal;
    
    //Constructores

    public TicketVO(String matricula) {
        Random rnd= new Random();
        int[] arrayPin= new int[6];
            for (int i = 0; i <6; i++) {
            arrayPin[i]=rnd.nextInt(9)+1;
            pin+=Integer.toString(arrayPin[i]);
        }
        this.matricula = matricula;
        this.fechaEntrada = LocalDateTime.now();
        this.fechaSalida=LocalDateTime.now();
        this.numeroPlaza = numeroPlaza;
        this.costeFinal=0;
    }

    public TicketVO() {
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

   

    public int getNumeroPlaza() {
        return numeroPlaza;
    }

    public void setNumeroPlaza(int numeroPlaza) {
        this.numeroPlaza = numeroPlaza;
    }

    @Override
    public String toString() {
        return "TicketVO{" + "pin=" + pin + ", matricula=" + matricula + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", numeroPlaza=" + numeroPlaza + '}';
    }

   
    public static void main(String[] args) {
        
        TicketVO prueba=new TicketVO("789775");
        TicketVO prueba2=new TicketVO("789885");
        
        System.out.println(prueba.toString());
                System.out.println(prueba2.toString());

        
    }
    
    
}
