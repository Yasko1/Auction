package by.epam.lab.dao;

import java.util.List;
import java.util.Map;

import by.epam.lab.entity.Lot;
import by.epam.lab.exception.DaoException;



public interface LotDao {
	/**
	 * Method designed for searching user lots depends on user identifier.
	 *
	 * @param id - User identifier in database
	 * @return an {@link List} implementation with an user {@link Lot} objects.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	List<Lot> findAllByUserId(long id) throws DaoException;

	/**
	 * The method searches for all active (which are in the auction) lots.
	 *
	 * @return an {@link List} implementation with {@link Lot} objects.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	List<Lot> findAllActive() throws DaoException;

	/**
	 * The method searches for lots with given parameters.
	 *
	 * @param parameters An {@link Map} object that maps keys(name of parameter) to
	 *                   values of parameters.
	 * @return an {@link List} implementation with {@link Lot} objects.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	List<Lot> findByParameters(Map<String, String> parameters) throws DaoException;

	/**
	 * Makes a bid based on the type of auction.
	 *
	 * @param lot The {@link Lot} object of the lot on which you want to bet.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	void bid(Lot lot) throws DaoException;
}
