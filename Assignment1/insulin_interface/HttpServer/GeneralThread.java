import java.util.concurrent.Callable;
import java.util.List;

public class GeneralThread implements Callable <Integer> {

	public int carbohydrateAmount, carbohydrateToInsulinRatio, preMealBloodSugar, targetBloodSugar, personalSensitivity, bodyWeight, physicalActivityLevel, result;
	public List<Integer> physicalActivitySamples, bloodSugarDropSamples;

	public int method, webService;
	
	public GeneralThread(int carbohydrateAmount, int carbohydrateToInsulinRatio, int preMealBloodSugar, int targetBloodSugar, int personalSensitivity, int method, int webService)
	{
		this.carbohydrateAmount = carbohydrateAmount;
		this.carbohydrateToInsulinRatio = carbohydrateToInsulinRatio;
		this.preMealBloodSugar = preMealBloodSugar;
		this.targetBloodSugar = targetBloodSugar;
		this.personalSensitivity = personalSensitivity;
		this.method = method;
		this.webService = webService;
	}
	public GeneralThread(int carbohydrateAmount, int carbohydrateToInsulinRatio, int preMealBloodSugar, int targetBloodSugar, int physicalActivityLevel, List<Integer> physicalActivitySamples, List<Integer>bloodSugarDropSamples, int method, int webService)
	{
		this.carbohydrateAmount = carbohydrateAmount;
		this.carbohydrateToInsulinRatio = carbohydrateToInsulinRatio;
		this.preMealBloodSugar = preMealBloodSugar;
		this.targetBloodSugar = targetBloodSugar;
		this.physicalActivityLevel = physicalActivityLevel;
		this.physicalActivitySamples = physicalActivitySamples;
		this.bloodSugarDropSamples = bloodSugarDropSamples;
		this.method = method;
		this.webService = webService;
	}
	public GeneralThread(int bodyWeight, int method, int webService)
	{
		this.bodyWeight = bodyWeight;
		this.method = method;
		this.webService = webService;
	}

	@Override
	public Integer call() throws Exception {
		if(method==0) //Standard
		{
			if(webService==0)
			{
				client.InsulinService service = new client.InsulinService();
				client.Insulin proxy = service.getInsulinPort();
				//Thread.sleep(3000);
				return proxy.mealtimeInsulinDose(this.carbohydrateAmount, this.carbohydrateToInsulinRatio, this.preMealBloodSugar, this.targetBloodSugar, this.personalSensitivity);
			}
			else if(webService==1)
			{
				ws2.InsulinDoseCalculatorService service = new ws2.InsulinDoseCalculatorService();
				ws2.InsulinDoseCalculator proxy = service.getInsulinDoseCalculatorPort();
				return proxy.mealtimeInsulinDose(this.carbohydrateAmount, this.carbohydrateToInsulinRatio, this.preMealBloodSugar, this.targetBloodSugar, this.personalSensitivity);
			}
			else if(webService==2)
			{
				ws3.InsulinDoseCalculatorService service = new ws3.InsulinDoseCalculatorService();
				ws3.InsulinDoseCalculator proxy = service.getInsulinDoseCalculatorPort();
				return proxy.mealtimeInsulinDose(this.carbohydrateAmount, this.carbohydrateToInsulinRatio, this.preMealBloodSugar, this.targetBloodSugar, this.personalSensitivity);
			}
			return -1;	
		}
		else if (method==1) //Custom
		{
			if(webService==0)
			{
				client.InsulinService service = new client.InsulinService();
				client.Insulin proxy = service.getInsulinPort();
				return proxy.mealtimeInsulinDose(this.carbohydrateAmount, this.carbohydrateToInsulinRatio, this.preMealBloodSugar, this.targetBloodSugar, proxy.personalSensitivityToInsulin(this.physicalActivityLevel, this.physicalActivitySamples, this.bloodSugarDropSamples));
			}
			else if(webService==1)
			{
				ws2.InsulinDoseCalculatorService service = new ws2.InsulinDoseCalculatorService();
				ws2.InsulinDoseCalculator proxy = service.getInsulinDoseCalculatorPort();
				return proxy.mealtimeInsulinDose(this.carbohydrateAmount, this.carbohydrateToInsulinRatio, this.preMealBloodSugar, this.targetBloodSugar, proxy.personalSensitivityToInsulin(this.physicalActivityLevel, this.physicalActivitySamples, this.bloodSugarDropSamples));
			}
			else if(webService==2)
			{
				ws3.InsulinDoseCalculatorService service = new ws3.InsulinDoseCalculatorService();
				ws3.InsulinDoseCalculator proxy = service.getInsulinDoseCalculatorPort();
				return proxy.mealtimeInsulinDose(this.carbohydrateAmount, this.carbohydrateToInsulinRatio, this.preMealBloodSugar, this.targetBloodSugar, proxy.personalSensitivityToInsulin(this.physicalActivityLevel, this.physicalActivitySamples, this.bloodSugarDropSamples));
			}
			return -1;	
		}
		else if(method==2) //Background
		{
			if(webService==0)
			{
				client.InsulinService service = new client.InsulinService();
				client.Insulin proxy = service.getInsulinPort();
				//Thread.sleep(3000);
				return proxy.backgroundInsulinDose(this.bodyWeight);
			}
			else if(webService==1)
			{
				ws2.InsulinDoseCalculatorService service = new ws2.InsulinDoseCalculatorService();
				ws2.InsulinDoseCalculator proxy = service.getInsulinDoseCalculatorPort();
				return proxy.backgroundInsulinDose(this.bodyWeight);
			}
			else if(webService==2)
			{
				ws3.InsulinDoseCalculatorService service = new ws3.InsulinDoseCalculatorService();
				ws3.InsulinDoseCalculator proxy = service.getInsulinDoseCalculatorPort();
				return proxy.backgroundInsulinDose(this.bodyWeight);
			}
		}
		return -1;
	}

}