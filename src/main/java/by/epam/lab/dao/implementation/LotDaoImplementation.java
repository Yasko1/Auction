package by.epam.lab.dao.implementation;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import by.epam.lab.config.AppConfig;
import by.epam.lab.dao.DynamicQueryBuilder;
import by.epam.lab.dao.LotDao;
import by.epam.lab.entity.Identifiable;
import by.epam.lab.entity.Lot;
import by.epam.lab.entity.LotStatusEnum;
import by.epam.lab.exception.DaoException;
import by.epam.lab.mapper.LotMapper;
import by.epam.lab.service.util.DateTimeParser;



/**
 * Class is an implementation of access to lot database and provides methods to
 * work with it.
 */
public class LotDaoImplementation implements LotDao {

	private static final String DATE_OF_START_FROM = "date_of_start_from";
	private static final String DATE_OF_END_TO = "date_of_end_to";

	private static final String ALL_LOTS_BY_USER_ID_QUERY = "SELECT * FROM \"lot\" WHERE owner_id = ?";
	private static final String INSERT_QUERY = "INSERT INTO \"lot\" (id_lot, price, owner_id, date_of_start, date_of_end, status)"
			+ " VALUES(?,?,?,?,?,?) " + " ON DUPLICATE KEY"
			+ " UPDATE id_lot = VALUES(id_lot), price = VALUES(price),owner_id = VALUES(owner_id),"
			+ " date_of_start = VALUES(date_of_start), date_of_end = VALUES(date_of_end)," + " status = VALUES(status)";
	private static final String BID_LOT_QUERY = "UPDATE \"lot\" SET lot.price = ? WHERE lot.id_lot = ? AND price = ?";

	private static final String TABLE_NAME = "\"lot\"";
	private static final String ALL = "All";
	private static final String SELECT_LOTS_BY_USER_ID_QUERY = "SELECT * FROM \"lot\" WHERE id_lot = ?";

	ApplicationContext context =
			new AnnotationConfigApplicationContext(AppConfig.class);
	private JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
	

	/*
	 * public LotDaoImplementation(Connection connection) { super(connection); }
	 */

	/**
	 * The method designed for the process of saving a {@link Lot} object in
	 * database.
	 *
	 * @param item an {@link Identifiable} {@link Lot} object that should be saved
	 *             to the database.
	 * @return created lot identifier in database.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	public long save(Identifiable item) throws DaoException {
		Lot lot = (Lot) item;

		long idLot = lot.getIdLot();
		String idLotString = String.valueOf(idLot);

		BigDecimal price = lot.getPrice();
		String priceString = price.toString();

		Date dateOfStart = lot.getDateOfStart();
		String dateOfStartString = DateTimeParser.parse(dateOfStart);

		Date dateOfEnd = lot.getDateOfEnd();
		String dateOfEndString = DateTimeParser.parse(dateOfEnd);

		long ownerId = lot.getOwnerId();
		String ownerIdString = String.valueOf(ownerId);

		LotStatusEnum lotStatusEnum = lot.getStatus();
		String losStatusString = lotStatusEnum != null ? lotStatusEnum.getValue() : LotStatusEnum.PROCESSING.getValue();

		return template.update(INSERT_QUERY, idLotString, priceString,
		 dateOfStartString, dateOfEndString, ownerIdString, losStatusString);
		/*
		 * return executeUpdate(INSERT_QUERY, idLotString, priceString,
		 * dateOfStartString, dateOfEndString, ownerIdString, losStatusString);
		 */
	}

	/**
	 *
	 * @return Name of the table designed for storage {@link Lot}.
	 */
	protected String getTableName() {
		return TABLE_NAME;
	}

	/**
	 * Method designed for searching user lots depends on user identifier.
	 *
	 * @param id - User identifier in database
	 * @return an {@link List} implementation with an user {@link Lot} objects.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	@Override
	public List<Lot> findAllByUserId(long id) throws DaoException {

		String idParameter = String.valueOf(id);

		return template.query(ALL_LOTS_BY_USER_ID_QUERY, new LotMapper(), idParameter);
		//return executeQuery(ALL_LOTS_BY_USER_ID_QUERY, new LotBuilder(), idParameter);
	}

	/**
	 * The method searches for all active (which are in the auction) lots.
	 *
	 * @return an {@link List} implementation with {@link Lot} objects.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	@Override
	public List<Lot> findAllActive() throws DaoException {
		Date currentDate = new Date();
		String currentDateString = DateTimeParser.parse(currentDate);

		Map<String, String> parameters = new HashMap<>();
		parameters.put(Lot.STATUS, LotStatusEnum.CONFIRMED.getValue());
		parameters.put(DATE_OF_START_FROM, currentDateString);
		parameters.put(DATE_OF_END_TO, currentDateString);

		return findByParameters(parameters);
	}

	/**
	 * The method searches for lots with given parameters.
	 *
	 * @param parameters a {@link Map} object that maps keys(name of parameter) to
	 *                   values of parameters.
	 * @return an {@link List} implementation with {@link Lot} objects.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	@Override
	public List<Lot> findByParameters(Map<String, String> parameters) throws DaoException {
		
		
		Map<String, String> processedParameters = new HashMap<>();

		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			String value = entry.getValue();
			if (!ALL.equals(value)) {
				String key = entry.getKey();
				if(key=="price_to" && value.isEmpty()) {
				processedParameters.put(key, "1000");
				} else {
					processedParameters.put(key, value);
				}
			}
		}

		String query = DynamicQueryBuilder.build(processedParameters);
		
		Collection<String> values = processedParameters.values();

		int size = processedParameters.size();
		String[] parameterValues = new String[size];
		values.toArray(parameterValues);

		List<Lot> lot = template.query(query, new LotMapper(), values.toArray());
		return lot;
		//return executeQuery(query, new LotBuilder(), parameterValues);
	}

	/**
	 * Makes a bid based on the type of auction.
	 *
	 * @param lot The {@link Lot} object of the lot on which you want to bet.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	@Override
	public void bid(Lot lot) throws DaoException {

		increasePrice(lot);

		long idLot = lot.getIdLot();
		String lotIdString = String.valueOf(idLot);

		BigDecimal oldPrice = lot.getPrice();
		String oldPriceString = oldPrice.toString();

		BigDecimal newPrice = lot.getPrice();
		String newPriceString = newPrice.toString();

		template.update(BID_LOT_QUERY, newPriceString, lotIdString, oldPriceString);
		/* executeUpdate(BID_LOT_QUERY, newPriceString, lotIdString, oldPriceString); */
	}

	/**
	 * Increases the price of the lot depending on its current bid.
	 *
	 * @param lot an {@link Lot}object to be subject to price increase.
	 */
	private void increasePrice(Lot lot) {
		BigDecimal currentBid = lot.getCurrentBid();
		BigDecimal oldPrice = lot.getPrice();
		BigDecimal newPrice = oldPrice.add(currentBid);
		lot.setPrice(newPrice);
	}
	
	public Optional<Lot> findById(long id) throws DaoException {
		Optional<Lot> opl = Optional.of(template.queryForObject(SELECT_LOTS_BY_USER_ID_QUERY, new LotMapper(), id));
		return opl;
    }

}
