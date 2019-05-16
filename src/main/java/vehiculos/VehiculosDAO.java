/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiculos;

import daw.Andrei.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chiri
 */
public class VehiculosDAO implements IVehiculos {

    private Connection con = null;

    public VehiculosDAO() {
        con = Conexion.getInstance();
    }

    @Override
    public List<VehiculosVO> getAll() throws SQLException {
        List<VehiculosVO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from Vehiculos");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                VehiculosVO p = new VehiculosVO();
                // Recogemos los datos de la persona, guardamos en un objeto
                p.setCodAbono(res.getInt("codAbono"));
                p.setMatricula(res.getString("matricula"));
                p.setTipoVehiculo(res.getString("tipoVehiculo"));

                //Añadimos el objeto a la lista
                lista.add(p);
            }
        }

        return lista;
    }

    @Override
    public VehiculosVO findByPk(String matricula) throws SQLException {

        ResultSet res = null;
        VehiculosVO vehiculo = new VehiculosVO();

        String sql = "select * from Vehiculos where matricula=?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setString(1, matricula);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.first()) {
                // Recogemos los datos de la persona, guardamos en un objeto
                vehiculo.setCodAbono(res.getInt("codAbono"));
                vehiculo.setMatricula(res.getString("matricula"));
                vehiculo.setTipoVehiculo(res.getString("tipoVehiculo"));
                return vehiculo;
            }

            return null;
        }
    }

    @Override
    public int insertVehiculo(VehiculosVO vehiculo) throws SQLException {

        int numFilas = 0;
        String sql = "insert into Vehiculos values (?,?,?)";

        if (findByPk(vehiculo.getMatricula()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setInt(2, vehiculo.getCodAbono());
                prest.setString(3, vehiculo.getMatricula());
                prest.setString(4, vehiculo.getTipoVehiculo());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }

    }

    @Override
    public int insertVehiculo(List<VehiculosVO> lista) throws SQLException {
        int numFilas = 0;

        for (VehiculosVO tmp : lista) {
            numFilas += insertVehiculo(tmp);
        }

        return numFilas;
    }

    @Override
    public int deleteVehiculo() throws SQLException {

        String sql = "delete from Vehiculos";

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
    public int deleteVehiculo(VehiculosVO vehiculo) throws SQLException {
        int numFilas = 0;

        String sql = "delete from Vehiculos where matricula = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setString(1, vehiculo.getMatricula());
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int updateVehiculo(String matricula, VehiculosVO nuevosDatos) throws SQLException {

        int numFilas = 0;
        String sql = "update Vehiculos set codAbono = ?, tipoVehiculo = ? where matricula=?";

        if (findByPk(matricula) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setInt(1, nuevosDatos.getCodAbono());
                prest.setString(2, nuevosDatos.getTipoVehiculo());
                prest.setString(3, matricula);

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

}
