package com.credit_suisse.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

//JAXB annotation
@XmlRootElement
@Entity
public class InstrumentPriceModifier {

	@Id @GeneratedValue
	private Long id;
	@Column
	private String name;
	@Column
	private Double modifier;

	public InstrumentPriceModifier(Long id, String name, Double modifier) {
		super();
		this.id = id;
		this.name = name;
		this.modifier = modifier;
	}

	public InstrumentPriceModifier() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getModifier() {
		return modifier;
	}

	public void setModifier(Double modifier) {
		this.modifier = modifier;
	}

	@Override
	public String toString() {
		return "Instrument [id=" + id + ", name=" + name + ", modifier=" + modifier + "]";
	}

}
