package gui.Login;

import gui.Principal.frmMenuPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmLogin extends JFrame{
    private JPanel jpaPrincipal;
    private JPanel jpaContenido;
    private JTextField txtCodigo;
    private JLabel lblCodigo;
    private JLabel lblContraseña;
    private JPanel jpaBotones;
    private JButton btnAceptar;
    private JLabel lblImagen;
    private JLabel lblUsuario;
    private JLabel lblContrseña;
    private JPasswordField passwordField1;
    private JLabel lblloginExito;
    private JLabel lblImagen1;


    public frmLogin() {
        //Imagen
        ImageIcon imagen = new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/portadaLogin.jpg");
        lblImagen.setIcon(imagen);
        ImageIcon imagenU = new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoUsuario.png");
        lblUsuario.setIcon(imagenU);
        ImageIcon imagenC = new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoContrseña.png");
        lblContrseña.setIcon(imagenC);
        lblUsuario.setIcon(imagenU);
        ImageIcon imagen1 = new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/login.png");
        lblImagen1.setIcon(imagen1);
        //Botones
        //boton aceptar
        btnAceptar.setIcon(new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoLogin.png"));
        //Listeners
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMenuPrincipal frame = new frmMenuPrincipal();
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Inicio Aeropuerto");
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
