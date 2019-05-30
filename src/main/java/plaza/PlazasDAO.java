/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plaza;

import abonados.AbonadosVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Chiri
 */
public class PlazasDAO implements IPlazas {

    private Connection con = null;

    public PlazasDAO() {
        con = Conexion.getInstance();
    }

    
    public void asignarPlaza(AbonadosVO abo){
        
        Scanner teclado= new Scanner(System.in);
        System.out.println("Introduzca matrícula");
        String matri=teclado.nextLine();
        System.out.println("Introduzca tipo de vehículo");
        String tipo=teclado.nextLine();
        
        
        
    }
    
    
    @Override
    public List<PlazasVO> getAll() throws SQLException {
        List<PlazasVO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from Plaza");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                PlazasVO p = new PlazasVO();
                // Recogemos los datos de la persona, guardamos en un objeto
                p.setNumPlaza(res.getInt("numPlaza"));
                p.setTipoPlaza(res.getString("tipoPlaza"));
                p.setEstadoPlaza(res.getBoolean("estadoPlaza"));
                p.setTarifa(res.getDouble("tarifa"));

                //Añadimos el objeto a la lista
                lista.add(p);
            }
        }

        return lista;
    }

    @Override
    public PlazasVO findByPk(int numPlaza) throws SQLException {

        ResultSet res = null;
        PlazasVO plaza = new PlazasVO();

        String sql = "select * from Plaza where numPlaza=?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setInt(1, numPlaza);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.first()) {
                // Recogemos los datos de la persona, guardamos en un objeto
                plaza.setNumPlaza(res.getInt("numPlaza"));
                plaza.setTipoPlaza(res.getString("tipoPlaza"));
                plaza.setEstadoPlaza(res.getBoolean("estadoPlaza"));
                plaza.setTarifa(res.getDouble("tarifa"));
                return plaza;
            }

            return null;
        }
    }

    @Override
    public int insertPlaza(PlazasVO plaza) throws SQLException {

        int numFilas = 0;
        String sql = "insert into Plaza values (?,?,?,?)";

        if (findByPk(plaza.getNumPlaza()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setInt(1, plaza.getNumPlaza());
                prest.setString(2, plaza.getTipoPlaza());
                prest.setBoolean(3, plaza.isEstadoPlaza());
                prest.setDouble(4, plaza.getTarifa());
                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }

    }

    @Override
    public int insertPlaza(List<PlazasVO> lista) throws SQLException {
        int numFilas = 0;

        for (PlazasVO tmp : lista) {
            numFilas += insertPlaza(tmp);
        }

        return numFilas;
    }

    @Override
    public int deletePlaza() throws SQLException {

        String sql = "delete from Plaza";

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

    public int deletePlaza(int pk) throws SQLException {
        int numFilas = 0;

        String sql = "delete from Plaza where numPlaza = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setInt(1, pk);
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int updatePlaza(int numPlaza, PlazasVO nuevosDatos) throws SQLException {

        int numFilas = 0;
        String sql = "update Plaza set tipoPlaza = ?, estadoPlaza = ?, tarifa = ? where numPlaza=?";

        if (findByPk(numPlaza) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(1, nuevosDatos.getTipoPlaza());
                prest.setBoolean(2, nuevosDatos.isEstadoPlaza());
                prest.setDouble(3, nuevosDatos.getTarifa());
                prest.setInt(4, numPlaza);

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    public static void main(String[] args) throws SQLException {
        PlazasDAO aux = new PlazasDAO();

    }
}
