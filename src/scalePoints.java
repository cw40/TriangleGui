public class scalePoints {

    public static int[] scaledLegs (int leg1, int leg2) {
        int scaledLegs[] = new int [2];

        double legOneScale = 200 / leg1;
        double legTwoScale = 200 / leg2;

        scaledLegs[0]= (int) legOneScale;
        scaledLegs[1]= (int) legTwoScale;




           // scaledPoints[i] = (int) (200 - (leg1 *legOneScale));
          //  xArray[i] = xArray[i] * xScale;




        return scaledLegs;
    }


}
