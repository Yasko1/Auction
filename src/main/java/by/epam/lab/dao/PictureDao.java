package by.epam.lab.dao;

import java.util.List;

import by.epam.lab.entity.Identifiable;
import by.epam.lab.entity.Picture;
import by.epam.lab.exception.DaoException;

public interface PictureDao {

	/**
	 * Method designed for searching picture depends on id of lot identifier.
	 *
	 * @param id - User identifier in database
	 */
	List<Picture> findPicturesByLotId(long id) throws DaoException;
	
	public List<Picture> findAllByLotId(long id) throws DaoException;
	
	public long save(Identifiable item) throws DaoException ;
	
}