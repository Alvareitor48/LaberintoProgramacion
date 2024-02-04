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
	private int[] coordinates = new int[5];
	public Maze() {
		this.loaded = false;
	}
	public void loadMaze() {
		int option = 0;
		ArrayList<char[]> list = new ArrayList<>();//Creo una lista de char
		try {
			File directory = new File(MAZE_PATH);
			String[] directoryArray = directory.list();//Con el list meto en un array los archivos del directorio
			System.out.println("\nEstos son los archivos disponibles:");
			for(int i = 0;i<directoryArray.length;i++) {
				System.out.println((i+1)+"--"+directoryArray[i]);
			}
			do {
				option = Input.getInt("Selecciona un archivo guapisimo:",true);
				if(option <= 0 || option > directoryArray.length) {
					System.out.println("Opcion no disponible");
				}else {
					System.out.println("Muy buen, muchas gracias");
				}
			} while (option <= 0 || option > directoryArray.length);
			this.filename = directoryArray[option-1];
			this.loaded = true;
			try {//Aqui despues de seleccionar el archivo creo y lo leo para guardarlo en el array doble map
				File directory2 = new File(MAZE_PATH,filename);
				Scanner sc = new Scanner(directory2);
				while(sc.hasNextLine()) {
					String line = sc.nextLine();
					char[] prov = new char[line.length()];
					for(int i = 0; i< prov.length;i++) {
						prov[i] = line.charAt(i);
					}
					list.add(prov);//Aqui añado a la lista el array de char con la linea del mapa del laberinto
				}
				this.map = new char[list.size()][list.get(0).length];//Le asigno un tamaño al map
				this.map = list.toArray(this.map);//Le meto la lista
				sc.close();
			} catch (Exception e) {
				System.out.println("ERROR 2");
			}
		} catch (Exception e) {
			System.out.println("ERROR");
		}
	}
	public void showMaze() {
		if(loaded == true) {
			for (int i = 0; i < map.length; i++) {
				if((i+1) == startI) {
					System.out.println(printLine(1, i,'I'));
				}else if((i+1) == endI) {
					System.out.println(printLine(3, i, 'F'));
				}else {
					System.out.println(printLine(4, i,'I'));
				}
			}
		}else {
			System.out.println("\nCarga primero un laberinto");
		}
	}
	public String printLine(int posC, int i,char c) {//Esto es para optimizar el proceso de mostrar el mapa por pantalla
		String impostorLine = "";
		for(int j = 0;j<map[i].length;j++) {
			if(j == coordinates[posC]-1) {
				impostorLine+=c;
			}else {
				impostorLine+=map[i][j];
			}
		}
		return impostorLine;
	}
	public void setStartEnd() {
		if(loaded == true) {
			System.out.println("\nGuapo, me vas a decir las casillas de inicio y fin:");
			do {
				this.startI = Input.getInt("Coordenada I inicio:",true);
				coordinates[0] = this.startI;
				this.startJ = Input.getInt("Coordenada J inicio:",true);
				coordinates[1] = this.startJ;
				this.endI = Input.getInt("Coordenada I fin:",true);
				coordinates[2] = this.endI;
				this.endJ = Input.getInt("Coordenada J fin:",true);
				coordinates[3] = this.endJ;
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
