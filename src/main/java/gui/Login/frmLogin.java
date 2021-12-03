package gui.Login;

import gui.Principal.frmPrincipal;
import gui.NuevoUsuario.frmNuevoUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmLogin extends JFrame{
    private JPanel jpaPrincipal;
    private JPanel jpaTitulo;
    private JLabel lblTitulo;
    private JPanel jpaContenido;
    private JTextField txtCodigo;
    private JTextField txtContraseña;
    private JLabel lblCodigo;
    private JLabel lblContraseña;
    private JPanel jpaBotones;
    private JButton btnAceptar;
    private JButton btnCrear;
    private JPanel jpaImagen;
    private JLabel lblImagen;


    public frmLogin() {
        //Imagen
        ImageIcon imagen = new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/login.png");
        lblImagen.setIcon(imagen);
        //Botones
        //boton aceptar
        btnAceptar.setIcon(new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoLlaveLogin.png"));
        btnCrear.setIcon(new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoMasLogin.png"));

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmPrincipal frame = new frmPrincipal();
                frame.setVisible(true);

            }
        });
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmNuevoUsuario frame = new frmNuevoUsuario();
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Log In");
        frame.setContentPane(new frmLogin().jpaPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoLogin.png");
        frame.setIconImage(imagen.getImage());

    }
}
