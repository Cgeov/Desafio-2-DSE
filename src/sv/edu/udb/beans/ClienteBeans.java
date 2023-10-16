package sv.edu.udb.beans;

public class ClienteBeans {
    int id_cliente;
    String titular;
    String DUI;
    int PIN;
    public int getIdCliente() {
        return id_cliente;
    }
    public void setIdCliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    public String getTitularCliente() {
        return titular;
    }
    public void setTitularCliente(String titular) {
        this.titular = titular;
    }

    public String getDUICliente() {
        return DUI;
    }
    public void setDUICliente(String DUICliente) {
        this.DUI = DUICliente;
    }

    public int getPINCliente() {
        return PIN;
    }
    public void setPINCliente(int pin) {
        this.PIN = pin;
    }
}
