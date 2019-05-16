/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiculos;


import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Chiri
 */
public interface IVehiculos {
    // Método para obtener todos los registros de la tabla
    List<VehiculosVO> getAll() throws SQLException;
    
    // Méodo para obtener un registro a partir de la PK
    VehiculosVO findByPk(String matricula) throws SQLException;
    
    // Método para insertar un registro
    int insertVehiculo (VehiculosVO vehiculo) throws SQLException;
    
    // Método para insertar varios registros
    int insertVehiculo (List<VehiculosVO> lista) throws SQLException;
    
    // Método para borrar un centro
    int deleteVehiculo (VehiculosVO p) throws SQLException;
    
    // Método para borrar toda la tabla
    int deleteVehiculo() throws SQLException;
    
    // Método para modificar una persona. Se modifica a la persona que tenga esa 'pk'
    // con los nuevos datos que traiga la persona 'nuevosDatos'
    int updateVehiculo (String matricula, VehiculosVO nuevosDatos) throws SQLException;
}
