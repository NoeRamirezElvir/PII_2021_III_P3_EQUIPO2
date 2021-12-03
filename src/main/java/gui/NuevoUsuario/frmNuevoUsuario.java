package gui.NuevoUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmNuevoUsuario extends JFrame{
    private JPanel jpaPrincipal;
    private JPanel jpaTitulo;
    private JLabel lblTitulo;
    private JPanel jpaContenido;
    private JTextField txtCodigo;
    private JTextField txtContraseña;
    private JPanel jpaBotones;
    private JLabel lblCodigo;
    private JLabel lblContraseña;
    private JButton btnRegistrar;
    private JButton btnCancelar;
    private JLabel lblImagen;

    public frmNuevoUsuario() {
        iniciar();


        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    private void iniciar(){
        setTitle("Registro de usuario");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/loginPrincipal/iconoLogin.png");
        setIconImage(imagen.getImage());
        //Imagen
        lblImagen.setIcon(new ImageIcon("src/main/java/recursos/imagenes/loginPrincipal/usuario.png"));
        //-----------------------------------------------------------------------------------
    }
}
