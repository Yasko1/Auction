package by.epam.lab.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.epam.lab.entity.RoleEnum;
import by.epam.lab.entity.User;
public class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet resultSet, int i) throws SQLException {
		
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
     
		/*
		 * User user = new User(); user.setIdUser(resultSet.getLong("id"));
		 * user.setFirstName(resultSet.getString("name"));
		 * user.setLastName(resultSet.getString("last_name"));
		 * user.setEmail(resultSet.getString("email"));
		 * user.setFirstName(resultSet.getString("name")); return user;
		 */
	}
}
