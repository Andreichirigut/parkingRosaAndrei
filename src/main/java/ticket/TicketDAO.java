/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;

import abonados.AbonadosVO;
import abonados.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rosa
 */
public class TicketDAO implements ITicket{
    
    private Connection con = null;

    public TicketDAO() {
        con = Conexion.getInstance();
    }

    @Override
    public List<TicketVO> getAll() throws SQLException {
         List<TicketVO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from Ticket");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) { //para movernos a la primera fila, devuelve true mientras haya filas sino false
                
               TicketVO p = new TicketVO();
                // Recogemos los datos del abonado, guardamos en un objeto
                p.setPin(res.getInt("pin"));
                p.setMatricula(res.getString("matricula"));
                p.setFecha(res.getDate("fecha").toLocalDate());
                p.setNumeroPlaza(res.getInt("numeroPlaza"));
                

                //Añadimos el objeto a la lista
                lista.add(p);
            }
        }

        return lista;
    }

    @Override
    public TicketVO findByPk(int pk) throws SQLException {
         ResultSet res = null;
         TicketVO p = new TicketVO();

        String sql = "select * from Ticket where pin = ?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setInt(1, pk);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.first()) {
                // Recogemos los datos del ticket, guardamos en un objeto
                p.setPin(res.getInt("pin"));
                p.setMatricula(res.getString("matricula"));
                p.setFecha(res.getDate("fecha").toLocalDate());
                p.setNumeroPlaza(res.getInt("numeroPlaza"));
                return p;
            }

            return null;
        }
    }

    @Override
    public int insertTicket(TicketVO ticket) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertListaTicket(List<TicketVO> lista) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteTicket() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete_Tickets(TicketVO ticket) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateTicket(int pk, TicketVO nuevoTicket) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
