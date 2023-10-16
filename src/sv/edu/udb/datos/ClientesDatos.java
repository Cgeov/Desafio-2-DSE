package sv.edu.udb.datos;

import sv.edu.udb.beans.ClienteBeans;
import sv.edu.udb.util.Conexion;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ClientesDatos {
    private final String SQL_INSERT = "INSERT INTO clientes(titular, DUI,PIN) VALUES(?,?,?)";

    //private final String SQL_UPDATE = "UPDATE clientes SET titular=?, DUI=?, PIN=? WHERE id_cliente=?";
    //private final String SQL_DELETE = "DELETE FROM clientes WHERE id_cliente = ?";
    private final String SQL_SELECT = "SELECT id_cliente, titular , DUI, PIN FROM clientes WHERE DUI = ? AND PIN= ?";


    public int insert(ClienteBeans clienteBeans) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setString(index++, clienteBeans.getTitularCliente());
            stmt.setString(index++, clienteBeans.getDUICliente());
            stmt.setInt(index, clienteBeans.getPINCliente());
            System.out.println("Ejecutando query:" + SQL_INSERT);
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
    public boolean select(ClienteBeans clienteBeans) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean foundUSer = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setString(1, clienteBeans.getDUICliente());
            stmt.setInt(2, clienteBeans.getPINCliente());

            System.out.println("Ejecutando query: " + SQL_SELECT);
            System.out.println(stmt);
            rs = stmt.executeQuery();

            if (rs.next()) {
                clienteBeans.setIdCliente(rs.getInt("id_cliente"));
                clienteBeans.setTitularCliente(rs.getString("titular"));
                clienteBeans.setDUICliente(rs.getString("DUI"));
                clienteBeans.setPINCliente(rs.getInt("PIN"));
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
}
