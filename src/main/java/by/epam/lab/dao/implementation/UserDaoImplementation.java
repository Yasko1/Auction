package by.epam.lab.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import by.epam.lab.config.AppConfig;
import by.epam.lab.dao.UserDao;
import by.epam.lab.entity.Identifiable;
import by.epam.lab.entity.User;
import by.epam.lab.exception.DaoException;
import by.epam.lab.mapper.UserMapper;

/**
 * Class is an implementation of access to user database and provides methods to
 * work with it.
 */
public class UserDaoImplementation implements UserDao {

	private static final String TABLE_NAME = "\"user\"";
	private static final String ALL_USERS_QUERY = "SELECT * FROM \"user\" WHERE role = 'user'";
	private static final String USERNAME_AND_PASSWORD_QUERY = "SELECT * FROM \"user\" WHERE username = ? AND password = ?";
	private static final String LOT_BIDDERS_QUERY = "SELECT"
			+ " id_user, first_name, last_name, username, email, password, role, is_banned, balance"
			+ " FROM \"bidder\"" + " LEFT JOIN user" + " ON user_id_user = user.id_user" + " WHERE lot_id_lot = ?";
	private static final String INSERT_QUERY = "INSERT INTO \"user\" (id_user, first_name, last_name, username, email,"
			+ " role, is_banned, balance)" + " VALUES(?,?,?,?,?,?,?,?) " + " ON DUPLICATE KEY"
			+ " UPDATE id_user = VALUES(id_user), first_name = VALUES(first_name), last_name = VALUES(last_name),"
			+ " username = VALUES(username), email = VALUES(email), role = VALUES(role),"
			+ " is_banned = VALUES(is_banned), balance = VALUES(balance)";
	private static final String INSERT_BIDDER_QUERY = "INSERT INTO \"bidder\" (lot_id_lot, user_id_user) VALUES(?,?)";
	private static final String SELECT_USERS_QUERY = "SELECT * FROM \"user\" WHERE id_user = ?";
	ApplicationContext context =
			new AnnotationConfigApplicationContext(AppConfig.class);
	private JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
	protected String getTableName() {
		return TABLE_NAME;
	}

	/**
	 * The method designed for the process of saving a {@link User} object in
	 * database.
	 *
	 * @param item an {@link Identifiable} {@link User} object that should be saved
	 *             to the database.
	 * @return created user identifier in database.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	public long save(Identifiable item) throws DaoException {
		return template.update(INSERT_QUERY, item);
		/*
		 * User user = (User) item;
		 * 
		 * long idUser = user.getIdUser(); String idUserString = String.valueOf(idUser);
		 * 
		 * String fistName = user.getFirstName(); String lastName = user.getLastName();
		 * String username = user.getUserName(); String email = user.getEmail();
		 * 
		 * RoleEnum roleEnum = user.getRole(); String roleString = roleEnum.getValue();
		 * 
		 * boolean isBanned = user.isBanned(); String isBannedString = isBanned ? "1" :
		 * "0";
		 * 
		 * BigDecimal balance = user.getBalance(); String balanceString =
		 * balance.toString();
		 * 
		 * return executeUpdate(INSERT_QUERY, idUserString, fistName, lastName,
		 * username, email, roleString, isBannedString, balanceString);
		 */
	}

	/**
	 * Method designed for searching user depends on user login and password.
	 *
	 * @param login    is a {@link String} object that contains user login
	 * @param password is a {@link String} object that contains user password
	 * @return a {@link Optional} object with finding {@link User} inside.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
		
		Optional<User> user = Optional
				.of(template.queryForObject(USERNAME_AND_PASSWORD_QUERY, new UserMapper(), login, password));
		return user;
		
		// return executeQueryForSingleResult(USERNAME_AND_PASSWORD_QUERY, new UserBuilder(), login, password);
	}

	/**
	 * Method designed for searching users.
	 *
	 * @return a {@link List} implementation with a {@link User} objects.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	public List<User> findAllUsers() throws DaoException {
		return template.query(ALL_USERS_QUERY, new UserMapper());
		// return executeQuery(ALL_USERS_QUERY, new UserBuilder());
	}

	/**
	 * Method designed for searching {@link entity.Lot} bidders depends on
	 * {@link entity.Lot} identifier.
	 *
	 * @param lotId is an identifier of {@link entity.Lot}
	 * @return a {@link List} implementation with a {@link User} objects.
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	public List<User> findLotBidders(long lotId) throws DaoException {
		String lotIdString = String.valueOf(lotId);
		return template.query(LOT_BIDDERS_QUERY, new UserMapper(), lotIdString);
		// return executeQuery(LOT_BIDDERS_QUERY, new UserBuilder(), lotIdString);
	}

	/**
	 * Method designed for saving {@link entity.Lot} bidders.
	 *
	 * @throws DaoException Signals that an database access object exception of some
	 *                      sort has occurred.
	 */
	public void saveLotBidder(User bidder, long lotId) throws DaoException {
		long bidderId = bidder.getId();
		String bidderIdString = String.valueOf(bidderId);
		template.update(INSERT_BIDDER_QUERY, String.valueOf(lotId), bidderIdString);
		// executeUpdate(INSERT_BIDDER_QUERY, lotIdString, bidderIdString);
	}

	public Optional<User> findById(long id) throws DaoException {
		Optional<User> user = Optional.of(template.queryForObject(SELECT_USERS_QUERY, new UserMapper(), id));
		return user;
	}
	
	
}
