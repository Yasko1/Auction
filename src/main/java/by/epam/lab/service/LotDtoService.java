package by.epam.lab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.epam.lab.entity.Lot;
import by.epam.lab.entity.LotDto;
import by.epam.lab.entity.LotPhoto;
import by.epam.lab.entity.Picture;
import by.epam.lab.exception.ServiceException;

/**
 * Class provides methods to work with {@link LotDto} objects.
 */
public class LotDtoService {

	/**
	 * The method searches for all active (which are in the auction) lots.
	 *
	 * @return an {@link List} implementation with {@link Lot} objects.
	 * @throws ServiceException Signals that service exception of some sort has
	 *                          occurred.
	 */
	public List<LotDto> findAllActive() throws ServiceException {

		LotService lotService = new LotService();
		List<Lot> lots = lotService.findAllActive();

		List<LotDto> lotDtos = new ArrayList<>();

		LotPhotoService lotPhotoService = new LotPhotoService();
		findLotsPhotos(lots, lotPhotoService, lotDtos);

		return lotDtos;
	}

	public List<LotDto> findAllPictByLotId(long id) throws ServiceException {
		PictureService pictureService = new PictureService();
		List<Picture> picturess = pictureService.findByIDLot(id);

		List<LotDto> lotsDTO = new ArrayList<>();
		for (Picture p : picturess) {
			LotDto lotDto = new LotDto(p);
			lotsDTO.add(lotDto);
		}
		return lotsDTO;
	}

	/**
	 * Method designed for searching user lots depends on user identifier.
	 *
	 * @param id - User identifier in database
	 * @return an {@link List} implementation with an user {@link Lot} objects.
	 * @throws ServiceException Signals that service exception of some sort has
	 *                          occurred.
	 */
	public List<LotDto> findAllByUserId(long id) throws ServiceException {
		LotService lotService = new LotService();
		List<Lot> lots = lotService.findAllByUserId(id);

		List<LotDto> lotDtos = new ArrayList<>();
		LotPhotoService lotPhotoService = new LotPhotoService();
		findLotsPhotos(lots, lotPhotoService, lotDtos);

		return lotDtos;
	}

	/**
	 * The method searches for lot with given identifier.
	 *
	 * @param id a object identifier in database
	 * @return a {@link Optional} implementation with {@link LotDto} object.
	 * @throws ServiceException Signals that service exception of some sort has
	 *                          occurred.
	 */
	public Optional<LotDto> findById(long id) throws ServiceException {
		LotService lotService = new LotService();
		Optional<Lot> lot = lotService.findById(id);

		LotPhotoService lotPhotoService = new LotPhotoService();
		List<LotPhoto> lotPhotos = lotPhotoService.getPhotoByLotId(id);

		LotDto lotDto = new LotDto(lot.get(), lotPhotos);
		return Optional.of(lotDto);
	}

	/**
	 * The method searches for lots with given parameters.
	 *
	 * @param parameters a {@link Map} object that maps keys(name of parameter) to
	 *                   values of parameters.
	 * @return an {@link List} implementation with {@link LotDto} objects.
	 * @throws ServiceException Signals that service exception of some sort has
	 *                          occurred.
	 */
	public List<LotDto> findByParameters(Map<String, String> parameters) throws ServiceException {
		LotService lotService = new LotService();
		List<Lot> lots = lotService.findByParameters(parameters);

		LotPhotoService lotPhotoService = new LotPhotoService();
		List<LotDto> lotDtos = new ArrayList<>();

		findLotsPhotos(lots, lotPhotoService, lotDtos);

		return lotDtos;
	}

	/**
	 * The method searches for lot photos and set them into {@link LotDto} object.
	 *
	 * @param lots            a {@link Lot} objects whose photos should be found.
	 * @param lotPhotoService a {@link LotPhotoService} object that provide method
	 *                        to find lot photo.
	 * @param lotsDTO         a {@link List} implementation with {@link LotDto}
	 *                        objects to set lot photo into them
	 * @throws ServiceException Signals that service exception of some sort has
	 *                          occurred.
	 */
	private void findLotsPhotos(List<Lot> lots, LotPhotoService lotPhotoService, List<LotDto> lotsDTO)
			throws ServiceException {
		for (Lot lot : lots) {

			long lotId = lot.getId();
			List<LotPhoto> lotPhotos = lotPhotoService.getPhotoByLotId(lotId);

			PictureService pService = new PictureService();

			List<Picture> picturess = pService.findByIDLot(lotId);
			for (Picture p : picturess) {
				if (lotId == p.getId()) {
					LotDto lotDto = new LotDto(lot, lotPhotos, p);
					lotsDTO.add(lotDto);
				}
			}
		}
	}
}
