/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonados;

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
 * @author rosa
 */
public class AbonadosDAO implements IAbonados {

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

        ResultSet res = null;
        AbonadosVO p = new AbonadosVO();

        String sql = "select * from Abonado where codAbono=?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setInt(1, pk);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.first()) {
                // Recogemos los datos de la persona, guardamos en un objeto
                p.setPk(res.getInt("codAbono"));
                p.setNombre(res.getString("nombre"));
                p.setNumTarjeta(res.getString("numTarjeta"));
                p.setTipoABono(res.getInt("tipoAbono"));
                p.setImporte(res.getInt("importe"));
                p.setFechaActiva(res.getDate("fechaActiva").toLocalDate());
                p.setFechaFin(res.getDate("fechaFin").toLocalDate());
                return p;
            }

            return null;
        }
    }


        @Override
        public int insertAbonado
        (AbonadosVO abonado) throws SQLException {
            int numFilas = 0;
            String sql = "insert into Abonado values (?,?,?,?,?,?,?)";

            if (findByPk(abonado.getPk()) != null) {
                // Existe un registro con esa pk
                // No se hace la inserción
                return numFilas;
            } else {
                // Instanciamos el objeto PreparedStatement para inserción
                // de datos. Sentencia parametrizada              
                try (PreparedStatement prest = con.prepareStatement(sql)) {

                    // Establecemos los parámetros de la sentencia
                    prest.setInt(1, abonado.getPk());
                    prest.setString(2, abonado.getNombre());
                    prest.setString(3, abonado.getNumTarjeta());
                    prest.setInt(4, abonado.getTipoABono());
                    prest.setInt(5, abonado.getImporte());
                    prest.setDate(6, Date.valueOf(abonado.getFechaActiva()));
                    prest.setDate(7, Date.valueOf(abonado.getFechaFin()));

                    numFilas = prest.executeUpdate();
                }
                return numFilas;
            }
        }

        @Override
        public int insertListAbonado
        (List<AbonadosVO> lista) throws SQLException {
            int numFilas = 0;

            for (AbonadosVO tmp : lista) {
                numFilas += insertAbonado(tmp);
            }

            return numFilas;
        }

        @Override
        public int deleteAbonado() throws SQLException {
            String sql = "delete from Abonado";

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

    

    public int deleteAbonados(AbonadosVO abonado) throws SQLException {
        int numFilas = 0;

        String sql = "delete from Abonado where codAbono = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setInt(1, abonado.getPk());
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int updateAbono(int pk, AbonadosVO nuevoABonado) throws SQLException {
        int numFilas = 0;
        String sql = "update Abonado set nombre = ?, numTarjeta = ?,tipoAbono = ?, importe = ?, fechaActiva = ?, fechaFin = ? where codABono=" + pk;

        if (findByPk(pk) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(1, nuevoABonado.getNombre());
                prest.setString(2, nuevoABonado.getNumTarjeta());
                prest.setInt(3, nuevoABonado.getTipoABono());
                prest.setInt(4, nuevoABonado.getImporte());
                prest.setDate(5, Date.valueOf(nuevoABonado.getFechaActiva()));
                prest.setDate(6, Date.valueOf(nuevoABonado.getFechaFin()));

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    public int cambiarNombres(String newName, String oldName) throws SQLException {

        int res = 0;
        // Dos ?, uno para newName y otro para oldName

        String sql = "{call cambiar_nombres (?,?)}";

        // Preparamos la llamada al procedimiento almacenado
        try (CallableStatement call = con.prepareCall(sql)) {
            // Establecemos parámetros a pasar al procedimiento
            call.setString(1, newName);
            call.setString(2, oldName);
            // Ejecutamos el procedimiento
            res = call.executeUpdate();

        }
        return res;
    }

}
