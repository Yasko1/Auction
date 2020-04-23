package by.epam.lab.dao;

import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.Identifiable;
import by.epam.lab.exception.DaoException;


/**
 * Designed to determine the behavior of the implementing class in the form of
 * database access objects of type T.
 * 
 * @param <T> - type of object.
 */
public interface Dao<T extends Identifiable> {
	/**
	 * Performs a parameterized read query to the database, expecting a single
	 * result in the form of an object of type T with the specified identifier.
	 * 
	 * @param id a object identifier in database
	 * @return a {@link Optional} implementation with object.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	Optional<T> findById(Long id) throws DaoException;

	/**
	 * Performs a parameterized read query to a database to find all object type T.
	 *
	 * @return a {@link List} implementation with all finding objects.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	List<T> findAll() throws DaoException;

	/**
	 * The method designed for the process of saving a objects in database.
	 *
	 * @param item an object type T that should be saved to the database.
	 * @return created lot identifier in database.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	long save(T item) throws DaoException;

	/**
	 * Using id delete an entity from the database.
	 *
	 * @param id
	 * @throws DaoException
	 */
	void removeById(Long id) throws DaoException;

}
