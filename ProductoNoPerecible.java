public class ProductoNoPerecible extends Producto  {
    private String categoria;
    private double descuento;

    
    public ProductoNoPerecible(String codigo, String nombre, String categoria, int cantidad, double precio, double descuento){
        super(codigo, nombre, cantidad, precio);
        this.categoria = categoria;
        this.descuento =descuento;
    }

    @Override
    double calcularPreciofinal(){
        return precio - (precio * (descuento /100)) ;
    }
    @Override
    public String generarEtiqueta(){
        return "Producto: " + nombre + " codigo: " + codigo  + "Precio: " + calcularPreciofinal();
    }
    @Override
    public String toString() {
    return super.toString();
    }
}
