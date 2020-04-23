package by.epam.lab.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.Identifiable;
import by.epam.lab.exception.DaoException;
import by.epam.lab.service.builder.Builder;
import by.epam.lab.service.factory.BuilderFactory;
import by.epam.lab.service.util.QueryPreparer;

/**
 * This class provides a skeletal implementation of the {@link Dao} interface,
 * to minimize the effort required to implement this interface.
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractDao<T extends Identifiable> implements Dao {

    private Connection connection;

	 
    private static final String GET_ALL_QUERY = "SELECT * FROM ";
    private String WHERE_ID_CONDITION = " WHERE id_" + getTableName() + "= ?";

	
	public AbstractDao(Connection connection) {
		this.connection = connection; 
	}
	 

    protected abstract String getTableName();
    /**
     * Performs a parameterized read query to a database with parameters, waiting for a set of objects,
     * and builds them with the help of a concrete builder implementation.
     *
     * @param query   a {@link String} object that contains database query.
     * @param builder a implementation of {@link Builder} with a concrete class whose objects are to be built.
     * @param params  a {@link String} objects that contains parameters that should be substituted in query.
     * @return a {@link List} implementation with objects.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    
    protected List<T> executeQuery(String query, Builder<T> builder, String... params) throws DaoException {

        List<T> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        	QueryPreparer.prepare(preparedStatement, params);
        	
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                T item = builder.build(rs);
                items.add(item);
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
        
        return items;
    }

    /**
     * Performs a parameterized read query to a database with parameters, waiting for an object,
     * and builds them with the help of a concrete builder implementation.
     *
     * @param query   a {@link String} object that contains database query.
     * @param builder a implementation of {@link Builder} with a concrete class whose object is to be built.
     * @param params  a {@link String} objects that contains parameters that should be substituted in query.
     * @return a {@link Optional} implementation with object.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    protected Optional<T> executeQueryForSingleResult(String query, Builder<T> builder, String... params) throws DaoException {

        List<T> items = executeQuery(query, builder, params);

        return items.size() == 1 ?
                Optional.of(items.get(0)) :
                Optional.empty();
    }

    /**
     * Performs a parameterized update query to a database with parameters.
     *
     * @param query  a {@link String} object that contains database query.
     * @param params a {@link String} objects that contains parameters that should be substituted in query.
     * @return a {@link long} identifier that was created during executing query
     * or 0 if no identifier was created.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    protected long executeUpdate(String query, String... params) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            QueryPreparer.prepare(preparedStatement, params);

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            long generatedKey = 0;
            if (generatedKeys.next()) {
                generatedKey = generatedKeys.getLong(1);
            }
            return generatedKey;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    /**
     * Performs a parameterized read query to the database,
     * expecting a single result in the form of an object of type T with the specified identifier.
     *
     * @param id a object identifier in database
     * @return a {@link Optional} implementation with object.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    @SuppressWarnings("unchecked")
	@Override
    public Optional<T> findById(Long id) throws DaoException {
    	 
        Builder builder = BuilderFactory.create(getTableName());
        System.out.println("gettable" + getTableName());
        String query = GET_ALL_QUERY + getTableName() + WHERE_ID_CONDITION;
        System.out.println("query :  " + query);
        String stringId = String.valueOf(id);
        return executeQueryForSingleResult(query, builder, stringId);
    }

    /**
     * Performs a parameterized read query to a database to find all object type T.
     *
     * @return a {@link List} implementation with all finding objects.
     * @throws DaoException Signals that an database access object exception of some sort has occurred.
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<T> findAll() throws DaoException {
        Builder builder = BuilderFactory.create(getTableName());
        String query = GET_ALL_QUERY + getTableName();
        return executeQuery(query, builder);
    }


    /**
     * Cannot remove entity from database.
     *
     * @param id
     * @throws DaoException
     */
    @Override
    public void removeById(Long id) throws DaoException {
        throw new UnsupportedOperationException("You cannot remove entity from database");
    }
}
