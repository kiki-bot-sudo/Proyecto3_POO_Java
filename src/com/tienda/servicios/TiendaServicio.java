package com.tienda.servicios;

import com.tienda.empleados.Empleado;
import com.tienda.exceptions.EmpleadoNoEncontradoException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TiendaServicio {

    private List<Empleado> empleados = new ArrayList<>();

    public void agregarEmpleado(Empleado e) {
        empleados.add(e);
    }

    // ✅ Eliminación con Iterator — evita ConcurrentModificationException
    public void eliminarEmpleado(String id) throws EmpleadoNoEncontradoException {
        Iterator<Empleado> it = empleados.iterator();
        boolean encontrado = false;

        while (it.hasNext()) {
            Empleado e = it.next();
            if (e.getId().equals(id)) {
                it.remove();
                encontrado = true;
                break;
            }
        }

        if (!encontrado)
            throw new EmpleadoNoEncontradoException(id);
    }

    // ✅ Filtrado por puesto con Iterator
    public List<Empleado> filtrarPorPuesto(String puesto) {
        List<Empleado> resultado = new ArrayList<>();
        Iterator<Empleado> it = empleados.iterator();

        while (it.hasNext()) {
            Empleado e = it.next();
            if (e.getPuesto().equalsIgnoreCase(puesto))
                resultado.add(e);
        }
        return resultado;
    }

    // ✅ Filtrado por salario mínimo con Iterator
    public List<Empleado> filtrarPorSalarioMinimo(double minimo) {
        List<Empleado> resultado = new ArrayList<>();
        Iterator<Empleado> it = empleados.iterator();

        while (it.hasNext()) {
            Empleado e = it.next();
            if (e.calcularSalario() >= minimo)
                resultado.add(e);
        }
        return resultado;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }
}