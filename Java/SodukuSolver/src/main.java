import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/*
 * minimalistic approach to a sudoku solver
 * just for fun
 * no academic code is to be found here (or if it is, it is an accident)
 * (c) Jeronimo Barraco Marmol 2014 GPL/v3
 * Done in 1hour, optimized and documented in like 3
 */
/*The trick in this approach is how the matrix is handled and set.
 * this is what the matrix looks like:
 * matrix is an array of submatrixes, is a 3x3 matrix of 3x3 submatrixes
 * 123 xxx yyy
 * 456 xxx yyy
 * 789 xxx yyy
 * zzz vvv bbb
 * zzz vvv bbb
 * zzz vvv bbb
 * www eee rrr
 * www eee rrr
 * www eee rrr
 * (the numbers represents a submatrix (an example) x,y,z,v,b,w,e,r are the other submatrixes)
 * the submatrixes are stored sequentially, not as matrixes, but as an array,
 * So itll actually look like this
 * [[123456789], [xxxxxxxxx], [yyyyyyyyy], [zzzzzzzzz], [vvvvvvvvv], ... , [rrrrrrrrr]] 
 * its probably not cpu/optimized (lots of div, mods and mults), and also hard to read, but its a lot easier to implement
 * (maybe "tomorrow" i'll change it ).
 * only one index rules the position using this calculation:
 * i -> x = i%3, y = i/3, P2I implements this.
 * each submatrix is another matrix of 3x3 elements.
 * the submatrix, also is implemented as an array (flattened matrix) for simplicity and coherence.
 * Each element on the submatrix represents a cell.
 * But the trick is this:
 * I dont store the "value" of the cell, that approach will make me lose a lot of time "searching" other cells for values, 
 * storing stuff and making comparations. Proably "scanning" the whole matrix time and time again.
 * Instead, each cell stores "all the possible values" it could hold. When a cell only has ONE possible value, it's automatically
 * said to be of that value (represented by the value and a "." when printed)
 * At first all the cells stores all the values (1,2,3,4,5,6,7,8 and 9).
 * As each cell receives a certain value, that value is taken from the submatrix, column, and row (just as sudoku rules states)
 * At the same time, each time a value is taken from any cell, the cell is checked to see if it ended up with one value,
 * if it only has one value (after actually removing another value) that means the cell can hold only one value, and then, 
 * that value is assigned to the cell (adding it to the stack) which in turn starts the whole mechanism again.
 * Basically it cascades to the solution as soon as all the necesary information is given.
 * I used a stack because it makes the code a little cleaner, and also, by not using recursion, i skip the possible problem
 * of a stack overflow.
 * The stack holds a list of values to set to a cell, and hence, remove elsewhere because they are being used. which triggers
 * the whole process again.
 */
 
public class main {
	public static class Pos {
		int value, x, y, subm;//x and y are cell relative.
	}
	static List<Integer> matrix[][];
	static List<Pos> stack;
	static boolean dbug = false;//use debug info
	static boolean log = true;
	//9 matrices, con 9 enteros, con 9 posibilidades, lol
	
	public static String Cell2Str(int subm, int celli){
		//convenience func 
		int x = celli%3;
		int y = celli/3;
		int submx = subm%3;
		int submy = subm/3;
		List<Integer> cell = matrix[subm][celli];
		return "sm("+submx+","+submy+") cell("+x+","+y+"): "+cell.toString();
	}
	public static void Remove(int subm, int celli, int v){
		//list.remove by default removes the position, hurray, anyway we need to do more stuff so is ok
		//be careful not to remove the last possible value 
		//See note on SetCell
		List<Integer> list= matrix[subm][celli];
		for(int i = 0; i<list.size(); i++){//avoid errors on empty cell
			if (list.get(i).equals(v)){
				list.remove(i);
				//check the cell only if removed a value
				//this magical part ensures that no infinite loops are triggered by readding a cell to the stack infinitly
				CheckCell(subm, celli);
				return; //in theory the same val shouldn't be twice on the arraylist
			}
		}
	}
	public static void initialize(){
		//initialize the matrix
		stack = new ArrayList<Pos>();
		matrix = new List[9][];
		for(int i = 0; i<9; i++){
			//initialize each submatrix
			matrix[i] = new ArrayList[9];
			for (int j=0; j<9; j++){
				//initialize each cell
				matrix[i][j] = new ArrayList<Integer>();
				for (int k = 1; k<10; k++){
					//adds a possibility to that cell
					//so it starts with the values 1,2,3,4,5,6,7,8,9
					matrix[i][j].add(k);
				}
			}
		}		
	}
	
	public static void print(){
		System.out.println("+----------+----------+----------+");
		//lol, i defy you to understand this (sober)
		for (int i=0; i<9; i++){//each row (global)
			for (int j=0; j<3; j++){//each submatrix (in column)
				System.out.print("| ");//submatrix col start
				for (int k=0; k<3;k++){//each column on the submatrix
					int smr = i/3;//submatrix (in row)
					int smrr = i%3; //submatrix row row
					//List<Integer> possibles = matrix[j+(smr*3)][k+(smrr*3)];//is just a happy coincidence that smr = i/3 and then smr*3 
					List<Integer> possibles = matrix[P2I(j, smr)][P2I(k, smrr)];//is just a happy coincidence that smr = i/3 and then smr*3
					if(possibles.size()>0){
						int tmp = possibles.get(0);
						int size = possibles.size();
						System.out.print(tmp);
						System.out.print(size>1?size:".");
					}else{
						System.out.print("E?");//because errors can happen (specially if the user is inputting values)
					}
					System.out.print(" ");
				}
			}
			System.out.println("|");//submatrix col end
			if(i%3==2){
				System.out.println("+----------+----------+----------+"); //submatrix row end
			}
		}
		System.out.println("----------------------------------\n\n");
	}
	public static void CheckCell(int subm, int submi){
		//check if a cell has only one possibility, if so, it adds it to the stack (of cells to set later)
		//be careful not to add the same cell two times. it won't remove more elements, but could create a infinite loop
		//(actually it won't but be careful anyway) (see Remove to know why)
		List<Integer> cell = matrix[subm][submi]; 
		if (cell.size()==1){
			if(log){
				System.out.println("Im a genius! i found a cell using logic!: " + Cell2Str(subm, submi) );
			}
			AddToStack(subm, submi%3, submi/3, cell.get(0));
			//read note in SetCell
		}
	}
	public static void SetCell(Pos p){
		//Sets a cell to a certain value, that in time will remove it from all the places it should
		/*Note:
		 * See CheckCell
		 * Bad part of this is that SetCell (me) can't differentiate if it's called as a result of CheckCell
		 * (in which case needs to execute the TakePos AND the cell will only have one value)
		 * or directly by user input, in which case it SHOULD have more than one possible value other way it could mean
		 * that we could take some value more than one time causing errors, and its also redundant.  
		 * i could add information for that (ie, using a class instead of a simple List.) 
		 * but that would make things much much complicated (taking in account that this is only a hack) 
		 * and given the fact that you CANT take what you already took, there SHOULD be no problem (in theory)
		 * from taking the same pos (takePos) several times (except wasting cpu and ram).
		 * "Remove" actually makes the difference, when trying to see if the cell should be added or not to the 
		 * stack. failing on that will create an infinite loop.
		 */
		List<Integer>poss = matrix[p.subm][P2I(p.x, p.y)];
		if(log){
			System.out.println("Setting the value '"+p.value+"' to the cell "+ Cell2Str(p.subm, P2I(p.x, p.y)));
		}
		//If the current possibility is the only one logically,
		//TakePos will already set this cell to the only possibility it could be
		//but heck, better to be sure than sorry
		
		//this order avoids that a) CheckCell gets called for this cell, 
		//b) The value gets removed from this cell instead of holding it
		poss.clear();
		TakePos(p);
		poss.add(p.value);
		
	}
	public static void TakePos(Pos p){
		//Take a pos (value at a point) from the equation
		//removes all occurrences of a point from the submatrix, the row and the column
		//if in the meantime a cell ends up with only one possibility it will add it to the stack to be taken later
		List<Integer> cell;
		
		//Removes it from the submatrix
		for(int i = 0; i<9; i++){
			Remove(p.subm, i, p.value);
		}
		
		int subm, submi;
		//remove it from global row
		int submc = p.subm%3;
		for(int i =0; i<9; i++){//row
			int submr = i/3;//integer division, != i/3.0*3.0
			int submrr = i%3;
			subm = P2I(submc, submr);
			submi = P2I(p.x, submrr);
			Remove(subm, submi, p.value);
		}
		
		//remove it from global col
		int submr = p.subm/3;//row for submatrix to do
		for(int i =0; i<9; i++){//col
			submc = i/3;//col for submatrix 
			int submcc = i%3;//col IN the submatrix
			
			subm=P2I(submc, submr);
			submi=P2I(submcc, p.y);
			Remove(subm,submi, p.value);
		}
	}
	
	public static int P2I(int x, int y){
		//just a simplification
		return (x+(y*3));
	}

	public static void AddToStack(int subm, int x, int y, int v){
		//does what it says
		//each Pos added to the stack will then be "Setted" to the cell when DoStack is called
		//this allows not use recursion, or simply not get a stackoverflow (.com)
		Pos p = new Pos();
		p.x = x;
		p.y = y;
		p.subm = subm;
		p.value = v;
		stack.add(p);
	}
	
	public static void Read(){
		//reads the board from the user
		print();
		char chars[];
		char c;
		for(int row = 0; row<9; row++){//row by row
			//(row+1)-> 1 based because 0 based is too complex for the client side
			String tmp = JOptionPane.showInputDialog("Input one line for the row '"+(row+1)+"':\nThe value of each cell (1 to 9) or ONE space ' ' (or a dash '-') if it is empty.\n (One after the other, without separations)\nExample '12-45--8-'(If you make a mistake, you will need to start over)");
			if (tmp==null) break;//allows to cancel
			//dont check for empty line, that is a valid input ( a line with no numbers, weird, but valid)
			//System.out.println(tmp.substring(0,1));
			int submc, submr;
			int subm;
			int x,y;
			
			System.out.println("Input: " +tmp);
			chars = tmp.toCharArray();
			
			//avoid index overflow, this part is the "client side" so, we must take precautions
			int s = chars.length<10?chars.length:9;
			
			for (int i =0; i<s; i++){ //index is needed
				c = chars[i];
				if(Character.isDigit(c)){
					//because no, i dont trust client data
					submc = i/3;
					submr = row/3;
					subm = P2I(submc, submr);
					x = i%3;
					y = row%3;
					AddToStack(subm, x, y, Character.getNumericValue(c));
				}
			}
			DoStack();
			print();
		};
		//unneeded
		System.out.println("Resultado:");
		DoStack();
		print();
	}
	
	public static boolean DoStack(){
		//each time its called it'll try to consume the whole stack setting the cells that it should
		//setcell can in turn add more stuff to the stack. so basically cascading cells. 
		//this will end when it can no longer logically assume values from the info it has.
		boolean ret = false;
		while (stack.size()>0){
			ret = true;
			Pos p = stack.get(0); //stack simulator 2.0
			stack.remove(0);
			SetCell(p);
		}
		return ret;
	}
	
	public static void Debug(){
		//example of a normal difficulty matrix
		print();
		
		AddToStack(0, 1, 0, 5);
		AddToStack(0, 0, 1, 4);
		AddToStack(0, 2, 1, 7);
		AddToStack(0, 1, 2, 1);
		
		AddToStack(1, 2, 0, 2);
		AddToStack(1, 0, 2, 4);
		
		AddToStack(2, 0, 0, 3);
		AddToStack(2, 2, 0, 1);
		AddToStack(2, 0, 1, 9);
		AddToStack(2, 1, 2, 8);
		
		
		AddToStack(3, 0, 0, 5);
		AddToStack(3, 1, 0, 9);
		AddToStack(3, 0, 2, 2);
		AddToStack(3, 1, 2, 6);
		
		AddToStack(4, 1, 0, 1);
		AddToStack(4, 0, 1, 9);
		AddToStack(4, 2, 1, 5);
		AddToStack(4, 1, 2, 4);
		
		AddToStack(5, 1, 0, 7);
		AddToStack(5, 2, 0, 4);
		AddToStack(5, 1, 2, 5);
		AddToStack(5, 2, 2, 9);
		
		AddToStack(6, 1, 0, 8);
		AddToStack(6, 2, 1, 6);
		AddToStack(6, 0, 2, 3);
		AddToStack(6, 2, 2, 1);
		
		AddToStack(7, 2, 0, 7);
		AddToStack(7, 0, 2, 2);
		
		AddToStack(8, 1, 0, 2);
		AddToStack(8, 0, 1, 5);
		AddToStack(8, 1, 1, 1);
		AddToStack(8, 2, 1, 8);
		AddToStack(8, 1, 2, 9);
		DoStack();
		System.out.println("\n--------------------------");
		print();
	}
	public static void main(String[] args) {
		initialize();
		//TODO add a menu to add cell by cell, i don't feel like it just now, i leave it as your homework
		if (dbug){
			Debug();
		}else{
			Read();
		}
	}
}
