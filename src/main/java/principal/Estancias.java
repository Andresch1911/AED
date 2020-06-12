package principal;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Estancias {
	private StringProperty nomResidecia = new SimpleStringProperty();
	private StringProperty nomUniversidad = new SimpleStringProperty();
	private StringProperty fechaIn = new SimpleStringProperty();
	private StringProperty fechaFin = new SimpleStringProperty();
	private StringProperty precio = new SimpleStringProperty();
	
	
	public Estancias(String nomResidecia, String nomUniversidad, String fechaIn,
			String fechaFin, String precio) {
		super();
		this.nomResidecia.set(nomResidecia);
		this.nomUniversidad.set(nomUniversidad);;
		this.fechaIn.set(fechaIn);
		this.fechaFin.set(fechaFin);;
		this.precio.set(precio);;
	}


	public final StringProperty nomResideciaProperty() {
		return this.nomResidecia;
	}
	


	public final String getNomResidecia() {
		return this.nomResideciaProperty().get();
	}
	


	public final void setNomResidecia(final String nomResidecia) {
		this.nomResideciaProperty().set(nomResidecia);
	}
	


	public final StringProperty nomUniversidadProperty() {
		return this.nomUniversidad;
	}
	


	public final String getNomUniversidad() {
		return this.nomUniversidadProperty().get();
	}
	


	public final void setNomUniversidad(final String nomUniversidad) {
		this.nomUniversidadProperty().set(nomUniversidad);
	}
	


	public final StringProperty fechaInProperty() {
		return this.fechaIn;
	}
	


	public final String getFechaIn() {
		return this.fechaInProperty().get();
	}
	


	public final void setFechaIn(final String fechaIn) {
		this.fechaInProperty().set(fechaIn);
	}
	


	public final StringProperty fechaFinProperty() {
		return this.fechaFin;
	}
	


	public final String getFechaFin() {
		return this.fechaFinProperty().get();
	}
	


	public final void setFechaFin(final String fechaFin) {
		this.fechaFinProperty().set(fechaFin);
	}
	


	public final StringProperty precioProperty() {
		return this.precio;
	}
	


	public final String getPrecio() {
		return this.precioProperty().get();
	}
	


	public final void setPrecio(final String precio) {
		this.precioProperty().set(precio);
	}
	
	

	}
