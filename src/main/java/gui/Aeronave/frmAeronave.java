package gui.Aeronave;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Aerolinea;
import model.Aeronave;
import model.Item;
import model.RestApiError;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmAeronave extends JFrame{
    private JPanel jpaPrincipal;
    private JPanel jpaTitulo;
    private JPanel jpaContenido;
    private JLabel lblTitulo;
    private JLabel lblID;
    private JLabel lblModelo;
    private JLabel lblFabricante;
    private JLabel lblAerolinea;
    private JLabel lblTamaño;
    private JLabel lblCapacidad;
    private JLabel lblTipo;
    private JLabel lblAeronaves;
    private JTextField txtID;
    private JTextField txtModelo;
    private JTextField txtFabricante;
    private JComboBox cboAerolineas;
    private JTextField txtTamaño;
    private JTextField txtCapacidad;
    private JComboBox cboTipo;
    private JComboBox cboAeronaves;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscarId;
    private JButton btnListar;
    private JButton btnLeerCBO;
    private JButton btnLimpiar;
    private JTable tblDatos;
    private JLabel lblImagen2;
    private JLabel lblImagen1;
    private JPanel jpaDatos;
    private JScrollPane sclPanDatos;
    private JPanel jpaBotones;
    private JButton btnBuscarMo;
    DefaultTableModel modelo;
    String respuesta = "";
    static final String URL2= "http://192.168.0.15:8080/api/v1/aerolineas";
    static final String URL = "http://192.168.0.15:8080/api/v1/aeronaves";


    public frmAeronave() {
        iniciar();
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/addAeronave");
                    Invocation.Builder solicitud = target.request();
                    Aeronave aeronave = new Aeronave();
                    aeronave.setModelo(txtModelo.getText());
                    aeronave.setFabricante(txtFabricante.getText());
                    aeronave.setAerolinea(String.valueOf(cboAerolineas.getSelectedItem()));
                    aeronave.setTamaño(Double.parseDouble(txtTamaño.getText()));
                    aeronave.setCapacidad(Integer.parseInt(txtCapacidad.getText()));
                    aeronave.setTipo(String.valueOf(cboTipo.getSelectedItem()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(aeronave);
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
                    Aeronave aeronave = new Aeronave();
                    aeronave.setId(Long.parseLong(txtID.getText()));
                    aeronave.setModelo(txtModelo.getText());
                    aeronave.setFabricante(txtFabricante.getText());
                    aeronave.setAerolinea(String.valueOf(cboAerolineas.getSelectedItem()));
                    aeronave.setTamaño(Double.parseDouble(txtTamaño.getText()));
                    aeronave.setCapacidad(Integer.parseInt(txtCapacidad.getText()));
                    aeronave.setTipo(String.valueOf(cboTipo.getSelectedItem()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(aeronave);
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
        sclPanDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               //aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaah
            }
        });
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    int filaSeleccionada = tblDatos.getSelectedRow();
                    txtID.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                    txtModelo.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                    txtFabricante.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                    cboAerolineas.setSelectedItem(modelo.getValueAt(filaSeleccionada,3).toString());
                    txtTamaño.setText(modelo.getValueAt(filaSeleccionada,4).toString());
                    txtCapacidad.setText(modelo.getValueAt(filaSeleccionada,5).toString());
                    cboTipo.setSelectedItem(modelo.getValueAt(filaSeleccionada,6).toString());
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
        btnBuscarId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    String id = JOptionPane.showInputDialog("¿Cuál es el ID a buscar?");
                    WebTarget target = client.target(URL+"/id/" + id);
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Aeronave data = new Gson().fromJson(responseJson,Aeronave.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getModelo(),
                                data.getFabricante(),
                                data.getAerolinea(),
                                data.getTamaño(),
                                data.getCapacidad(),
                                data.getTipo()
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
        btnBuscarMo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/modelo/" + txtModelo.getText());
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Aeronave data = new Gson().fromJson(responseJson,Aeronave.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getModelo(),
                                data.getFabricante(),
                                data.getAerolinea(),
                                data.getTamaño(),
                                data.getCapacidad(),
                                data.getTipo()
                        };
                        modelo.addRow(registro);
                        tblDatos.setModel(modelo);
                    }else{
                        throw new Exception("> No se encontró el modelo");
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
                    Object obj = cboAeronaves.getSelectedItem();
                    long item = ((Item)obj).getID();
                    JOptionPane.showMessageDialog(null,"ID de la aeronave: " + item,"INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar();
            }
        });
    }
    private void iniciar(){
        setTitle("Registro de Aeronaves");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoAvion.png");
        setIconImage(imagen.getImage());
        //Imagenes
        lblImagen1.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/avion.png"));
        lblImagen2.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/Helicoptero.png"));
        //Botones
        //boton registrar
        btnRegistrar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoRegistrar.png"));
        //boton actualizar
        btnActualizar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoActualizar.png"));
        //boton eliminar
        btnEliminar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoEliminar.png"));
        //boton buscar id
        btnBuscarId.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoBuscar.png"));
        //boton buscar modelo
        btnBuscarMo.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoBuscar.png"));
        //boton listar
        btnListar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoListar.png"));
        //boton leerCBO
        btnLeerCBO.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoLeerCBO.png"));
        //boton limpiar
        btnLimpiar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoLimpiarAVB.png"));
        //-----------------------------------------------------------------------------------
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("ID");
        modelo.addColumn("Modelo");
        modelo.addColumn("Fabricante");
        modelo.addColumn("Aerolínea");
        modelo.addColumn("Tamaño");
        modelo.addColumn("Capacidad");
        modelo.addColumn("Tipo");
        llenarCBOAerolineas();
        leerDatos();
        limpiar();
    }
    private void leerDatos(){
        Client client = ClientBuilder.newClient();
        try{
            WebTarget target = client.target(URL+"");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Aeronave> data = new Gson().fromJson(responseJson,new TypeToken<List<Aeronave>>(){}.getType());
            if(get.getStatus() == 200){
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
                modelo.setRowCount(0);
                for (Aeronave aeronave: data) {
                    Object [] registro={
                            aeronave.getId(),
                            aeronave.getModelo(),
                            aeronave.getFabricante(),
                            aeronave.getAerolinea(),
                            aeronave.getTamaño(),
                            aeronave.getCapacidad(),
                            aeronave.getTipo()
                    };
                    modelo.addRow(registro);
                    //Llenar el Cbo Aeronave
                    Item item = new Item(aeronave.getId(),aeronave.getModelo());
                    modeloCombo.addElement(item);
                }
                tblDatos.setModel(modelo);
                cboAeronaves.setModel(modeloCombo);
            }else{
                throw new Exception("> No se cargaron los datos.");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        finally{
            client.close();
        }
    }
    private void llenarCBOAerolineas(){
        Client client = ClientBuilder.newClient();
        try{
            WebTarget target = client.target(URL2+"");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Aerolinea> data = new Gson().fromJson(responseJson,new TypeToken<List<Aerolinea>>(){}.getType());
            if(get.getStatus() == 200){
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
                for (Aerolinea aerolinea: data) {
                    modeloCombo.addElement(aerolinea.getNombre());
                }
                cboAerolineas.setModel(modeloCombo);
            }else{
                throw new Exception("No se cargaron los datos de aerolinea");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        finally{
            client.close();
        }
    }
    private void limpiar(){
        txtID.setText("0");
        txtModelo.setText("");
        txtFabricante.setText("");
        txtTamaño.setText("0");
        txtCapacidad.setText("0");
    }

}
