package by.epam.lab.dao.implementation;

import java.sql.Connection;
import java.util.List;

import by.epam.lab.dao.AbstractDao;
import by.epam.lab.dao.LotPhotoDao;
import by.epam.lab.entity.Identifiable;
import by.epam.lab.entity.LotPhoto;
import by.epam.lab.exception.DaoException;
import by.epam.lab.service.builder.LotPhotoBuilder;




public class LotPhotoDaoImplementation extends AbstractDao<LotPhoto> implements LotPhotoDao {
	/**
	 *  Class is an implementation of access to lot photo database and provides methods to work with it.
	 */
	    private static final String TABLE_NAME = "lot_photo";
	    private static final String FIND_PHOTO_BY_ID_QUERY = "SELECT * from lot_photo where lot_id_lot = ?::bigint";
	    private static final String INSERT_QUERY = "INSERT INTO lot_photo (photo_url, lot_id_lot) VALUES(?,?)";

	    public LotPhotoDaoImplementation(Connection connection) {
	        super(connection);
	    }

	    /**
	     *  Method designed for searching lot photos depends on lot identifier.
	     *
	     * @param id - Lot identifier in database
	     * @return an {@link List} implementation with an lot {@link LotPhoto} objects.
	     * @throws DaoException Signals that an database access object exception of some sort has occurred.
	     */
	    @Override
	    public List<LotPhoto> findLotPhotosByLotId(long id) throws DaoException {
	        return executeQuery(FIND_PHOTO_BY_ID_QUERY, new LotPhotoBuilder(), String.valueOf(id));
	    }

	    /**
	     *
	     * @return Name of the table designed for storage {@link LotPhoto}.
	     */
	    @Override
	    protected String getTableName() {
	        return TABLE_NAME;
	    }

	    /**
	     * The method designed for the process of saving an {@link LotPhoto} object in database.
	     *
	     * @param item an {@link Identifiable} {@link LotPhoto} object that should be saved to the database.
	     * @return created lot photo identifier in database.
	     * @throws DaoException Signals that an database access object exception of some sort has occurred.
	     */
	    @Override
	    public long save(Identifiable item) throws DaoException {
	        LotPhoto lotPhoto = (LotPhoto) item;

	        String url = lotPhoto.getUrl();

	        long idLot = lotPhoto.getIdLot();
	        String idLotString = String.valueOf(idLot);

	        return executeUpdate(INSERT_QUERY, url, idLotString);
	    }
}
