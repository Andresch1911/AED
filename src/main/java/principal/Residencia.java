package principal;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Residencia {
	private StringProperty codResidencia = new SimpleStringProperty();
	private StringProperty nomResi = new SimpleStringProperty();
	private StringProperty nomUnive = new SimpleStringProperty();
	private StringProperty precio3 = new SimpleStringProperty();
	private BooleanProperty comedor = new SimpleBooleanProperty();
	public Residencia(String codResidencia, String nomResidencia, String nomUniversidad,
			String precio, Boolean comedor) {
		super();
		this.codResidencia.set(codResidencia);;
		this.nomResi.set(nomResidencia);;
		this.nomUnive.set(nomUniversidad);;
		this.precio3.set(precio);;
		this.comedor.set(comedor);;
	}
	public final StringProperty codResidenciaProperty() {
		return this.codResidencia;
	}
	
	public final String getCodResidencia() {
		return this.codResidenciaProperty().get();
	}
	
	public final void setCodResidencia(final String codResidencia) {
		this.codResidenciaProperty().set(codResidencia);
	}
	
	public final StringProperty nomResiProperty() {
		return this.nomResi;
	}
	
	public final String getNomResi() {
		return this.nomResi.get();
	}
	
	public final void setNomResi(final String nomResi) {
		this.nomResiProperty().set(nomResi);
	}
	
	public final StringProperty nomUniveProperty() {
		return this.nomUnive;
	}
	
	public final String getNomUnive() {
		return this.nomUniveProperty().get();
	}
	
	public final void setNomUniversidad(final String nomUnive) {
		this.nomUniveProperty().set(nomUnive);
	}
	
	public final StringProperty precioProperty() {
		return this.precio3;
	}
	
	public final String getPrecio() {
		return this.precioProperty().get();
	}
	
	public final void setPrecio(final String precio) {
		this.precioProperty().set(precio);
	}
	
	public final BooleanProperty comedorProperty() {
		return this.comedor;
	}
	
	public final boolean isComedor() {
		return this.comedorProperty().get();
	}
	
	public final void setComedor(final boolean comedor) {
		this.comedorProperty().set(comedor);
	}
	
	
	
	
	
}
