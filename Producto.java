abstract class Producto implements Vendible{

    protected String codigo; // Actualizar de privado a protected
    protected String nombre;
    protected double precio;
    protected int cantidad;

    //agregar este constructor al UML
    public Producto(String codigo, String nombre, int cantidad, double precio ){ 
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.precio = precio;
        this.nombre = nombre;

    }

    abstract double calcularPreciofinal();

    public String getCodigo() {
        return codigo;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }
    public double getPrecio() {
        return precio;
    }

    public String toString() {
        return "Codigo: " + codigo + " Nombre: " + nombre + " Precio: $" + precio;
    }
}
