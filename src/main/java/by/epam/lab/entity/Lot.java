package by.epam.lab.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Objects;

public class Lot implements Identifiable {

	protected long idLot;
	protected BigDecimal price;
	protected long ownerId;
	protected Date dateOfStart;
	protected Date dateOfEnd;
	protected LotStatusEnum status;
	protected BigDecimal currentBid;
	
	public static final String ID= "id_lot";
	public static final String PRICE ="price";
	public static final String OWNER_ID = "owner_id";
	public static final String DATE_OF_START = "date_of_start";
	public static final String DATE_OF_END = "date_of_end";
	public static final String STATUS = "status";
	public static final String IS_DAMAGED = "is_damaged";

	private static final BigDecimal COEFFICIENT_BID_TO_PRICE = new BigDecimal(0.1);
	private static final int SCALE_TO_ROUND = 0;

	public Lot() {
	}

	public Lot(long idLot, BigDecimal price, long ownerId, Date dateOfStart, Date dateOfEnd, LotStatusEnum status) {
		this.idLot = idLot;
		this.price = price;
		this.ownerId = ownerId;
		this.dateOfStart = dateOfStart;
		this.dateOfEnd = dateOfEnd;
		this.status = status;
		this.setCurrentBid(calculateBid(price));
	}

	public long getIdLot() {
		return idLot;
	}

	public void setIdLot(long idLot) {
		this.idLot = idLot;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
		this.setCurrentBid(calculateBid(price));
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public Date getDateOfStart() {
		return dateOfStart;
	}

	public void setDateOfStart(Date dateOfStart) {
		this.dateOfStart = dateOfStart;
	}

	public Date getDateOfEnd() {
		return dateOfEnd;
	}

	public void setDateOfEnd(Date dateOfEnd) {
		this.dateOfEnd = dateOfEnd;
	}

	public LotStatusEnum getStatus() {
		return status;
	}

	public void setStatus(LotStatusEnum status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIdLot(), getOwnerId(), getPrice(), getDateOfStart(), getDateOfEnd(), getStatus());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Lot lot = (Lot) o;
		return getIdLot() == lot.getIdLot() && getOwnerId() == lot.getOwnerId()
				&& Objects.equals(getPrice(), lot.getPrice()) && Objects.equals(getCurrentBid(), lot.getCurrentBid())
				&& Objects.equals(getDateOfStart(), lot.getDateOfStart())
				&& Objects.equals(getDateOfEnd(), lot.getDateOfEnd()) && getStatus() == lot.getStatus();
	}

	private BigDecimal calculateBid(BigDecimal price) {
		BigDecimal currentBid = price.multiply(COEFFICIENT_BID_TO_PRICE);
		return currentBid.setScale(SCALE_TO_ROUND, RoundingMode.HALF_UP);
	}

	public BigDecimal getCurrentBid() {
		return currentBid;
	}

	public void setCurrentBid(BigDecimal currentBid) {
		this.currentBid = currentBid;
	}

	@Override
	public long getId() {
		return idLot;
	}
}
