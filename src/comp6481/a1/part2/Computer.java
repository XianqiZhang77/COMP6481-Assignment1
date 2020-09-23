package comp6481.a1.part2;

public class Computer {
	private int index;
	private String brand;
	private String model;
	private long SN;
	private double price;
	private static int totalNumber = 0;
	public Computer(String brand, String model, long sN, double price, int index) {
		this.brand = brand;
		this.model = model;
		this.SN = sN;
		this.price = price;
		this.index = index;
		totalNumber++;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public long getSN() {
		return SN;
	}
	public void setSN(long sN) {
		SN = sN;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getIndex() {
		return index;
	}
	public static int findNumberOfCreatedComputers() {
		return totalNumber;
	}
	
	public boolean equals(Object object) {
		if (this == null || object == null) {
			return false;
		} else if (getClass() != object.getClass()) {
			return false;
		} else {
			Computer computer = (Computer)object;
			return (brand.equals(computer.getBrand()) &&
					model.equals(computer.getModel()) &&
					price == computer.getPrice());
		}

	}
	
	@Override
	public String toString() {
		return "Computer #" + index + "\n"
				+ "Brand: " + brand + "\n"
				+ "Model: " + model + "\n"
				+ "SN: " + SN + "\n"
				+ "Price: $" + price;
		
	}	
	
}