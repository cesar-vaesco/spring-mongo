package com.vaesco.example.apirest.exception;

import java.util.Date;

public class ErrorDetails {

	private Date registro_tiempo;
	private String mensage;
	private String detalles;

	public ErrorDetails(Date registro_tiempo, String mensage, String detalles) {
		super();
		this.registro_tiempo = registro_tiempo;
		this.mensage = mensage;
		this.detalles = detalles;
	}

	public Date getRegistro_tiempo() {
		return registro_tiempo;
	}

	public void setRegistro_tiempo(Date registro_tiempo) {
		this.registro_tiempo = registro_tiempo;
	}

	public String getMensage() {
		return mensage;
	}

	public void setMensage(String mensage) {
		this.mensage = mensage;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

}
