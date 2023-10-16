package sv.edu.udb.beans;

public class CuentaBeans {
    String id_cuenta;
    int id_cliente;
    float saldo;

    public String getIdCuenta() {
        return id_cuenta;
    }
    public void setIdCuenta(String id_cuenta) {
        this.id_cuenta = id_cuenta;
    }
    public int getIdClienteCuenta() {
        return id_cliente;
    }
    public void setIdClienteCuenta(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    public float getSaldoCuenta() {
        return saldo;
    }
    public void setSaldoCuenta(float saldo) {
        this.saldo = saldo;
    }

}
