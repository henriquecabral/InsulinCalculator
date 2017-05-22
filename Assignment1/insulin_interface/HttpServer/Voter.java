import java.io.*;
import java.net.InetSocketAddress;
import java.lang.StringBuilder;

import java.util.*;
import java.util.concurrent.*;

public class Voter {
	
	public int carbohydrateAmount, carbohydrateToInsulinRatio, preMealBloodSugar, targetBloodSugar, personalSensitivity, bodyWeight, physicalActivityLevel, result;
	public List<Integer> physicalActivitySamples, bloodSugarDropSamples;
    public int[] results;

	Voter(int carbohydrateAmount, int carbohydrateToInsulinRatio, int preMealBloodSugar, int targetBloodSugar, int personalSensitivity)
	{
		this.carbohydrateAmount = carbohydrateAmount;
		this.carbohydrateToInsulinRatio = carbohydrateToInsulinRatio;
		this.preMealBloodSugar = preMealBloodSugar;
		this.targetBloodSugar = targetBloodSugar;
		this.personalSensitivity = personalSensitivity;
	}
	Voter(int carbohydrateAmount, int carbohydrateToInsulinRatio, int preMealBloodSugar, int targetBloodSugar, int physicalActivityLevel, List<Integer> physicalActivitySamples, List<Integer> bloodSugarDropSamples)
	{
		this.carbohydrateAmount = carbohydrateAmount;
		this.carbohydrateToInsulinRatio = carbohydrateToInsulinRatio;
		this.preMealBloodSugar = preMealBloodSugar;
		this.targetBloodSugar = targetBloodSugar;
		this.personalSensitivity = personalSensitivity;
		this.physicalActivityLevel = physicalActivityLevel;
		this.physicalActivitySamples = physicalActivitySamples;
		this.bloodSugarDropSamples = bloodSugarDropSamples;
	}
	Voter(int bodyWeight)
	{
		this.bodyWeight = bodyWeight;
	}

	public int result(int webService)
	{
        int res=-1;
		

		ExecutorService pool = Executors.newFixedThreadPool(3);
        CompletionService <Integer> threadResults = new ExecutorCompletionService<Integer>(pool);

        if(webService==2)
        {
            threadResults.submit(new GeneralThread(this.bodyWeight,webService,0));
       	    threadResults.submit(new GeneralThread(this.bodyWeight,webService,1));
       	    threadResults.submit(new GeneralThread(this.bodyWeight,webService,2));
        }
        else if(webService==0)
        {
            threadResults.submit(new GeneralThread(this.carbohydrateAmount, this.carbohydrateToInsulinRatio, this.preMealBloodSugar, this.targetBloodSugar, this.personalSensitivity,webService,0));
            threadResults.submit(new GeneralThread(this.carbohydrateAmount, this.carbohydrateToInsulinRatio, this.preMealBloodSugar, this.targetBloodSugar, this.personalSensitivity,webService,1));
            threadResults.submit(new GeneralThread(this.carbohydrateAmount, this.carbohydrateToInsulinRatio, this.preMealBloodSugar, this.targetBloodSugar, this.personalSensitivity,webService,2));
        }
        else if(webService==1)
        {
           threadResults.submit(new GeneralThread(this.carbohydrateAmount, this.carbohydrateToInsulinRatio, this.preMealBloodSugar, this.targetBloodSugar, this.physicalActivityLevel, this.physicalActivitySamples, this.bloodSugarDropSamples,webService,0));
            threadResults.submit(new GeneralThread(this.carbohydrateAmount, this.carbohydrateToInsulinRatio, this.preMealBloodSugar, this.targetBloodSugar, this.physicalActivityLevel, this.physicalActivitySamples, this.bloodSugarDropSamples,webService,1));
            threadResults.submit(new GeneralThread(this.carbohydrateAmount, this.carbohydrateToInsulinRatio, this.preMealBloodSugar, this.targetBloodSugar, this.physicalActivityLevel, this.physicalActivitySamples, this.bloodSugarDropSamples,webService,2)); 
        }
       	
      	res = voting(pool, threadResults);

        pool.shutdown();

        return res;
	}
  public int voting(ExecutorService poll, CompletionService<Integer> threadResults)
  {
    int res=-1;
    long t = 3000L;
    long init = System.currentTimeMillis();
    results = new int[3];

    for(int i=0;i<3;i++)
    {
        try
        {
            int tmp;
            results[i] = threadResults.poll(t, TimeUnit.MILLISECONDS).get();
            t=t-(System.currentTimeMillis()-init);
        
        } catch (InterruptedException e) {
            System.out.println("Error: "+ e);
        } catch (ExecutionException e) {
            System.out.println("Error: "+ e);
        }
        catch (NullPointerException e){ //TIMEOUT
            results[i] = -1;
            t=t-(System.currentTimeMillis()-init);
        }

        System.out.println("O result["+i+"] é: "+ results[i]+"!\n");
    }
    
    if(results[0]==results[1] || results[1] == results[2])
        res=results[1];
    else if(results[2]==results[0])
        res=results[2];
    else if(results[0]-results[1]==1 || results[2]-results[1]==1)
        res=results[1];
    else if(results[1]-results[0]==1 || results[2]-results[0]==1)
        res=results[0];
    else if(results[1]-results[2]==1 || results[0]-results[2]==1)
        res=results[2];

    return res;
  }
}