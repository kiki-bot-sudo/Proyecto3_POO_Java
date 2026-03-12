public class Cajero {

    // Atributos
    private int cajaAsignada;
    private int dia;

    // Constructor
    public Cajero(int cajaAsignada, int dia) {
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

    public String getPuesto() {
        return "Cajero";
    }

    @Override
    public String toString() {
        return "Cajero{cajaAsignada=" + cajaAsignada + ", dia=" + dia + "}";
    }
}
