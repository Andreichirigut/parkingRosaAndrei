/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonados;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author rosa
 */
public interface IAbonados {
    
    // Método para obtener todos los registros de la tabla
    List<AbonadosVO> getAll() throws SQLException;
    
    // Método para obtener un registro a partir de la PK
    AbonadosVO findByPk(int pk) throws SQLException;
    
    // Método para insertar un registro
    int insertAbonado (AbonadosVO abonado) throws SQLException;
    
    // Método para insertar varios registros
    int insertListAbonado (List<AbonadosVO> lista) throws SQLException;
    
    // Método para borrar un abonado
    int deleteAbonado () throws SQLException;
    
    // Método para borrar toda la tabla
    int deleteAbonados(AbonadosVO abonado) throws SQLException;
    
    // Método para modificar un abonado. Se modifica el abono que tenga esa 'pk'
    // con los nuevos datos que traiga el abono nuevoABonado
    int updateAbono (int pk, AbonadosVO nuevoABonado) throws SQLException;
    
}
