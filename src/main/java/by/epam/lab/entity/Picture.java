package by.epam.lab.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Picture extends Painter{

	private long idPicture;
	private String name;
	private Date yearOfPainting;

	public static final String NAME_OF_PICTURE = "name";
	public static final String YEAR_OF_PAINTING = "year_of_painting";

	public Picture() {
	}

	public Picture(long idPicture, String name, Date dateOfPainting) {
		this.idPicture = idPicture;
		this.name = name;
		this.yearOfPainting = dateOfPainting;
	}
	public Picture(long idLot, BigDecimal price, Date dateOfStart, Date dateOfEnd, LotStatusEnum status, long ownerId,
			String nameOfPainter, String surnameOfPainter, String nameOfPicture, Date dateOfPaint) {
		this.idLot = idLot;
		this.price = price;
		this.dateOfStart = dateOfStart;
		this.dateOfEnd = dateOfEnd;
		this.status = status;
		this.nameOfPainter = nameOfPainter;
		this.surnameOfPainter=surnameOfPainter;
		this.name = nameOfPicture;
		this.yearOfPainting=dateOfPaint;
		this.ownerId = ownerId;
	}

	public long getId() {
		return idPicture;
	}

	public void setId(long id) {
		this.idPicture = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfPainting() {
		return yearOfPainting;
	}
	
	@SuppressWarnings("deprecation")
	public int getYear() {
		return yearOfPainting.getYear() + 1900;
	}

	public void setDateOfPainting(Date dateOfPainting) {
		this.yearOfPainting = dateOfPainting;
	}

}
