package Classes;

public class User {
	public String username;
	public String password;
	public String name;
	public String nif;
	public String email;
	public String address;
	public String birthdate;
	public String role;
	
	
	public User(String username, String password, String name, String nif, String email, String address, String birthdate, String role) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.nif = nif;
		this.email = email;
		this.address = address;
		this.birthdate = birthdate;
		this.role = role;
	}

	public String toString() {
		return "Ususuario: " + username + "\nContraseña: " + password + "\nNombre: " + name + "\nNIF: " + nif + "\nEmail: "
				+ email + "\nCalle: " + address + "\nCumpleaños: " + birthdate + "\nRol: " + role;
	}
	
	
	
	
}
