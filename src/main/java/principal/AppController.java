package principal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

public class AppController implements Initializable {
	// MODELO

	private Stage stage;

	public IntegerProperty NumTab = new SimpleIntegerProperty();
	private IntegerProperty NumProcMy = new SimpleIntegerProperty();
	private IntegerProperty NumProSQL = new SimpleIntegerProperty();

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
	@FXML
	private TableView<Residencia> MyTable;

	@FXML
	private TableColumn<Residencia, String> codResiMy;

	@FXML
	private TableColumn<Residencia, String> nomResiMy;

	@FXML
	private TableColumn<Residencia, String> nomUniMy;

	@FXML
	private TableColumn<Residencia, String> precioMy;

	@FXML
	private TableColumn<Residencia, Boolean> comedorMy;

	private ListProperty<Residencia> residenciasMySQL = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Residencia> residenciasSQL = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Residencia> residenciasAccess = new SimpleListProperty<>(FXCollections.observableArrayList());

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
	@FXML
	private TableView<Residencia> SQLTable;
	@FXML
	private TableColumn<Residencia, String> codResiSQL;
	@FXML
	private TableColumn<Residencia, String> nomResiSQL;
	@FXML
	private TableColumn<Residencia, String> nomUniSQL;
	@FXML
	private TableColumn<Residencia, String> precioSQL;
	@FXML
	private TableColumn<Residencia, Boolean> comedorSQL;

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
    @FXML
    private TableView<Residencia> AccessTable;

    @FXML
    private TableColumn<Residencia, String> codResiAccess;

    @FXML
    private TableColumn<Residencia, String> nomResiAccess;

    @FXML
    private TableColumn<Residencia, String> nomUniAccess;

    @FXML
    private TableColumn<Residencia, String> precioAccess;

    @FXML
    private TableColumn<Residencia, Boolean> comedorAcces;

	// BOTONES MYSQL
	@FXML
	public void onVisualizarMyAction(ActionEvent event) {
		residenciasMySQL.addAll(MySQLConnection.Visualizar());
	}

	@FXML
	public void onInsertarMyAction(ActionEvent event) {
		InsertarMy(stage);
		onVisualizarMyAction(event);

	}

	@FXML
	public void onEliminarMyAction(ActionEvent event) {
		EliminarMy(stage);

	}

	public void RellenarComboMySql() {
		ObservableList<String> data = FXCollections.observableArrayList();
		data.add("Insertar Residencias");
		data.add("Listar Estancias");
		data.add("Numero de Residencias");
		data.add("Numero de Meses");
		ProcedimientosMyCombo.setItems(data);
	}

	public void RellenarComboSql() {
		ObservableList<String> data = FXCollections.observableArrayList();
		data.add("Insertar Residencias");
		data.add("Listar Estancias");
		data.add("Numero de Residencias");
		data.add("Numero de Meses");
		ProcedimientosSQLCombo.setItems(data);
	}

	@FXML
	public void onModificarMyAction(ActionEvent event) {
		ModificarMy(stage);
	}

	// BOTONES SQLSERVER
	@FXML
	public void onVisualizarSQLAction(ActionEvent event) {
		SQLTable.getItems().clear();
		residenciasSQL.addAll(SQLConnection.Visualizar());
	}

	@FXML
	public void onInsertarSQLAction(ActionEvent event) {
		InsertarSQL(stage);
	}

	@FXML
	public void onEliminarSQLAction(ActionEvent event) {
		EliminarSQL(stage);
	}

	@FXML
	public void onModificarSQLAction(ActionEvent event) {
		ModificarSQL(stage);
	}

	// BOTONES ACCESS
	@FXML
	public void onVisualizarAccessAction(ActionEvent event) {
		residenciasAccess.addAll(AccesConnection.Visualizar());
	}

	@FXML
	public void onInsertarAccessAction(ActionEvent event) {
		InsertarAcces(stage);
	}

	@FXML
	public void onEliminarAccess(ActionEvent event) {

	}

	@FXML
	public void onModificarAccess(ActionEvent event) {
		ModificarMy(stage);
	}

	// INITIALIZE

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RellenarComboMySql();
		RellenarComboSql();

		residenciasMySQL.addAll(MySQLConnection.Visualizar());

		MyTable.itemsProperty().bind(residenciasMySQL);

		codResiMy.setCellValueFactory(v -> v.getValue().codResidenciaProperty());
		nomResiMy.setCellValueFactory(v -> v.getValue().nomResiProperty());
		nomUniMy.setCellValueFactory(v -> v.getValue().nomUniveProperty());
		precioMy.setCellValueFactory(v -> v.getValue().precioProperty());
		comedorMy.setCellValueFactory(v -> v.getValue().comedorProperty());
		comedorMy.setCellFactory(CheckBoxTableCell.forTableColumn(comedorMy));
		
	
		residenciasSQL.addAll(SQLConnection.Visualizar());
		
		SQLTable.itemsProperty().bind(residenciasSQL);
		codResiSQL.setCellValueFactory(v -> v.getValue().codResidenciaProperty());
		nomResiSQL.setCellValueFactory(v -> v.getValue().nomResiProperty());
		nomUniSQL.setCellValueFactory(v -> v.getValue().nomUniveProperty());
		precioSQL.setCellValueFactory(v -> v.getValue().precioProperty());
		comedorSQL.setCellValueFactory(v -> v.getValue().comedorProperty());
		comedorSQL.setCellFactory(CheckBoxTableCell.forTableColumn(comedorSQL));
		
		residenciasAccess.addAll(AccesConnection.Visualizar());
		
		AccessTable.itemsProperty().bind(residenciasAccess);
		codResiAccess.setCellValueFactory(v -> v.getValue().codResidenciaProperty());
		nomResiAccess.setCellValueFactory(v -> v.getValue().nomResiProperty());
		nomUniAccess.setCellValueFactory(v -> v.getValue().nomUniveProperty());
		precioAccess.setCellValueFactory(v -> v.getValue().precioProperty());
		comedorAcces.setCellValueFactory(v -> v.getValue().comedorProperty());
		comedorAcces.setCellFactory(CheckBoxTableCell.forTableColumn(comedorAcces));
		
		

			NumTabProperty().bind(view.getSelectionModel().selectedIndexProperty());
			NumTabProperty().addListener((o, ov, nv) -> {
				//System.out.println("Numero de la tab: " + getNumTab());
				
			});
		

		NumProcMyProperty().bind(ProcedimientosMyCombo.getSelectionModel().selectedIndexProperty());
		NumProcMyProperty().addListener((o, ov, nv) -> {
			System.out.println(getNumProcMy());
			switch (getNumProcMy()) {
			case 0: {
				MyProcedimientoAlmacenado0(stage);
			}
				break;
			case 1: {
				MyProcedimientoAlmacenado1(stage);
			}
				break;

			case 2: {
				MyProcedimientoAlmacenado2(stage);
			}
				break;
			case 3:{
				MyMeses(stage);
				}
			}
		});

		NumProSQLProperty().bind(ProcedimientosSQLCombo.getSelectionModel().selectedIndexProperty());
		NumProSQL.addListener((o, ov, nv) -> {
			switch (getNumProSQL()) {
			case 0: {
				SQLProcedimientoAlmacenado0(stage);
			}
				break;
			case 1: {
				SQLProcedimientoAlmacenado1(stage);
			}
				break;
			case 2: {
				SQLProcedimientoAlmacenado2(stage);
			}
				break;
			case 3:{
				SQLMeses(stage);
			}
			}
		});

		try {
			if (!MySQLConnection.getCon().isClosed()) {
				ProgressMy.setProgress(1);

			}
			if(!SQLConnection.getCon().isClosed()) {
				ProgressSQL.setProgress(1);
			}
		ProgressAccess.setProgress(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// CONTROLLERS
	public AppController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/App.fxml"));
		loader.setController(this);
		loader.load();

	}

	// MYSQL STAGES
	public void InsertarMy(Stage stage) {
		try {
			InsertarMyController controller = new InsertarMyController();
			Scene scene = new Scene(controller.getView());
			stage = new Stage();
			stage.setTitle("Insertar");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {

		}
	}

	public void EliminarMy(Stage stage) {
		try {
			EliminarMyController controller = new EliminarMyController();
			Scene scene = new Scene(controller.getView());
			stage = new Stage();
			stage.setTitle("Eliminar");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();

		} catch (IOException e) {

		}
	}

	public void ModificarMy(Stage stage) {
		try {
			ModificarMyController modificarcontroller = new ModificarMyController();
			Scene scene = new Scene(modificarcontroller.getView());
			stage = new Stage();
			stage.setTitle("Modificar");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			System.out.println("Este es mi tab: " + getNumTab());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void MyProcedimientoAlmacenado1(Stage stage) {
		try {
			ProcedimientoDNIController controller = new ProcedimientoDNIController();
			Scene scene = new Scene(controller.getView());
			stage = new Stage();
			stage.setTitle("Procedimiento Almacenado");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void MyProcedimientoAlmacenado0(Stage stage) {
		try {
			ProcedimientoInsertarController controller = new ProcedimientoInsertarController();
			Scene scene = new Scene(controller.getView());
			stage = new Stage();
			stage.setTitle("Procedimiento Almacenado");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void MyProcedimientoAlmacenado2(Stage stage) {
		try {
			ProcedimientoPrecioController controller = new ProcedimientoPrecioController();
			Scene scene = new Scene(controller.getView());
			stage = new Stage();
			stage.setTitle("Procedimiento Almacenado");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void MyMeses(Stage stage) {
		try {
			FuncionMyController controller = new FuncionMyController();
			Scene scene = new Scene(controller.getView());
			stage = new Stage();
			stage.setTitle("Procedimiento Almacenado");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// SQLSERVER STAGES
	public void ModificarSQL(Stage stage) {
		try {
			ModificarSQLController modificarcontroller = new ModificarSQLController();
			Scene scene = new Scene(modificarcontroller.getView());
			stage = new Stage();
			stage.setTitle("Modificar");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void InsertarSQL(Stage stage) {
		try {
			InsertarSQLController controller = new InsertarSQLController();
			Scene scene = new Scene(controller.getView());
			stage = new Stage();
			stage.setTitle("Insertar");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {

		}
	}
	public void EliminarSQL(Stage stage) {
		try {
			EliminarSQLController controller = new EliminarSQLController();
			Scene scene = new Scene(controller.getView());
			stage = new Stage();
			stage.setTitle("Eliminar");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();

		} catch (IOException e) {

		}
	}
	public void SQLProcedimientoAlmacenado1(Stage stage) {
		try {
			ProcedimientoDNISQLController controller = new ProcedimientoDNISQLController();
			Scene scene = new Scene(controller.getView());
			stage = new Stage();
			stage.setTitle("Procedimiento Almacenado");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void SQLProcedimientoAlmacenado2(Stage stage) {
		try {
			ProcedimientoPrecioSQLController controller = new ProcedimientoPrecioSQLController();
			Scene scene = new Scene(controller.getView());
			stage = new Stage();
			stage.setTitle("Procedimiento Almacenado");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void SQLProcedimientoAlmacenado0(Stage stage) {
		try {
			ProcedimientoInsertarSQLController controller = new ProcedimientoInsertarSQLController();
			Scene scene = new Scene(controller.getView());
			stage = new Stage();
			stage.setTitle("Procedimiento Almacenado");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void InsertarAcces(Stage stage) {
		try {
			InsertarAccessController controller = new InsertarAccessController();
			Scene scene = new Scene(controller.getView());
			stage = new Stage();
			stage.setTitle("Insertar");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {

		}
	}
	// GETS SETS ETC
	public final IntegerProperty NumTabProperty() {
		return this.NumTab;
	}
	public void SQLMeses(Stage stage) {
		try {
			FuncionSQLController controller = new FuncionSQLController();
			Scene scene = new Scene(controller.getView());
			stage = new Stage();
			stage.setTitle("Procedimiento Almacenado");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public TextArea getTextAreaMy() {
		return TextAreaMy;
	}

	public void setTextAreaMy(TextArea textAreaMy) {
		TextAreaMy = textAreaMy;
	}

	public final IntegerProperty NumProcMyProperty() {
		return this.NumProcMy;
	}

	public final int getNumProcMy() {
		return this.NumProcMyProperty().get();
	}

	public final void setNumProcMy(final int NumProcMy) {
		this.NumProcMyProperty().set(NumProcMy);
	}

	public final IntegerProperty NumProSQLProperty() {
		return this.NumProSQL;
	}

	public final int getNumProSQL() {
		return this.NumProSQLProperty().get();
	}

	public final void setNumProSQL(final int NumProSQL) {
		this.NumProSQLProperty().set(NumProSQL);
	}

	public final ListProperty<Residencia> residenciasProperty() {
		return this.residenciasMySQL;
	}

	public final ObservableList<Residencia> getResidencias() {
		return this.residenciasProperty().get();
	}

	public final void setResidencias(final ObservableList<Residencia> residencias) {
		this.residenciasProperty().set(residencias);
	}

	//

}
