package de.twins.util;

public class DegreeHelper {

    public static double calculateDegree(double x, double y) {
        double radian = Math.atan2(y, x);
        return Math.toDegrees(radian);
    }
}
