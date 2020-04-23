package by.epam.lab.service.builder;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.lab.entity.RoleEnum;
import by.epam.lab.entity.User;
import by.epam.lab.exception.DaoException;


/**
 * Designed to build an object of type {@link entity.User} with specified characteristics.
 */
public class UserBuilder implements Builder<User> {

    /**
     * Builds an object of type User with properties.
     */
    @Override
    public User build(ResultSet resultSet) throws DaoException {
        try {
            long id = resultSet.getLong(User.ID);
            String firstName = resultSet.getString(User.FIRST_NAME);
            String lastName = resultSet.getString(User.LAST_NAME);
            String userName = resultSet.getString(User.USERNAME);
            String email = resultSet.getString(User.EMAIL);
            String password = resultSet.getString(User.PASSWORD);

            String roleString = resultSet.getString(User.ROLE);
            roleString = roleString.toUpperCase();
            RoleEnum role = RoleEnum.valueOf(roleString);

            boolean isBanned = resultSet.getBoolean(User.IS_BANNED);

            BigDecimal balance = resultSet.getBigDecimal(User.BALANCE);

            return new User(id, firstName, lastName, userName, email, password, role, isBanned, balance);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}