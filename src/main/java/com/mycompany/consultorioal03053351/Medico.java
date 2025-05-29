/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consultorioal03053351;

/**
 *
 * @author spino
 */
public class Medico {
    String nombre;
    String especialidad;
    public String toCSV() {
    return nombre + "," + especialidad;
}

public static Medico fromCSV(String linea) {
    String[] partes = linea.split(",");
    Medico m = new Medico();
    m.nombre = partes[0];
    m.especialidad = partes[1];
    return m;
}
public String getNombre() {
    return nombre;
}

public String getEspecialidad() {
    return especialidad;
}
}
