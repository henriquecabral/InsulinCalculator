package client;

class Client {
	public static void main(String[] args) {
		InsulinService service = new InsulinService();
		Insulin proxy = service.getInsulinPort();
		int unit = proxy.backgroundInsulinDose(94);
		System.out.println("Deverá tomar: " + unit +  " units!");
	}
}
