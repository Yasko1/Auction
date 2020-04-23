package by.epam.lab.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.epam.lab.entity.LotPhoto;

public class LotPhotoMapper implements RowMapper<LotPhoto> {

	@Override
	public LotPhoto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		long idPhoto = resultSet.getLong(LotPhoto.ID_PHOTO);
        long idLot = resultSet.getLong(LotPhoto.ID_LOT);
        String url = resultSet.getString(LotPhoto.URL);

        return new LotPhoto(idPhoto, idLot, url);
	}
	

}
