/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;


import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author rosa
 */
public interface ITicket {
    
     // Método para obtener todos los registros de la tabla
    List<TicketVO> getAll() throws SQLException;
    
    // Método para obtener un registro a partir de la PK
    TicketVO findByPk(int pk) throws SQLException;
    
    // Método para insertar un registro
    int insertTicket (TicketVO ticket) throws SQLException;
    
    // Método para insertar varios registros
    int insertListaTicket (List<TicketVO> lista) throws SQLException;
    
    // Método para borrar un abonado
    int deleteTicket () throws SQLException;
    
    // Método para borrar toda la tabla
    int delete_Tickets(TicketVO ticket) throws SQLException;
    
    // Método para modificar un abonado. Se modifica el abono que tenga esa 'pk'
    // con los nuevos datos que traiga el abono nuevoABonado
    int updateTicket (int pk, TicketVO nuevoTicket) throws SQLException;
}
