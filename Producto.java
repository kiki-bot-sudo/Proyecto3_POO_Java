abstract class Producto implements Vendible{

    private String codigo; 
    private String nombre;
    private double precio;
    private int cantidad;

    
    public Producto(String codigo, String nombre, int cantidad, double precio )throws ProductoException { 
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new ProductoException("El código del producto no puede estar vacío");
        }
        this.cantidad = cantidad;

        if (nombre == null || codigo.trim().isEmpty()) {
            throw new ProductoException("El código del producto no puede estar vacío ");
        }
        this.codigo = codigo;

        if (precio <= 0) {
            throw new ProductoException("El precio debe ser mayor a 0");
        }
        if (cantidad <= 0) {
            throw new ProductoException(" El precio debe ser mayor a 0 ");
        }
        
        this.precio = precio;
        
        this.nombre = nombre;

    }


    // setter
    public void setCantidad(int cantidad)throws ProductoException {
        if (cantidad <= 0) {
            throw new ProductoException(" El precio debe ser mayor a 0 ");
        }
        this.cantidad = cantidad;
    }
    public void setPrecio(double precio) {

        if (precio < 0) {
            throw new IllegalArgumentException("Error el precio no puede ser negativo");
        }
        this.precio = precio;
    }

    //metodos x
    public String toString() {
        return "Codigo: " + codigo + " Nombre: " + nombre + " Precio: $: " + precio + "Cantidad: " + cantidad;
    }

    abstract double calcularPrecioFinal();

    //getter
    public String getCodigo() {
        return codigo;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    public String getNombre() {
        return nombre;
    }
    public double getPrecio() {
        return precio;
    }
    

    

    
}
