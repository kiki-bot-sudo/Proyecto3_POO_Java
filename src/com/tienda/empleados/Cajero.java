package com.tienda.empleados;

import com.tienda.exceptions.EmpleadoException;
import com.tienda.exceptions.SalarioInvalidoException;
import com.tienda.models.Venta;

public class Cajero extends Empleado {

    private int cajaAsignada;
    private int dia;

    public Cajero(String id, String nombre, double salario, int cajaAsignada, int dia)
            throws SalarioInvalidoException, EmpleadoException {
        super(id, nombre, salario, "Cajero");

        if (cajaAsignada <= 0)
            throw new EmpleadoException("El número de caja debe ser mayor que 0");
        if (dia <= 0)
            throw new EmpleadoException("Los días trabajados deben ser mayor que 0");

        this.cajaAsignada = cajaAsignada;
        this.dia = dia;
    }

    public int getCajaAsignada() {
        return cajaAsignada;
    }

    public int getDia() {
        return dia;
    }

    public void procesarVenta(Venta venta) {
        System.out.println("Procesando venta: " + venta.toString());
    }

    @Override
    public double calcularSalario() {
        return getSalario() * dia;
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + cajaAsignada + "," + dia;
    }

    public static Cajero fromCSV(String linea)
            throws SalarioInvalidoException, EmpleadoException {
        String[] campos = linea.split(",");
        if (campos.length < 6)
            throw new EmpleadoException("Línea CSV inválida para Cajero: " + linea);

        String id = campos[0].trim();
        String nombre = campos[1].trim();
        double salario = Double.parseDouble(campos[2].trim());

        int caja = Integer.parseInt(campos[4].trim());
        int dia = Integer.parseInt(campos[5].trim());

        return new Cajero(id, nombre, salario, caja, dia);
    }

    @Override
    public String toString() {
        return "Cajero{id='" + getId() + "', nombre='" + getNombre() +
                "', cajaAsignada=" + cajaAsignada + ", dia=" + dia + "}";
    }
}