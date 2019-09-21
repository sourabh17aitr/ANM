package com.acheron.domain;

public enum Status {
	SUCCESS("SUCCESS");
	private String value;

	Status(final String value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
