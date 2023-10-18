package sv.edu.udb.datos;

import sv.edu.udb.beans.ClienteBeans;
import sv.edu.udb.util.Conexion;
import sv.edu.udb.beans.TransaccionBeans;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class TransacionesDatos {

    private final String SQL_SELECT_TRANSACTIONS = "SELECT id_cuenta, cantidad , saldo_anterior, tipo FROM transacciones WHERE id_cuenta = ? ORDER BY id_transaccion ASC";
    private final String SQL_INSERT_TRANSACTION = "INSERT INTO transacciones(id_cuenta, saldo_anterior, cantidad, tipo) VALUES (?,?,?,?)";

    //private final String SQL_INSERT = "INSERT INTO cuentas(id_cuenta, id_cliente,saldo) VALUES(?,?,?)";

    public int insert(String id_cuenta, float saldo, float cantidad, String tipo){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows =0;

        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_TRANSACTION);
            int index = 1;
            stmt.setString(index++, id_cuenta);
            stmt.setFloat(index++, saldo);
            stmt.setFloat(index++, cantidad);
            stmt.setString(index++, tipo);
            System.out.println("Ejecutando query: " + SQL_INSERT_TRANSACTION);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);

        }catch (SQLException e){
            e.printStackTrace();
        }


        return rows;

    }

    public DefaultTableModel selectTransactions(String id_cuenta){
        DefaultTableModel dtm = new DefaultTableModel();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_TRANSACTIONS);
            stmt.setString(1, id_cuenta);
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
