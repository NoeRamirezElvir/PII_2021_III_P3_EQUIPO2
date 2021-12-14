package gui.VehiculoAeroportuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.*;

import java.util.List;

public class frmVehiculoAeroportuario extends JFrame{
    private JPanel jpaPrincipal;
    private JTable tblDatos;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscarID;
    private JButton btnListar;
    private JButton btnLeerCBO;
    private JButton btnLimpiar;
    private JTextField txtID;
    private JTextField txtCodigo;
    private JTextField txtNumPlaca;
    private JComboBox cboTipo;
    private JTextField txtDescripcion;
    private JTextField txtEstado;
    private JComboBox cboTipoCombustible;
    private JTextField txtCapacidad;
    private JComboBox cboVehiculos;
    private JLabel lblTitulo;
    private JLabel lblID;
    private JLabel lblCodigo;
    private JLabel lblNumPlaca;
    private JLabel lblTipo;
    private JLabel lblDescripcion;
    private JLabel lblEstado;
    private JLabel lblTipoCombustible;
    private JLabel lblCapacidad;
    private JLabel lblVehiculos;
    private JLabel lblImagen2;
    private JLabel lblImagen1;
    private JPanel jpaTitulo;
    private JPanel jpaContenido;
    private JPanel jpaBotones;
    private JPanel jpaDatos;
    private JScrollPane sclPanDatos;
    private JButton btnBuscarCodigo;
    DefaultTableModel modelo;
    static final String URL = "http://192.168.0.15:8080/api/v1/vehiculosAeroportuarios";

    public frmVehiculoAeroportuario() {
        iniciar();
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/addVehiculo");
                    Invocation.Builder solicitud = target.request();
                    VehiculoAeroportuario vehiculoAeroportuario = new VehiculoAeroportuario();
                    vehiculoAeroportuario.setCodigo(txtCodigo.getText());
                    vehiculoAeroportuario.setNumeroPlaca(txtNumPlaca.getText());
                    vehiculoAeroportuario.setTipo(String.valueOf(cboTipo.getSelectedItem()));
                    vehiculoAeroportuario.setDescripcion(txtDescripcion.getText());
                    vehiculoAeroportuario.setEstado(txtEstado.getText());
                    vehiculoAeroportuario.setTipoCombustible(String.valueOf(cboTipoCombustible.getSelectedItem()));
                    vehiculoAeroportuario.setCapacidad(Integer.parseInt(txtCapacidad.getText()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(vehiculoAeroportuario);
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
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"");
                    Invocation.Builder solicitud = target.request();
                    VehiculoAeroportuario vehiculoAeroportuario = new VehiculoAeroportuario();
                    vehiculoAeroportuario.setId(Long.parseLong(txtID.getText()));
                    vehiculoAeroportuario.setCodigo(txtCodigo.getText());
                    vehiculoAeroportuario.setNumeroPlaca(txtNumPlaca.getText());
                    vehiculoAeroportuario.setTipo(String.valueOf(cboTipo.getSelectedItem()));
                    vehiculoAeroportuario.setDescripcion(txtDescripcion.getText());
                    vehiculoAeroportuario.setEstado(txtEstado.getText());
                    vehiculoAeroportuario.setTipoCombustible(String.valueOf(cboTipoCombustible.getSelectedItem()));
                    vehiculoAeroportuario.setCapacidad(Integer.parseInt(txtCapacidad.getText()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(vehiculoAeroportuario);
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
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/delete/" + txtID.getText());
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
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leerDatos();
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar();
            }
        });
        btnBuscarID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    String id = JOptionPane.showInputDialog("¿Cuál es el ID a buscar?");
                    WebTarget target = client.target(URL+"/id/" + id);
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    VehiculoAeroportuario data = new Gson().fromJson(responseJson,VehiculoAeroportuario.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getCodigo(),
                                data.getNumeroPlaca(),
                                data.getTipo(),
                                data.getDescripcion(),
                                data.getEstado(),
                                data.getTipoCombustible(),
                                data.getCapacidad()
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
        btnBuscarCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/codigo/" + txtCodigo.getText());
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    VehiculoAeroportuario data = new Gson().fromJson(responseJson,VehiculoAeroportuario.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getCodigo(),
                                data.getNumeroPlaca(),
                                data.getTipo(),
                                data.getDescripcion(),
                                data.getEstado(),
                                data.getTipoCombustible(),
                                data.getCapacidad()
                        };
                        modelo.addRow(registro);
                        tblDatos.setModel(modelo);
                    }else{
                        throw new Exception("> No se encontró el Código");
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
                    Object obj = cboVehiculos.getSelectedItem();
                    long item = ((Item)obj).getID();
                    JOptionPane.showMessageDialog(null,"ID del vehículo: " + item,"INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               try{
                   int filaSeleccionada = tblDatos.getSelectedRow();
                   txtID.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                   txtCodigo.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                   txtNumPlaca.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                   cboTipo.setSelectedItem(modelo.getValueAt(filaSeleccionada,3).toString());
                   txtDescripcion.setText(modelo.getValueAt(filaSeleccionada,4).toString());
                   txtEstado.setText(modelo.getValueAt(filaSeleccionada,5).toString());
                   cboTipoCombustible.setSelectedItem(modelo.getValueAt(filaSeleccionada,6).toString());
                   txtCapacidad.setText(modelo.getValueAt(filaSeleccionada,7).toString());
               }catch(Exception e1){
                   JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
               }
            }
        });
    }
    private void iniciar(){
        setTitle("Registro de Vehículos");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoVehiculo.png");
        setIconImage(imagen.getImage());
        //Imagenes
        lblImagen1.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/imagenMontaCarga.png"));
        lblImagen2.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/imagenGrua.png"));
        //Botones
        //boton registrar
        btnRegistrar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoRegistrar.png"));
        //boton actualizar
        btnActualizar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoActualizar.png"));
        //boton eliminar
        btnEliminar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoEliminar.png"));
        //boton buscar ID
        btnBuscarID.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoBuscar.png"));
        //boton buscar Codigo
        btnBuscarCodigo.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoBuscar.png"));
        //boton listar
        btnListar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoListar.png"));
        //boton leerCBO
        btnLeerCBO.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoLeerCBO.png"));
        //boton limpiar
        btnLimpiar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoLimpiarAVB.png"));
        //-----------------------------------------------------------------------------------
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("ID");
        modelo.addColumn("Código");
        modelo.addColumn("Número de Placa");
        modelo.addColumn("Tipo");
        modelo.addColumn("Descripción");
        modelo.addColumn("Estado");
        modelo.addColumn("Tipo Combustible");
        modelo.addColumn("Capacidad");
        leerDatos();
        limpiar();
    }
    private void leerDatos() {
        Client client = ClientBuilder.newClient();
        try {
            WebTarget target = client.target(URL + "");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<VehiculoAeroportuario> data = new Gson().fromJson(responseJson, new TypeToken<List<VehiculoAeroportuario>>() {
            }.getType());
            if (get.getStatus() == 200) {
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
                modelo.setRowCount(0);
                for (VehiculoAeroportuario vehiculo : data) {
                    Object[] registro = {
                            vehiculo.getId(),
                            vehiculo.getCodigo(),
                            vehiculo.getNumeroPlaca(),
                            vehiculo.getTipo(),
                            vehiculo.getDescripcion(),
                            vehiculo.getEstado(),
                            vehiculo.getTipoCombustible(),
                            vehiculo.getCapacidad()
                    };
                    modelo.addRow(registro);
                    //CBO
                    Item item = new Item(vehiculo.getId(), vehiculo.getCodigo());
                    modeloCombo.addElement(item);
                }
                tblDatos.setModel(modelo);
                cboVehiculos.setModel(modeloCombo);
            } else {
                throw new Exception("> No se cargaron los datos.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            client.close();
        }
     }
    private void limpiar(){
        txtID.setText("0");
        txtCodigo.setText("");
        txtCapacidad.setText("0");
        txtDescripcion.setText("");
        txtNumPlaca.setText("");
        txtEstado.setText("");
    }
}
