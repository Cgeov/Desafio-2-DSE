package sv.edu.udb.datos;

import sv.edu.udb.beans.CuentaBeans;
import sv.edu.udb.util.Conexion;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class CuentasDatos {
    private final String SQL_INSERT = "INSERT INTO cuentas(id_cuenta, id_cliente,saldo) VALUES(?,?,?)";

    private final String SQL_UPDATE = "UPDATE cuentas SET saldo= ? WHERE id_cuenta= ?";
    //private final String SQL_DELETE = "DELETE FROM clientes WHERE id_cliente = ?";
    private final String SQL_SELECT_CUENTA = "SELECT a.id_cuenta, b.titular, a.saldo FROM cuentas a  INNER JOIN clientes b ON a.id_cliente = b.id_cliente WHERE a.id_cuenta= ?";

    private final String SQL_SELECT_ALL = "SELECT a.id_cuenta, b.titular, a.saldo FROM cuentas a  INNER JOIN clientes b ON a.id_cliente = b.id_cliente WHERE a.id_cliente= ? ORDER BY a.id_cuenta";

    public int insert(CuentaBeans cuentaBeans) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setString(index++, cuentaBeans.getIdCuenta());
            stmt.setInt(index++, cuentaBeans.getIdClienteCuenta());
            stmt.setFloat(index, cuentaBeans.getSaldoCuenta());
            System.out.println("Ejecutando query:" + stmt);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    public boolean selectSpecific(CuentaBeans cuentaBeans) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean foundUSer = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_CUENTA);
            stmt.setString(1, cuentaBeans.getIdCuenta());

            System.out.println("Ejecutando query: " + SQL_SELECT_CUENTA);
            System.out.println(stmt);
            rs = stmt.executeQuery();

            if (rs.next()) {
                cuentaBeans.setIdCuenta(rs.getString("id_cuenta"));
                cuentaBeans.setIdClienteCuenta(rs.getInt("id_cliente"));
                cuentaBeans.setSaldoCuenta(rs.getFloat("saldo"));
                foundUSer = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return foundUSer;
    }



    public DefaultTableModel selectAll(int id_cliente){
        DefaultTableModel dtm = new DefaultTableModel();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();

            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            stmt.setInt(1, id_cliente);
            rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int numberOfColumns = meta.getColumnCount();
            for (int i = 1; i <= numberOfColumns; i++) {
                dtm.addColumn(meta.getColumnLabel(i));
            }
            while (rs.next()) {
                Object[] fila = new Object[numberOfColumns];
                for (int i = 0; i < numberOfColumns; i++) {
                    fila[i]=rs.getObject(i+1);
                }
                dtm.addRow(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return dtm;
    }

}
