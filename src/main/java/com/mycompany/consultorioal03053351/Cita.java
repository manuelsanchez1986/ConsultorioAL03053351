/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consultorioal03053351;

public class Cita {
    String fecha;
    String hora;
    Medico medico;
    Paciente paciente;

    public String toCSV() {
        return fecha + "," + hora + "," + medico.getNombre() + "," + paciente.getNombre();
    }

    public static Cita fromCSV(String linea) {
        String[] partes = linea.split(",");
        
        if (partes.length < 4) {
            System.out.println("Linea inválida: " + linea);
            return null;
        }

        Cita c = new Cita();
        c.fecha = partes[0];
        c.hora = partes[1];

        for (Medico m : Main.listaMedico) {
            if (m.getNombre().equalsIgnoreCase(partes[2])) {
                c.medico = m;
                break;
            }
        }

        for (Paciente p : Main.listaPaciente) {
            if (p.getNombre().equalsIgnoreCase(partes[3])) {
                c.paciente = p;
                break;
            }
        }

        if (c.medico == null || c.paciente == null) {
            System.out.println("No se encontró médico o paciente para la cita: " + linea);
            return null;
        }

        return c;
    }
}
