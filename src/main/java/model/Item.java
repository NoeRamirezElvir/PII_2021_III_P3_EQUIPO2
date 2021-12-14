package model;

public class Item {
    private long ID;
    private String nombre;

    // public Item(){}
    public Item(long ID, String nombre){
        this.ID = ID;
        this.nombre = nombre;
    }

    public long getID() {
        return ID;
    }
    public void setID(long ID) {
        this.ID = ID;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString(){
        return nombre;
    }
}
