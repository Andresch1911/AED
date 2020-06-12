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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.CheckBox;

import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

public class ModificarSQLController implements Initializable {
	@FXML
	private VBox view;
	@FXML
	private TextField NomResiText;
	@FXML
	private ComboBox<String> CodUniCombo;
	@FXML
	private TextField CantidadText;
	@FXML
	private CheckBox ComedorCheck;
	@FXML
	private Button ModificarButton;
	@FXML
	private TextField CodResidenciaText;

	private IntegerProperty codResi = new SimpleIntegerProperty();
	
	int codigoResidencia;
	private String nombreResidencia;
	private String nombreUniversidad;
	private int preciopagado;
	private boolean comedor;
	
	

	
	//BUTTON	
	@FXML
 	public void onModificarButtonAction(ActionEvent event) {
		ModificarSQL();
		
			
			
		
	}
	


	//METODOS SQL
	public void RellenarComboSQL() {
		try {

			ObservableList<String> data = FXCollections.observableArrayList();
			for (int i = 0; i < SQLConnection.Combo().size(); i++) {
				data.add(MySQLConnection.Combo().get(i));
			}

			CodUniCombo.setItems(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ActualizarSQL() {		
			String[] cadena = SQLConnection.Visualizar(getCodResi()).split("/");
			// DATOS ANTERIORES
			NomResiText.setText(cadena[1]);
			for (int i = 0; i < SQLConnection.Combo().size(); i++) {	if (SQLConnection.Combo(SQLConnection.Combo().get(i).toString(), getCodResi())) {CodUniCombo.getSelectionModel().select(i);}}
			CantidadText.setText(cadena[3]);
			ComedorCheck.setSelected(Boolean.valueOf(cadena[4]));

			

		
	}
	
	
	
	//METODOS
	private void ModificarSQL() {
		try {
			codigoResidencia = getCodResi();
			nombreResidencia = NomResiText.getText();
			nombreUniversidad = CodUniCombo.getSelectionModel().getSelectedItem().toString();			
			preciopagado = Integer.parseInt(CantidadText.getText());
			comedor = ComedorCheck.isSelected();
			
				SQLConnection.UpdateSQL(codigoResidencia, nombreResidencia, nombreUniversidad, preciopagado, comedor);
			
			
			
			
			}catch(Exception e) {
				System.out.println("ERROR EN LA INTRODUCCION DE DATOS");
			}
		
	}
	
	
	//INITIALIZE
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {			
		RellenarComboSQL();
		
		CodResidenciaText.textProperty().bindBidirectional(codResi, new NumberStringConverter());
		codResiProperty().addListener((o, ov, nv) -> {
			ActualizarSQL();	
		});
		
		}catch(Exception e) {
			
		}
		
	}
	
	//GETS SETS ETC
	public ModificarSQLController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Modificar.fxml"));
		loader.setController(this);
		loader.load();

	}

	public VBox getView() {
		return view;
	}



	public final IntegerProperty codResiProperty() {
		return this.codResi;
	}
	



	public final int getCodResi() {
		return this.codResiProperty().get();
	}
	



	public final void setCodResi(final int codResi) {
		this.codResiProperty().set(codResi);
	}
	

}

