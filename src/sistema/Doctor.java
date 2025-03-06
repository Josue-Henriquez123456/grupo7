/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;

/**
 *
 * @author 
 */
public class Doctor extends personas {
     private String area;
    
    public Doctor() {
    }
    
    public Doctor(String nombre, String apellido, int edad, double salario, String area) {
        super(nombre, apellido, edad, salario);
        this.area = area;
    }
    
    public String getArea() {
        return area;
    }
    
    public void setArea(String area) {
        this.area = area;
    }
    
    @Override
    public String toString() {
          return super.toString() + "\nÁrea de especialidad: " + area;
}
}