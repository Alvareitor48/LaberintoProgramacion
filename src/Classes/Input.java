package Classes;

import java.util.Scanner;
import java.util.*;

public class Input {
	private static Scanner keyboard = new Scanner(System.in);
	private static final String INT_ERROR = "\nEl valor introducido no es un numero";
	private static final String DNI_ERROR = "\nEl formato del DNI es erroneo";
	private static final String EMAIL_ERROR = "\nEl formato del DNI es erroneo";
	private static final String DATE_ERROR = "\nEl formato de la fecha es erroneo";
	private static final String CONTINUE = "\nPulse 'enter' para continuar"; 
	
	// Sin enunciado
	public static int getInt() {
		int number = -1;
		try {
			number = Integer.parseInt(keyboard.nextLine().trim());
		} catch (Exception e) {
			System.out.println(INT_ERROR);
		}
		return number;
	}

	// Con enunciado
	public static int getInt(String text) {
		int number = -1;
		System.out.print(text);
		try {
			number = Integer.parseInt(keyboard.nextLine().trim());
		} catch (Exception e) {
			System.out.println(INT_ERROR);
		}
		return number;
	}
	
	// Con enunciado, recibe un boleano para repetir o no hasta que sea valido
	public static int getInt(String text, boolean repeat) {
		int number = -1;
		boolean success = false;
		int count = 0;
		System.out.print(text);
		do {
			try {
				number = Integer.parseInt(keyboard.nextLine().trim());
				success = true;
			} catch (Exception e) {
				if(count == 0) {
					count++;
					continue;
				}
				System.out.println(INT_ERROR);
				if(!repeat) {
					return -1;
				}
			}
		} while(!success);
		return number;
	}

	// Sin enunciado
	public static String getString() {
		String value = keyboard.nextLine().trim();
		return value;
	}

	// Con enunciado
	public static String getString(String text) {
		System.out.print(text);
		return keyboard.nextLine().trim();
	}

	// Metodo para que pida una accion para continuar
	public static void toContinue() {
		System.out.print(CONTINUE);
		try {
			System.in.read();
		} catch (Exception e) {
			// Excepcion
		}
	}
	public static String getNIF(String text) {
		String textF = "";
		boolean success = false;
		System.out.print(text);
		do {
			textF = keyboard.nextLine();
			success = textF.matches("^[0-9]{8}[A-Z]$");
			if(!success) {
				System.out.println(DNI_ERROR);
			}
		} while(!success);
		return textF;
	}
	
	public static String getEmail(String text) {
		String textF = "";
		boolean success = false;
		System.out.print(text);
		do {
			textF = keyboard.nextLine();
			success = textF.matches("^[0-9A-Za-z_-]+@[0-9A-Za-z-.]+\\.[A-Za-z_-]{2,}$");
			if(!success) {
				System.out.println(EMAIL_ERROR);
			}
		} while(!success);
		return textF;
	}
	
	public static String getDate(String text) {
		String textF = "";
		boolean success = false;
		System.out.print(text);
		do {
			textF = keyboard.nextLine();
			success = textF.matches("^(0?[1-9]|[12][0-9]|3[01])[\\/](0?[1-9]||1[0-2])[\\/]([12][0-9])\\d{2}$");
			if(!success) {
				System.out.println(DATE_ERROR);
			}
		} while(!success);
		return textF;
	}
}

