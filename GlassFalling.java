/**
 * Glass Falling
 * Author: Akash Sharma
 */
public class GlassFalling {
  // Do not change the parameters!
  public int glassFallingRecur(int floors, int sheets) {
    // Fill in here and change the return
    if(floors <= 1) return floors; //If the value of floors is 1 or less, than going further is pointless because 1 floor or less is all you cna test
    if (sheets == 1)  return floors; //If only one glass sheet is available and we want to be absolutely sure of getting the right result, we can only try from the first floor and move up one by one. 

        int minValue = Integer.MAX_VALUE; //assignment of great value in java so we can outcompete any value passed in
        int x, res = 0; //Declaration and instaniation to 0
    for (x = 1; x <= floors; x++)  {
        int Broken= glassFallingRecur(x-1, sheets-1); //Note how sheets-1: every call reduces the sheets and based on the current value using the number floors we decrease
        int Survive = glassFallingRecur(floors - x, sheets);  // 
        res = Math.max(Broken, Survive);  //Check which of the values is bigger using a math function whichc hecks wich value is greater
        if (minValue > res) minValue = res;  // minValue has to be the greatest value. This checks allows us update minValue to see
    }//for  
    return minValue + 1;  //minVaue that is found up there is returned and plus 1 to account for the level passed
  }//GlassFalling 

//   // Optional:
//   // Pick whatever parameters you want to, just make sure to return an int.
//   public int glassFallingMemoized() {
//     // Fill in here and change the return
//     return 0;
//   }

  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {
     int[][] trials = new int[sheets+1][floors+1]; //) 0 sheets in a no go, floors taken care of below.. Array to assist.
     int res =0; 
     int g, j, x,sG,fS; //declaration of for loop variables and more

    //  if(floors <= 0) return 0; //If the value of floors is 0 or less, than going further is pointless because 1 floor or less is all you cna test
    //  if (sheets == 0)  return 0; //If only zero glass sheet is available and we want to be absolutely sure of getting the right result, we can only try from the first floor and move up one by one. 
        
     // To settle the peripheries of the 2d array
     for (g = 1; g <= sheets; ++g) { 
        trials[g][1] = 1; // If the amount of floors to be tested is 1, then mostly likely all we need is one trial
        trials[g][0] = 0; //If the amount of floors to be tested is 0, then guess how many trials we can do
    } //for
      // With only 1 sheet of glass, we'll have to test floors in ascending manner
      for (j = 1; j <= floors; ++j) 
      trials[1][j] = j; //[sheets][floor] == [1 sheet][j floors]
     //triple foor loop
     for ( sG = 2; sG <= sheets; ++sG) { //Start the for loop at 2 because we did the 1 sheet of glass 
         for (fS = 2; fS <= floors; ++fS) { //Start at 2 because we did floor 0 and floor 1
             trials[sG][fS] = Integer.MAX_VALUE; //We assign the greatest value here so we can compare it later. Obviously nothing can top this value
             for (x = 1; x <= fS; ++x) { //Using this array to help in calculation of what is the max value
                  res = 1 + Math.max(trials[sG-1][x-1], trials[sG][fS-x]);  // math function to see the greater of the two values
                  if (res < trials[sG][fS]) // Obviosuly, the value must be less
                     trials[sG][fS] = res; //Assign to this position the value o fthe minimum value "res"
             }//for 3 
         } //for 2
     } //for 1
     return trials[sheets][floors];  //Access the array and 2nd lastmost value. Works hand in hand with the +1
  }//glassFallingBottomUp
  


  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();

      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Recur = gf.glassFallingRecur(100, 3);
    //   int minTrials2Memo = gf.glassFallingMemoized(100, 3, third);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Recur + " " + minTrials2Bottom);
    //   System.out.println(minTrials2Memo + " " + minTrials2Bottom);
  }//main

}//GlassFalling
