/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horariomaterias;

import conexionmysql.HorarioMateriaDaoImp;
import conexionmysql.MateriaDaoImp;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import materias.Materia;
import registroalumnos.RegistroAlumnos;

/**
 * FXML Controller class
 *
 * @author alanglezh
 */
public class FXMLHorarioMateriasController implements Initializable {

    @FXML
    ObservableList<String> horas;

    @FXML
    private ChoiceBox cbHinicio1, cbHinicio2, cbHinicio3, cbHinicio4, cbHinicio5, cbHinicio6, cbHfin1, cbHfin2,
            cbHfin3, cbHfin4, cbHfin5, cbHfin6;

    @FXML
    private Button btAgregar, btEditar, btEliminar, btGuardar;

    @FXML
    private ChoiceBox<Materia> cbMaterias;

    @FXML
    private TableColumn<HorarioMateria, String> tcNrc, tcAcademico, tcSalon, tcCupo, tcHorasLunes, tcHorasMartes, tcHorasMiercoles,
            tcHorasJueves, tcHorasViernes, tcHorasSabados;

    @FXML
    private TableView<HorarioMateria> tblHorariosMaterias;

    @FXML
    private TextField txfAcademico, txfCupo, txfSalon;

    @FXML
    ObservableList<HorarioMateria> Hmateriast;

    RegistroAlumnos programaPrincipal;
    
    @FXML
    private MenuItem miControlMaterias;
    
    @FXML
    private MenuItem miControlAlumnos;

    public void setProgramaPrincipal(RegistroAlumnos ProgramaPrincipal) {
        this.programaPrincipal = ProgramaPrincipal;
    }
    
    @FXML
    private void ventanaAlumnos() throws IOException{
        programaPrincipal.mostrarVentanaAlumnos();
    }

    @FXML
    private void ventanaMaterias(ActionEvent event) {
        try {
            programaPrincipal.mostrarVentanaMaterias();
        } catch (IOException ex) {
            Logger.getLogger(FXMLHorarioMateriasController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    @FXML
    public void eliminarHorarioMateria(ActionEvent event) {
        HorarioMateria hMateria = obtenerHorarioMateriaSeleccionada();
        System.out.println(hMateria);
        int posicionSeleccionado = tblHorariosMaterias.getSelectionModel().getSelectedIndex();
        if (posicionSeleccionado != -1) {
            HorarioMateriaDaoImp daoMateria = new HorarioMateriaDaoImp(); 
            
            daoMateria.eliminarHorarioMateria(hMateria.getId());
            Hmateriast.remove(posicionSeleccionado);
        } else {
            //JOptionPane.showMessageDialog(null, "Debe de selecionar primero un alumno de la tabla");
        }
    }
    
    @FXML
    public void guardarCambios(ActionEvent event) {
        int nrc = obtenerMateriaSeleccionada().getNRC();
        String academico = txfAcademico.getText();
        String salon = txfSalon.getText();
        String cupo = txfCupo.getText();

        String horasLunes = validarCampos((String) cbHinicio1.getValue(), (String) cbHfin1.getValue());
        String horasMartes = validarCampos((String) cbHinicio2.getValue(), (String) cbHfin2.getValue());
        String horasMiercoles = validarCampos((String) cbHinicio3.getValue(), (String) cbHfin3.getValue());
        String horasJueves = validarCampos((String) cbHinicio4.getValue(), (String) cbHfin4.getValue());
        String horasViernes = validarCampos((String) cbHinicio5.getValue(), (String) cbHfin5.getValue());
        String horasSabados = validarCampos((String) cbHinicio6.getValue(), (String) cbHfin6.getValue());

        if ((String.valueOf(nrc).equals("") != true) && (academico.equals("") != true)
                && (cupo.equals("") != true) && (salon.equals("") != true)) {
            HorarioMateria Hmateria = new HorarioMateria(nrc, academico, salon, Integer.parseInt(cupo), horasLunes, horasMartes,
                    horasMiercoles, horasJueves, horasViernes, horasSabados);

            HorarioMateriaDaoImp daoHMateria = new HorarioMateriaDaoImp();

            daoHMateria.editarHorarioMateria(nrc, academico, salon, Integer.parseInt(cupo), formatoBD(horasLunes), formatoBD(horasMartes),
                    formatoBD(horasMiercoles), formatoBD(horasJueves), formatoBD(horasViernes), formatoBD(horasSabados));

            int posicionSeleccionado = tblHorariosMaterias.getSelectionModel().getSelectedIndex();
            
           Hmateriast.set(posicionSeleccionado, Hmateria);
            limpiar();
        } else {
            //JOptionPane.showMessageDialog(null, "Rellene todos los campos");
        }
        
            

    }

    @FXML
    public String formatoBD(String hora) {
        if (hora != null) {
            return "'" + hora + "'";
        } else {
            return null;
        }
    }

    @FXML
    public int encontrarMateria() {
        try {
            int posicion = 0;
            MateriaDaoImp daoHmateria = new MateriaDaoImp();
            List<Materia> lista = daoHmateria.getMaterias();
            HorarioMateria hM = obtenerHorarioMateriaSeleccionada();
            for (Materia materia : lista) {
                if (materia.getNRC() == hM.getNrc()) {
                    return posicion;
                }
                posicion++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLHorarioMateriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @FXML
    public HorarioMateria obtenerHorarioMateriaSeleccionada() {
        System.out.println("Entra");
        return tblHorariosMaterias.getSelectionModel().getSelectedItem();
    }
    
    @FXML 
    public String verificarDia(String dia,int i){
        
        if(dia != null){
            String[] partes = dia.split("-");
            if(i ==0){
                return partes[0];
            }else{
                return partes[1];
            }
        }else{
            return null;
        }
    }

    @FXML
    public void EditarHorarioMateria() {
        int posicion = tblHorariosMaterias.getSelectionModel().getSelectedIndex();
        if (posicion != -1) {
            btAgregar.setVisible(false);
            btEliminar.setVisible(false);
            btEditar.setVisible(false);
            btGuardar.setVisible(true);
            int posicionMateria = encontrarMateria();
            HorarioMateria hMateria = obtenerHorarioMateriaSeleccionada();

            cbMaterias.getSelectionModel().select(posicionMateria);
            cbMaterias.setDisable(true);
            txfAcademico.setText(hMateria.getAcademico());
            txfSalon.setText(hMateria.getSalon());
            txfCupo.setText(String.valueOf(hMateria.getCupo()));
            
            cbHinicio1.setValue(verificarDia(hMateria.getHorasLunes(),0));
            cbHinicio2.setValue(verificarDia(hMateria.getHorasMartes(),0));
            cbHinicio3.setValue(verificarDia(hMateria.getHorasMiercoles(),0));
            cbHinicio4.setValue(verificarDia(hMateria.getHorasJueves(),0));
            cbHinicio5.setValue(verificarDia(hMateria.getHorasViernes(),0));
            cbHinicio6.setValue(verificarDia(hMateria.getHorasSabados(),0));
            
            cbHfin1.setValue(verificarDia(hMateria.getHorasLunes(),1));
            cbHfin2.setValue(verificarDia(hMateria.getHorasMartes(),1));
            cbHfin3.setValue(verificarDia(hMateria.getHorasMiercoles(),1));
            cbHfin4.setValue(verificarDia(hMateria.getHorasJueves(),1));
            cbHfin5.setValue(verificarDia(hMateria.getHorasViernes(),1));
            cbHfin6.setValue(verificarDia(hMateria.getHorasSabados(),1));
            
            
        } else {
            System.out.println("Seleccione  un alumno");

        }
    }

    public String validarCampos(String horaInicio, String horaFin) {
        if (horaInicio != null && horaFin != null) {
            return "" + horaInicio + "-" + horaFin + "";
        } else {
            return null;
        }

    }

    @FXML
    void agregarHorario(ActionEvent event) {
        int nrc = obtenerMateriaSeleccionada().getNRC();
        String academico = txfAcademico.getText();
        String salon = txfSalon.getText();
        String cupo = txfCupo.getText();

        String horasLunes = validarCampos((String) cbHinicio1.getValue(), (String) cbHfin1.getValue());
        String horasMartes = validarCampos((String) cbHinicio2.getValue(), (String) cbHfin2.getValue());
        String horasMiercoles = validarCampos((String) cbHinicio3.getValue(), (String) cbHfin3.getValue());
        String horasJueves = validarCampos((String) cbHinicio4.getValue(), (String) cbHfin4.getValue());
        String horasViernes = validarCampos((String) cbHinicio5.getValue(), (String) cbHfin5.getValue());
        String horasSabados = validarCampos((String) cbHinicio6.getValue(), (String) cbHfin6.getValue());

        if ((String.valueOf(nrc).equals("") != true) && (academico.equals("") != true)
                && (cupo.equals("") != true) && (salon.equals("") != true)) {
            HorarioMateria Hmateria = new HorarioMateria(nrc, academico, salon, Integer.parseInt(cupo), horasLunes, horasMartes,
                    horasMiercoles, horasJueves, horasViernes, horasSabados);

            HorarioMateriaDaoImp daoHMateria = new HorarioMateriaDaoImp();

            daoHMateria.nuevoHorarioMateria(nrc, academico, salon, Integer.parseInt(cupo), formatoBD(horasLunes), formatoBD(horasMartes),
                    formatoBD(horasMiercoles), formatoBD(horasJueves), formatoBD(horasViernes), formatoBD(horasSabados));

            Hmateriast.add(Hmateria);
            limpiar();
        } else {
            //JOptionPane.showMessageDialog(null, "Rellene todo");
        }
    }

    @FXML
    private Materia obtenerMateriaSeleccionada() {
        Materia materia = (Materia) cbMaterias.getSelectionModel().getSelectedItem();
        return materia;

    }

    @FXML
    public void limpiar() {
        cbHinicio1.setValue(null);
        cbHinicio2.setValue(null);
        cbHinicio3.setValue(null);
        cbHinicio4.setValue(null);
        cbHinicio5.setValue(null);
        cbHinicio6.setValue(null);
        cbHfin1.setValue(null);
        cbHfin2.setValue(null);
        cbHfin3.setValue(null);
        cbHfin4.setValue(null);
        cbHfin5.setValue(null);
        cbHfin6.setValue(null);

        txfAcademico.setText("");
        txfCupo.setText("");
        txfSalon.setText("");

        btAgregar.setVisible(true);
        btEliminar.setVisible(true);
        btEditar.setVisible(true);
        btGuardar.setVisible(false);
        cbMaterias.setDisable(false);
    }

    public void cargarTablaHorarioMaterias() throws SQLException {

        try {
            tcNrc.setCellValueFactory(new PropertyValueFactory<>("nrc"));
            tcAcademico.setCellValueFactory(new PropertyValueFactory<>("academico"));
            tcSalon.setCellValueFactory(new PropertyValueFactory<>("salon"));
            tcCupo.setCellValueFactory(new PropertyValueFactory<>("cupo"));
            tcHorasLunes.setCellValueFactory(new PropertyValueFactory<>("HorasLunes"));
            tcHorasMartes.setCellValueFactory(new PropertyValueFactory<>("HorasMartes"));
            tcHorasMiercoles.setCellValueFactory(new PropertyValueFactory<>("HorasMiercoles"));
            tcHorasJueves.setCellValueFactory(new PropertyValueFactory<>("HorasJueves"));
            tcHorasViernes.setCellValueFactory(new PropertyValueFactory<>("HorasViernes"));
            tcHorasSabados.setCellValueFactory(new PropertyValueFactory<>("HorasSabados"));

            HorarioMateriaDaoImp daoHMateria = new HorarioMateriaDaoImp();

            List<HorarioMateria> hMateriasD = daoHMateria.getHorariosMaterias();

            Hmateriast = FXCollections.observableArrayList();

            for (HorarioMateria hMateria : hMateriasD) {
                Hmateriast.add(hMateria);
            }
            tblHorariosMaterias.setItems(Hmateriast);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void cargarMaterias() throws SQLException {
        MateriaDaoImp daoMaterias = new MateriaDaoImp();
        List<Materia> listaMaterias = daoMaterias.getMaterias();
        ObservableList materiasOb = FXCollections.observableArrayList();

        for (Materia materia : listaMaterias) {
            materiasOb.add(materia);
        }

        cbMaterias.setItems(materiasOb);
        cbMaterias.getSelectionModel().select(0);

    }

    public void cargarHorarioFin() {
        String horarios[] = {null, "8:00", "9:00", "10:00",
            "11:00", "12:00", "13:00", "14:00", "15:00", "16:00",
             "17:00", "18:00", "19:00", "20:00"};
        horas = FXCollections.observableArrayList();
        for (int i = 0; i <= 13; i++) {
            horas.add(horarios[i]);
        }
        cbHfin1.setItems(horas);
        cbHfin2.setItems(horas);
        cbHfin3.setItems(horas);
        cbHfin4.setItems(horas);
        cbHfin5.setItems(horas);
        cbHfin6.setItems(horas);

    }

    public void cargarHorasInicio() {
        String horarios[] = {null, "7:00", "8:00", "9:00", "10:00",
            "11:00", "12:00", "13:00", "14:00", "15:00", "16:00",
             "17:00", "18:00", "19:00"};
        horas = FXCollections.observableArrayList();

        for (int i = 0; i <= 13; i++) {
            horas.add(horarios[i]);
        }
        cbHinicio1.setItems(horas);
        cbHinicio2.setItems(horas);
        cbHinicio3.setItems(horas);
        cbHinicio4.setItems(horas);
        cbHinicio5.setItems(horas);
        cbHinicio6.setItems(horas);

    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cargarHorasInicio();
            cargarHorarioFin();
            cargarMaterias();
            cargarTablaHorarioMaterias();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLHorarioMateriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
