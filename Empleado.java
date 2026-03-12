public abstract class Empleado {
    //atributos
    protected  String id;
    protected  String nombre;
    protected  double salario;
    protected  String puesto;
    
    //Constructor
    public Empleado(String id, String nombre, double salario, String puesto){
        this.id= id;
        this.nombre = nombre;
        this.salario = salario;
        this.puesto = puesto;
    }

    //getters
    public String getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public double getSalario(){
        return salario;
    }

    //metodos abstractos
    public abstract double calcularSalario();
    public abstract String getPuesto();
    

    @Override
    public String toString() {
        return "Empleado{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", salario=" + salario +
                '}';
    }

}
