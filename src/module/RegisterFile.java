package module;
public class RegisterFile {
	private Register[] gprs;
	private int readRegisterIndex1;
	private int readRegisterIndex2;
	private int readData1;
	private int readData2;
	private int writeDataIndex;
	private int writeData;
	private int regWriteSig;

	public RegisterFile() {
		gprs = new Register[32];
		final Register zeroRegister = new Register(0, "R0");
		gprs[0] = zeroRegister;
		for (int i = 1; i < gprs.length; i++)
			gprs[i] = new Register(0, "R" + i);
	}

	public Register[] getGprs() {
		return gprs;
	}

	public void setGprs(Register[] gprs) {
		this.gprs = gprs;
	}

	public int getReadRegisterIndex1() {
		return readRegisterIndex1;
	}

	public void setReadRegisterIndex1(int readRegisterIndex1) {
		this.readRegisterIndex1 = readRegisterIndex1;
		this.readData1 = gprs[readRegisterIndex1].getData();
	}

	public int getReadRegisterIndex2() {
		return readRegisterIndex2;
	}

	public void setReadRegisterIndex2(int readRegisterIndex2) {
		this.readRegisterIndex2 = readRegisterIndex2;
		this.readData2 = gprs[readRegisterIndex2].getData();
	}

	public int getReadData1() {
		return readData1;
	}

	public void setReadData1(int readData1) {
		this.readData1 = readData1;
	}

	public int getReadData2() {
		return readData2;
	}

	public void setReadData2(int readData2) {
		this.readData2 = readData2;
	}

	public int getWriteDataIndex() {
		return writeDataIndex;
	}

	public void setWriteDataIndex(int writeDataIndex) {
		this.writeDataIndex = writeDataIndex;
	}

	public int getWriteData() {
		return writeData;
	}

	public void setWriteData(int writeData) {
		this.writeData = writeData;
	}

	public int getRegWriteSig() {
		return regWriteSig;
	}

	public void setRegWriteSig(int regWriteSig) {
		this.regWriteSig = regWriteSig;
	}

}
