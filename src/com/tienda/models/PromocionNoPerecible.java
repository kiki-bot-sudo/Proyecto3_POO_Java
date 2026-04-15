public class PromocionNoPerecible implements PrecioStrategy   {
    

    private static int  cantidadMaxima = 30;
    private static double oferta = 0.05;

    @Override
    public double calcular(double precioBase, double porcentaje, int cantidad) {
        double precio = precioBase - (precioBase * (porcentaje / 100));

        if (cantidad > cantidadMaxima) {
            precio = precio - (precio * oferta);
        }

        return Math.round(precio * 100.0) / 100.0;
    }
    
}
