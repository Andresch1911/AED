package principal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class FuncionMyController implements Initializable{
	@FXML
	private TextField entradaText;
	@FXML
	private Label salida;
    @FXML
    private VBox view;
	
	private StringProperty meses = new SimpleStringProperty();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
		meses.set(MySQLConnection.FuncionMesesDif(entradaText.getText()));
		salida.textProperty().bind(meses);
		entradaText.textProperty().addListener((o, ov, nv) -> {
			
			meses.set("Numero de meses: "+MySQLConnection.FuncionMesesDif(entradaText.getText()));
			salida.setText(getMeses());
		});
		}catch(Exception e) {
			
		}
			
		
		
	}
	public FuncionMyController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Funcion.fxml"));
		loader.setController(this);
		loader.load();

	}

	public VBox getView() {
		return view;
	}
	public final StringProperty mesesProperty() {
		return this.meses;
	}
	
	public final String getMeses() {
		return this.mesesProperty().get();
	}
	
	public final void setMeses(final String meses) {
		this.mesesProperty().set(meses);
	}
	

}
