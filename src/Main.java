/**
 * Main.java
 * Programa principal del sistema para resolver un laberinto
 * DMS - 2023.12.20
 * version 0.1.0
 */
	import Classes.*;
public class Main {
	public static Session SessionObject = new Session();
	public static int decision = 0;
	public static void main(String[] args) {
		System.out.println(Config.WELCOME);
		UNLOGGED_MENU();
	}
	
	public static void UNLOGGED_MENU() {
		
		int decision = 0;
		int count = 0;
		do {
			if(count >0) {
				Input.toContinue();
			}else {
				count++;
			}
			decision = Input.getInt(Config.UNLOGGED_MENU,true);
			switch(decision) {
				case 1:
					SessionObject.login();
					if(SessionObject.isLogged() == true) {//Confirmacion de que se ha logueado antes de cargar el menu del login
						Input.toContinue();
						LOGGED_MENU();
						decision = 0;
					}
					break;
				case 2:
					SessionObject.signup();
					System.out.println("\n");
					break;
				case 0:
					System.out.println(Config.GOODBYE);
					break;
				default:
					System.out.println("Numero no valido");
			}
			
		}while(decision != 0);
	}
	
	public static void LOGGED_MENU() {
		int decision = 0;
		do {
			decision = Input.getInt("\n\n"+Config.LOGGED_MENU, true);
			switch(decision) {
				case 1:
					System.out.println(Config.SOON);
					break;
				case 2:
					System.out.println(Config.SOON);
					break;
				case 3:
					System.out.println(Config.SOON);
					break;
				case 4:
					System.out.println(Config.SOON);
					break;
				case 5:
					SessionObject.showUser();
					continue;
				case 6:
					SessionObject.logout();
					System.out.println("\n");
					UNLOGGED_MENU();
					decision = 0;
					break;
				case 0:
					System.out.println(Config.GOODBYE);
					break;
				default:
					System.out.println("Numero no valido");
					break;
			}
		}while(decision != 0);
	}
}
