package by.epam.lab.service.builder;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import by.epam.lab.entity.*;
import by.epam.lab.exception.*;


/**
 * Designed to build an object of type {@link entity.Lot} with
 * specified characteristics.
 */
public class LotBuilder implements Builder<Lot> {
	/**
	 * Builds an object of type Lot with properties.
	 *
	 * @param resultSet Instance of {@link java.sql.ResultSet} with property set to
	 *                  build an object type Lot.
	 * @return Returns built object type Lot.
	 * @throws DaoException Throws when SQL Exception is caught.
	 */
	@Override
	public Lot build(ResultSet resultSet) throws DaoException {
		try {
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
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}
}
