package gui.Equipaje;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmEquipaje extends JFrame {
    private JPanel jpaPrincipal;
    private JPanel jpaTitulo;
    private JPanel jpaContain;
    private JPanel jpaBotones;
    private JPanel jpaDatos;
    private JScrollPane sclPaneDatos;
    private JTable tblDatos;
    private JLabel lblTitulo;
    private JLabel lblImagen1;
    private JLabel lblImagen2;
    private JButton btnRegistrar;
    private JButton btnListar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLeerCBO;
    private JButton btnLimpiar;
    private JLabel lblId;
    private JLabel lblBoleto;
    private JLabel lblNombreP;
    private JLabel lblPeso;
    private JTextField txtId;
    private JComboBox cboBoleto;
    private JTextField txtNombreP;
    private JTextField txtPeso;
    private JLabel lblFechaRegistro;
    private JLabel lblDestino;
    private JLabel lblColor;
    private JLabel lbltamaño;
    private JTextField txtFecha;
    private JTextField txtDestino;
    private JTextField txtColor;
    private JComboBox cboTama;
    private JLabel lblEquipajes;
    private JComboBox cboEquipaje;
    private JButton btnaBuscarId;
    private JButton btnBuscarPasajero;
    DefaultTableModel modelo = new DefaultTableModel();
    static final String URL = "http://192.168.1.3:8080/api/v1/equipajes";
    static final String URL2 = "http://192.168.1.3:8080/api/v1/boletos";

    public frmEquipaje (){
        iniciar();
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/addEquipaje");
                    Invocation.Builder solicitud = target.request();
                    Equipaje equipaje = new Equipaje();
                    equipaje.setBoleto(Long.parseLong(cboBoleto.getSelectedItem().toString()));
                    equipaje.setPasajero(txtNombreP.getText());
                    equipaje.setPeso(Double.parseDouble(txtPeso.getText()));
                    equipaje.setFechaRegistro(txtFecha.getText());
                    equipaje.setDestino(txtDestino.getText());
                    equipaje.setColor(txtColor.getText());
                    equipaje.setTamaño(cboTama.getSelectedItem().toString());
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(equipaje);
                    Response post = solicitud.post(Entity.json(jsonString));
                    String responseJson = post.readEntity(String.class);
                    if(post.getStatus() == 201){
                        JOptionPane.showMessageDialog(null,"Agregado correctamente","GUARDADO",JOptionPane.INFORMATION_MESSAGE);
                        leerDatos();
                    }else if(post.getStatus() == 500){
                        RestApiError apiError = new Gson().fromJson(responseJson, RestApiError.class);
                        throw new Exception(apiError.getErrorDetails());
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    client.close();
                }
            }
        });
        cboBoleto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = cboBoleto.getSelectedItem();
                String item1 = ((ItemEquipaje)obj).getNombre();
                String item2 = ((ItemEquipaje)obj).getDestino();
                txtNombreP.setText(item1);
                txtDestino.setText(item2);
            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leerDatos();
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"");
                    Invocation.Builder solicitud = target.request();
                    Equipaje equipaje = new Equipaje();
                    equipaje.setId(Long.parseLong(txtId.getText()));
                    equipaje.setBoleto(Long.parseLong(cboBoleto.getSelectedItem().toString()));
                    equipaje.setPasajero(txtNombreP.getText());
                    equipaje.setPeso(Double.parseDouble(txtPeso.getText()));
                    equipaje.setFechaRegistro(txtFecha.getText());
                    equipaje.setDestino(txtDestino.getText());
                    equipaje.setColor(txtColor.getText());
                    equipaje.setTamaño(cboTama.getSelectedItem().toString());
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(equipaje);
                    Response put = solicitud.put(Entity.json(jsonString));
                    String responseJson = put.readEntity(String.class);
                    if(put.getStatus() == 200){
                        JOptionPane.showMessageDialog(null,"Actualizado correctamente","ACTUALIZADO",JOptionPane.INFORMATION_MESSAGE);
                        leerDatos();
                    }else{
                        RestApiError apiError = new Gson().fromJson(responseJson, RestApiError.class);
                        throw new Exception(apiError.getErrorDetails());
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    client.close();
                }
            }
        });
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    int filaSeleccionada = tblDatos.getSelectedRow();
                    txtId.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                    cboBoleto.setSelectedItem(modelo.getValueAt(filaSeleccionada,1).toString());
                    txtNombreP.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                    txtPeso.setText(modelo.getValueAt(filaSeleccionada,3).toString());
                    txtFecha.setText(modelo.getValueAt(filaSeleccionada,4).toString());
                    txtDestino.setText(modelo.getValueAt(filaSeleccionada,5).toString());
                    txtColor.setText(modelo.getValueAt(filaSeleccionada,6).toString());
                    cboTama.setSelectedItem(modelo.getValueAt(filaSeleccionada,7).toString());
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/delete/" + txtId.getText());
                    Invocation.Builder solicitud = target.request();
                    Response post = solicitud.delete();
                    if(post.getStatus() == 200){
                        JOptionPane.showMessageDialog(null,"Eliminado correctamente","ELIMINADO",JOptionPane.INFORMATION_MESSAGE);
                        leerDatos();
                    }else{
                        throw new Exception("> No se encontró el objeto");
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    client.close();

                }
            }
        });
        btnLeerCBO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Object obj = cboEquipaje.getSelectedItem();
                    String item = ((Item)obj).getNombre();
                    JOptionPane.showMessageDialog(null,"Propietario de la maleta: " + item,"Detalles de la maleta",JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtId.setText("");
                txtColor.setText("");
                txtDestino.setText("");
                txtNombreP.setText("");
                txtPeso.setText("");
                txtFecha.setText("");
            }
        });
        btnaBuscarId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    String id = JOptionPane.showInputDialog(null,"Ingrese el ID del Equipaje a buscar","Búsqueda",JOptionPane.QUESTION_MESSAGE);
                    WebTarget target = client.target(URL+"/id/" + id);
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Equipaje data = new Gson().fromJson(responseJson,Equipaje.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getBoleto(),
                                data.getPasajero(),
                                data.getPeso(),
                                data.getFechaRegistro(),
                                data.getDestino(),
                                data.getColor(),
                                data.getTamaño()
                        };
                        modelo.addRow(registro);
                        tblDatos.setModel(modelo);
                    }else{
                        throw new Exception("> No se encontró el ID");
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    client.close();

                }
            }
        });
        btnBuscarPasajero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/pasajero/" + txtNombreP.getText());
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Equipaje data = new Gson().fromJson(responseJson,Equipaje.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getBoleto(),
                                data.getPasajero(),
                                data.getPeso(),
                                data.getFechaRegistro(),
                                data.getDestino(),
                                data.getColor(),
                                data.getTamaño()
                        };
                        modelo.addRow(registro);
                        tblDatos.setModel(modelo);
                    }else{
                        throw new Exception("> No se encontró el Pasajero");
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    client.close();
                }
            }
        });
    }

    private void leerDatos() {
        Client client = ClientBuilder.newClient();
        try{
            WebTarget target = client.target(URL+"");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Equipaje> data = new Gson().fromJson(responseJson,new TypeToken<List<Equipaje>>(){}.getType());
            if(get.getStatus() == 200){
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
                modelo.setRowCount(0);
                for (Equipaje equipaje: data) {
                    Object [] registro ={
                            equipaje.getId(),
                            equipaje.getBoleto(),
                            equipaje.getPasajero(),
                            equipaje.getPeso(),
                            equipaje.getFechaRegistro(),
                            equipaje.getDestino(),
                            equipaje.getColor(),
                            equipaje.getTamaño()
                    };
                    modelo.addRow(registro);
                    //CBO
                    ItemEquipaje item = new ItemEquipaje(equipaje.getId(),equipaje.getPasajero(),equipaje.getDestino());
                    //Item item = new Item(equipaje.getId(),equipaje.getPasajero());
                    modeloCombo.addElement(item);
                }
                tblDatos.setModel(modelo);
                cboEquipaje.setModel(modeloCombo);
            }else{
                throw new Exception("> No se cargaron los datos.");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        finally {
            client.close();
        }
    }
    private void llenarCboBoletos(){
        Client client = ClientBuilder.newClient();
        try{
            WebTarget target = client.target(URL2+"");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Boleto> data = new Gson().fromJson(responseJson,new TypeToken<List<Boleto>>(){}.getType());
            if(get.getStatus() == 200){
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
                for (Boleto boleto: data) {
                    ItemEquipaje item = new ItemEquipaje(boleto.getId(),
                            boleto.getNombrePasajero(),
                            boleto.getVuelo());
                    modeloCombo.addElement(item);
                }
                cboBoleto.setModel(modeloCombo);
            }else{
                throw new Exception("No se cargaron los datos de los pasajeros.");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        finally{
            client.close();
        }
    }

    private void iniciar(){
        setTitle("Equipajes");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        //place holders
        TextPrompt titulos = new TextPrompt("Kg.",txtPeso);
        TextPrompt titulo = new TextPrompt("yyyy-MM-dd.",txtFecha);
        titulos.changeAlpha(0.75f);
        titulo.changeAlpha(0.75f);
        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/loginPrincipal/iconoEquipaje.png");
        setIconImage(imagen.getImage());
        //Imagenes
        lblImagen1.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/equipaje.png"));
        lblImagen2.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/equipaje2.png"));
        //Botones
        //boton registrar
        btnRegistrar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoRegistrar.png"));
        //boton actualizar
        btnActualizar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoActualizar.png"));
        //boton eliminar
        btnEliminar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoEliminar.png"));
        //boton buscar
        btnaBuscarId.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoBuscar.png"));
        //boton buscar pasajero
        btnBuscarPasajero.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoBuscar.png"));
        //boton listar
        btnListar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoListar.png"));
        //boton leerCBO
        btnLeerCBO.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoLeerCBO.png"));
        //boton limpiar
        btnLimpiar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoLimpiarAVB.png"));
        //-----------------------------------------------------------------------------------
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("ID");
        modelo.addColumn("Boleto");
        modelo.addColumn("Pasajero");
        modelo.addColumn("Peso");
        modelo.addColumn("Fecha de Registro");
        modelo.addColumn("Destino");
        modelo.addColumn("Color");
        modelo.addColumn("Tamaño");
        leerDatos();
        llenarCboBoletos();
    }
}
