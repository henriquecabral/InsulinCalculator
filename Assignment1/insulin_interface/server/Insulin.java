package server;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.xml.ws.Endpoint;


@WebService
public class Insulin implements InsulinDoseCalculator{
	@WebMethod
	public int mealtimeInsulinDose(int carbohydrateAmount, int carbohydrateToInsulinRatio, int preMealBloodSugar, int targetBloodSugar, int personalSensitivity)
	{

		if(carbohydrateAmount < 60 || carbohydrateAmount > 120 || carbohydrateToInsulinRatio < 10 || carbohydrateToInsulinRatio > 15 || preMealBloodSugar < 120 || preMealBloodSugar > 250 || targetBloodSugar < 80 || targetBloodSugar > 120 || personalSensitivity < 15 || personalSensitivity > 100)
			return -1;

		if(targetBloodSugar > preMealBloodSugar)
			return 0;

		return (int) Math.round(((double)carbohydrateAmount/carbohydrateToInsulinRatio/personalSensitivity*50)+((preMealBloodSugar-targetBloodSugar)/personalSensitivity));
	}
	@WebMethod
	public int backgroundInsulinDose(int bodyWeight)
	{
		if(bodyWeight < 40 || bodyWeight > 130)
			return -1;

		return (int) Math.round((bodyWeight*0.55)/50);
	}
	@WebMethod
	public int personalSensitivityToInsulin(int physicalActivityLevel, int[] physicalActivitySamples, int[] bloodSugarDropSamples)
	{
		if(physicalActivityLevel < 0 || physicalActivityLevel > 10 || physicalActivitySamples.length != bloodSugarDropSamples.length || bloodSugarDropSamples.length < 2 || bloodSugarDropSamples.length > 10)
			return -1;

		double beta=0, alpha=0, averageX=0, averageY=0, powX=0, multXY=0;

		// CALC AUX
		for (int i = 0; i < bloodSugarDropSamples.length; i++)
		{
			averageX+=physicalActivitySamples[i];
			averageY+=bloodSugarDropSamples[i];
			powX += Math.pow(physicalActivitySamples[i],2);
			multXY += physicalActivitySamples[i]*bloodSugarDropSamples[i]; 
		}
		
		//CALC BETA
		beta = (bloodSugarDropSamples.length*multXY-averageX*averageY)/
				(bloodSugarDropSamples.length*powX-Math.pow(averageX,2));

		//CALC ALPHA
		averageX/=physicalActivitySamples.length;
		averageY/=bloodSugarDropSamples.length;

		alpha=averageY-beta*averageX;

		return (int) Math.round(alpha+beta*physicalActivityLevel);
	}
	public static void main(String[] argv)
	{
		Object object = new Insulin();
		Endpoint endpoint = Endpoint.publish("http://10.17.1.7:8081/insulin", object);
		System.out.println("Server Up!");
  	}
	
}