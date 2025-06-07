/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consultorioal03053351;

public class Paciente {
    String nombre;
    String apellido;

    public String toCSV() {
        return nombre + "," + apellido;
    }

    public static Paciente fromCSV(String linea) {
        String[] partes = linea.split(",", -1);
        Paciente p = new Paciente();
        p.nombre = partes[0];
        p.apellido = partes[1];
        return p;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
}
