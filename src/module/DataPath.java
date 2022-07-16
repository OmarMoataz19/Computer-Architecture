package module;

import java.util.HashMap;
import java.util.Stack;

public class DataPath {
	private int currentInstruction;
	private Computer computer;
	private boolean fetchFlag;
	private boolean memFlag;
	private boolean writeBackFlag;
	private HashMap<String, Integer> IF;
	private HashMap<String, Integer> ID;
	private HashMap<String, Integer> EX;
	private HashMap<String, Integer> MEM;
	private boolean branchFlag = false;
	private int decodeCounter = 0;
	private int executeCounter = 0;
	private static boolean done = false;
	private boolean lastFetch = false;
	private int fetchCounter = 6;
	public String print = "";
	private Stack temp;
	private int branchCount = 0;
	public DataPath(Computer computer) {
		this.computer = computer;
		this.fetchFlag = true;
		this.memFlag = false;
		this.writeBackFlag = false;
		this.IF = new HashMap<String, Integer>();
		this.ID = new HashMap<String, Integer>();
		this.EX = new HashMap<String, Integer>();
		this.MEM = new HashMap<String, Integer>();
		this.temp = new Stack();

	}

	public void fetch() {	
	 if (!branchFlag) {
			if (computer.getProcessor().getProgramCounter().getData() < Parser.numberOfInstructions) {
				if (fetchFlag) {
					if (computer.getProcessor().getProgramCounter().getData() == Parser.numberOfInstructions-1) {
						lastFetch = true;
					}
					print += "Instruction in Fetch: Instruction "
							+ computer.getProcessor().getProgramCounter().getData() + "\n";
					System.out.println("Instruction in Fetch: Instruction "
							+ computer.getProcessor().getProgramCounter().getData() + "\n");
					System.out.println("PC value: " + computer.getProcessor().getProgramCounter().getData());
					print += "PC value: " + computer.getProcessor().getProgramCounter().getData() + "\n";
					IF.put("instruction",
							(computer.getMemory()[computer.getProcessor().getProgramCounter().getData()]));
					IF.put("PColdValue", computer.getProcessor().getProgramCounter().getData());
					computer.getProcessor().getProgramCounter()
							.setData((computer.getProcessor().getProgramCounter().getData() + 1));// increment the pc
					System.out.println(
							"PC value after incrementation: " + computer.getProcessor().getProgramCounter().getData());
					print += "PC value after incrementation: " + computer.getProcessor().getProgramCounter().getData()
							+ "\n";
					this.fetchFlag = false;
					this.decodeCounter = 2;
				}
			} else {
				done = true;
			}
		}
	}

	public void decode() {
		if (!branchFlag) {
			if (decodeCounter == 2) {
				if (lastFetch && fetchCounter == 6)
					fetchCounter--;
				int PColdValue = IF.get("PColdValue");
				print += "Instruction in Decode FirstTime: Instruction " + PColdValue + "\n";
				System.out.println("Instruction in Decode FirstTime: Instruction " + PColdValue);
				int instruction = IF.get("instruction");
				int opcode = (instruction & (0b1111 << 28)) >> 28;
				opcode = opcode & 15;
				int rd = (instruction & (0b11111 << 23)) >> 23;
				int rs = (instruction & (0b11111 << 18)) >> 18;
				int rt = (instruction & (0b11111 << 13)) >> 13;
				int immediate = instruction & (0b111111111111111111);
				int temp=instruction & 0b0100000000000000000;
	            temp=temp>>17;
	            if (temp==1) {
	            immediate=immediate|0b11111111111111000000000000000000;
	            }
				int label = instruction & (0b1111111111111111111111111111);
				int shamt = instruction & (0b1111111111111);
				computer.getProcessor().getRegisterFile().setReadRegisterIndex1(rs);
				computer.getProcessor().getRegisterFile().setReadRegisterIndex2(rt);
				print += "Decode Inputs\n";
				System.out.println("Decode Inputs");
				print += "opcode: " + opcode + "\n";
				System.out.println("opcode: " + opcode);
				print += "rs: " + rs + "\n";
				System.out.println("rs: " + rs);
				print += "rt: " + rt + "\n";
				System.out.println("rt: " + rs);
				print += "rd: " + rd + "\n";
				System.out.println("rd: " + rt);
				print += "imm: " + immediate + "\n";
				System.out.println("imm: " + immediate);
				print += "label: " + label + "\n";
				System.out.println("label: " + label);
				print += "shamt: " + shamt + "\n";
				System.out.println("shamt:" + shamt);

				ID.put("opcode", opcode);
				ID.put("readData1", computer.getProcessor().getRegisterFile().getReadData1());
				ID.put("readData2", computer.getProcessor().getRegisterFile().getReadData2());
				ID.put("immediate", immediate);
				ID.put("rd", rd);
				ID.put("label", label);
				ID.put("shamt", shamt);
				ID.put("PColdValue", PColdValue);

			}
			if (decodeCounter == 1) {
				if (lastFetch && fetchCounter == 5)
					fetchCounter--;
				this.fetchFlag = true;
				this.executeCounter = 2;
				int PColdValue = IF.get("PColdValue");
				print += "Instruction in Decode SecondTime: Instruction " + PColdValue + "\n";
				System.out.println("Instruction in Decode SecondTime: Instruction " + PColdValue);
				int instruction = IF.get("instruction");
				int opcode = (instruction & (0b1111 << 28)) >> 28;
				opcode = opcode & 15;
				int rd = (instruction & (0b11111 << 23)) >> 23;
				int rs = (instruction & (0b11111 << 18)) >> 18;
				int rt = (instruction & (0b11111 << 13)) >> 13;
				int immediate = instruction & (0b111111111111111111);
				int label = instruction & (0b1111111111111111111111111111);
				int shamt = instruction & (0b1111111111111);
				computer.getProcessor().getRegisterFile().setReadRegisterIndex1(rs);
				computer.getProcessor().getRegisterFile().setReadRegisterIndex2(rt);
				print += "Decode Inputs\n";
				System.out.println("Decode Inputs");
				print += "opcode: " + opcode + "\n";
				System.out.println("opcode: " + opcode);
				print += "rs: " + rs + "\n";
				System.out.println("rs: " + rs);
				print += "rt: " + rt + "\n";
				System.out.println("rt: " + rs);
				print += "rd: " + rd + "\n";
				System.out.println("rd: " + rt);
				print += "imm: " + immediate + "\n";
				System.out.println("imm: " + immediate);
				print += "label: " + label + "\n";
				System.out.println("label: " + label);
				print += "shamt: " + shamt + "\n";
				System.out.println("shamt:" + shamt);
			}

			this.decodeCounter--;

		}
	}

	public void execute() {
		if (this.executeCounter == 2) {
			if (lastFetch && fetchCounter == 4)
				fetchCounter--;
			int PColdValue = ID.get("PColdValue");
			print += "Instruction in Execute FirstTime: Instruction " + PColdValue + "\n";
			System.out.println("Instruction in Execute FirstTime: Instruction " + PColdValue);
			int opcode = ID.get("opcode");
			int readData1 = ID.get("readData1");// rs
			int readData2 = ID.get("readData2");// rt
			int immediate = ID.get("immediate");
			int label = ID.get("label");
			int shamt = ID.get("shamt");
			int rd = ID.get("rd");
			temp.add(rd);
			temp.add(shamt);
			temp.add(label);
			temp.add(immediate);
			temp.add(readData2);
			temp.add(readData1);
			temp.add(opcode);
			temp.add(PColdValue);
			print += "Execute Inputs\n";
			System.out.println("Execute Inputs");
			print += "opcode: " + opcode + "\n";
			System.out.println("opcode: " + opcode);
			print += "rd: " + rd + "\n";
			System.out.println("rd: " + rd); // check de
			print += "readData1: " + readData1 + "\n";
			System.out.println("readData1: " + readData1);
			print += "readData2: " + readData2 + "\n";
			System.out.println("readData2: " + readData2);
			print += "imm: " + immediate + "\n";
			System.out.println("imm: " + immediate);
			print += "label: " + label + "\n";
			System.out.println("label: " + label);
			print += "shamt: " + shamt + "\n";
			System.out.println("shamt:" + shamt);
			computer.getProcessor().getAlu().execute(opcode,
					computer.getProcessor().getRegisterFile().getGprs()[rd].getData(), readData1, readData2, immediate,
					shamt); // check logic for rd later
			EX.put("opcode", opcode);
			EX.put("ALUOutput", computer.getProcessor().getAlu().getOutput());
			EX.put("rd", rd);
			EX.put("PColdValue", PColdValue);
			if (opcode == 4) {// BNE
				if (!computer.getProcessor().getAlu().isZero()) {
					computer.getProcessor().getProgramCounter().setData(computer.getProcessor().getProgramCounter().getData() + immediate - 1); //check de
					System.out.println("PC: " + computer.getProcessor().getProgramCounter().getData());
					this.branchFlag = true;
					this.branchCount++;
					if(lastFetch==true) {
					this.lastFetch=false;
					this.fetchCounter=6; 
					}
				}
			} else if (opcode == 7) { // jump
				int tmp = computer.getProcessor().getProgramCounter().getData() - 1;// old pc;
				tmp = tmp & (0b1111 << 28);
				tmp = tmp | (label); // check this part (label -1)
				computer.getProcessor().getProgramCounter().setData(tmp);
				this.branchFlag = true;
				this.branchCount++;
				if(lastFetch == true) {
				this.lastFetch=false;
				this.fetchCounter=6;
				}
			}
		}
		if (executeCounter == 1) {
			if (lastFetch && fetchCounter == 3)
				fetchCounter--;
			this.memFlag = true;
			int PColdValue = (int) temp.pop();
			print += "Instruction in Execute SecondTime: Instruction " + PColdValue + "\n";
			System.out.println("Instruction in Execute SecondTime: Instruction " + PColdValue);
			int opcode = (int) temp.pop();
			int readData1 = (int) temp.pop();
			int readData2 = (int) temp.pop();
			int immediate = (int) temp.pop();
			int label = (int) temp.pop();
			int shamt = (int) temp.pop();
			int rd = (int) temp.pop();
			print += "Execute Inputs\n";
			System.out.println("Execute Inputs");
			print += "opcode: " + opcode + "\n";
			System.out.println("opcode: " + opcode);
			print += "rd: " + rd + "\n";
			System.out.println("rd: " + rd); // check de
			print += "readData1: " + readData1 + "\n";
			System.out.println("readData1: " + readData1);
			print += "readData2: " + readData2 + "\n";
			System.out.println("readData2: " + readData2);
			print += "imm: " + immediate + "\n";
			System.out.println("imm: " + immediate);
			print += "label: " + label + "\n";
			System.out.println("label: " + label);
			print += "shamt: " + shamt + "\n";
			System.out.println("shamt:" + shamt);
			EX.put("PColdValue", PColdValue);
			if(branchFlag) {
				branchCount++;
			}
			
		}
		this.executeCounter--;

	}

	public void mem() {
	
		if (memFlag) {
			if(branchFlag) {
				branchCount++;
			}
			if (lastFetch && fetchCounter == 2)
				fetchCounter--;
			int PColdValue = EX.get("PColdValue");
			print += "Instruction in MEM: Instruction " + PColdValue + "\n";
			System.out.println("Instruction in MEM: Instruction " + PColdValue);
			int opcode = EX.get("opcode");
			int ALUOutput = EX.get("ALUOutput");
			int rd = EX.get("rd");
			print += "MEM Inputs\n";
			print += "opcode: " + opcode + "\n";
			print += "ALUOutput: " + ALUOutput + "\n";
			print += "rd: " + rd + "\n";
			System.out.println("MEM inputs: ");
			System.out.println("opcode: " + opcode);
			System.out.println("ALUOutput: " + ALUOutput);
			System.out.println("rd: " + rd);
			int memoryLoad = 0;
			if (opcode == 10) {// lw
				int address = ALUOutput;
				memoryLoad = computer.getMemory()[address];
				print += "address: " + address + "\n";
				print += "memoryLoad: " + memoryLoad + "\n";
				System.out.println("address: " + address);
				System.out.println("memoryLoad: " + memoryLoad);

			} else if (opcode == 11) {// sw
				int address = ALUOutput;
				int rdData = computer.getProcessor().getRegisterFile().getGprs()[rd].getData();
				computer.getMemory()[address] = rdData;
				print += "address: " + address + "\n";
				print += "memoryLoad: " + rdData + "\n";
				System.out.println("address: " + address);
				System.out.println("memoryStore: " + rdData);

			}
			MEM.put("rd", rd);
			MEM.put("memoryLoad", memoryLoad);
			MEM.put("opcode", opcode);
			MEM.put("ALUOutput", ALUOutput);
			MEM.put("PColdValue", PColdValue);
			this.memFlag = false;
			this.writeBackFlag = true;

		}

	}

	public void writeBack() {
		if (writeBackFlag) {
			if (lastFetch && fetchCounter == 1)
				fetchCounter--;
			int PColdValue = MEM.get("PColdValue");
			print += "Instruction in writeBack: Instruction " + PColdValue + "\n";
			System.out.println("Instruction in writeBack: Instruction " + PColdValue);
			print += "writeBack inputs: \n";
			System.out.println("writeBack inputs: ");
			int opcode = MEM.get("opcode");
			int rd = MEM.get("rd");
			int memoryLoad = MEM.get("memoryLoad");
			int ALUOutput = MEM.get("ALUOutput");
			print += "rd: " + rd + "\n";
			print += "memoryLoad: " + memoryLoad + "\n";
			print += "ALUOutput: " + ALUOutput + "\n";
			print += "opcode: " + opcode + "\n";
			System.out.println("rd: " + rd);
			System.out.println("memoryLoad: " + memoryLoad);
			System.out.println("ALUOutput: " + ALUOutput);
			System.out.println("opcode: " + opcode);
			if (opcode == 0 || opcode == 1 || opcode == 2 || opcode == 3 || opcode == 5 || opcode == 6 || opcode == 8
					|| opcode == 9) {
				computer.getProcessor().getRegisterFile().getGprs()[rd].setData(ALUOutput);

			} else if (opcode == 10) {// lw
				computer.getProcessor().getRegisterFile().getGprs()[rd].setData(memoryLoad);
			} else if (opcode == 11) {

			}
			print += "Register Value at rd after everything: "+ computer.getProcessor().getRegisterFile().getGprs()[rd].getData()+"\n";
			System.out.println("Register Value at rd after everything: "
					+ computer.getProcessor().getRegisterFile().getGprs()[rd].getData());
			this.writeBackFlag = false;
			if (branchFlag && branchCount==3) {
				if (opcode == 4 || opcode == 7) {
					this.fetchFlag = true;
					this.branchFlag = false;
					this.decodeCounter = 0;
					this.executeCounter = 0;
					this.memFlag = false;
					this.writeBackFlag = false;
					branchCount = 0;
				} else {
					this.fetchFlag = true;
				}
			}
		}
	}

	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}

	public boolean isFetchFlag() {
		return fetchFlag;
	}

	public void setFetchFlag(boolean fetchFlag) {
		this.fetchFlag = fetchFlag;
	}

	public boolean isMemFlag() {
		return memFlag;
	}

	public void setMemFlag(boolean memFlag) {
		this.memFlag = memFlag;
	}

	public boolean isWriteBackFlag() {
		return writeBackFlag;
	}

	public void setWriteBackFlag(boolean writeBackFlag) {
		this.writeBackFlag = writeBackFlag;
	}

	public HashMap<String, Integer> getIF() {
		return IF;
	}

	public void setIF(HashMap<String, Integer> iF) {
		IF = iF;
	}

	public HashMap<String, Integer> getID() {
		return ID;
	}

	public void setID(HashMap<String, Integer> iD) {
		ID = iD;
	}

	public HashMap<String, Integer> getEX() {
		return EX;
	}

	public void setEX(HashMap<String, Integer> eX) {
		EX = eX;
	}

	public HashMap<String, Integer> getMEM() {
		return MEM;
	}

	public void setMEM(HashMap<String, Integer> mEM) {
		MEM = mEM;
	}

	public int getDecodeCounter() {
		return decodeCounter;
	}

	public void setDecodeCounter(int decodeCounter) {
		this.decodeCounter = decodeCounter;
	}

	public int getExecuteCounter() {
		return executeCounter;
	}

	public void setExecuteCounter(int executeCounter) {
		this.executeCounter = executeCounter;
	}

	public int getCurrentInstruction() {
		return currentInstruction;
	}

	public void setCurrentInstruction(int currentInstruction) {
		this.currentInstruction = currentInstruction;
	}

	public boolean isBranchFlag() {
		return branchFlag;
	}

	public void setBranchFlag(boolean branchFlag) {
		this.branchFlag = branchFlag;
	}

	public static boolean isDone() {
		return done;
	}

	public static void setDone(boolean done) {
		DataPath.done = done;
	}

	public int getFetchCounter() {
		return fetchCounter;
	}

	public void setFetchCounter(int fetchCounter) {
		this.fetchCounter = fetchCounter;
	}

	public boolean isLastFetch() {
		return lastFetch;
	}

	public void setLastFetch(boolean lastFetch) {
		this.lastFetch = lastFetch;
	}

	public String getBinaryValue(int value, int lengthExpected) {
		String str = Integer.toBinaryString(value) + "";
		for (int i = str.length(); i < lengthExpected; i++) {
			str = "0" + str;
		}
		return str;
	}
}
