package com.tienda.models;

import com.tienda.empleados.*;
import com.tienda.exceptions.EmpleadoNoEncontradoException;
import com.tienda.exceptions.SalarioInvalidoException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmpleadoServicio {

    private List<Empleado> empleados = new ArrayList<>();

    // Agregar empleado con validación
    public void agregar(Empleado empleado) throws SalarioInvalidoException {
        if (empleado.getSalario() <= 0) {
            throw new SalarioInvalidoException(empleado.getSalario());
        }
        empleados.add(empleado);
        System.out.println("Empleado agregado: " + empleado.getNombre());
    }

    // Buscar por ID — retorna un objeto
    public Empleado buscarPorId(String id) throws EmpleadoNoEncontradoException {
        for (Empleado e : empleados) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        throw new EmpleadoNoEncontradoException(id);
    }

    // Filtrar por puesto — retorna una sublista
    public List<Empleado> filtrarPorPuesto(String puesto) {
        List<Empleado> resultado = new ArrayList<>();
        for (Empleado e : empleados) {
            if (e.getPuesto().equalsIgnoreCase(puesto)) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    // Eliminar por ID usando Iterator (para evitar ConcurrentModificationException)
    public void eliminar(String id) throws EmpleadoNoEncontradoException {
        Iterator<Empleado> iterator = empleados.iterator();
        boolean encontrado = false;
        while (iterator.hasNext()) {
            Empleado e = iterator.next();
            if (e.getId().equals(id)) {
                iterator.remove();
                System.out.println("Empleado eliminado: " + e.getNombre());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new EmpleadoNoEncontradoException(id);
        }
    }

    // Listar todos con for-each
    public void listarTodos() {
        System.out.println("\n--- Lista de empleados ---");
        for (Empleado e : empleados) {
            System.out.println(e.getPuesto() + " | " + e.getNombre()
                    + " | Salario calculado: $" + e.calcularSalario());
        }
    }
}