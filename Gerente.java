public class Gerente {

    // Atributo
    private String departamento;

    // Constructor
    public Gerente(String departamento) {
        this.departamento = departamento;
    }

    // Métodos
    public String getDepartamento() {
        return departamento;
    }

    public void autorizarDescuento(Producto producto, double porcentaje) {
        // Lógica para autorizar el descuento al producto
        System.out.println("Descuento de " + porcentaje + "% autorizado para el producto: " + producto.toString());
    }

    public String getPuesto() {
        return "Gerente";
    }

    @Override
    public String toString() {
        return "Gerente{departamento='" + departamento + "'}";
    }
}
