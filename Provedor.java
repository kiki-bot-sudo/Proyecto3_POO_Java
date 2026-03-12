public class Provedor {
    private String id;
    private String nombre;
    private String telefono;

    public Provedor(String id, String nombre, String telefono){
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getId(){
        return id;
    }

    public String getNombre(){
        return nombre; 
    }

    public String getTelefono(){
        return telefono;
    }

    @Override
    public String toString() {
        return "Provedor{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
