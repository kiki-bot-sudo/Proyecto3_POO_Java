public class ProductoPerecible extends Producto {
    private String fechaVencimiento;
    private double descuento;
    private String categoria;

    public ProductoPerecible(String codigo, String nombre, String fechaVencimiento, String categoria, int cantidad, double precio, double descuento){
        super(codigo, nombre, cantidad, precio);
        this.categoria = categoria;
        this.descuento =descuento;
        this.fechaVencimiento = fechaVencimiento;

    }
    @Override
    double calcularPreciofinal(){
        return precio - (precio * (descuento /100)) ;
    }

    @Override
    public String generarEtiqueta(){
        return "Producto: " + nombre + " codigo: " + codigo + "Descuento de: "+ descuento + "Precio: " + calcularPreciofinal();
    }
    @Override
    public String toString() {
        return super.toString() + " Vence: " + fechaVencimiento+ " Descuento: " + descuento + " % ";
    }
    



}
