package by.epam.lab.service;


import java.util.List;
import java.util.Optional;

import by.epam.lab.dao.implementation.UserDaoImplementation;
import by.epam.lab.entity.User;
import by.epam.lab.exception.DaoException;
import by.epam.lab.exception.ServiceException;

/**
 * Class provides methods to work with {@link User} objects.
 */
public class UserService {

	UserDaoImplementation userDao;
    /**
     * Method designed for searching user depends on user login and password.
     *
     * @param login    is a {@link String} object that contains user login
     * @param password is a {@link String} object that contains user password
     * @return a {@link Optional} object with finding {@link User} inside.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    public Optional<User> login(String login, String password) throws ServiceException {

    	UserDaoImplementation usD = new UserDaoImplementation();
        try {
            Optional<User> user=usD.findUserByLoginAndPassword(login, password);
        	return user;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * The method searches for user with given identifier.
     *
     * @param id an object identifier in database
     * @return a {@link Optional} implementation with object.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    public Optional<User> findById(long id) throws ServiceException {

        try {
        	 return userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Method designed for searching users.
     *
     * @return a {@link List} implementation with a {@link User} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    public List<User> findAllUsers() throws ServiceException {

        try {
            return userDao.findAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * The method designed for the process of saving a {@link User} object in database.
     *
     * @param item a {@link User} object that should be saved to the database.
     * @return created user identifier in database.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    public long save(User item) throws ServiceException {
        try {
       	  return userDao.save(item);
         } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Method designed for searching {@link com.epam.auction.model.Lot} bidders
     * depends on {@link com.epam.auction.model.Lot} identifier.
     *
     * @param lotId is an identifier of {@link com.epam.auction.model.Lot}
     * @return a {@link List} implementation with a {@link User} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    public List<User> findLotBidders(long lotId) throws ServiceException {
        try {
            return userDao.findLotBidders(lotId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Method designed for saving {@link com.epam.auction.model.Lot} bidders.
     *
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    public void saveLotBidder(User bidder, long lotId) throws ServiceException {
        try {
            userDao.saveLotBidder(bidder, lotId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
