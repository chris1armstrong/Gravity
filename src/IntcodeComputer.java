import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class IntcodeComputer {
	private ArrayList<Integer> opcodes;
	
	public IntcodeComputer() {
		this.opcodes = new ArrayList<Integer>(); 
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		File input = new File("input2.txt");
		Scanner reader = new Scanner(input);
		
		IntcodeComputer computer = new IntcodeComputer();
		String line = reader.next();
		String[] nums = line.split(",");
		for (String i : nums) {
			computer.opcodes.add(Integer.parseInt(i));
		}
		
		reader.close();
		
		computer.performOperations();
		System.out.println("DONE");
	}

	private void performOperations() {
		Boolean valid = true;
		Integer current = 0;
		
		String fullcode;
		Integer opcode;
		Integer argMode1;
		Integer argMode2;
		//Integer argMode3;
		Integer firstVal;
		Integer secondVal;
		Integer result;
		
		Scanner scan = new Scanner(System.in);
		while(valid) {
			fullcode = String.format("%1$5d",this.opcodes.get(current)).replace(' ', '0');
			argMode1 = Integer.parseInt(fullcode.substring(2, 3));
			argMode2 = Integer.parseInt(fullcode.substring(1, 2));
			//argMode3 = Integer.parseInt(fullcode.substring(0, 1));
			opcode = Integer.parseInt(fullcode.substring(3));
			switch (opcode) {
				case 1 :
					if (argMode1 != 0) {
						firstVal = this.opcodes.get(current+1);
					} else {
						firstVal = this.opcodes.get(this.opcodes.get(current+1));
					}
					if (argMode2 != 0) {
						secondVal = this.opcodes.get(current+2);
					} else {
						secondVal = this.opcodes.get(this.opcodes.get(current+2));
					}
					result = firstVal + secondVal;
					this.opcodes.set(this.opcodes.get(current+3), result);
					current = current + 4;
					break;
				case 2 :
					if (argMode1 != 0) {
						firstVal = this.opcodes.get(current+1);
					} else {
						firstVal = this.opcodes.get(this.opcodes.get(current+1));
					}
					if (argMode2 != 0) {
						secondVal = this.opcodes.get(current+2);
					} else {
						secondVal = this.opcodes.get(this.opcodes.get(current+2));
					}
					result = firstVal * secondVal;
					this.opcodes.set(this.opcodes.get(current+3), result);
					current = current + 4;
					break;
				case 3 :
					System.out.println("Input: ");
					result = Integer.parseInt(scan.next());
					firstVal = this.opcodes.get(current+1);
					this.opcodes.set(firstVal, result);
					current = current + 2;
					break;
				case 4 :
					if (argMode1 != 0) {
						firstVal = this.opcodes.get(current+1);
					} else {
						firstVal = this.opcodes.get(this.opcodes.get(current+1));
					}
					System.out.println(firstVal);
					current = current + 2;
					break;
				case 5 :
					if (argMode1 != 0) {
						firstVal = this.opcodes.get(current+1);
					} else {
						firstVal = this.opcodes.get(this.opcodes.get(current+1));
					}
					if (argMode2 != 0) {
						secondVal = this.opcodes.get(current+2);
					} else {
						secondVal = this.opcodes.get(this.opcodes.get(current+2));
					}
					
					if (!firstVal.equals(0)) {
						current = secondVal;
					} else {
						current = current + 3;
					}
					break;
				case 6 :
					if (argMode1 != 0) {
						firstVal = this.opcodes.get(current+1);
					} else {
						firstVal = this.opcodes.get(this.opcodes.get(current+1));
					}
					if (argMode2 != 0) {
						secondVal = this.opcodes.get(current+2);
					} else {
						secondVal = this.opcodes.get(this.opcodes.get(current+2));
					}
					
					if (firstVal.equals(0)) {
						current = secondVal;
					} else {
						current = current + 3;
					}
					break;
				case 7 :
					if (argMode1 != 0) {
						firstVal = this.opcodes.get(current+1);
					} else {
						firstVal = this.opcodes.get(this.opcodes.get(current+1));
					}
					if (argMode2 != 0) {
						secondVal = this.opcodes.get(current+2);
					} else {
						secondVal = this.opcodes.get(this.opcodes.get(current+2));
					}
					
					if (firstVal.compareTo(secondVal) < 0) {
						this.opcodes.set(this.opcodes.get(current+3), 1);
					} else {
						this.opcodes.set(this.opcodes.get(current+3), 0);
					}
					current = current + 4;
					break;
				case 8 :
					if (argMode1 != 0) {
						firstVal = this.opcodes.get(current+1);
					} else {
						firstVal = this.opcodes.get(this.opcodes.get(current+1));
					}
					if (argMode2 != 0) {
						secondVal = this.opcodes.get(current+2);
					} else {
						secondVal = this.opcodes.get(this.opcodes.get(current+2));
					}
					
					if (firstVal.equals(secondVal)) {
						this.opcodes.set(this.opcodes.get(current+3), 1);
					} else {
						this.opcodes.set(this.opcodes.get(current+3), 0);
					}
					current = current + 4;
					break;
				case 99 :
					valid = false;
					break;
				default :
					valid = false;
			}
		}
		scan.close();
	}
}
