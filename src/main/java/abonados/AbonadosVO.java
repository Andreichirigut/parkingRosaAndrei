/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonados;

import java.time.LocalDate;

/**
 *
 * @author rosa
 */
public class AbonadosVO {
    
  
    private int pk;
    private int codCliente;
    private String nombre;
    private String numTarjeta;
    private int tipoAbono; //Para que sea 1,2,3,6,12 (meses)
    private int importe;
    private LocalDate fechaActiva;
    private LocalDate fechaFin;
    private static int contador=0;
    private static int contCliente=999;

    public AbonadosVO(String nombre, String numTarjeta, int tipoAbono, int importe) {
       
        this.pk = contador;
        this.codCliente = contCliente--;
        this.nombre = nombre;
        this.numTarjeta = numTarjeta;
        this.tipoAbono = tipoAbono;
        this.importe = importe;
        this.fechaActiva = LocalDate.now(); //Cuando se crea un abono se pone la fecha actual
        this.fechaFin=LocalDate.now().plusMonths(tipoAbono);//le sumo los meses que esté abonado
        contador++;
        
    }

    public AbonadosVO() {
    }
    
    //GETTERS Y SETTERS

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
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

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        AbonadosVO.contador = contador;
    }
    
    //ToString

    @Override
    public String toString() {
        return "Abono nº:"+pk+"\t Cliente:"+ codCliente+"\t Nombre:"+ nombre+"\t Num.Tarjeta:"+numTarjeta+"\t Tipo Abono:"+tipoAbono+"\t Importe"+importe+"\t Fecha inicio:"+fechaActiva+"\t Fecha Fin:"+fechaFin;
    }
    
 


}
