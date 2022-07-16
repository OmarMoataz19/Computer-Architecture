
package module;
public class ALU {
	private int output;
	private boolean zero;
	Processor processor;

	public ALU() {
		this.zero = false;
	}

	public void execute(int opcode, int rd, int rs, int rt, int immediate, int shamt) { 
		if (opcode == 0) {
			output = rs + rt; // add
		} else if (opcode == 1) {
			output = rs - rt; // sub
		} else if (opcode == 2) {
			output = rs * immediate; // multimmediate
		} else if (opcode == 3) {
			output = rs + immediate; // addi
		} else if (opcode == 4) {
			output = rd - rs;// bne

			// System.out.println();

		} else if (opcode == 5) {
			output = rs & immediate; // andi
		} else if (opcode == 6) {
			output = rs | immediate; // orI

		} else if (opcode == 7) {
			// jump do nothing here
		} else if (opcode == 8) {
			output = rs << shamt; // shiftleftlogical

		} else if (opcode == 9) {
			output = rs >>> shamt; // shiftrightlogical (unsigned)????
		} else if (opcode == 10) {
			output = rs + immediate; // loadword
		}

		else if (opcode == 11) {
			output = rs + immediate; // storeword

		}

		if (output == 0) {
			zero = true;
		} else {
			zero = false;
		}
		System.out.println("output: " + output);
		System.out.println("Zero: " + zero);
	}

	public Processor getProcessor() {
		return processor;
	}

	public void setProcessor(Processor processor) {
		this.processor = processor;
	}

	public int getOutput() {
		return output;
	}

	public void setOutput(int output) {
		this.output = output;
	}

	public boolean isZero() {
		return zero;
	}

	public void setZero(boolean zero) {
		this.zero = zero;
	}

}
