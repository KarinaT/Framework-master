package com.epam.preproduction.entities;

import java.util.HashMap;
import java.util.Map;

public class Microwave {
	private Map<String, String> characteristics;

	public Microwave() {
		System.out.println("===new===>Microwave()");
		this.characteristics = new HashMap<String, String>();
	}

	public Map<String, String> getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(Map<String, String> characteristics) {
		this.characteristics = characteristics;
	}

	@Override
	public String toString() {
		return "Microwave [characteristics=" + characteristics + "]";
	}
}
