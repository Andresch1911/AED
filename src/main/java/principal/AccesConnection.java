package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AccesConnection {
	static Connection con;

	public static Connection getCon() {
		String msAccDB = "C:/Users/a_ch9/Desktop/Exportacion/ProyectoAED/Database2.accdb";
		String dbURL = "jdbc:ucanaccess://" + msAccDB;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			con = DriverManager.getConnection(dbURL);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

	}

	public static void Cerrar() {
		try {
			if (!con.isClosed()) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
				getCon().close();
				}catch(Exception e) {
					
				}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return residencias;

	}

	public static void InsertarAccess(String nomResidencia,  String codUniversidad, int precioPagado, boolean comedor) {

		try {
			Statement s = getCon().createStatement();
			s.execute("INSERT INTO residencias(nomResidencia, codUniversidad, precioMensual, Comedor) VALUES ('"
					+ nomResidencia + "','" + codUniversidad + "'," + precioPagado + "," + comedor + ")");

		} catch (Exception e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Se ha producido un error");
			a.setHeaderText("ERROR");
			a.setContentText("Error: " + e);
			a.showAndWait();
			e.printStackTrace();
			System.out.println(a);

		}
		Cerrar();
	}

	public static List<String> ConsultaResidenciasAccess() {

		String cadena;
		List<String> lista = new ArrayList<>();
		try {
			Statement s = getCon().createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = s.executeQuery("SELECT codUniversidad FROM universidades");
			// iterate through the java resultset
			while (rs.next()) {
				String nombre = rs.getString(1);
				cadena = nombre + "\n";
				lista.add(cadena);
			}
			s.close();
		} catch (Exception e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Se ha producido un error");
			a.setHeaderText("ERROR");
			a.setContentText("Hola: " + e);
			e.printStackTrace();
			a.showAndWait();
		}
		Cerrar();
		return lista;
	}
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
			e.printStackTrace();
		}

		return lista;

	}

	public static void EliminarAccess(int codResidencia) {

		try {
			Statement s = getCon().createStatement();
			s.execute("DELETE FROM residencias WHERE codResidencia = " + codResidencia);
			s.close();
		} catch (Exception e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Se ha producido un error");
			a.setHeaderText("ERROR");
			a.setContentText("Error: " + e);
			a.showAndWait();
			e.printStackTrace();
		}
		Cerrar();
	}

	public static void UpdateAccess(int codResidencia, String nomResidencia, int precioPagado, String codUniversidad,
			int comedor) {

		try {
			nomResidencia = nomResidencia.replaceAll("\n", "");
			codUniversidad = codUniversidad.replaceAll("\n", "");
			Statement s = getCon().createStatement();
			s.execute("UPDATE residencias   SET " + "codResidencia = " + codResidencia + "," + " nomResidencia ='"
					+ codUniversidad + "'," + " codUniversidad ='" + nomResidencia + "'," + " precioMensual ="
					+ precioPagado + "," + " Comedor =" + comedor + " WHERE codResidencia = " + codResidencia);
			s.close();
		} catch (Exception e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Se ha producido un error");
			a.setHeaderText("ERROR");
			a.setContentText("Error: " + e);
			a.showAndWait();
			e.printStackTrace();
		}
		Cerrar();
	}

}
