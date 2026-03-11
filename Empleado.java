public abstract class Empleado {
    //atributos
    private String id;
    private String nombre;
    private double salario;
    
    //Constructor
    public Empleado(String id, String nombre, double salario){
        this.id= id;
        this.nombre = nombre;
        this.salario = salario;
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
    public abstract void calcularSalario();

    @Override
    public String toString() {
        return "Empleado{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", salario=" + salario +
                '}';
    }

}
