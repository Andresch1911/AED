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

public class ModificarController implements Initializable {
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
	private IntegerProperty codigo = new SimpleIntegerProperty();

	private int codigoResidencia;
	private String nombreResidencia;
	private String nombreUniversidad;
	private int preciopagado;
	private boolean comedor;
	
	private int a = 0;

	
	//BUTTON	
	@FXML
 	public void onModificarButtonAction(ActionEvent event) {
		try {			
			ModificarMySQL();	
			
		} catch (Exception e) {
		}
	}
	


	//METODOS MYSQL
	public void RellenarComboMySQL() {
		try {

			ObservableList<String> data = FXCollections.observableArrayList();
			for (int i = 0; i < MySQLConnection.Combo().size(); i++) {
				data.add(MySQLConnection.Combo().get(i));
			}

			CodUniCombo.setItems(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ActualizarMySQL() {
		try {
			String[] cadena = MySQLConnection.Visualizar(getCodResi()).split("/");
			// DATOS ANTERIORES
			NomResiText.setText(cadena[1]);
			for (int i = 0; i < MySQLConnection.Combo().size(); i++) {	if (MySQLConnection.Combo(MySQLConnection.Combo().get(i).toString(), getCodResi())) {CodUniCombo.getSelectionModel().select(i);}}
			CantidadText.setText(cadena[3]);
			ComedorCheck.setSelected(Boolean.valueOf(cadena[4]));

			

		} catch (Exception e) {

		}
	}
	
	
	
	//METODOS
	private void ModificarMySQL() {
		try {
			codigoResidencia = getCodResi();
			nombreResidencia = NomResiText.getText();
			nombreUniversidad = CodUniCombo.getSelectionModel().getSelectedItem().toString();			
			preciopagado = Integer.parseInt(CantidadText.getText());
			comedor = ComedorCheck.isSelected();
			MySQLConnection.UpdateMySQL(codigoResidencia, nombreResidencia, nombreUniversidad, preciopagado, comedor);
			
			}catch(Exception e) {
				System.out.println("ERROR EN LA INTRODUCCION DE DATOS");
			}
		
	}
	
	
	//INITIALIZE
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
		RellenarComboMySQL();
		NomResiText.setText("Vacio");
		CantidadText.setText("Vacio");
		CodUniCombo.getSelectionModel().selectLast();
		ComedorCheck.setSelected(Boolean.valueOf(false));
		
		// ------------------------------------------------------
		CodResidenciaText.textProperty().bindBidirectional(codResi, new NumberStringConverter());
		
		codResiProperty().addListener((o, ov, nv) -> {
			ActualizarMySQL();	
		});
		codigoProperty().addListener((o, ov, nv) -> {
			System.out.println("Numero tabla: "+getCodigo());
			System.out.println(codigoProperty().getValue());
			try {
			AppController controller = new AppController();
			controller.NumTabProperty().getValue();
			}catch(Exception e) {
				
			}
		});
		}catch(Exception e) {
			
		}
		
	}
	
	//GETS SETS ETC
	public ModificarController() throws IOException {
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



	

	public final IntegerProperty codigoProperty() {
		return this.codigo;
	}
	



	public final int getCodigo() {
		return this.codigoProperty().get();
	}
	



	public final void setCodigo(final int codigo) {
		this.codigoProperty().set(codigo);
	}
	

	
	
	

}
