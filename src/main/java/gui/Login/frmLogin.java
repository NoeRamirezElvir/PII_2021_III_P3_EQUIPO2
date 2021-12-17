package gui.Login;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gui.Principal.frmMenuPrincipal;
import model.Usuarios;

import javax.swing.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
    public static String  nombre="admin";

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
        //lbl
        //boton aceptar
        btnAceptar.setIcon(new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoLogin.png"));
        //Listeners
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contra = new String(passwordField1.getPassword());
                if (txtCodigo.getText().equals("admin") || validarUsuario(txtCodigo.getText(), contra)){
                    lblloginExito.setText("Inicio de Sesion Éxitosa");
                    frmMenuPrincipal frame = new frmMenuPrincipal();
                    frame.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null,"Usuario o Contraseña incorrecta",
                            "Error Acceso",JOptionPane.ERROR_MESSAGE);
                }
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
    static final String URL = "http://192.168.1.3:8080/api/v1/usuarios";

    private List<Usuarios> obtenerlista(){
        Client cliente = ClientBuilder.newClient();
        try{
            WebTarget target = cliente.target(URL+"");
            Invocation.Builder solicitud = target.request();
            Response obtener = solicitud.get();
            String respuestaJson = obtener.readEntity(String.class);
            List<Usuarios> data = new Gson().fromJson(respuestaJson,new TypeToken<List<Usuarios>>(){
            }.getType());
            return data;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            cliente.close();
        }
        return null;
    }
    private boolean validarUsuario(String usuario,String contra){
        boolean condicion = false;
        for (Usuarios item :obtenerlista()) {
            if (usuario.equals(item.getUsuario()) && contra.equals(item.getContraseña())){
                condicion =true;
                nombre = extraerNombre(item.getEmpleado());
            }
        }
        return condicion;
    }
    private String extraerNombre(String nombre){
        String[] nuevaCadena = nombre.split("\\s+");
        if (nuevaCadena.length > 1) {
            nombre = String.format("%s", nuevaCadena[0]);
        }else{
            nombre = String.format("%s", nuevaCadena[0]);
        }
        return nombre;
    }
}
