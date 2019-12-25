import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Gravity {
	private HashMap<Integer,Integer> opcodes;
	
	public Gravity() {
		this.opcodes = new HashMap<Integer,Integer>(); 
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		File input = new File("input.txt");
		Scanner reader = new Scanner(input);
		
		Gravity system = new Gravity();
		Integer position = 0;
		String line = reader.next();
		String[] nums = line.split(",");
		for (String i : nums) {
			system.opcodes.put(position, Integer.parseInt(i));
			position++;
		}
		
		reader.close();
		System.out.println(system.calculateGravity(nums));
	}
	
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
							System.out.println("Done");
							valid = false;
							break;
						default :
							System.out.println("You have made a dumb");
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
}
