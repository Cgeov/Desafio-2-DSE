package sv.edu.udb.beans;

public class TransaccionBeans {
    int id_transaccion;
    String id_cuenta;
    String tipo;
    float saldo_anterior;
    float cantidad;

    public int getIdTransaccion() {
        return id_transaccion;
    }
    public void setIdTransaccion(int id_transaccion) {
        this.id_transaccion = id_transaccion;
    }
    public String getIdCuentaTransaccion() {
        return id_cuenta;
    }
    public void setIdCuentaTransaccion(String id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getTipoTransaccion() {
        return tipo;
    }
    public void setTipoTransaccion(String tipo) {
        this.tipo = tipo;
    }

    public float getSaldoAnteriorTransaccion() {
        return saldo_anterior;
    }
    public void setSaldoAnteriorTransaccion(float saldo_anterior) {
        this.saldo_anterior = saldo_anterior;
    }

    public float getCantidadTransaccion() {
        return cantidad;
    }
    public void setCantidadTransaccion(float cantidad) {
        this.cantidad = cantidad;
    }
}
