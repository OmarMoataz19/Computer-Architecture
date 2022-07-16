package module;

import java.util.Arrays;

public class Processor {
	private RegisterFile registerFile;
	private Register programCounter;
	private ALU alu;
	private int clockCycles = 1;

	public Processor() {
		registerFile = new RegisterFile();
		programCounter = new Register(0, "PC");
		alu = new ALU();
	}
 
	public void run(int[] memory, DataPath dataPath) {

		while (dataPath.getFetchCounter() > 0) {
			dataPath.print += "Cycle: " + clockCycles + "\n";
			System.out.println("Cycle: " + clockCycles++);
			dataPath.writeBack();
			dataPath.mem();
			dataPath.execute();
			dataPath.decode();
			dataPath.fetch();
			dataPath.print += "---------------------------------------\n";
			System.out.println("---------------------------------------");
		}
		for (int i = 0; i < 32; i++) {
			System.out.print("Register " + i + ":");
			System.out.print(registerFile.getGprs()[i].getData());
			dataPath.print += "Register " + i + ":" + registerFile.getGprs()[i].getData() + "\n";
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------");
		dataPath.print += "----------------------------------------------------------------\n";
	}

	public Register getProgramCounter() {
		return programCounter;
	}

	public void setProgramCounter(Register programCounter) {
		this.programCounter = programCounter;
	}

	public ALU getAlu() {
		return alu;
	}

	public void setAlu(ALU alu) {
		this.alu = alu;
	}

	public RegisterFile getRegisterFile() {
		return registerFile;
	}

	public void setRegisterFile(RegisterFile registerFile) {
		this.registerFile = registerFile;
	}
}
