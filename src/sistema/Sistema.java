/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistema;

/**
 *
 * @author josue 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Sistema {
 private final GestorPersonas gestor;
    
    public Sistema() {
        gestor = new GestorPersonas();
        
        setTitle("Sistema de Gestión de Personas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JButton btnDoctor = new JButton("Registrar Doctor");
        JButton btnDeportista = new JButton("Registrar Deportista");
        JButton btnMostrar = new JButton("Mostrar Personas Registradas");
        JButton btnSalir = new JButton("Salir");
        
        btnDoctor.addActionListener((ActionEvent e) -> {
            registrarDoctor();
        });
        
        btnDeportista.addActionListener((ActionEvent e) -> {
            registrarDeportista();
        });
        
        btnMostrar.addActionListener((ActionEvent e) -> {
            mostrarPersonas();
        });
        
        btnSalir.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        
        panel.add(btnDoctor);
        panel.add(btnDeportista);
        panel.add(btnMostrar);
        panel.add(btnSalir);
        





        add(panel);
    }
    
    private void registrarDoctor() {
        JTextField txtNombre = new JTextField();
        JTextField txtApellido = new JTextField();
        JTextField txtEdad = new JTextField();
        JTextField txtSalario = new JTextField();
        JTextField txtArea = new JTextField();
        
        Object[] message = {
            "Nombre:", txtNombre,
            "Apellido:", txtApellido,
            "Edad:", txtEdad,
            "Salario:", txtSalario,
            "Área de especialidad:", txtArea
        };
        
        int option = JOptionPane.showConfirmDialog(null, message, "Registrar Doctor", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            try {
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                int edad = Integer.parseInt(txtEdad.getText());
                double salario = Double.parseDouble(txtSalario.getText());
                String area = txtArea.getText();
                
                Doctor doctor = new Doctor(nombre, apellido, edad, salario, area);
                gestor.agregarPersona(doctor);
                
                JOptionPane.showMessageDialog(null, "Doctor registrado exitosamente.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error en los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void registrarDeportista() {
        JTextField txtNombre = new JTextField();
        JTextField txtApellido = new JTextField();
        JTextField txtEdad = new JTextField();
        JTextField txtSalario = new JTextField();
        JTextField txtDeporte = new JTextField();
        
        Object[] message = {
            "Nombre:", txtNombre,
            "Apellido:", txtApellido,
            "Edad:", txtEdad,
            "Salario:", txtSalario,
            "Deporte:", txtDeporte
        };
        
        int option = JOptionPane.showConfirmDialog(null, message, "Registrar Deportista", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            try {
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                int edad = Integer.parseInt(txtEdad.getText());
                double salario = Double.parseDouble(txtSalario.getText());
                String deporte = txtDeporte.getText();
                
                Deportista deportista = new Deportista(nombre, apellido, edad, salario, deporte);
                gestor.agregarPersona(deportista);
                
                JOptionPane.showMessageDialog(null, "Deportista registrado exitosamente.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error en los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void mostrarPersonas() {
        ArrayList<personas> personas = gestor.getPersonas();
        
        if (personas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay personas registradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Panel para todos
        JPanel panelTodos = crearPanelPersonas(personas, "Todas las Personas");
        
        // Panel para doctores
        ArrayList<Doctor> doctores = gestor.getDoctores();
        JPanel panelDoctores = crearPanelPersonas(new ArrayList<>(doctores), "Doctores");
        
        // Panel para deportistas
        ArrayList<Deportista> deportistas = gestor.getDeportistas();
        JPanel panelDeportistas = crearPanelPersonas(new ArrayList<>(deportistas), "Deportistas");
        
        tabbedPane.addTab("Todos", panelTodos);
        tabbedPane.addTab("Doctores", panelDoctores);
        tabbedPane.addTab("Deportistas", panelDeportistas);
        
      
       JDialog dialog = new JDialog((Frame) null, "Personas Registradas", true);
        dialog.setSize(600, 400);
        
        dialog.add(tabbedPane);
        dialog.setVisible(true);
    }
    
    private JPanel crearPanelPersonas(ArrayList<? extends personas> lista, String titulo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        
        DefaultListModel<String> model = new DefaultListModel<>();
        for (int i = 0; i < lista.size(); i++) {
            model.addElement((i + 1) + ". " + lista.get(i).getNombre() + " " + lista.get(i).getApellido());
        }
        
        JList<String> listPersonas = new JList<>(model);
        listPersonas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(listPersonas);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        JButton btnVerDetalles = new JButton("Ver Detalles");
        btnVerDetalles.addActionListener((ActionEvent e) -> {
            int index = listPersonas.getSelectedIndex();
            if (index != -1) {
                personas persona = lista.get(index);
                JOptionPane.showMessageDialog(panel, persona.toString(), "Detalles de " + persona.getNombre(), JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "Seleccione una persona para ver sus detalles.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnVerDetalles);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        return panel;
    }
    
   
    public static void main(String[] args) {
         SwingUtilities.invokeLater(() -> {
             Sistema ventana = new Sistema ();
             ventana.setVisible(true);
         });
    }

    private void setTitle(String sistema_de_Gestión_de_Personas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void setSize(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void setDefaultCloseOperation(int EXIT_ON_CLOSE) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void add(JPanel panel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
