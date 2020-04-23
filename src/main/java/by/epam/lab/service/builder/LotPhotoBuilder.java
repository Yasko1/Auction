package by.epam.lab.service.builder;


import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.lab.entity.LotPhoto;
import by.epam.lab.exception.DaoException;

/**
 * Designed to build an object of type {@link entity.LotPhoto} with specified characteristics.
 */
public class LotPhotoBuilder implements Builder<LotPhoto> {

    /**
     * Builds an object of type LotPhoto with properties.
     *
     * @param resultSet Instance of {@link java.sql.ResultSet} with property set to build an object type LotPhoto.
     * @return Returns built object type LotPhoto.
     * @throws DaoException Throws when SQL Exception is caught.
     */
    @Override
    public LotPhoto build(ResultSet resultSet) throws DaoException {
        try {
            long idPhoto = resultSet.getLong(LotPhoto.ID_PHOTO);
            long idLot = resultSet.getLong(LotPhoto.ID_LOT);
            String url = resultSet.getString(LotPhoto.URL);

            return new LotPhoto(idPhoto, idLot, url);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}