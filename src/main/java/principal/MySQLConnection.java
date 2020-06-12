package principal;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class MySQLConnection {

	private static Connection con;

	// ABRIR CONEXION
	public static Connection getCon() {
		try {
			String bae = "jdbc:mysql://localhost/residenciasescolares";
			String user = "root";
			String pass = "";
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(bae, user, pass);
		} catch (Exception e) {
			System.out.println(e);
			Alert(e);
		}
		return con;

	}

	// VISUALIZAR
	public static List<Residencia> Visualizar() {
		ListProperty<Residencia> residencias = new SimpleListProperty<>(FXCollections.observableArrayList());
		try {
			Statement s = getCon().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM residencias");
			while (rs.next()) {
				String codResidencia = rs.getString(1);
				String nomResidencia = rs.getString(2);
				String codUniversidad = rs.getString(3);
				String precio = rs.getString(4);
				boolean comedor = rs.getBoolean(5);

				residencias.add(new Residencia(codResidencia, nomResidencia, codUniversidad, precio, comedor));

			}
			try {
			s.close();
			}catch(Exception e) {
				
			}
		} catch (Exception e) {
			Alert(e);

		}
		return residencias;

	}

	public static String Visualizar(Integer codResi) {
		String cadena = "";
		int codResidencia, precio;
		String nomResidencia, codUniversidad;
		boolean comedor;
		try {
			Statement s = getCon().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM residencias WHERE codResidencia =" + codResi);
			while (rs.next()) {
				codResidencia = rs.getInt(1);
				nomResidencia = rs.getString(2);
				codUniversidad = rs.getString(3);
				precio = rs.getInt(4);
				comedor = rs.getBoolean(5);

				cadena += codResidencia + "/" + nomResidencia + "/" + codUniversidad + "/" + precio + "/" + comedor
						+ "/";

			}
			try {
			s.close();
			}catch(Exception e) {
				
			}
		} catch (Exception e) {
			Alert(e);

		}

		return cadena;

	}

	// INSERTAR
	public static void Insertar(String nomResidencia, String codUniversidad, int precio, boolean comedor) {
		try {
			Statement s = getCon().createStatement();
			s.execute("INSERT INTO residencias(nomResidencia, codUniversidad, precioMensual, Comedor) " + "VALUES ('"
					+ nomResidencia + "','" + codUniversidad + "'," + precio + "," + comedor + ")");
			try {
			s.close();
			}catch(Exception e) {
				
			}
		} catch (Exception e) {
			Alert(e);

		}

	}

	// COMBOBOX
	public static List<String> Combo() {
		List<String> lista = new ArrayList<>();
		try {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT codUniversidad FROM universidades");
			while (rs.next()) {
				String nombre = rs.getString(1);
				lista.add(nombre);
			}
			try {
			s.close();
			}catch(Exception e) {
				
			}
		} catch (Exception e) {
			Alert(e);
		}

		return lista;

	}

	public static boolean Combo(String entrada, int codResi) {
		boolean a = false;
		try {
			String[] cadena = Visualizar(codResi).split("/");
			if (entrada.equals(cadena[2].toString())) {
				a = true;
			}
		} catch (Exception e) {
			Alert(e);
		}

		return a;
	}

	// ELIMINAR
	public static void Eliminar(int codResidencia) {
		try {
			Statement s = getCon().createStatement();
			s.execute("DELETE FROM `residencias` WHERE `codResidencia` = " + codResidencia);
			try {
			s.close();
			}catch(Exception e) {
				
			}
		} catch (Exception e) {
			Alert(e);

		}

	}

	// UPDATE
	public static void UpdateMySQL(int codigoResidencia, String nombreResidencia, String nombreUniversidad,
			int preciopagado, boolean comedor) {

		try {
			Statement s = con.createStatement();
			s.execute("UPDATE `residencias`   SET " + "codResidencia = " + codigoResidencia + "," + " nomResidencia ='"
					+ nombreResidencia + "'," + " codUniversidad ='" + nombreUniversidad + "'," + " precioMensual ="
					+ preciopagado + "," + " Comedor =" + comedor + " WHERE codResidencia = " + codigoResidencia);
			try {
			s.close();
			}catch(Exception e) {
				
			}
		} catch (Exception e) {

			Alert(e);
		}

	}

	// PROCEDIMIENTO DNI
	public static List<Estancias> ProcedimientAlmacenado1(String dni) {
		ListProperty<Estancias> estancias = new SimpleListProperty<>(FXCollections.observableArrayList());

		try {
			String sql = " CALL listaestancias (?);";
			CallableStatement stmt = getCon().prepareCall(sql);
			stmt.setString(1, dni);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				String nombre = rs.getString(1);
				String uni = rs.getString(2);
				String fechaInicio = rs.getString(3);
				String fechaFi = rs.getString(4);
				String preciopag = rs.getString(5);

				Estancias a = new Estancias(nombre, uni, fechaInicio, fechaFi, preciopag);
				estancias.add(a);

			}
			try {
			stmt.close();
			}catch(Exception e) {
				
			}
		} catch (Exception e) {
			//Alert(e);

		}
		return estancias;
	}

	// PROCEDIMIENTO INSERTAR
	public static void ProcedimientoAlmacenado2(String nomResidencia1, String codUniversidad, int precioPagado,
			boolean comedor, TextArea b) {
		try {
			System.out.println("Nombre Residencia: " + nomResidencia1 + " Codigo Universidad: " + codUniversidad
					+ " Precio: " + precioPagado + " Comedor: " + comedor);
			String sql = " CALL Insertarresidencia(?, ?, ?, ?, ?, ?);";

			CallableStatement stmt = getCon().prepareCall(sql);
			stmt.setString(1, nomResidencia1);
			stmt.setString(2, codUniversidad);
			stmt.setInt(3, precioPagado);
			stmt.setBoolean(4, comedor);
			stmt.registerOutParameter(5, Types.INTEGER);
			stmt.registerOutParameter(6, Types.INTEGER);
			stmt.execute();

			int nombre = stmt.getInt(5);
			int uni = stmt.getInt(6);

			b.setText("UniversidadExiste: " + nombre + " Insercion Correta: " + uni);
			try {
				stmt.close();
				}catch(Exception e) {
					
				}
		} catch (Exception e) {
			Alert(e);
		}
	}

	// PROCEDIMIENTO PRECIO
	public static void ProcedimientoAlmacenado3(String nombreUniversidad, int precio, TextArea b) {

		try {
			String sql = " CALL NumeroResidenciasPrecio (?,?,?,?);";
			CallableStatement stmt = getCon().prepareCall(sql);

			stmt.setString(1, nombreUniversidad);
			stmt.setInt(2, precio);
			stmt.registerOutParameter(3, Types.INTEGER);
			stmt.registerOutParameter(4, Types.INTEGER);
			stmt.execute();
			int nombre = stmt.getInt(3);
			int uni = stmt.getInt(4);
			b.setText("Residencias Numero: " + nombre + "\n" + "ResidenciasInferior: " + uni);
			try {
				stmt.close();
				}catch(Exception e) {
					
				}
		} catch (Exception e) {
			Alert(e);
		}
	}

	// FUNCION
	public static String FuncionMesesDif(String dni) {
		String result = "";
		try {
			String llamada = "";
			llamada = "select Numerodemeses(?)";

			CallableStatement stmt = getCon().prepareCall(llamada);
			stmt.setString(1, dni);
			ResultSet resultado = stmt.executeQuery();

			while (resultado.next()) {
				result += resultado.getString(1) + " ";
			}
			try {
				stmt.close();
				}catch(Exception e) {
					
				}

		} catch (SQLException e) {
			e.printStackTrace();
			;
		}

		return result;
	}

	// ALERT
	public static void Alert(Exception e) {
		Alert a = new Alert(AlertType.ERROR);
		a.setTitle("Se ha producido un error");
		a.setHeaderText("Error Conexion MySQL  ");
		a.setContentText("Error: " + e);
		a.showAndWait();
		e.printStackTrace();
	}

}