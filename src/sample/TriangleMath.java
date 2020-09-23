

package sample;
import java.util.*;

public class TriangleMath {
    public static double[] triangleMath (int leg1, int leg2, int lengthHypt) {
        // creates array for future angles I find

        double[] finalAngles = new double[2];

        //should calculate the sin of the angles
        double A = (double)leg2/lengthHypt;

        double B = (double)leg1/lengthHypt;

        // makes the bigger angle across from the smaller side
        if (B>A){
            double a = A;
            A = B;
            B = a;
        }

        //finds the angle based off of the inverse of the sin
        double bigAngle = Math.asin(A);
        double smallAngle = Math.asin(B);

        //adds to the array created at the start
        finalAngles[0] = Math.toDegrees(bigAngle);
        finalAngles[1] = Math.toDegrees(smallAngle);

        //Returns the array made at the top
        return finalAngles;
    }


}
