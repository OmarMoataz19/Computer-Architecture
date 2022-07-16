package module;
import java.util.ArrayList;

public class Parser {
	public static int numberOfInstructions;

	public static int[] parseInstructions(ArrayList<String[]> instructions) {
		int parsedInstructions[] = new int[instructions.size()];
		int i = 0;
		for (String[] instruction : instructions) {
			int parsed = 0;
			switch (instruction[0]) {
			case "ADD":
				parsed |= (0b0000 << 28);// R
				parsed |= Integer.parseInt(instruction[1].substring(1)) << 23;
				parsed |= Integer.parseInt(instruction[2].substring(1)) << 18;
				parsed |= Integer.parseInt(instruction[3].substring(1)) << 13;
				break;

			case "SUB":
				parsed |= (0b0001 << 28);// R
				parsed |= Integer.parseInt(instruction[1].substring(1)) << 23;
				parsed |= Integer.parseInt(instruction[2].substring(1)) << 18;
				parsed |= Integer.parseInt(instruction[3].substring(1)) << 13;
				break;

			case "MULI":
				parsed |= (0b0010 << 28);
				parsed |= Integer.parseInt(instruction[1].substring(1)) << 23;
				parsed |= Integer.parseInt(instruction[2].substring(1)) << 18;
				parsed |= (Integer.parseInt(instruction[3])&0b0111111111111111111);
				break;

			case "ADDI":
				parsed |= (0b0011 << 28);
				parsed |= Integer.parseInt(instruction[1].substring(1)) << 23;
				parsed |= Integer.parseInt(instruction[2].substring(1)) << 18;
				parsed |= (Integer.parseInt(instruction[3])&0b0111111111111111111);
				break;

			case "BNE":
				parsed |= (0b0100 << 28);
				parsed |= Integer.parseInt(instruction[1].substring(1)) << 23;
				parsed |= Integer.parseInt(instruction[2].substring(1)) << 18;
				parsed |= (Integer.parseInt(instruction[3])&0b0111111111111111111);
				break;

			case "ANDI":
				parsed |= (0b0101 << 28);
				parsed |= Integer.parseInt(instruction[1].substring(1)) << 23;
				parsed |= Integer.parseInt(instruction[2].substring(1)) << 18;
				parsed |= (Integer.parseInt(instruction[3])&0b0111111111111111111);
				break;

			case "ORI":
				parsed |= (0b0110 << 28);
				parsed |= Integer.parseInt(instruction[1].substring(1)) << 23;
				parsed |= Integer.parseInt(instruction[2].substring(1)) << 18;
				parsed |= (Integer.parseInt(instruction[3])&0b0111111111111111111);
				break;

			case "J":
				parsed |= (0b0111 << 28);
				parsed |= Integer.parseInt(instruction[1]);
				break;

			case "SLL":
				parsed |= (0b1000 << 28);// R
				parsed |= Integer.parseInt(instruction[1].substring(1)) << 23;
				parsed |= Integer.parseInt(instruction[2].substring(1)) << 18;
				parsed |= Integer.parseInt(instruction[3]);
				break;

			case "SRL":
				parsed |= (0b1001 << 28);// R
				parsed |= Integer.parseInt(instruction[1].substring(1)) << 23;
				parsed |= Integer.parseInt(instruction[2].substring(1)) << 18;
				parsed |= Integer.parseInt(instruction[3]);
				break;

			case "LW":
				parsed |= (0b1010 << 28);
				parsed |= Integer.parseInt(instruction[1].substring(1)) << 23;
				parsed |= Integer.parseInt(instruction[2].substring(1)) << 18;
				parsed |= Integer.parseInt(instruction[3]);
				break;

			case "SW":
				parsed |= (0b1011 << 28);
				parsed |= Integer.parseInt(instruction[1].substring(1)) << 23;
				parsed |= Integer.parseInt(instruction[2].substring(1)) << 18;
				parsed |= Integer.parseInt(instruction[3]);
				break;

			default:
			}
			parsedInstructions[i++] = parsed;
		}
		//parsedInstructions[i] = 0;
		numberOfInstructions = parsedInstructions.length;
		return parsedInstructions;

	}

}
