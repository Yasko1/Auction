package by.epam.lab.dao;

import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.User;
import by.epam.lab.exception.DaoException;

public interface UserDao {
	/**
	 * Method designed for searching user depends on user login and password.
	 *
	 * @param login    is a {@link String} object that contains user login
	 * @param password is a {@link String} object that contains user password
	 * @return a {@link Optional} object with finding {@link User} inside.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

	/**
	 * Method designed for searching users.
	 *
	 * @return a {@link List} implementation with a {@link User} objects.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	List<User> findAllUsers() throws DaoException;

	/**
	 * Method designed for searching {@link entity.Lot} bidders
	 * depends on {@link model.Lot} identifier.
	 *
	 * @param lotId is an identifier of {@link entity.Lot}
	 * @return a {@link List} implementation with a {@link entity.User} objects.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	List<User> findLotBidders(long lotId) throws DaoException;

	/**
	 * Method designed for saving {@link entity.Lot} bidders.
	 *
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	void saveLotBidder(User bidder, long lotId) throws DaoException;

}
