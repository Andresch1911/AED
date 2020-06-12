package principal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.layout.HBox;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;

public class ProcedimientoPrecioSQLController implements Initializable {
	@FXML
	private HBox view;
	@FXML
	private TextField nombreUniversidadText;
	@FXML
	private TextField PrecioText;
	@FXML
	private Button ejecutarButton;
	@FXML
	private TextArea TextAreaA;

	// Event Listener on Button[#ejecutarButton].onAction
	@FXML
	public void onejecutarButtonAction(ActionEvent event) {
		String nombreUniversidad = nombreUniversidadText.getText();
		int precio = Integer.parseInt(PrecioText.getText());
		SQLConnection.ProcedimientoAlmacenado3(nombreUniversidad, precio, TextAreaA);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public ProcedimientoPrecioSQLController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProcedimientoPrecio.fxml"));
		loader.setController(this);
		loader.load();

	}

	public HBox getView() {
		return view;
	}
}
