public class Cajero extends Empleado{

    // Atributos
    private int cajaAsignada;
    private int dia;

    // Constructor
    public Cajero(String id, String nombre, double salario, String puesto, int cajaAsignada, int dia) {
        super(id, nombre, salario, puesto);
        this.cajaAsignada = cajaAsignada;
        this.dia = dia;
    }

    // Métodos
    public int getCajaAsignada() {
        return cajaAsignada;
    }

     public void procesarVenta(Venta venta) {
        // Lógica para procesar la venta
        System.out.println("Procesando venta: " + venta.toString());
    }

    @Override
    public double calcularSalario(){
        return salario * dia;
    }
    
    @Override
    public String getPuesto() {
        return "Cajero";
    }

    @Override
    public String toString() {
        return "Cajero{cajaAsignada=" + cajaAsignada + ", dia=" + dia + "}";
    }
}
