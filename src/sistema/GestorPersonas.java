/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;

/**
 *
 * @author Josue
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GestorPersonas {
     private ArrayList<personas> persona;
    private final String ARCHIVO_DATOS = "personas.dat";
    
      public GestorPersonas() {
        persona = new ArrayList<>();
        cargarDatos();
    }
    
    public void agregarPersona(personas personas) {
        persona.add(personas);
        guardarDatos();
    }
    
    public ArrayList<personas> getPersonas() {
        return persona;
    }
    
    public ArrayList<Doctor> getDoctores() {
        ArrayList<Doctor> doctores = new ArrayList<>();
        for (personas p : persona) {
            if (p instanceof Doctor doctor) {
                doctores.add(doctor);
            }
        }
        return doctores;
    }
    
    public ArrayList<Deportista> getDeportistas() {
        ArrayList<Deportista> deportistas = new ArrayList<>();
        for (personas p : persona) {
            if (p instanceof Deportista deportista) {
                deportistas.add(deportista);
            }
        }
        return deportistas;
    }
    
    private void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_DATOS))) {
            oos.writeObject(persona);
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    private void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_DATOS))) {
            persona = (ArrayList<personas>) ois.readObject();
            System.out.println("Datos cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se encontraron datos previos o hubo un error al cargarlos.");
            persona = new ArrayList<>();
        }
    }
}
