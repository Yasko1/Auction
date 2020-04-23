package by.epam.lab.entity;

import java.util.List;
import java.util.Objects;

public class LotDto {

    private Lot lot;
    private List<LotPhoto> photos;
    private Picture picture;

    public LotDto() {
    }

    public LotDto(Lot lot, List<LotPhoto> photos) {
        this.lot = lot;
        this.photos = photos;
    }
    
    public LotDto(Picture picture) {
        this.picture=picture;
    }
    
    public LotDto(Lot lot, List<LotPhoto> photos, Picture picture) {
        this.lot = lot;
        this.photos = photos;
        this.picture=picture;
    }

    public Lot getLot() {
        return lot;
    }

    public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public void setLot(Lot lot) {
        this.lot = lot;
    }

    public List<LotPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<LotPhoto> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LotDto lotDTO = (LotDto) o;
        return Objects.equals(getLot(), lotDTO.getLot()) &&
                Objects.equals(getPhotos(), lotDTO.getPhotos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLot(), getPhotos());
    }
}
