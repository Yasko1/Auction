package by.epam.lab.entity;

public enum LotStatusEnum {

	PROCESSING("processing"), CONFIRMED("confirmed"), REFUSED("refused"), NOTPURCHASED("not-purchased"),
	PAYMENTWAITING("payment-waiting"), PAID("paid");

	private String value;

	private LotStatusEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;

	}

	public static LotStatusEnum findByValue(String value) {
		LotStatusEnum[] values = LotStatusEnum.values();
		LotStatusEnum result = null;
		for (LotStatusEnum lotStatusEnum : values) {
			String enumValue = lotStatusEnum.getValue();
			if (enumValue.equals(value)) {
				result = lotStatusEnum;
			}
		}
		return result;
	}
}
