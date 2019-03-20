/**
 *
 * @author alanglezh
 * Descripcion: Clase para controlar la interfaz FXMLAlumnos
 */
package registroalumnos;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import alumnos.Alumno;
import conexionmysql.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLAlumnosController implements Initializable {

    private RegistroAlumnos programaPrincipal;

    @FXML
    private Button btAgregar;

    @FXML
    private Button btEliminar;

    @FXML
    private Button btEditar;

    @FXML
    private Button btGuardar;

    @FXML
    private TextField txfNombre;

    @FXML
    private TextField txfMatricula;

    @FXML
    private TextField txfApellidoPaterno;

    @FXML
    private TextField txfApellidoMaterno;

    @FXML
    private TableColumn tcMatricula;

    @FXML
    private TableColumn tcPaterno;

    @FXML
    private TableColumn tcMaterno;

    @FXML
    private TableColumn tcNombre;

    @FXML
    private TableView<Alumno> tblAlumnos;

    @FXML
    ObservableList<Alumno> alumnost;

    @FXML
    private MenuItem miControlMaterias;
    
    @FXML
    private MenuItem miControlHorariosMaterias;

    public void setProgramaPrincipal(RegistroAlumnos ProgramaPrincipal) {
        this.programaPrincipal = ProgramaPrincipal;
    }
    
    @FXML
    private void ventanaHorariosMaterias() throws IOException{
        programaPrincipal.mostrarVentanaHorarioMaterias();
    }

    @FXML
    private void ventanaMaterias(ActionEvent event) {
        try {
            programaPrincipal.mostrarVentanaMaterias();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
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

        String nombre = txfNombre.getText();
        String paterno = txfApellidoPaterno.getText();
        String materno = txfApellidoMaterno.getText();
        String matricula = obtenerAlumnoSeleccionado().getMatricula();
        String matriculaNueva = txfMatricula.getText();
        System.out.println(matricula);
        if ((matricula.equals("") != true) && (nombre.equals("") != true)
                && (paterno.equals("") != true) && (materno.equals("") != true)) {
            AlumnoDaoImp daoAlumno = new AlumnoDaoImp();
            daoAlumno.editarAlumno(matricula, nombre, paterno, materno, matriculaNueva);
            Alumno alumno = new Alumno(nombre, paterno, materno, matriculaNueva);
            int posicionSeleccionado = tblAlumnos.getSelectionModel().getSelectedIndex();
            alumnost.set(posicionSeleccionado, alumno);
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos");
        }

    }

    /**
     * Elimina el alumno que el usuario selecciono de la tabla
     *
     * @param event recibe el evento cuando el usuario dda click en eliminar
     */
    @FXML
    private void eliminarAlumno(ActionEvent event) {
        Alumno alumno = obtenerAlumnoSeleccionado();
        int posicionSeleccionado = tblAlumnos.getSelectionModel().getSelectedIndex();
        if (posicionSeleccionado != -1) {
            AlumnoDaoImp daoAlumno = new AlumnoDaoImp();
            daoAlumno.eliminarAlumno(alumno.getMatricula());
            alumnost.remove(posicionSeleccionado);
        } else {
            //JOptionPane.showMessageDialog(null, "Debe de selecionar primero un alumno de la tabla");
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
    private void EditarAlumno(ActionEvent event) {

        int posicion = tblAlumnos.getSelectionModel().getSelectedIndex();
        if (posicion != -1) {
            btAgregar.setVisible(false);
            btEliminar.setVisible(false);
            btEditar.setVisible(false);
            btGuardar.setVisible(true);
            Alumno alumno = obtenerAlumnoSeleccionado();
            System.out.println("AQui");
            txfMatricula.setText(alumno.getMatricula());
            txfNombre.setText(alumno.getNombre());
            txfApellidoPaterno.setText(alumno.getApellidoPaterno());
            txfApellidoMaterno.setText(alumno.getApellidoMaterno());
        } else {
            System.out.println("Seleccione  un alumno");

        }
    }

    /**
     * Obtiene el alumno que el usuario selecciona de la tabla
     *
     * @return regrese el alumno que el usuario esta seleccionando de la tabla
     * regresa null si la tabla esta vacia
     */
    @FXML
    private Alumno obtenerAlumnoSeleccionado() {
        if (tblAlumnos != null) {
            Alumno alumno = tblAlumnos.getSelectionModel().getSelectedItem();
            return alumno;
        } else {
            return null;
        }
    }

    /**
     *
     * @param event recibe el evento cuando el usuario da click en el boton
     * agregar
     */
    @FXML
    private void agregarAlumno(ActionEvent event) {
        String matricula = txfMatricula.getText();
        String nombre = txfNombre.getText();
        String paterno = txfApellidoPaterno.getText();
        String materno = txfApellidoMaterno.getText();
        if ((matricula.equals("") != true) && (nombre.equals("") != true)
                && (paterno.equals("") != true) && (materno.equals("") != true)) {
            Alumno alumno = new Alumno(nombre, paterno, materno, matricula);
            AlumnoDaoImp daoAlumno = new AlumnoDaoImp();
            daoAlumno.nuevoAlumno(nombre, materno, paterno, matricula);
            alumnost.add(alumno);
            limpiar();
        } else {
            //JOptionPane.showMessageDialog(null, "Rellene todos los campos");
        }
    }

    /**
     * Quita eltexto de todos los textfield para que el usuario ingrese datos
     */
    @FXML
    private void limpiar() {
        txfMatricula.setText("");
        txfNombre.setText("");
        txfApellidoPaterno.setText("");
        txfApellidoMaterno.setText("");
        btAgregar.setVisible(true);
        btEliminar.setVisible(true);
        btEditar.setVisible(true);
        btGuardar.setVisible(false);

    }

    /**
     * Cargar en la tabla los datos de los alumnos recuperados de la base de
     * datos
     */
    @FXML
    private void cargarTablaAlumnos() {

        try {
            tcMatricula.setCellValueFactory(new PropertyValueFactory<Alumno, String>("matricula"));
            tcNombre.setCellValueFactory(new PropertyValueFactory<Alumno, String>("nombre"));
            tcPaterno.setCellValueFactory(new PropertyValueFactory<Alumno, String>("apellidoPaterno"));
            tcMaterno.setCellValueFactory(new PropertyValueFactory<Alumno, String>("apellidoMaterno"));

            AlumnoDaoImp daoAlumno = new AlumnoDaoImp();

            List<Alumno> alumnosD = daoAlumno.getAlumnos();
            alumnost = FXCollections.observableArrayList();
            for (Alumno alumno : alumnosD) {
                alumnost.add(alumno);
            }
            tblAlumnos.setItems(alumnost);
           
            

        } catch (SQLException ex) {
            Logger.getLogger(FXMLAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cargarTablaAlumnos();
        

    }

}
