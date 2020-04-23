package by.epam.lab.service;

import java.util.List;
import java.util.Map;

import by.epam.lab.dao.DaoCreator;
import by.epam.lab.dao.implementation.PictureDaoImplementation;
import by.epam.lab.entity.Picture;
import by.epam.lab.exception.DaoException;
import by.epam.lab.exception.ServiceException;



public class PictureService {

	public List<Picture> findByIDLot(long id) throws ServiceException {
		try (DaoCreator daoCreator = new DaoCreator()) {
			PictureDaoImplementation pictureDao = daoCreator.getPictureDaoImpl();

			return pictureDao.findPicturesByLotId(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * The method designed for the process of saving an {@link Picture} object.
	 *
	 * @param item an {@link Picture} object that should be saved to the database.
	 * @return created lot identifier in database.
	 * @throws ServiceException Signals that service exception of some sort has
	 *                          occurred.
	 */
	public long save(Picture item) throws ServiceException {
		try (DaoCreator daoCreator = new DaoCreator()) {
			PictureDaoImplementation pictureDao = daoCreator.getPictureDaoImpl();

			return pictureDao.save(item);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
     *  Method designed for searching lot picture depends on user identifier.
     *
     * @param id - User identifier in database
     * @return an {@link List} implementation with an lot {@link entity.Picture} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
	public List<Picture> findAllByLotId(long id) throws ServiceException {

		try (DaoCreator daoCreator = new DaoCreator()) {
			PictureDaoImplementation pictureDao = daoCreator.getPictureDaoImpl();

			return pictureDao.findAllByLotId(id);

		} catch (DaoException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
    /**
     * The method searches for picturess with given parameters.
     *
     * @param parameters a {@link Map} object that maps keys(name of parameter) to values of parameters.
     * @return an {@link List} implementation with {@link Picture} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    public List<Picture> findByParameters(Map<String, String> parameters) throws ServiceException {
    	DaoCreator daoCreator = new DaoCreator();
    	try {        	
			PictureDaoImplementation pDao = daoCreator.getPictureDaoImpl();
			System.out.println(pDao.findByParameters(parameters));
            return pDao.findByParameters(parameters);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        } finally {
        	daoCreator.close();
		}
    }
}
