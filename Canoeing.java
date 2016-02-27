import java.util.Random	;
//Author:Arpit Parikh
//Solving the Canoeing on the Cheap Problem through DP

public class Canoeing {
	
public static void main(String[] args)
{
        int n = 100;//no of Renting Post/
        int cost[][] = new int[n][n];//f{i,j} Renting from your Journey 
        int minCost[] = new int[n];//Finding the minimum Journey Cost

        Random random = new Random(1);//Taking Randomize input

        //This loops assign the randomize value of cost
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int var = random.nextInt(256);
                cost[i][j] = var;
                
            }
        }
        
        
        
        minCost[0] = 0;//Assigning the Minimum Cost while the input of number of post is 0
        
        
        
        //Solving through the Dynamic Programming consider as function Cost
        long startTime = System.currentTimeMillis();
        for (int i = 1; i < n; i++) {
        	int min = Integer.MAX_VALUE;//Assigning the Max Value for the consideration!
        	//System.out.println(min);
            for (int j = 0; j < i; j++)
            {
            	
            	
            	int temp = minCost[j]+cost[j][i];//Minimizing the recursion
            	//System.out.println(temp);
                
            	
            	
            	if (temp < min)
                {
                    min = temp;	
                }
            }
            minCost[i] = min;//Minimizing the recursion
            
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        //System.out.println( minCost[n - 1] );
        System.out.println(duration+"ms");
    }



}
