package module;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import exceptions.MemoryOutOfBoundException;

public class Computer {

	int[] memory;
	Processor processor;
	DataPath dataPath;

	public Computer(String filename) {

		try {
			initializeMemory(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (MemoryOutOfBoundException e) {
			e.printStackTrace();
		}
		this.processor = new Processor();
		this.dataPath = new DataPath(this);
	}

	public DataPath getDataPath() {
		return dataPath;
	}

	public void setDataPath(DataPath dataPath) {
		this.dataPath = dataPath;
	}

	public int[] getMemory() {
		return memory;
	}

	public void setMemory(int[] memory) {
		this.memory = memory;
	}

	public void setProcessor(Processor processor) {
		this.processor = processor;
	}

	public Processor getProcessor() {
		return processor;
	}

	public void initializeMemory(String fileName) throws FileNotFoundException, MemoryOutOfBoundException {
		int[] instructions = Parser.parseInstructions(readFile(fileName));
		memory = new int[2048];
		for (int i = 0; i < instructions.length; i++)
			memory[i] = instructions[i];
	}

	public ArrayList<String[]> readFile(String path) throws FileNotFoundException {
		ArrayList<String[]> instructions = new ArrayList<String[]>();
		File file = new File(path);
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			instructions.add(scanner.nextLine().split(" "));
		}
		return instructions;
	}

}
