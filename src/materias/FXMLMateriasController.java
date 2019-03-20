/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package materias;

import alumnos.Alumno;
import conexionmysql.MateriaDaoImp;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import registroalumnos.RegistroAlumnos;

/**
 * FXML Controller class
 *
 * @author alanglezh
 */
public class FXMLMateriasController implements Initializable {

    private RegistroAlumnos programaPrincipal;

    @FXML
    private TextField txfNRC;

    @FXML
    private TextField txfNombre;

    @FXML
    private TextField txfCreditos;

    @FXML
    private TextField txfHpracticas;

    @FXML
    private TextField txfHteoricas;

    @FXML
    private MenuItem miControlAlumnos, miControlHorarios;

    @FXML
    private TableColumn<Materia, String> tcNRC;

    @FXML
    private TableColumn<Materia, String> tcNombre;

    @FXML
    private TableColumn<Materia, String> tcCreditos;

    @FXML
    private TableColumn<Materia, String> tcHpracticas;

    @FXML
    private TableColumn<Materia, String> tcHteoricas;

    @FXML
    private TableView<Materia> tblMaterias;

    @FXML
    private Button btAgregar;

    @FXML
    private Button btEliminar;

    @FXML
    private Button btEditar;

    @FXML
    private Button btGuardar;

    @FXML
    ObservableList<Materia> materiast;
    
    

    public void setProgramaPrincipal(RegistroAlumnos ProgramaPrincipal) {
        this.programaPrincipal = ProgramaPrincipal;
    }

    @FXML
    private void ventanaAlumnos() {
        try {
            programaPrincipal.mostrarVentanaAlumnos();
        } catch (IOException ex) {
            Logger.getLogger(FXMLMateriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML void ventanaHorarios() throws IOException{
        programaPrincipal.mostrarVentanaHorarioMaterias();
    }
    
    
    
    /**
     * Elimina el alumno que el usuario selecciono de la tabla
     *
     * @param event recibe el evento cuando el usuario dda click en eliminar
     */
    @FXML
    private void eliminarMateria(ActionEvent event) {
        Materia materia = obtenerMateriaSeleccionada();
        int posicionSeleccionado = tblMaterias.getSelectionModel().getSelectedIndex();
        if (posicionSeleccionado != -1) {
             MateriaDaoImp daoMateria = new MateriaDaoImp(); 
            daoMateria.eliminarMateria(String.valueOf(materia.getNRC()));
            materiast.remove(posicionSeleccionado);
        } else {
            //JOptionPane.showMessageDialog(null, "Debe de selecionar primero un alumno de la tabla");
        }
    }
    
    /**
     * Guarda el texto de los TXTField y lo envia a la conexionSql para
     * guardarlo
     *
     * @param event Recibe el evento cuando el usuario da click en el boton
     * guardar
     */
    @FXML
    private void guardarCambios(ActionEvent event) {
        
        int nrcNuevo = Integer.parseInt(txfNRC.getText());
        String nombre = txfNombre.getText();
        int creditos = Integer.parseInt(txfCreditos.getText());
        String hPracticas = txfHpracticas.getText();
        String hTeoricas = txfHteoricas.getText();
        
        int nrc = obtenerMateriaSeleccionada().getNRC();
      
     
        if ((String.valueOf(nrc).equals("") != true) && (nombre.equals("") != true)
                && ( (String.valueOf(creditos)).equals("") != true) && (hPracticas.equals("") != true)) {
           MateriaDaoImp daoMateria = new MateriaDaoImp(); 
           daoMateria.editarMateria(nrc, nombre, creditos, hPracticas,hTeoricas, nrcNuevo);
            Materia materia = new Materia(nrcNuevo,nombre, creditos, hPracticas, hTeoricas);
            int posicionSeleccionado = tblMaterias.getSelectionModel().getSelectedIndex();
            materiast.set(posicionSeleccionado, materia);
            limpiar();
        } else {
            //JOptionPane.showMessageDialog(null, "Rellene todos los campos");
        }

    }
    
    /**
     * Envia a los TXTField los datos del alumno seleccionado en la tabla para
     * poder editarlos
     *
     * @param event Recibe el evento cuando el usuario da click en el boton
     * editar alumno
     */
    @FXML
    private void editarMateria(ActionEvent event) {

        int posicion = tblMaterias.getSelectionModel().getSelectedIndex();
        if (posicion != -1) {
            btAgregar.setVisible(false);
            btEliminar.setVisible(false);
            btEditar.setVisible(false);
            btGuardar.setVisible(true);
            Materia materia = obtenerMateriaSeleccionada();
            System.out.println("AQui");
            txfNRC.setText(String.valueOf(materia.getNRC()));
            txfNombre.setText(materia.getNombre());
            txfCreditos.setText(String.valueOf(materia.getCreditos()));
            txfHpracticas.setText(materia.getHoras_practicas());
            txfHteoricas.setText(materia.getHoras_teoricas());
        } else {
            System.out.println("Seleccione  un alumno");

        }
    }
    
    
    @FXML
    private Materia obtenerMateriaSeleccionada() {
        if (tblMaterias != null) {
            Materia materia = tblMaterias.getSelectionModel().getSelectedItem();
            return materia;
        } else {
            return null;
        }
    }
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void agregarMateria(ActionEvent event) {
        String nrc = txfNRC.getText();
        String nombre = txfNombre.getText();
        String creditos = txfCreditos.getText();
        String hPracticas = txfHpracticas.getText();
        String hTeoricas = txfHteoricas.getText();
        if ((nrc.equals("") != true) && (nombre.equals("") != true)
                && (creditos.equals("") != true) && (hPracticas.equals("") != true)) {
            Materia materia = new Materia(Integer.parseInt(nrc),nombre,Integer.parseInt(creditos), hPracticas, hTeoricas);
            MateriaDaoImp daoMateria = new MateriaDaoImp(); 
            daoMateria.nuevaMateria(Integer.parseInt(nrc),nombre, Integer.parseInt(creditos), hPracticas,hTeoricas);
            materiast.add(materia);
            limpiar();
        } else {
            //JOptionPane.showMessageDialog(null, "Rellene todos los campos");
        }
        
    }
    
    /**
     * 
     */
    @FXML
    private void limpiar() {
        txfNRC.setText("");
        txfNombre.setText("");
        txfCreditos.setText("");
        txfHpracticas.setText("");
        txfHteoricas.setText("");
        
        btAgregar.setVisible(true);
        btEliminar.setVisible(true);
        btEditar.setVisible(true);
        btGuardar.setVisible(false);

    }

    @FXML
    public void cargarTablaMaterias() {

        try {
            tcNRC.setCellValueFactory(new PropertyValueFactory<>("NRC"));
            tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tcCreditos.setCellValueFactory(new PropertyValueFactory<>("creditos"));
            tcHpracticas.setCellValueFactory(new PropertyValueFactory<>("horas_practicas"));
            tcHteoricas.setCellValueFactory(new PropertyValueFactory<>("horas_teoricas"));

            MateriaDaoImp daoMateria = new MateriaDaoImp();

            List<Materia> materiasD = daoMateria.getMaterias();

            materiast = FXCollections.observableArrayList();

            for (Materia materia : materiasD) {
                materiast.add(materia);
            }
            tblMaterias.setItems(materiast);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTablaMaterias();
        
    }

}
