package by.epam.lab.service;

import java.util.List;

import by.epam.lab.dao.DaoCreator;
import by.epam.lab.dao.implementation.LotPhotoDaoImplementation;
import by.epam.lab.entity.LotPhoto;
import by.epam.lab.exception.DaoException;
import by.epam.lab.exception.ServiceException;

/**
 * Class provides methods to work with {@link LotPhoto} objects.
 */
public class LotPhotoService {

	/**
	 * Method designed for searching lot photos depends on lot identifier.
	 *
	 * @param id - Lot identifier in database
	 * @return an {@link List} implementation with an lot {@link LotPhoto} objects.
	 * @throws ServiceException Signals that service exception of some sort has
	 *                          occurred.
	 */
	public List<LotPhoto> getPhotoByLotId(long id) throws ServiceException {
		try (DaoCreator daoCreator = new DaoCreator()) {
			LotPhotoDaoImplementation lotPhotoDao = daoCreator.getLotPhotoDaoImpl();

			return lotPhotoDao.findLotPhotosByLotId(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * The method designed for the process of saving an {@link LotPhoto} object.
	 *
	 * @param lotPhoto an {@link LotPhoto} object that should be saved to the
	 *                 database.
	 * @return created lot photo identifier in database.
	 * @throws ServiceException Signals that service exception of some sort has
	 *                          occurred.
	 */
	public long saveLotPhoto(LotPhoto lotPhoto) throws ServiceException {
		try (DaoCreator daoCreator = new DaoCreator()) {
			LotPhotoDaoImplementation lotPhotoDao = daoCreator.getLotPhotoDaoImpl();

			return lotPhotoDao.save(lotPhoto);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
