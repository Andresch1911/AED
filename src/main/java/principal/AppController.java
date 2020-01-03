package principal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

public class AppController implements Initializable {
	// MODELO
	
	private  Stage stage ;

	ModificarController modificarcontroller;
	
	private IntegerProperty NumTab = new SimpleIntegerProperty();
	private IntegerProperty NumProc = new SimpleIntegerProperty();
	
	
	
	// VISTA
	@FXML
	private TabPane view;

	public TabPane getView() {
		return view;
	}

	// MySQL
	@FXML
	private Button VisualizarMy;
	@FXML
	private Button InsertarMy;
	@FXML
	private Button EliminarMy;
	@FXML
	private Button ModificarMy;
	@FXML
	private ComboBox<String> ProcedimientosMyCombo;
	@FXML
	private ProgressBar ProgressMy;
	@FXML
	private TextArea TextAreaMy;
	
	// SQLServer
	@FXML
	private Button VisualizarSQL;
	@FXML
	private Button InsertarSQL;
	@FXML
	private Button EliminarSQL;
	@FXML
	private Button ModificarSQL;
	@FXML
	private ComboBox<String> ProcedimientosSQLCombo;
	@FXML
	private ProgressBar ProgressSQL;
	@FXML
	private TextArea TextAreaSQL;
	
	// Access
	@FXML
	private Button VisualizarAccess;
	@FXML
	private Button InsertarAccess;
	@FXML
	private Button EliminarAccess;
	@FXML
	private Button ModificarAccess;
	@FXML
	private ProgressBar ProgressAccess;
	@FXML
	private TextArea TextAreaAccess;

	// BOTONES MYSQL
	@FXML
	public void onVisualizarMyAction(ActionEvent event) {
		MySQLConnection.Visualizar(TextAreaMy);
	}

	@FXML
	public void onInsertarMyAction(ActionEvent event) {
		Insertar(stage);
		onVisualizarMyAction(event);
		
	}

	@FXML
	public void onEliminarMyAction(ActionEvent event) {
		Eliminar(stage);
		
	}
	
	public void RellenarComboMySql() {
		ObservableList<String> data = FXCollections.observableArrayList();
		data.add("Insertar Residencias");
		data.add("Listar Estancias");
		data.add("Numero de Residencias");
		ProcedimientosMyCombo.setItems(data);
	}

	@FXML
	public void onModificarMyAction(ActionEvent event) {
		Modificar(stage);
	}

	// BOTONES SQLSERVER
	@FXML
	public void onVisualizarSQLAction(ActionEvent event) {

	}

	@FXML
	public void onInsertarSQLAction(ActionEvent event) {

	}

	@FXML
	public void onEliminarSQLAction(ActionEvent event) {

	}

	@FXML
	public void onModificarSQLAction(ActionEvent event) {
		Modificar(stage);
	}

	// BOTONES ACCESS
	@FXML
	public void onVisualizarAccessAction(ActionEvent event) {

	}

	@FXML
	public void onInsertarAccessAction(ActionEvent event) {

	}

	@FXML
	public void onEliminarAccess(ActionEvent event) {

	}

	@FXML
	public void onModificarAccess(ActionEvent event) {
		Modificar(stage);
	}

	// INITIALIZE
	
 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		RellenarComboMySql();
		try {

		NumTabProperty().bind(view.getSelectionModel().selectedIndexProperty());
		NumTabProperty().addListener((o, ov, nv) -> {
			System.out.println("Numero de la tab: "+ getNumTab());
			try {
				ModificarController controller = new ModificarController();				
				controller.setCodigo(getNumTab());				
			}catch(Exception e) {}			
		});
		
		NumProcProperty().bind(ProcedimientosMyCombo.getSelectionModel().selectedIndexProperty());
		NumProcProperty().addListener((o, ov, nv) ->{
			System.out.println(getNumProc());
			switch(getNumProc()) {
			case 0:{
				
			}break;
			case 1:{
				
			}break;
			
			case 2:{
				
			}break;
			}
		});
		
		
		
				
			if (!MySQLConnection.getCon().isClosed()) {
				ProgressMy.setProgress(1);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	
	}

	

	

	//CONTROLLERS
	public AppController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/App.fxml"));
		loader.setController(this);
		loader.load();

	}
	public  void Insertar( Stage stage ) {
		 try {
			 	InsertarController controller = new InsertarController();
				Scene scene = new Scene(controller.getView());
		        stage = new Stage();
		        stage.setTitle("Insertar");
		        stage.setScene(scene);
		        stage.show();
		        
		    } catch (IOException e) {
		        
		    }
	}
	public  void Eliminar( Stage stage ) {
		 try {
			 	EliminarController controller = new EliminarController();			 	
				Scene scene = new Scene(controller.getView());
		        stage = new Stage();
		        stage.setTitle("Eliminar");
		        stage.setScene(scene);
		        stage.show();
		        
		    } catch (IOException e) {
		        
		    }
	}
	public  void Modificar( Stage stage ) {
		 try {
			 System.out.println("hola");
			  modificarcontroller = new ModificarController();			 	
				Scene scene = new Scene(modificarcontroller.getView());
		        stage = new Stage();
		        stage.setTitle("Modificar");
		        stage.setScene(scene);
		        stage.show();
		        
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		 
	}

	
	//GETS SETS ETC
	public final IntegerProperty NumTabProperty() {
		return this.NumTab;
	}
	
	public final int getNumTab() {
		return this.NumTabProperty().get();
	}
	
	public final void setNumTab(final int NumTab) {
		this.NumTabProperty().set(NumTab);
	}
	
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public final IntegerProperty NumProcProperty() {
		return this.NumProc;
	}
	

	public final int getNumProc() {
		return this.NumProcProperty().get();
	}
	

	public final void setNumProc(final int NumProc) {
		this.NumProcProperty().set(NumProc);
	}
	
	public TextArea getTextAreaMy() {
		return TextAreaMy;
	}

	public void setTextAreaMy(TextArea textAreaMy) {
		TextAreaMy = textAreaMy;
	}
	

	//
	
	
	
}
