package by.epam.lab.service.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import by.epam.lab.entity.Picture;
import by.epam.lab.exception.DaoException;


public class PictureBuilder implements Builder<Picture> {


/**
 * Designed to build an object of type {@link entity.User} with specified characteristics.
 */
	@Override
	public Picture build(ResultSet resultSet) throws DaoException {
		try {
		long idPicture= resultSet.getLong(Picture.ID);	
		String name = resultSet.getString(Picture.NAME_OF_PICTURE);
		
		Timestamp dateOfPaintingStamp = resultSet.getTimestamp(Picture.YEAR_OF_PAINTING);
		long yearOfPainting = dateOfPaintingStamp.getTime();
		
		Date date = new Date(yearOfPainting);
		
		return new Picture(idPicture, name, date);
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

}