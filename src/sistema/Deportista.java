/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;

/**
 *
 * @author Josue
 */
public class Deportista extends personas {
      private String deporte;
    
    public Deportista() {
    }
    
    public Deportista(String nombre, String apellido, int edad, double salario, String deporte) {
        super(nombre, apellido, edad, salario);
        this.deporte = deporte;
    }
    
    public String getDeporte() {
        return deporte;
    }
    
    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\nDeporte: " + deporte;
    }
}
