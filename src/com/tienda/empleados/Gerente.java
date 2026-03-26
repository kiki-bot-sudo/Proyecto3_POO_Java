package com.tienda.empleados;

import com.tienda.exceptions.EmpleadoException;
import com.tienda.exceptions.ProductoException;
import com.tienda.exceptions.SalarioInvalidoException;
import com.tienda.models.Producto;

public class Gerente extends Empleado {

    private String departamento;
    private static final double BONO_GERENTE = 0.25;

    public Gerente(String id, String nombre, double salario, String departamento)
            throws SalarioInvalidoException, EmpleadoException {
        super(id, nombre, salario, "Gerente");

        if (departamento == null || departamento.trim().isEmpty())
            throw new EmpleadoException("El departamento del gerente no puede estar vacío");

        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void autorizarDescuento(Producto producto, double porcentaje)
            throws ProductoException {
        if (porcentaje < 0 || porcentaje > 100)
            throw new ProductoException(
                    "Porcentaje inválido: " + porcentaje + ". Debe estar entre 0 y 100");
        System.out.println("Descuento de " + porcentaje +
                "% autorizado para: " + producto.toString());
    }

    @Override
    public double calcularSalario() {
        return getSalario() * (1 + BONO_GERENTE);
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + departamento;
    }

    public static Gerente fromCSV(String linea)
            throws SalarioInvalidoException, EmpleadoException {
        String[] campos = linea.split(",");
        if (campos.length < 5)
            throw new EmpleadoException("Línea CSV inválida para Gerente: " + linea);

        String id = campos[0].trim();
        String nombre = campos[1].trim();
        double salario = Double.parseDouble(campos[2].trim());

        String departamento = campos[4].trim();

        return new Gerente(id, nombre, salario, departamento);
    }

    @Override
    public String toString() {
        return "Gerente{id='" + getId() + "', nombre='" + getNombre() +
                "', departamento='" + departamento + "'}";
    }
}