package sv.edu.udb.datos;

import sv.edu.udb.util.Conexion;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class TransacionesDatos {

    private final String SQL_SELECT_TRANSACTIONS = "SELECT id_cuenta, cantidad , saldo_anterior, tipo FROM transacciones WHERE id_cuenta = ? ORDER BY id_transaccion ASC";


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
