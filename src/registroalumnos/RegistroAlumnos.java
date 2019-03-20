/**
 * @author Alan Gonzalez Heredia
 * Desripcion: Clase principal
 */
package registroalumnos;

import horariomaterias.FXMLHorarioMateriasController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import materias.FXMLMateriasController;

public class RegistroAlumnos extends Application {

    private Stage stagePrincipal;
    private AnchorPane rootPane;

    @Override
    public void start(Stage stage) throws IOException {
        this.stagePrincipal = stage;
        mostrarVentanaAlumnos();

    }

    public void mostrarVentanaAlumnos() throws IOException {

        FXMLLoader loader = new FXMLLoader(RegistroAlumnos.class.getResource("FXMLAlumnos.fxml"));
        rootPane = (AnchorPane) loader.load();
        Scene scene = new Scene(rootPane);
        stagePrincipal.setTitle("Control Alumnos");
        stagePrincipal.setScene(scene);
        FXMLAlumnosController controller = loader.getController();
        controller.setProgramaPrincipal(this);
        stagePrincipal.show();
    }

    public void mostrarVentanaMaterias() throws IOException {
        FXMLLoader loader = new FXMLLoader(RegistroAlumnos.class.getResource("/materias/FXMLMaterias.fxml"));
        rootPane = (AnchorPane) loader.load();
        Scene scene = new Scene(rootPane);
        stagePrincipal.setTitle("Control Materias");
        stagePrincipal.setScene(scene);
        FXMLMateriasController controller = loader.getController();
        controller.setProgramaPrincipal(this);
        stagePrincipal.show();
    }

    public void mostrarVentanaHorarioMaterias() throws IOException {
        FXMLLoader loader = new FXMLLoader(RegistroAlumnos.class.getResource("/horariomaterias/FXMLHorarioMaterias.fxml"));
        rootPane = (AnchorPane) loader.load();
        Scene scene = new Scene(rootPane);
        stagePrincipal.setTitle("Control Horario Materias");
        stagePrincipal.setScene(scene);
        FXMLHorarioMateriasController controller = loader.getController();
        controller.setProgramaPrincipal(this);
        stagePrincipal.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
