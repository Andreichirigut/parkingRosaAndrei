/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;

import abonados.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
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
                p.setPin(res.getString("pin"));
                p.setMatricula(res.getString("matricula"));
                p.setFechaEntrada(res.getDate("fechaEntrada").toLocalDate());
                p.setHora_Entrada(res.getTime("hora_Entrada").toLocalTime());
                p.setHora_Salida(res.getTime("hora_Salida").toLocalTime());
                p.setFechaSalida(res.getDate("fechaSalida").toLocalDate());
                p.setNumeroPlaza(res.getInt("numeroPlaza"));
                p.setCosteFinal(res.getDouble("costeFinal"));
                

                //Añadimos el objeto a la lista
                lista.add(p);
            }
        }

        return lista;
    }

    @Override
    public TicketVO findByPk(String pk) throws SQLException {
         ResultSet res = null;
         TicketVO p = new TicketVO();

        String sql = "select * from Ticket where pin = ?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setString(1, pk);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.first()) {
                // Recogemos los datos del ticket, guardamos en un objeto
                p.setPin(res.getString("pin"));
                p.setMatricula(res.getString("matricula"));
                p.setFechaEntrada(res.getDate("fechaEntrada").toLocalDate());
                p.setHora_Entrada(res.getTime("hora_Entrada").toLocalTime());
                p.setHora_Salida(res.getTime("hora_Salida").toLocalTime());
                p.setFechaSalida(res.getDate("fechaSalida").toLocalDate());
                p.setNumeroPlaza(res.getInt("numeroPlaza"));
                p.setCosteFinal(res.getDouble("costeFinal"));
                return p;
            }

            return null;
        }
    }

    @Override
    public int insertTicket(TicketVO ticket) throws SQLException {
         int numFilas = 0;
        String sql = "insert into Ticket values (?,?,?,?,?,?,?,?)";

        if (findByPk(ticket.getPin()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(1, ticket.getPin());
                prest.setString(2, ticket.getMatricula());          
                prest.setDate(3, Date.valueOf(ticket.getFechaEntrada()));
                prest.setTime(4,Time.valueOf(ticket.getHora_Entrada()));
                prest.setTime(5,Time.valueOf(ticket.getHora_Salida()));
                prest.setDate(6, Date.valueOf(ticket.getFechaEntrada()));           
                prest.setInt(7, ticket.getNumeroPlaza());
                prest.setDouble(8, ticket.getCosteFinal());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    @Override
    public int insertListaTicket(List<TicketVO> lista) throws SQLException {
        int numFilas = 0;

        for (TicketVO tmp : lista) {
            numFilas += insertTicket(tmp);
        }

        return numFilas;
    }

    @Override
    public int deleteTicket() throws SQLException {
        String sql = "delete from Ticket";

        int nfilas = 0;

        // Preparamos el borrado de datos  mediante un Statement
        // No hay parámetros en la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecución de la sentencia
            nfilas = st.executeUpdate(sql);
        }

        // El borrado se realizó con éxito, devolvemos filas afectadas
        return nfilas;
    }

    @Override
    public int delete_Tickets(TicketVO ticket) throws SQLException {
        int numFilas = 0;
        
        
        String sql = "delete from Ticket where pin = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setString(1, ticket.getPin());
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int updateTicket(String pk, TicketVO nuevoTicket) throws SQLException {
        
         int numFilas = 0;
        String sql = "update Ticket set matricula = ?, fechaEntrada = ?,hora_Entrada = ?, hora_Salida = ?, fechaSalida = ?, costeFinal = ? where pin="+pk;

        if (findByPk(pk) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
               
                prest.setString(1, nuevoTicket.getMatricula());         
                prest.setDate(2, Date.valueOf(nuevoTicket.getFechaEntrada()));
                prest.setTime(3,Time.valueOf(nuevoTicket.getHora_Entrada()));
                prest.setTime(4,Time.valueOf(nuevoTicket.getHora_Salida()));
                prest.setDate(5, Date.valueOf(nuevoTicket.getFechaEntrada()));           
                prest.setDouble(6, nuevoTicket.getCosteFinal());
               
                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }
    
    
    
}
