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
		Integer advance = 0;
		Integer firstVal;
		Integer secondVal;
		Integer result;
		Scanner scan = new Scanner(System.in);
		while(valid) {
			switch (this.opcodes.get(current)) {
				case 1 :
					firstVal = this.opcodes.get(this.opcodes.get(current+1));
					secondVal = this.opcodes.get(this.opcodes.get(current+2));
					result = firstVal + secondVal;
					this.opcodes.set(this.opcodes.get(current+3), result);
					advance = 4;
					break;
				case 2 :
					firstVal = this.opcodes.get(this.opcodes.get(current+1));
					secondVal = this.opcodes.get(this.opcodes.get(current+2));
					result = firstVal * secondVal;
					this.opcodes.set(this.opcodes.get(current+3), result);
					advance = 4;
					break;
				case 3 :
					System.out.println("Input: ");
					result = Integer.parseInt(scan.next());
					firstVal = this.opcodes.get(this.opcodes.get(current+1));
					this.opcodes.set(firstVal, result);
					advance = 2;
					break;
				case 4 :
					firstVal = this.opcodes.get(this.opcodes.get(current+1));
					System.out.println(this.opcodes.get(firstVal));
					advance = 2;
					break;
				case 99 :
					valid = false;
					break;
				default :
					valid = false;
			}
			current = current + advance;
		}
		scan.close();
	}
	
	/*
	public Integer calculateGravity(String[] nums) {
		Boolean valid = true;
		Integer current = 0;
		Boolean noResult = true;
		Integer outerLoop = 0;
		Integer innerLoop = 0;
		
		while(noResult) {
			innerLoop = 0;
			while(innerLoop < 100) {
				valid = true;
				this.opcodes.put(1, outerLoop);
				this.opcodes.put(2, innerLoop);
				current = 0;
				while(valid) {
					Integer firstVal;
					Integer secondVal;
					Integer result;
					switch (this.opcodes.get(current)) {
						case 1 :
							firstVal = this.opcodes.get(this.opcodes.get(current+1));
							secondVal = this.opcodes.get(this.opcodes.get(current+2));
							result = firstVal + secondVal;
							this.opcodes.put(this.opcodes.get(current+3), result);
							break;
						case 2 :
							firstVal = this.opcodes.get(this.opcodes.get(current+1));
							secondVal = this.opcodes.get(this.opcodes.get(current+2));
							result = firstVal * secondVal;
							this.opcodes.put(this.opcodes.get(current+3), result);
							break;
						case 99 :
							valid = false;
							break;
						default :
							valid = false;
					}
					current = current + 4;
				}
				
				if (this.opcodes.get(0) == 19690720) {
					System.out.println("noun: " + outerLoop + " verb: " + innerLoop + " result = " + this.opcodes.get(0));
					System.out.println("answer= " + (100*outerLoop+innerLoop));
					noResult = false;
					break;
				}
				
				//reset codes
				Integer position = 0;
				for (String i : nums) {
					this.opcodes.put(position, Integer.parseInt(i));
					position++;
				}
				innerLoop++;
			}
			outerLoop++;
		}
		return 0;
	}
	*/
}
