package model;

public class ItemEquipaje extends Item{
    private String destino;

    public ItemEquipaje(long ID, String nombre,String destino) {
        super(ID, nombre);
        this.destino = destino;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    @Override
    public String toString(){
        return String.valueOf(getID()) ;
    }
}
