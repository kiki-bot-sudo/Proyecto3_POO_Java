package com.tienda.empleados;

public abstract class Empleado {
    //atributos
    protected  String id;
    protected  String nombre;
    protected  double salario;
    protected  String puesto;
    
    //Constructor
    public Empleado(String id, String nombre, double salario, String puesto){
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del empleado no puede estar vacío");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del empleado no puede estar vacío");
        }
        if (salario < 0) {
            throw new IllegalArgumentException("El salario no puede ser negativo");
        }
        if (puesto == null || puesto.trim().isEmpty()) {
            throw new IllegalArgumentException("El puesto del empleado no puede estar vacío");
        }
        
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
                ", puesto='" + puesto + '\'' +
                '}';
    }
}
