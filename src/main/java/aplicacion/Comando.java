/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

/**
 *
 * @author andrei
 */
public enum Comando {
    
    
    DEPOSITAR_VEHICULO(0, "Depositar vehiculo"),
    RETIRAR_VEHICULO(1, "Retirar vehiculo"),
    DEPOSITAR_ABONOS(2, "Depositar abonos"),
    RETIRAR_ABONOS(3, "Retirar abonos"),
    
    VER_ESTADO_PARKING(4, "Ver estado parking"),
    FACTURAR_ENTRE_FECHAS(5, "Facturar entre fechas"),
    FACTURACION_ABONADOS(6, "Facturar los abonados"),
    DAR_DE_ALTA(7, "Dar de alta"),
    MODIFICAR_ABONO(8, "Modificar abono"),
    DAR_DE_BAJA(9, "Dar de baja"),
    CONSULTAR_CADUCIDAD_ABONADOS(10, "Consultar caducidad abonados"),
    CONSULTAR_CADUCIDAD_ABONADOS_10DIAS(11, "Consultar caducidad abonados ultimos 10 dias"),
    CREAR_COPIA_SEGURIDAD(12, "Crear copia de seguridad"),
    RESTAURAR_COPIA_SEGURIDAD(13, "Restaurar copia de seguridad"),
    SALIR(14,"Salir del sistema");
    
    
    
    private int codigo;
    private String descripcion;

    private Comando(int codigo, String descripcion){
        this.codigo = codigo;
        this.descripcion = descripcion;
    }
    
    public int getCodigo(){
        return this.codigo;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }
}
