package Classes;
import java.util.*;
import java.io.*;
public class Maze {
	private char[][] map;
	private String filename;
	private boolean loaded;
	private int startI;
	private int startJ;
	private int endI;
	private int endJ;
	private String MAZE_PATH = ".\\assets\\files\\mazes";
	public Maze() {
		this.loaded = false;
	}
	
	public void loadMaze() {
		int opcion = 0;
		try {
			File directory = new File(MAZE_PATH);
			String[] directoryArray = directory.list();//Con el list meto en un array los archivos del directorio
			System.out.println("\nEstos son los archivos disponibles:");
			for(int i = 0;i<directoryArray.length;i++) {
				System.out.println((i+1)+"--"+directoryArray[i]);
			}
			do {
				opcion = Input.getInt("Selecciona un archivo guapisimo:",true);
				if(opcion <= 0 || opcion > directoryArray.length) {
					System.out.println("Opcion no disponible");
				}else {
					System.out.println("Muy buen, muchas gracias");
				}
			} while (opcion <= 0 || opcion > directoryArray.length);
			this.filename = directoryArray[opcion-1];
			this.loaded = true;
		} catch (Exception e) {
			System.out.println("ERROR");
		}
	}
	
	public void showMaze() {
		int count_i = 0;
		if(loaded == true) {
			try {
				File file = new File(MAZE_PATH,filename);
				Scanner readFile = new Scanner(file);
				while(readFile.hasNextLine()) {
					count_i++;
					String line = readFile.nextLine();
					String impostorLine = "";
					if(count_i == startI) {
						for(int i = 0;i<line.length();i++) {
							if(i == startJ-1) {
								impostorLine+="I";
							}else {
								impostorLine+=line.charAt(i);
							}
						}
						System.out.println(impostorLine);
						continue;
					}
					if(count_i == endI) {
						for(int i = 0;i<line.length();i++) {
							if(i == endJ-1) {
								impostorLine+="F";
							}else {
								impostorLine+=line.charAt(i);
							}
						}
						System.out.println(impostorLine);
						continue;
					}
					System.out.println(line);
				}
				readFile.close();
			} catch (Exception e) {
				
			}
			
		}else {
			System.out.println("\nCarga primero un laberinto");
		}
	}
	public void setStartEnd() {
		if(loaded == true) {
			System.out.println("\nGuapo, me vas a decir las casillas de inicio y fin:");
			do {
				this.startI = Input.getInt("Coordenada I inicio:",true);
				this.startJ = Input.getInt("Coordenada J inicio:",true);
				this.endI = Input.getInt("Coordenada I fin:",true);
				this.endJ = Input.getInt("Coordenada J fin:",true);
				if(startI == endI && startJ == endJ) {
					System.out.println("Para que pones las mismas coordenadas de inicio y fin, ponmelas otra vez anda");
				}
			}while(startI == endI && startJ == endJ);
			System.out.println("Vale, muchas gracias");
		}else {
			System.out.println("\nCarga primero un laberinto");
		}
	}
	
	
}
