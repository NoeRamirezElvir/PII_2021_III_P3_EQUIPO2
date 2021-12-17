package model;

public class ItemUsuario extends Item{
    private String codigo;

    public ItemUsuario(long ID, String nombre,String codigo) {
        super(ID, nombre);
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    @Override
    public String toString(){
        return codigo;
    }
}
