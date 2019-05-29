/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonados;

import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author rosa
 */
public class AbonadosVO {
    
  
    private int pk;
    private String nombre;
    private String numTarjeta;
    private int tipoAbono; //Para que sea 1,2,3,6,12 (meses)
    private int importe;
    private LocalDate fechaActiva;
    private LocalDate fechaFin;


    public AbonadosVO(String nombre, String numTarjeta, int tipoAbono, int importe) {
        Random rnd=new Random();
        int num=rnd.nextInt(999);
        this.pk =num;
        this.nombre = nombre;
        this.numTarjeta = numTarjeta;
        this.tipoAbono = tipoAbono;
        this.importe = importe;
        this.fechaActiva = LocalDate.now(); //Cuando se crea un abono se pone la fecha actual
        this.fechaFin=LocalDate.now().plusMonths(tipoAbono);//le sumo los meses que esté abonado
        
        
    }

    public AbonadosVO() {
        Random rnd=new Random();
        int num=rnd.nextInt(999);
        this.pk =num;
        this.nombre = "Cliente";
        this.numTarjeta = "0000000000000000";
        this.tipoAbono = 0;
        this.importe = 0;
        this.fechaActiva = LocalDate.now(); //Cuando se crea un abono se pone la fecha actual
        this.fechaFin=LocalDate.now().plusMonths(tipoAbono);//le sumo los meses que esté abonado
        
        
    }
    
    //GETTERS Y SETTERS

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public int getTipoABono() {
        return tipoAbono;
    }

    public void setTipoABono(int tipoABono) {
        this.tipoAbono = tipoABono;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public LocalDate getFechaActiva() {
        return fechaActiva;
    }

    public void setFechaActiva(LocalDate fechaActiva) {
        this.fechaActiva = fechaActiva;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }


    
    //ToString

    @Override
    public String toString() {
        return "Abono nº:"+pk+"\t Nombre:"+ nombre+"\t Num.Tarjeta:"+numTarjeta+"\t Tipo Abono:"+tipoAbono+"\t Importe"+importe+"\t Fecha inicio:"+fechaActiva+"\t Fecha Fin:"+fechaFin;
    }
    
 


}
