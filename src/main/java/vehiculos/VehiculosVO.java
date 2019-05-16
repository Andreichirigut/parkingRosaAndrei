/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiculos;



/**
 *
 * @author Chiri
 */
public class VehiculosVO {
    private int codAbono;
    private String matricula;
    private String tipoVehiculo;

    public VehiculosVO(int codAbono, String matricula, String tipoVehiculo) {
        this.codAbono = codAbono;
        this.matricula = matricula;
        this.tipoVehiculo = tipoVehiculo;
    }

    public VehiculosVO() {
    }

    public int getCodAbono() {
        return codAbono;
    }

    public void setCodAbono(int codAbono) {
        this.codAbono = codAbono;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    @Override
    public String toString() {
        return "VehiculosVO{" + "codAbono=" + codAbono + ", matricula=" + matricula + ", tipoVehiculo=" + tipoVehiculo + '}';
    }

    

    
   
    
    

   
    
    
}
