public class Cajero extends Empleado {
    private int dia;
    private int cajaAsignada;


    public Cajero(String id, String nombre,double salario,String puesto, int cajaAsignada, int dia){
        super(id, nombre, salario, puesto);
        this.dia = dia;
        this.cajaAsignada = cajaAsignada;
    }

    public int getCajaAsignada(){
        return cajaAsignada;
    }

    @Override
    public String getPuesto(){
        return "Cajero";
    }

    @Override
    public String toString(){
        return "Cajero{" +
                "id='" + getId() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", salario=" + getSalario() +
                ", puesto='" + getPuesto() + '\'' +
                ", cajaAsignada=" + cajaAsignada +
                ", dia=" + dia +
                '}';
    }

    
    
}
