package principal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;

public class ProcedimientoDNISQLController implements Initializable{
	@FXML
	private HBox view;
	@FXML
	private TextField dniEntrada;
	@FXML
	private Button VizualizarButton;
	@FXML
    private TableView<Estancias> table;
	
	   @FXML
	    private TableColumn<Estancias, String> ResidenciaColumn;

	    @FXML
	    private TableColumn<Estancias, String>UniversidadColumn;

	    @FXML
	    private TableColumn<Estancias, String> FechaIniColumn;

	    @FXML
	    private TableColumn<Estancias, String> FechaFinColumn;

	    @FXML
	    private TableColumn<Estancias, String> PrecioColumn;
	
	private StringProperty dni  = new SimpleStringProperty();

	

	// Event Listener on Button[#VizualizarButton].onAction
	@FXML
	public void onVizualizarButtonAction(ActionEvent event) {
		
				
	
	}
		

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ListProperty<Estancias> estancias = new SimpleListProperty<>(FXCollections.observableArrayList());
		try {
		table.itemsProperty().bind(estancias);
		dniProperty().bindBidirectional(dniEntrada.textProperty());
		dniProperty().addListener((o, ov, nv) ->{
		
				table.getItems().clear();
				ResidenciaColumn.setCellValueFactory(v -> v.getValue().nomResideciaProperty());
				UniversidadColumn.setCellValueFactory(v -> v.getValue().nomUniversidadProperty());
				FechaIniColumn.setCellValueFactory(v -> v.getValue().fechaInProperty());
				FechaFinColumn.setCellValueFactory(v -> v.getValue().fechaFinProperty());
				PrecioColumn.setCellValueFactory(v -> v.getValue().precioProperty());
				estancias.addAll(SQLConnection.ProcedimientAlmacenado1(dniEntrada.getText()));
				
		});
		}catch(Exception e) {
			
		}
		
	}
	//GETS SETS ETC
	public ProcedimientoDNISQLController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProcedimientoDNI.fxml"));
		loader.setController(this);
		loader.load();

	}

	public HBox getView() {
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
