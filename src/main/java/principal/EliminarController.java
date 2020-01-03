package principal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.layout.VBox;

public class EliminarController {
	@FXML
	private VBox view;
	@FXML
	private TextField CodResiText;
	@FXML
	private Button EliminarButton;
	@SuppressWarnings("unused")
	private int CodResidencia;
	// Event Listener on Button[#EliminarButton].onAction
	@FXML
	public void onEliminarButtonAction(ActionEvent event) {
		CodResidencia = Integer.parseInt(CodResiText.getText().toString());
		MySQLConnection.Eliminar(CodResidencia);
	}
	public EliminarController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Eliminar.fxml"));
		loader.setController(this);
		loader.load();

	}
	public VBox getView() {
		return view;
	}
}
