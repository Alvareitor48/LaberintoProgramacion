package Classes;
import java.io.*;
import java.util.Scanner;

public class Session {
	private User user;
	private boolean logged;
	private final String FILE_PATH = ".\\assets\\files";//Ruta del archivo
	private final String USERS_FILE ="users.txt";//Nombre del archivo

	public Session() {
		logged = false;
	}

	public void login() {
		String userLg;//Usuario
		String passwordLg;//Contraseña
		int attempts = 0;
		String continues = "S";

		do {
			//Guardamos usuario y contraseña
			System.out.println("\n\n----Login----");
			userLg = Input.getString("Usuario: ");
			passwordLg = Input.getString("Contraseña: ");
			try {
				//El File lo que hace es cargar el archivo txt
				File file = new File(FILE_PATH, USERS_FILE);
				Scanner fileScanner = new Scanner(file);//Scanner para leer el archivo
				while(fileScanner.hasNextLine()) {//Esto significa, que si hay otra linea
					//Guarda en un String la linea y luego la mete en un Array con el split
					String voucher = fileScanner.nextLine();
					String[] userArray = voucher.split("#");
					//Crea un objeto de tipo User para el atributo user
					user = new User(userArray[0],userArray[1], userArray[2], userArray[3],userArray[4],userArray[5],userArray[6],userArray[7]);			
					//Comprueba usuario y contraseña, para ver si puede iniciar sesion, y setea logged a true
					if(userArray[0].equals(userLg)== true && userArray[1].equals(passwordLg)) {
						System.out.println("Usuario Disponible");
						logged = true;
						break;
					}else {
						if(fileScanner.hasNextLine()== false) {
							System.out.println("Usuario o contraseña erroneos, intentelo de nuevo");
							attempts++;//Suma intentos
							if(attempts >= 2) {//Pregunta intentos para continuar o no
								try {//Pregunta si quiere continuar intentandolo
									do {
										continues = Input.getString("\nLlevas "+attempts+" intentos, quieres continuar intentandolo?(S/N)");
									} while (continues.equalsIgnoreCase("S") == false && continues.equalsIgnoreCase("N") == false);
									
								} catch (Exception e) {
									System.out.println("Valor no valido");
								}
							}
						}
						user = null;
						continue;
					}
				}
				fileScanner.close();
			}catch(Exception e) {
				System.out.println("No hay base de datos");
			}
		}while(logged == false && continues.equalsIgnoreCase("S") == true);//Si cualquiera de los dos se deja de cumplir se corta el bucle
	}

	public void signup() {
		//Variables a usar
		File file = new File(FILE_PATH, USERS_FILE);//El File lo que hace es cargar el archivo txt
		FileWriter writer;//El FileWriter es por asi decirlo el que selecciona donde escribir(el escritor)
		PrintWriter writing;//El PrintWriter es lo que escribe
		String user = "";
		String password,name,nif,email,address,birthdate;
		int attempts = 0;
		String continues = "S";
		try {
			do {//Este do while es para comprobar que el usuario que quiere crear no exista previamente
				Scanner fileScanner = new Scanner(file);
				System.out.println("\n\n----Sign up----");
				user = Input.getString("Nombre de usuario: ");
				while(fileScanner.hasNextLine()) {
					String voucher = fileScanner.nextLine();
					String[] userArray = voucher.split("#");
					if(userArray[0].equals(user)== true) {
						System.out.println("Nombre de usuario ya existente, intente de nuevo");
						attempts++;
						if(attempts >= 2) {//Pregunta intentos para continuar o no
							try {//Pregunta si quiere continuar intentandolo
								do {
									continues = Input.getString("\nLlevas "+attempts+" intentos, quieres continuar intentandolo?(S/N)");
								} while (continues.equalsIgnoreCase("S") == false && continues.equalsIgnoreCase("N") == false);
								if(continues.equalsIgnoreCase("N") == true) {
									return;
								}
							} catch (Exception e) {
								System.out.println("Valor no valido");
							}
						}
						user = "";
						break;
					}else {
						continue;
					}
				}
				fileScanner.close();
			}while(user.equals("")==true);	
		}catch(Exception e) {
			System.out.println("No hay base de datos");
		}
		
		password = Input.getString("Contraseña: ");
		name = Input.getString("Nombre Completo: ");
		nif = Input.getNIF("NIF: ");
		email = Input.getEmail("Email: ");
		address = Input.getString("Direccion: ");
		birthdate = Input.getDate("Fecha de nacimiento: ");
		try {
			//Aqui es cuando se escribe en el txt
			writer = new FileWriter(file,true);//El FileWriter selecciona el archivo File, y con el true hace que lo escriba al final
			writing = new PrintWriter(writer);//El PrintWriter va a escribir donde le diga el FileWriter
			writing.println(user+"#"+password+"#"+name+"#"+nif+"#"+email+"#"+address+"#"+birthdate+"#user");
			System.out.println("----Usuario Creado Correctamente----");
			writing.close();
		}catch(Exception e){
			System.out.println("No hay base de datos");
		}
	}

	public boolean isLogged() {//Esto es para comprobar si el usuario esta loggeado
		return logged;
	}

	public void showUser() {//Esto es para mostrar el usuario, con el atributo user de tipo User
		System.out.println("\n"+user);
	}

	public void logout() {//Esto es para salirse, setea logged a false y user a null, para que se quite la informacion guardada
		logged = false;
		user = null;
	}
}
