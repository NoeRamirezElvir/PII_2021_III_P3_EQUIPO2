package model;

public class Boleto {
    private long id;
    private String codigoFactura;
    private String nombrePasajero;
    private String vuelo;
    private String asiento;
    private String numeroPuertaEmbarque;
    private String clase;
    private double total;

    public Boleto(){
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(String codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public String getNombrePasajero() {
        return nombrePasajero;
    }

    public void setNombrePasajero(String nombrePasajero) {
        this.nombrePasajero = nombrePasajero;
    }

    public String getVuelo() {
        return vuelo;
    }

    public void setVuelo(String vuelo) {
        this.vuelo = vuelo;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public String getNumeroPuertaEmbarque() {
        return numeroPuertaEmbarque;
    }

    public void setNumeroPuertaEmbarque(String numeroPuertaEmbarque) {
        this.numeroPuertaEmbarque = numeroPuertaEmbarque;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
