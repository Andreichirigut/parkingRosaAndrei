/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonados;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rosa
 */
public class AbonadosDAO implements IAbonados{
    
     private Connection con = null;

    public AbonadosDAO() {
        con = Conexion.getInstance();
    }
     
    @Override
    public List<AbonadosVO> getAll() throws SQLException {
       List<AbonadosVO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from Abonado");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) { //para movernos a la primera fila, devuelve true mientras haya filas sino false
                
               AbonadosVO p = new AbonadosVO();
                // Recogemos los datos del abonado, guardamos en un objeto
                p.setPk(res.getInt("codAbono"));
                p.setCodCliente(res.getInt("codcliente"));
                p.setNombre(res.getString("nombre"));
                p.setNumTarjeta(res.getString("numTarjeta"));
                p.setTipoABono(res.getInt("tipoAbono"));
                p.setImporte(res.getInt("importe"));
                p.setFechaActiva(res.getDate("fechaActiva").toLocalDate());
                p.setFechaFin(res.getDate("fechaFin").toLocalDate());
                

                //Añadimos el objeto a la lista
                lista.add(p);
            }
        }

        return lista;
    }

    @Override
    public AbonadosVO findByPk(int pk) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertAbonado(AbonadosVO abonado) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertListAbonado(List<AbonadosVO> lista) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteAbonado() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteAbonados(AbonadosVO abonado) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateAbono(int pk, AbonadosVO nuevoABonado) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
