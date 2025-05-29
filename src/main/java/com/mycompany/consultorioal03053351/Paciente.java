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
public class Paciente {
    String nombre;
    String apellido;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String toCSV() {
        return nombre + "," + apellido;
    }
    public static Paciente fromCSV(String linea) {
        String[] partes = linea.split(",");
        Paciente p = new Paciente();
        p.setNombre(partes[0]);
        p.setApellido(partes[1]);
        return p;
    }
   }
