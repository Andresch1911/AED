package principal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.layout.VBox;

public class EliminarSQLController {
	@FXML
	private VBox view;
	@FXML
	private TextField CodResiText;
	@FXML
	private Button EliminarButton;
	private int CodResidencia;
	// Event Listener on Button[#EliminarButton].onAction
	@FXML
	public void onEliminarButtonAction(ActionEvent event) {
		CodResidencia = Integer.parseInt(CodResiText.getText().toString());
		SQLConnection.Eliminar(CodResidencia);
	}
	public EliminarSQLController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Eliminar.fxml"));
		loader.setController(this);
		loader.load();

	}
	public VBox getView() {
		return view;
	}
}
