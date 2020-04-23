package by.epam.lab.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import by.epam.lab.entity.Lot;
import by.epam.lab.entity.LotStatusEnum;

public class LotMapper implements RowMapper<Lot> {

	@Override
	public Lot mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		
		long id = resultSet.getLong(Lot.ID);
		BigDecimal price = resultSet.getBigDecimal(Lot.PRICE);

		Timestamp dateOfStartTimeStamp = resultSet.getTimestamp(Lot.DATE_OF_START);
		long dateOfStartTimeMillisecond = dateOfStartTimeStamp.getTime();
		Date dateOfStart = new Date(dateOfStartTimeMillisecond);

		Timestamp dateOfEndTimeStamp = resultSet.getTimestamp(Lot.DATE_OF_END);
		long dateOfEndTimeMillisecond = dateOfEndTimeStamp.getTime();
		Date dateOfEnd = new Date(dateOfEndTimeMillisecond);

		long ownerId = resultSet.getLong(Lot.OWNER_ID);

		String statusString = resultSet.getString(Lot.STATUS);
		LotStatusEnum status = LotStatusEnum.findByValue(statusString);

		return new Lot(id, price, ownerId, dateOfStart, dateOfEnd, status);
	}

	
}
