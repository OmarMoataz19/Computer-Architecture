package module;
public class Register {
	private int data;
	private String name;

	public Register(int data, String name) {
		this.data = data;
		this.name = name;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		if (!getName().equals("R0"))
			this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}