package beans.util;

import util.Parameters;

public class User extends Bean {
	public String email;

	public String login;
	public String password;

	public User(Parameters params)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		super(params);
		// TODO Auto-generated constructor stub
	}
public User() {
	// TODO Auto-generated constructor stub
}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String myKey() {
		// TODO Auto-generated method stub
		return "users";
	}

}
