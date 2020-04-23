package by.epam.lab.entity;

import java.util.Objects;

public class LotPhoto implements Identifiable {

	private long idPhoto;
	private long idLot;
	private String url;

	public static final String ID_PHOTO = "id_photo";
    public static final String URL = "photo_url";
    public static final String ID_LOT = "lot_id_lot";

	public LotPhoto() {
	}

	public LotPhoto(long idPhoto, long idLot, String url) {
		this.idPhoto = idPhoto;
		this.idLot = idLot;
		this.url = url;
	}

	public long getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(long idPhoto) {
		this.idPhoto = idPhoto;
	}

	public long getIdLot() {
		return idLot;
	}

	public void setIdLot(long idLot) {
		this.idLot = idLot;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LotPhoto lotPhoto = (LotPhoto) o;
		return getIdPhoto() == lotPhoto.getIdPhoto() && getIdLot() == lotPhoto.getIdLot()
				&& Objects.equals(getUrl(), lotPhoto.getUrl());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIdPhoto(), getIdLot(), getUrl());
	}

	@Override
	public String toString() {
		return "LotPhoto{" + "idPhoto=" + idPhoto + ", idLot=" + idLot + ", url='" + url + '\'' + '}';
	}

	@Override
	public long getId() {
		return idLot;
	}

}
