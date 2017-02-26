package beans.util;

import util.Parameters;

public class Post extends Bean {
	//ID,DATE,TEXT,ID_USER
	
	public String date;
	public String text;
	public String id_user;
	
	
	public Post() {
		// TODO Auto-generated constructor stub
		super();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	public Post(Parameters params)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		super(params);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String myKey() {
		// TODO Auto-generated method stub
		return "messages";
	}

}
