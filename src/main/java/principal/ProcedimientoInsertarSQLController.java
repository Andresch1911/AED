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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import javafx.scene.control.CheckBox;

public class ProcedimientoInsertarSQLController implements Initializable {
	@FXML
	private HBox view;
	@FXML
	private TextField NombreResidenciaText;
	@FXML
	private ComboBox<String> CodigoUniversidadCombo;
	@FXML
	private TextField PrecioText;
	@FXML
	private CheckBox ComedorCheck;
	@FXML
	private Button InsertarButton;
	@FXML
	private TextArea TextAreaA;

	// Event Listener on Button[#InsertarButton].onAction
	@FXML
	public void onInsertarButtonAction(ActionEvent event) {
		String nombreResidencia = NombreResidenciaText.getText();
		String nombreUniversidad = CodigoUniversidadCombo.getSelectionModel().getSelectedItem().toString().replace("\n",
				"");
		int precio = Integer.parseInt(PrecioText.getText());
		boolean comedor = ComedorCheck.isSelected();
		if(comedor) {
		SQLConnection.ProcedimientoAlmacenado2(nombreResidencia, nombreUniversidad, precio, 1, TextAreaA);
		}else{
			SQLConnection.ProcedimientoAlmacenado2(nombreResidencia, nombreUniversidad, precio, 0, TextAreaA);
		}
		//System.out.println(nombreResidencia + " " + nombreUniversidad + " " + precio + " " + comedor);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RellenarComboSQL();
	}

	// RELLENAR COMBO

	public void RellenarComboSQL() {
		try {

			ObservableList<String> data = FXCollections.observableArrayList();
			for (int i = 0; i < MySQLConnection.Combo().size(); i++) {
				data.add(SQLConnection.Combo().get(i));
			}
			CodigoUniversidadCombo.setItems(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// CONTROLLERS
	public ProcedimientoInsertarSQLController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProcedimientoInsertar.fxml"));
		loader.setController(this);
		loader.load();

	}

	public HBox getView() {
		return view;
	}

}
