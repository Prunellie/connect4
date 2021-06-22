////////////////////////////
//
//Projet final - Jeu <connect 4>
//<4R14N3-TCH4K4M>
//<22 juin 2021>
//<ICS3U>
//
///////////////////////////

package game;
//Importation du Scanner
import java.util.Scanner;
/*
RECETTE DE DESIGN

jeux connect 4
contrat: Int--->String
but: ce programme est un jeux de connect 4, l'objectif est qu'un des joueurs réussi à faire une suite de quatres de ses poins 
		soit en rangee en colonne ou en diagonale.
Exemple1:   0 1 2 3 4 5 6
			---------------
			| | | | | | | |
			---------------
			| | | | | | | |
			---------------
			|O| | | | | | |
			---------------
			|O| | | | | | |
			---------------
			|O| | | | | | |
			---------------
			|O| | | | | | |
			---------------
			 0 1 2 3 4 5 6
            
exemple2:   0 1 2 3 4 5 6
			---------------
			| | | | | | | |
			---------------
			| | | | | | | |
			---------------
			| | | | | | | |
			---------------
			| | | | | | | |
			---------------
			| | | | | | | |
			---------------
			|O|O|O|O| | | |
			---------------
			 0 1 2 3 4 5 6
			 
exemple3:	 0 1 2 3 4 5 6
			---------------
			| | | | | | | |
			---------------
			| | | | | | | |
			---------------
			| | | |O| | | |
			---------------
			| | |O| | | | |
			---------------
			| |O| | | | | |
			---------------
			|O| | | | | | |
			---------------
			 0 1 2 3 4 5 6			 
*/

//MÉTHODE
public class Connect4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//initialisation du scanner capteur
		Scanner capteur = new Scanner(System.in);
		
		//initialisation de ma grille
		char[][] grille = new char[6][7];
		
		
		for (int row = 0; row < grille.length; row++){
			for (int col = 0; col < grille[0].length; col++){
				grille[row][col] = ' ';
			}
		}
		
		//initialisation des variables
		int turn = 1;
		char joueur = 'O';
		boolean winner = false;		
		
		//boucle while jusqu'à ce qu'il y a un gagnant ou que la grille soit pleine
		while (winner == false && turn <= 42){
			boolean validPlay;
			int play;
			//boucle do while pour valider l'entrer de l'utilisateur
			do {
				//on affiche la grille 
				display(grille);
				//on demande à l'utilisateur d'entrez la colonne dans laquelle il aimerait positionner son point
				System.out.print("joueur " + joueur + ", choisi une colonne: ");
				play = capteur.nextInt();
				
				//on valide le choix du joueur
				validPlay = validate(play,grille);
				
			}while (validPlay == false);
			
			//On depose la piece du joueur dans la grille
			for (int row = grille.length-1; row >= 0; row--){
				if(grille[row][play] == ' '){
					grille[row][play] = joueur;
					break;
				}
			}
			
			//determine si il y a un gagnant
			winner = isWinner(joueur,grille);
			
			//on change du Joueur O au joueur X
			if (joueur == 'X'){
				joueur = 'O';
			}else{
				joueur = 'X';
			}
			
			turn++;			
		}
		display(grille);
		//On imprime le resultat du jeu (le nom du gagnant ou s'il y a un match nul
		if (winner){
			if (joueur=='X'){
				System.out.println("Joueur O gagne");
			}else{
				System.out.println("Joueur X gagne");
			}
		}else{
			System.out.println("Match nul");
		}
		
	}
	//Classe qui imprime la grille du jeu
	public static void display(char[][] grille){
		System.out.println(" 0 1 2 3 4 5 6");
		System.out.println("---------------");
		//boucle for dans une boucle for pour pourvoir imprimer la grille
		//par rangee
		for (int row = 0; row < grille.length; row++){
			System.out.print("|");
			//par colonne
			for (int col = 0; col < grille[0].length; col++){
				System.out.print(grille[row][col]);
				System.out.print("|");
			}
			System.out.println();
			System.out.println("---------------");
		}
		System.out.println(" 0 1 2 3 4 5 6");
		System.out.println();
	}
	
	public static boolean validate(int column, char[][] grille){
		//la colonne est-elle valide?
		if (column < 0 || column > grille[0].length){
			return false;
		}
		
		//La colonne est-elle vide?
		if (grille[0][column] != ' '){
			return false;
		}
		
		return true;
	}
	
	public static boolean isWinner(char joueur, char[][] grille){
		//on cherche pour une suite de 4 horizontalement
		for(int row = 0; row<grille.length; row++){
			for (int col = 0;col < grille[0].length - 3;col++){
				if (grille[row][col] == joueur   && 
					grille[row][col+1] == joueur &&
					grille[row][col+2] == joueur &&
					grille[row][col+3] == joueur){
					return true;
				}
			}			
		}
		//On cherche pour une suite de 4 verticalement
		for(int row = 0; row < grille.length - 3; row++){
			for(int col = 0; col < grille[0].length; col++){
				if (grille[row][col] == joueur   && 
					grille[row+1][col] == joueur &&
					grille[row+2][col] == joueur &&
					grille[row+3][col] == joueur){
					return true;
				}
			}
		}
		//on cherche pour une suite de 4 diagonalement (d'en haut)
		for(int row = 3; row < grille.length; row++){
			for(int col = 0; col < grille[0].length - 3; col++){
				if (grille[row][col] == joueur   && 
					grille[row-1][col+1] == joueur &&
					grille[row-2][col+2] == joueur &&
					grille[row-3][col+3] == joueur){
					return true;
				}
			}
		}
		//on cherche pour une suite de 4 diagonalement (d'en bas)
		for(int row = 0; row < grille.length - 3; row++){
			for(int col = 0; col < grille[0].length - 3; col++){
				if (grille[row][col] == joueur   && 
					grille[row+1][col+1] == joueur &&
					grille[row+2][col+2] == joueur &&
					grille[row+3][col+3] == joueur){
					return true;
				}
			}
		}
		return false;
	}

}