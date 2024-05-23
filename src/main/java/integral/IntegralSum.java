package integral;

public class IntegralSum implements Summator{
    private double totalSum;
    @Override
    public void addSum(double sum) {
        totalSum += sum;
    }

    @Override
    public double getSum() {
        return totalSum;
    }
}
