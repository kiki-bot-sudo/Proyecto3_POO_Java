public class ProductoPerecible extends Producto {
    private String fechaVencimiento;
    private double descuento;
    private String categoria;

    public ProductoPerecible(String codigo, String nombre, String fechaVencimiento, String categoria, int cantidad, double precio, double descuento) throws ProductoException{
        super(codigo, nombre, cantidad, precio);

        if (fechaVencimiento == null || fechaVencimiento.trim().isEmpty()){
            throw new ProductoException("La fecha de vencimiento no puede estar vacía ");
        }
        if (categoria == null || categoria.trim().isEmpty()){
            throw new ProductoException("Error la categoría no puede estar vacía ");
        }
        if (descuento < 0 ){
            throw new ProductoException(" Error el descuento tiene que ser entre 0 y 100 %");
        }


        this.categoria = categoria;
        this.descuento =descuento;
        this.fechaVencimiento = fechaVencimiento;

    }

    //agregar al UML
    public void setDescuento(double descuento)throws ProductoException {
        if (descuento < 0 || descuento > 100){
            throw new ProductoException(" Error el descuento tiene que ser entre 0 y 100");
        }
        this.descuento = descuento;
    }

    @Override
    double calcularPrecioFinal(){
        return getPrecio() - (getPrecio() * (descuento /100)) ;
    }

    @Override
    public String generarEtiqueta(){
        return "Producto: " + getNombre() + " codigo: " + getNombre() + "Descuento de: "+ descuento + "Precio total: " + calcularPrecioFinal();
    }
    @Override
    public String toString() {
        return super.toString() + " Vence: " + fechaVencimiento+ " Descuento: " + descuento + " % " + "Categoria" + categoria;
    }
    



}
