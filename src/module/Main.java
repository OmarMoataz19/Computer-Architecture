package module;

import java.util.Arrays;

public class Main {
	String path;
	public Computer computer;

	public Main(String path) {
		this.path = path;
	}

	public void run() {
		computer = new Computer(path);
		computer.getProcessor().run(computer.getMemory(), computer.getDataPath());
		int j = 0;
		for (int i : computer.getMemory()) {
			System.out.println("Memory " + j + ": " + i);
			computer.getDataPath().print += "Memory " + j + ": " + i + "\n";
			j++;
		}
	}

}
