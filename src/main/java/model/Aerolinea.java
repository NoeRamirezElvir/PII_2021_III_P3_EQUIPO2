package model;

public class Aerolinea {
    private long id;
    private String codigo;
    private long telefonoAtencion;
    private long telefonoCarga;
    private String direccion;
    private String paginaWeb;
    private String ubicacionAeropuerto;
    private String Nombre;

    public Aerolinea(){
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public long getTelefonoAtencion() {
        return telefonoAtencion;
    }

    public void setTelefonoAtencion(long telefonoAtencion) {
        this.telefonoAtencion = telefonoAtencion;
    }

    public long getTelefonoCarga() {
        return telefonoCarga;
    }

    public void setTelefonoCarga(long telefonoCarga) {
        this.telefonoCarga = telefonoCarga;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getUbicacionAeropuerto() {
        return ubicacionAeropuerto;
    }

    public void setUbicacionAeropuerto(String ubicacionAeropuerto) {
        this.ubicacionAeropuerto = ubicacionAeropuerto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
