package by.epam.lab.dao.implementation;

import java.sql.Connection;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.epam.lab.dao.AbstractDao;
import by.epam.lab.dao.DynamicQueryBuilder;
import by.epam.lab.dao.PictureDao;
import by.epam.lab.entity.Identifiable;
import by.epam.lab.entity.Picture;
import by.epam.lab.exception.DaoException;
import by.epam.lab.service.builder.PictureBuilder;
import by.epam.lab.service.util.DateTimeParser;


/** 
 *  Class is an implementation of access to picture database and provides methods to work with it.
 */
public class PictureDaoImplementation extends AbstractDao<Picture> implements PictureDao {
	
	private static final String INSERT_QUERY= "INSERT INTO \"picture\" (name, year_of_painting) VALUES(?,?)";
	private static final String TABLE_NAME = "picture";
	private static final String PICTURE_BY_LOT_ID_QUERY = "SELECT * FROM \"picture\" WHERE id_lot = ?::bigint";
	private static final String ALL = "All";
	
	public PictureDaoImplementation(Connection connection) {
		super(connection);
	}
	
	@Override
	public List<Picture> findPicturesByLotId(long id) throws DaoException {
		String idParameter = String.valueOf(id);
		return executeQuery(PICTURE_BY_LOT_ID_QUERY, new PictureBuilder(), idParameter);
	}

	/**
     * The method designed for the process of saving a {@link Picture} object in database.
     *
     * @param item an {@link Identifiable} {@link entity.Picture} object that should be saved to the database.
     * @return created picture identifier in database.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
	@Override
	public long save(Identifiable item) throws DaoException {
		Picture picture = (Picture) item;
		
		String nameOfPicture=picture.getName();
		
		Date yearOfpainting = picture.getDateOfPainting();
	     String yearOfPaintingString = DateTimeParser.parse(yearOfpainting);
		
		return executeUpdate(INSERT_QUERY, nameOfPicture, yearOfPaintingString);
	}

	/**
	 * Method designed for searching user lots depends on user identifier.
	 *
	 * @param id - Lot identifier in database
	 * @return an {@link List} implementation with an user {@link entity.Picture} objects.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	@Override
	public List<Picture> findAllByLotId(long id) throws DaoException {    	
        String idParameter = String.valueOf(id);        
        return executeQuery(PICTURE_BY_LOT_ID_QUERY, new PictureBuilder(), idParameter);
    }
	
    /**
    *
    * @return Name of the table designed for storage {@link entity.Picture}.
    */
	@Override
	protected String getTableName() {		
		return TABLE_NAME;
	}
	
	/**
	 * The method searches for pictures with given parameters.
	 *
	 * @param parameters a {@link Map} object that maps keys(name of parameter) to
	 *                   values of parameters.
	 * @return an {@link List} implementation with {@link Pictures} objects.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	public List<Picture> findByParameters(Map<String, String> parameters) throws DaoException {
		Map<String, String> processedParameters = new HashMap<>();

		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			String value = entry.getValue();
			if (!ALL.equals(value)) {
				String key = entry.getKey();
				processedParameters.put(key, value);
			}
		}

		String query = DynamicQueryBuilder.build(processedParameters);

		System.out.println("query: " + query);
		Collection<String> values = processedParameters.values();

		int size = processedParameters.size();
		String[] parameterValues = new String[size];
		values.toArray(parameterValues);

		return executeQuery(query, new PictureBuilder(), parameterValues);
	}
}
