/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

/**
 *
 * @author rosa
 */
public class TicketVO {
    
    //Atributos
    private String pin="";
    private String matricula;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private LocalTime hora_Entrada;
    private LocalTime hora_Salida;
    private int numeroPlaza;
    private double costeFinal;
 
    
    //Constructores

    public TicketVO(String matricula, int numeroPlaza) {
        Random rnd= new Random();
        int[] arrayPin= new int[6];
            for (int i = 0; i <6; i++) {
            arrayPin[i]=rnd.nextInt(9)+1;
            pin+=Integer.toString(arrayPin[i]);
        }
        this.matricula = matricula;
        this.fechaEntrada = LocalDate.now();
        this.fechaSalida=LocalDate.now();
        this.hora_Entrada=LocalTime.now();
        this.hora_Salida=LocalTime.now();
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

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }


    public LocalTime getHora_Entrada() {
        return hora_Entrada;
    }

    public void setHora_Entrada(LocalTime hora_Entrada) {
        this.hora_Entrada = hora_Entrada;
    }

    public LocalTime getHora_Salida() {
        return hora_Salida;
    }

    public void setHora_Salida(LocalTime hora_Salida) {
        this.hora_Salida = hora_Salida;
    }



    public double getCosteFinal() {
        return costeFinal;
    }

    public void setCosteFinal(double costeFinal) {
        this.costeFinal = costeFinal;
    }

   
    public int getNumeroPlaza() {
        return numeroPlaza;
    }

    public void setNumeroPlaza(int numeroPlaza) {
        this.numeroPlaza = numeroPlaza;
    }

    @Override
    public String toString() {
        return "Pin:" + pin + "|Matrícula:" + matricula + "|Fecha Entrada:" + fechaEntrada + "|Fecha Salida:" + fechaSalida + "|Hora Entrada:" + hora_Entrada + "|Hora_Salida:" + hora_Salida + "|NumeroPlaza:" + numeroPlaza + "|CosteFinal:" + costeFinal;
        
    }

   

   

   
    public static void main(String[] args) {
        
        TicketVO prueba=new TicketVO("789775", 9);
        TicketVO prueba2=new TicketVO("789885", 7);
        
        System.out.println(prueba.toString());
                System.out.println(prueba2.toString());

        
    }
    
    
}
