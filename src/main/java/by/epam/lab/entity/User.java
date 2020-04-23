package by.epam.lab.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class User implements Identifiable {

	private long idUser;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String password;
	private RoleEnum role;
	private boolean isBanned;
	private BigDecimal balance;
	
	public static final String ID = "id_user";
	public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String IS_BANNED = "is_banned";
    public static final String BALANCE = "balance";

	public User() {
	}

	public User(long idUser, String firstName, String lastName, String userName, String email, String password,
			RoleEnum role, boolean isBanned, BigDecimal balance) {
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.isBanned = isBanned;
		this.balance = balance;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public long getIdUser() {
		return idUser;
	}

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean banned) {
		isBanned = banned;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		User user = (User) o;
		return getIdUser() == user.getIdUser() && isBanned() == user.isBanned()
				&& Objects.equals(getFirstName(), user.getFirstName())
				&& Objects.equals(getLastName(), user.getLastName())
				&& Objects.equals(getUserName(), user.getUserName()) && Objects.equals(getEmail(), user.getEmail())
				&& Objects.equals(getPassword(), user.getPassword()) && getRole() == user.getRole()
				&& Objects.equals(getBalance(), user.getBalance());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIdUser(), getFirstName(), getLastName(), getUserName(), getEmail(), getPassword(),
				getRole(), isBanned(), getBalance());
	}

	@Override
	public long getId() {
		return idUser;
	}

}
