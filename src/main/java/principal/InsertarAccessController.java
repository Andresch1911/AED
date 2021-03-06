package principal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class InsertarAccessController implements Initializable{
	@FXML
    private VBox view;
	@FXML
	private TextField NomResiText;
	@FXML
	private  ComboBox<String> CodUniCombo;
	@FXML
	private TextField CantidadText;
	@FXML
	private CheckBox ComedorCheck;
	
	AppController controller = new AppController();

  




	public void setInsertarButton(Button insertarButton) {
		InsertarButton = insertarButton;
	}
	@FXML
    private Button InsertarButton;
    private String nomResidencia;
	private String codUniversidad;
	private int precio;
	private boolean comedor;
	
	
    @FXML
    void onInsertarButtonAction(ActionEvent event ) {
    	nomResidencia = NomResiText.getText();
    	codUniversidad = CodUniCombo.getValue();
    	precio = Integer.parseInt(CantidadText.getText());
    	comedor = ComedorCheck.isSelected();
    	AccesConnection.InsertarAccess(nomResidencia, codUniversidad, precio, comedor);
    }
	
   
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RellenarComboAcces();
		
	}
	public InsertarAccessController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Insertar.fxml"));
		loader.setController(this);
		loader.load();

	}
	public VBox getView() {
		return view;
	}
	
	public  void RellenarComboAcces() {
		try {
			
			ObservableList<String> data = FXCollections.observableArrayList();
			for (int i = 0; i < AccesConnection.Combo().size(); i++) {
				data.add(AccesConnection.Combo().get(i));
			}
			CodUniCombo.setItems(data);
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
}
