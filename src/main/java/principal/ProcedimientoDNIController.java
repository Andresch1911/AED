package principal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;

import javafx.scene.layout.VBox;

public class ProcedimientoDNIController implements Initializable{
	@FXML
	private VBox view;
	@FXML
	private TextField dniEntrada;
	@FXML
	private Button VizualizarButton;
	
	private StringProperty dni  = new SimpleStringProperty();
	

	// Event Listener on Button[#VizualizarButton].onAction
	@FXML
	public void onVizualizarButtonAction(ActionEvent event) {
		try {
		AppController controller = new AppController();
		MySQLConnection.ProcedimientAlmacenado1(dni, controller.getTextAreaMy());
		}catch(Exception e) {
			
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dniProperty().bind(dniEntrada.onInputMethodTextChangedProperty());
		
	}
	//GETS SETS ETC
	public ProcedimientoDNIController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProcedimientoDNI.fxml"));
		loader.setController(this);
		loader.load();

	}

	public VBox getView() {
		return view;
	}

	public final StringProperty dniProperty() {
		return this.dni;
	}
	

	public final String getDni() {
		return this.dniProperty().get();
	}
	

	public final void setDni(final String dni) {
		this.dniProperty().set(dni);
	}
	
}
