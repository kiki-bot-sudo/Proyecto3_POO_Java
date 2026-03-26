package com.tienda.empleados;

import com.tienda.exceptions.SalarioInvalidoException;

public abstract class Empleado {

    private String id;
    private String nombre;
    private double salario;
    private String puesto;

    public Empleado(String id, String nombre, double salario, String puesto)
            throws SalarioInvalidoException {
        if (id == null || id.trim().isEmpty())
            throw new IllegalArgumentException("El ID del empleado no puede estar vacío");
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre del empleado no puede estar vacío");
        if (salario <= 0)
            throw new SalarioInvalidoException(salario);
        if (puesto == null || puesto.trim().isEmpty())
            throw new IllegalArgumentException("El puesto del empleado no puede estar vacío");

        this.id = id;
        this.nombre = nombre;
        this.salario = salario;
        this.puesto = puesto;
    }
    
 private boolean salarioValido(double salario) {
        return salario > 0; 
    }

    public void setSalario(double salario) throws SalarioInvalidoException {
        if (salarioValido(salario)) {
            this.salario = salario;
        } else {
            throw new SalarioInvalidoException(salario);
        }
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalario() {
        return salario;
    }

    public String getPuesto() {
        return puesto;
    }

    public abstract double calcularSalario();

    public String toCSV() {
        return id + "," + nombre + "," + salario + "," + puesto;
    }

    @Override
    public String toString() {
        return "Empleado{id='" + id + "', nombre='" + nombre +
                "', salario=" + salario + ", puesto='" + puesto + "'}";
    }
}