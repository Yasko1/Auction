package by.epam.lab.entity;

public enum RoleEnum {

	USER("user"), ADMIN("admin");

	private String value;

	private RoleEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
