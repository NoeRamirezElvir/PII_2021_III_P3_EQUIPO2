package gui.Principal;

import gui.Persona.frmPersona;
import gui.Pasajero.frmPasajero;
import gui.Empleado.frmEmpleado;
import gui.Aeronave.frmAeronave;
import gui.VehiculoAeroportuario.frmVehiculoAeroportuario;
import gui.Boleto.frmBoleto;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmPrincipal extends JFrame{
    private JPanel jpaPrincipal;
    private JPanel jpaTitulo;
    private JLabel lblTitulo;
    private JPanel jpaContenido;
    private JButton btnPersona;
    private JButton btnPasajeros;
    private JButton btnEmpleado;
    private JButton btnAeronave;
    private JButton btnVehiculoAerportuario;
    private JButton registrarBoletoButton;
    private JButton button7;
    private JButton button8;
    private JPanel jpaImagen;
    private JLabel lblImagen;


    public frmPrincipal() {
        iniciar();
        btnPersona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmPersona frame = new frmPersona();
                frame.setVisible(true);
            }
        });
        btnPasajeros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmPasajero frame = new frmPasajero();
                frame.setVisible(true);
            }
        });
        btnEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmEmpleado frame = new frmEmpleado();
                frame.setVisible(true);
            }
        });
        btnAeronave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmAeronave frame = new frmAeronave();
                frame.setVisible(true);
            }
        });
        btnVehiculoAerportuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmVehiculoAeroportuario frame = new frmVehiculoAeroportuario();
                frame.setVisible(true);
            }
        });
        registrarBoletoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmBoleto frame = new frmBoleto();
                frame.setVisible(true);
            }
        });
    }

    private void iniciar(){
        setTitle("Menú Principal");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        /*ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/loginPrincipal/iconoLogin.png");
        setIconImage(imagen.getImage());*/
         //-----------------------------------------------------------------------------------
    }


}
