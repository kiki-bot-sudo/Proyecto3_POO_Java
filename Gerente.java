public class Gerente extends Empleado {

    // Atributo
    private String departamento;

    // Constructor
    public Gerente(String id, String nombre, double salario, String puesto, String departamento) {
        super(id, nombre, salario, puesto);
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
    public double calcularSalario(){
        return salario;
    }

    @Override
    public String toString() {
        return "Gerente{departamento='" + departamento + "'}";
    }
}
