package JDBC.beans;


import java.sql.Date;

// Player MODEL - JAVA BEAN
// We can also call this our Database Schema

public class Player {
	// PRIVATE INSTANCE VARIABLES ++++++++++++++++++++++
	private int _player_id;
	private String _fistName;
	private String _lastName;
	private String _address;
	private String _postalCode;
	private String _province;
	private String _phoneNumber;
		
	// PUBLIC PROPERTIES +++++++++++++++++++++++++++++++
	public int getPlayerId() {
		return this._player_id;
	}
	
	public void setPlayerId(int id) {
		this._player_id = id;
	}
	
	public String getFistName() {
		return this._fistName;
	}

	public void setFistName(String fistName) {
		this._fistName = fistName;
	}

	public String getLastName() {
		return this._lastName;
	}

	public void setLastName(String lastName) {
		this._lastName = lastName;
	}

	public String getAddress() {
		return this._address;
	}

	public void setAddress(String address) {
		this._address = address;
	}

	public String getPostalCode() {
		return this._postalCode;
	}

	public void setPostalCode(String postalCode) {
		this._postalCode = postalCode;
	}

	public String getProvince() {
		return this._province;
	}

	public void setProvince(String province) {
		this._province = province;
	}

	public String getPhoneNumber() {
		return this._phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this._phoneNumber = phoneNumber;
	}

	
}

