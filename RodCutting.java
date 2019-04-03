/**
 * Rod cutting problem described in Chapter 15 of textbook
 * Author: Akash Sharma
 */
public class RodCutting {
  //int q = Integer.MIN_VALUE;  doesn't work globally, so locally is the way to go.
  
  // Do not change the parameters!
  public int rodCuttingRecur(int rodLength, int[] lengthPrices) { //Start
    if(!(rodLength >=1 )) return 0; //If the rod is 0 or less in size, then we can't really charge a price for it
    int q = Integer.MIN_VALUE; ////Assign q the most minium value. Makes sense to do it after the check above because if the checks fails, we did a useless computation    
    for(int i = 0; i < rodLength; i++){ //for loop going to less than rodLength to compare the lowest values
        q = Math.max(q, lengthPrices[i]+rodCuttingRecur(rodLength-i-1, lengthPrices)); // 3 parameters. Q is assigned the greatest value
      //q itself, array access with recursion, and array
         }
    return q; //price
  } //rodCuttinRecur

  // Do not change the parameters!
  public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {
    int[] arb = new int[rodLength+1]; //allocate space and add one to compensate that 0th position
    arb[0] = 0; //Position zero means rod of length 0; use of an array for this sotrage technique
    int y,t; //declaration
    int q = Integer.MIN_VALUE; ////Assign q the most minium value. Makes sense to do it after the check above because if the checks fails, we did a useless computation
    for( y = 1; y <= rodLength; y++){ //Skip 0th poisiton because there's nothing useful there 
    //Optimal solution requires looking to end of array
        for(t = 0; t < y; t++){ // as long as t is less than the value of y which is current rodLength
          q = Math.max(q, lengthPrices[t] + arb[y-t-1]); //Math.max function is run to see which value is the greatest
          arb[y] = q; // The winner is crowned 
        }
    }
    return arb[rodLength]; //access array to return that value

  }//rodCuttingBottomUp


  public static void main(String args[]){
      RodCutting rc = new RodCutting();

      // In your turned in copy, do not touch the below lines of code.
      // Make sure below is your only output.
      int length1 = 7;
      int[] prices1 = {1, 4, 7, 3, 19, 5, 12};
      int length2 = 14;
      int[] prices2 = {2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26};
      int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
      int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
      int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
      int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
      System.out.println(maxSell1Recur + " " + maxSell1Bottom);
      System.out.println(maxSell2Recur + " " + maxSell2Bottom);
  }//main

}//RodCutting 
