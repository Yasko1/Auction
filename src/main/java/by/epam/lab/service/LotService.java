package by.epam.lab.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.epam.lab.dao.implementation.LotDaoImplementation;
import by.epam.lab.entity.Lot;
import by.epam.lab.exception.DaoException;
import by.epam.lab.exception.ServiceException;

/**
 *  Class provides methods to work with {@link Lot} objects.
 */
public class LotService {

    /**
     * The method searches for all active (which are in the auction) lots.
     *
     * @return an {@link List} implementation with {@link Lot} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    public List<Lot> findAllActive() throws ServiceException {
    	LotDaoImplementation lotDao = new LotDaoImplementation();
        try {
        	List<Lot> lot =lotDao.findAllActive();
           return lot;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * The method searches for lot with given identifier.
     *
     * @param id an object identifier in database
     * @return a {@link Optional} implementation with object.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    public Optional<Lot> findById(long id) throws ServiceException {
    	LotDaoImplementation lotDao = new LotDaoImplementation();
        try {
            return lotDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     *  Method designed for searching user lots depends on user identifier.
     *
     * @param id - User identifier in database
     * @return an {@link List} implementation with an user {@link Lot} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    public List<Lot> findAllByUserId(long id) throws ServiceException {
    	
    	LotDaoImplementation lotDao = new LotDaoImplementation();
        try {
            return lotDao.findAllByUserId(id);
            
        } catch (DaoException e) {
        	e.printStackTrace();
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * The method searches for lots with given parameters.
     *
     * @param parameters a {@link Map} object that maps keys(name of parameter) to values of parameters.
     * @return an {@link List} implementation with {@link Lot} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    public List<Lot> findByParameters(Map<String, String> parameters) throws ServiceException {
    	LotDaoImplementation ld = new LotDaoImplementation();
    	try {        	
    		System.out.println("LotService :  " + parameters);
    		List<Lot> ll = ld.findByParameters(parameters);
   			return ll;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * The method designed for the process of saving an {@link Lot} object.
     *
     * @param item an {@link Lot} object that should be saved to the database.
     * @return created lot identifier in database.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    public long save(Lot itsem) throws ServiceException {
    	LotDaoImplementation lotDao = new LotDaoImplementation();
        try {
            return lotDao.save(itsem);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Makes a bid based on the type of auction.
     *
     * @param lot The {@link Lot} object of the lot on which you want to bet.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    public void bid(Lot lot) throws ServiceException {
    	LotDaoImplementation lotDao = new LotDaoImplementation();
        try {
            lotDao.bid(lot);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
