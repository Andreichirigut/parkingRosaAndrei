/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plaza;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Chiri
 */
public interface IPlazas {
    // Método para obtener todos los registros de la tabla
    List<PlazasVO> getAll() throws SQLException;
    
    // Méodo para obtener un registro a partir de la PK
    PlazasVO findByPk(int numPlaza) throws SQLException;
    
    // Método para insertar un registro
    int insertPlaza (PlazasVO plaza) throws SQLException;
    
    // Método para insertar varios registros
    int insertPlaza (List<PlazasVO> lista) throws SQLException;
    
    // Método para borrar un centro
    int deletePlaza (int pk) throws SQLException;
    
    // Método para borrar toda la tabla
    int deletePlaza() throws SQLException;
    
    // Método para modificar una persona. Se modifica a la persona que tenga esa 'pk'
    // con los nuevos datos que traiga la persona 'nuevosDatos'
    int updatePlaza (int numPlaza, PlazasVO nuevosDatos) throws SQLException;
}
