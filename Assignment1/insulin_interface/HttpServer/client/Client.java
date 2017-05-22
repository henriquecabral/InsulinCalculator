package client;

public class Client {

	public static int backgroundInsulinDose(int  bodyWeight) {
		InsulinService service = new InsulinService();
		Insulin proxy = service.getInsulinPort();
		int unit = proxy.backgroundInsulinDose(bodyWeight);
		System.out.println("Deverá tomar: " + unit +  " units!");
		return unit;
	}
}
