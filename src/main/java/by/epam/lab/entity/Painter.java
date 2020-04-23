package by.epam.lab.entity;

public class Painter extends Lot {

	protected long idPainter;
	protected String nameOfPainter;
	protected String surnameOfPainter;
	protected String nationality;

	public static final String NAME_OF_PAINTER = "name";
	public static final String SURNAME_OF_PAINTER = "surname";
	public static final String NATIONALITY = "nationality";

	public Painter() {
	}

	public Painter(long idPainter, String name, String surname, String nationality) {
		this.idPainter = idPainter;
		this.nameOfPainter = name;
		this.surnameOfPainter = surname;
		this.nationality = nationality;
	}

	public long getIdPainter() {
		return idPainter;
	} 

	public void setIdPainter(long idPainter) {
		this.idPainter = idPainter;
	}

	public String getNameOfPainter() {
		return nameOfPainter;
	}

	public void setNameOfPainter(String name) {
		this.nameOfPainter = name;
	}

	public String getSurnameOfPainter() {
		return surnameOfPainter;
	}

	public void setSurnameOfPainter(String surnameOfPainter) {
		this.surnameOfPainter = surnameOfPainter;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (idPainter ^ (idPainter >>> 32));
		result = prime * result + ((nameOfPainter == null) ? 0 : nameOfPainter.hashCode());
		result = prime * result + ((nationality == null) ? 0 : nationality.hashCode());
		result = prime * result + ((surnameOfPainter == null) ? 0 : surnameOfPainter.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Painter other = (Painter) obj;
		if (idPainter != other.idPainter)
			return false;
		if (nameOfPainter == null) {
			if (other.nameOfPainter != null)
				return false;
		} else if (!nameOfPainter.equals(other.nameOfPainter))
			return false;
		if (nationality == null) {
			if (other.nationality != null)
				return false;
		} else if (!nationality.equals(other.nationality))
			return false;
		if (surnameOfPainter == null) {
			if (other.surnameOfPainter != null)
				return false;
		} else if (!surnameOfPainter.equals(other.surnameOfPainter))
			return false;
		return true;
	}

	

}
