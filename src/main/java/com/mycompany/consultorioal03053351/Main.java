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
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    
    static List<Medico> listaMedico =new ArrayList<>();
    static List<Paciente> listaPaciente =new ArrayList<>();
    static List<Cita> listaCitas = new ArrayList<>();

    
    public static void main (String[] args) {
        Scanner entrada = new Scanner(System.in);
        cargarMedicosCSV("medicos.csv");
        cargarPacientesCSV("pacientes.csv");
        cargarCitasCSV("citas.csv");
        String usuario;
        String contrasena;
        System.out.println("Bievenido al programa alta de citas");
        System.out.println("==== Iniciar sesion ====");
        System.out.println("Usuario:");
        usuario = entrada.nextLine();
        System.out.println("Contrasena:");
        contrasena = entrada.nextLine();
        
        if (validarUsuario(usuario, contrasena)){
            System.out.println("Bienvenido: " + usuario);
            while(true){
            menu();
            operaciones();
            }
    }else{
            System.out.println("Credenciales invalidas");
        }
    }
    static boolean validarUsuario(String usuario, String contrasena){
        String usuarioLocal="Administrador";
        String contrasenaLocal="AL03053351";
        if (usuarioLocal.equals(usuario) && contrasenaLocal.equals(contrasena)) {
            return true;
    }
    return false;
    }
    
    static void menu(){
        System.out.println("1.-Dar de alta a medico");
        System.out.println("2.-Dar de alta a paciente");
        System.out.println("3.-Crear cita");
        System.out.println("4.-Listar medicos");
        System.out.println("5.-Listar pacientes");
        System.out.println("6.-Citas");
    }
    static void operaciones(){
        Scanner entrada=new Scanner(System.in);
        Integer opcion;
        System.out.println("Teclerar una opcion");
        opcion=entrada.nextInt();  
        
        switch (opcion){
            case 1:
                System.out.println("Alta de un medico");
                altaMedico();
                break;
            case 2:
                System.out.println("Alta paciente");
                altaPaciente();
                break;
            case 3:
                System.out.println("Crear cita");
                crearCita();
                break;
            case 4:
                System.out.println("Lista de medicos");
                listarMedico();
                break;
            case 5:
                System.out.println("Lista de pacientes");
                listarPaciente();
                break;
            case 6:
                System.out.println("Lista de citas");
                listarCitas();
                break;
        }
    }
    static void altaMedico(){
        Medico medico=new Medico();
    Scanner entrada = new Scanner(System.in);
    System.out.println("Nombre:");
    medico.nombre=entrada.nextLine();
    System.out.println("Especialidad:");
    medico.especialidad=entrada.nextLine();
    listaMedico.add(medico);
    guardarMedicosCSV("medicos.csv");
}
    static void altaPaciente(){
        Paciente paciente=new Paciente();
    Scanner entrada = new Scanner(System.in);
    System.out.println("Nombre:");
    paciente.nombre=entrada.nextLine();
    System.out.println("Apellido:");
    paciente.apellido=entrada.nextLine();
    listaPaciente.add(paciente);
    guardarPacientesCSV("pacientes.csv");
    }
    static void listarMedico(){
        for (Medico medico : listaMedico){
            System.out.println("Nombre: "+medico.nombre);
            System.out.println("Especialidad: "+medico.especialidad);
        }
        }
        static void listarPaciente(){
        for (Paciente paciente : listaPaciente){
            System.out.println("Nombre: "+paciente.nombre);
            System.out.println("Apellido: "+paciente.apellido);
    }
        }
        static void crearCita(){
            if (listaMedico.isEmpty() || listaPaciente.isEmpty()) {
        System.out.println("Debes tener al menos un médico y un paciente registrados para la creacion de una cita.");
        return;
    }

    Scanner entrada = new Scanner(System.in);
    Cita cita = new Cita();

    System.out.println("Fecha (dd/mm/aaaa): ");
    cita.fecha = entrada.nextLine();
    System.out.println("Hora (hh:mm): ");
    cita.hora = entrada.nextLine();

    System.out.println("Selecciona un médico:");
    for (int i = 0; i < listaMedico.size(); i++) {
        System.out.println(i + " - " + listaMedico.get(i).nombre + " (" + listaMedico.get(i).especialidad + ")");
    }
    int indexMedico = entrada.nextInt();
    entrada.nextLine();

    System.out.println("Selecciona un paciente:");
    for (int i = 0; i < listaPaciente.size(); i++) {
        System.out.println(i + " - " + listaPaciente.get(i).nombre + " " + listaPaciente.get(i).apellido);
    }
    int indexPaciente = entrada.nextInt();
    entrada.nextLine(); 

    cita.medico = listaMedico.get(indexMedico);
    cita.paciente = listaPaciente.get(indexPaciente);

    listaCitas.add(cita);
    guardarCitasCSV("citas.csv");
    System.out.println("La lista se creo correctamente.");
}
        static void listarCitas() {
    if (listaCitas.isEmpty()) {
        System.out.println("Sin citas registradas.");
    } else {
        for (Cita cita : listaCitas) {
            System.out.println("Fecha: " + cita.fecha + ", Hora: " + cita.hora);
            System.out.println("Doctor: " + cita.medico.nombre + " (" + cita.medico.especialidad + ")");
            System.out.println("Paciente: " + cita.paciente.nombre + " " + cita.paciente.apellido);
            System.out.println("=====================================");
        }
    }
}
        public static void guardarMedicosCSV(String rutaArchivo) {
    try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo))) {
        for (Medico m : listaMedico) {
            pw.println(m.toCSV());
        }
    } catch (IOException e) {
        System.out.println("Error al guardar medico: " + e.getMessage());
    }
    }
    public static void cargarMedicosCSV(String rutaArchivo) {
    listaMedico.clear(); // limpia la lista actual
    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            Medico m = Medico.fromCSV(linea);
            listaMedico.add(m);
        }
    } catch (IOException e) {
        System.out.println("Error al cargar medicos: " + e.getMessage());
    }
}
    public static void guardarPacientesCSV(String rutaArchivo) {
    try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo))) {
        for (Paciente p : listaPaciente) {
            pw.println(p.toCSV());
        }
    } catch (IOException e) {
        System.out.println("Error al guardar paciente: " + e.getMessage());
    }
}

public static void cargarPacientesCSV(String rutaArchivo) {
    listaPaciente.clear();
    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            Paciente p = Paciente.fromCSV(linea);
            listaPaciente.add(p);
        }
    } catch (IOException e) {
        System.out.println("Error al cargar pacientes: " + e.getMessage());
    }
}
public static void guardarCitasCSV(String rutaArchivo) {
    try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo))) {
        for (Cita c : listaCitas) {
            pw.println(c.toCSV());
        }
    } catch (IOException e) {
        System.out.println("Error al guardar cita: " + e.getMessage());
    }
}

public static void cargarCitasCSV(String rutaArchivo) {
    listaCitas.clear();
    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            Cita c = Cita.fromCSV(linea);
            if (c.medico != null && c.paciente != null) {
                listaCitas.add(c);
            }
        }
    } catch (IOException e) {
        System.out.println("Error al cargar citas: " + e.getMessage());
    }
}
}



