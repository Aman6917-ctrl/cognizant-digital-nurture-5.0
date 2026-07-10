public class Forecast {

    public static double predictValue(double currentValue, double growthRate, int years) {

        // Base Case
        if (years == 0) {
            return currentValue;
        }

        // Recursive Case
        return predictValue(currentValue * (1 + growthRate), growthRate, years - 1);
    }

}