package principal;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;

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
	public static String Visualizar(TextArea a) {
		String cadena = "";
		int codResidencia, precio;
		String nomResidencia, codUniversidad;
		boolean comedor;
		try {
			Statement s = getCon().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM residencias");
			while (rs.next()) {
				codResidencia = rs.getInt(1);
				nomResidencia = rs.getString(2);
				codUniversidad = rs.getString(3);
				precio = rs.getInt(4);
				comedor = rs.getBoolean(5);

				cadena += "Codigo de Residencia: " + codResidencia + "\n" 
						+ "Nombre de Residencia: " + nomResidencia + "\n" 
						+ "Codigo de Universidad: " + codUniversidad + "\n" 
						+ "Precio: " + precio + "\n"
						+ "Comedor: " + comedor + "\n" 
						+ "-----------------------------------------\n";
			

			}
			a.setText(cadena);
			s.close();
		} catch (Exception e) {
			Alert(e);
			
		}
		
		return cadena;
		

	}
	
	public static String Visualizar(Integer codResi) {
		String cadena = "";
		int codResidencia, precio;
		String nomResidencia, codUniversidad;
		boolean comedor;
		try {
			Statement s = getCon().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM residencias WHERE codResidencia ="+codResi);
			while (rs.next()) {
				codResidencia = rs.getInt(1);
				nomResidencia = rs.getString(2);
				codUniversidad = rs.getString(3);
				precio = rs.getInt(4);
				comedor = rs.getBoolean(5);

				cadena += codResidencia + "/" 
						+ nomResidencia + "/" 
						+ codUniversidad + "/" 
						+ precio + "/"
						+ comedor + "/" 						;
			

			}
			
			s.close();
		} catch (Exception e) {
			Alert(e);
			
		}

		return cadena;
		

	}
	// INSERTAR
	public static void Insertar(String nomResidencia, String codUniversidad, int precio, boolean comedor) {
		try {
			Statement s = getCon().createStatement();
			s.execute("INSERT INTO residencias(nomResidencia, codUniversidad, precioMensual, Comedor) "
					+ "VALUES ('" + nomResidencia + "','" + codUniversidad + "'," + precio + "," + comedor + ")");
			
		}catch(Exception e) {
			Alert(e);
			
		}
		try {
			getCon().close();
		}catch(Exception e){
			Alert(e);
		}
	}
	
	//COMBOBOX
	public static List<String> Combo() {
		List<String> lista = new ArrayList<>();
		try {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT codUniversidad FROM universidades");
			while (rs.next()) {
				String nombre = rs.getString(1);
				lista.add(nombre);
			}
			
			s.close();
		} catch (Exception e) {
			Alert(e);
		}
		
		return lista;
		
	}
	
	public static boolean Combo(String entrada, int codResi) {
		boolean a = false;
		try {
		String [] cadena = Visualizar(codResi).split("/");
			if(entrada.equals(cadena[2].toString())) {				
				a = true;
			}
		}catch(Exception e) {
			Alert(e);	
		}
		
		return a;
	}

	//ELIMINAR
	public static void Eliminar(int codResidencia) {
		try {
			Statement s = getCon().createStatement();
			s.execute("DELETE FROM `residencias` WHERE `codResidencia` = " + codResidencia);
			s.close();
		} catch (Exception e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Se ha producido un error");
			a.setHeaderText("ERROR");
			a.setContentText("Error: " + e);
			a.showAndWait();
			e.printStackTrace();
			
		}
		
	}
	
	//UPDATE
	public static void UpdateMySQL(int codigoResidencia, String nombreResidencia, String nombreUniversidad, int preciopagado, boolean comedor) {
		
		try {			
			Statement s = con.createStatement();
			s.execute("UPDATE `residencias`   SET "
					+ "codResidencia = " + codigoResidencia +","
					+ " nomResidencia ='"+  nombreResidencia + "'," 
					+ " codUniversidad ='" + nombreUniversidad + "'," 
					+ " precioMensual ="+ preciopagado + "," 
					+ " Comedor =" + comedor +
					" WHERE codResidencia = " + codigoResidencia);
			
			s.close();
		} catch (Exception e) {
			
			Alert(e);
		}
		
	}

	//PROCEDIMIENTO DNI
	public static String ProcedimientAlmacenado1(String dni, TextArea b) {
		
		String result = "";
		try {
			//
			String sql = " CALL `listaestancias`(?);";
			CallableStatement stmt = getCon().prepareCall(sql);
			stmt.setString(1, dni);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				String nombre = rs.getString(1);
				String uni = rs.getString(2);
				Date fechaIn = rs.getDate(3);
				Date fechaFi = rs.getDate(4);
				int precio = rs.getInt(5);
				result += nombre + "/" + uni + "/" + fechaFi + "/" + fechaIn + "/" + precio + "\n";
				
			}
			b.setText(result);

		} catch (Exception e) {
			Alert(e);
		}
		return result;
	}
	
	//ALERT
	public static void Alert(Exception e) {
		Alert a = new Alert(AlertType.ERROR);
		a.setTitle("Se ha producido un error");
		a.setHeaderText("Error Conexion MySQL  ");
		a.setContentText("Error: " + e);
		a.showAndWait();
		e.printStackTrace();
	}
}
