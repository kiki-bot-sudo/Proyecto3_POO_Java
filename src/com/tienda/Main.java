package com.tienda;

import com.tienda.servicios.TiendaServicio;
import com.tienda.ui.MenuConsola;

public class Main {

    public static void main(String[] args) {
        TiendaServicio servicio = new TiendaServicio();
        servicio.cargarDatos();
        MenuConsola menuConsola = new MenuConsola(servicio);
        menuConsola.iniciar();
    }
}
