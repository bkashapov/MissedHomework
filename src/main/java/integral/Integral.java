package integral;

import lombok.Getter;

public class Integral extends Thread{
    private double start;
    private double end;
    private static final double N = 1000;
    @Getter
    private double sum;
    public Integral(double start, double end) {
        this.start = start;
        this.end = end;
        sum = 0;
    }

    @Override
    public void run() {
        double addingValue = (end - start) / N;
        for(double i = start; i < end; i += addingValue) {
            sum += (F.function(i) + F.function(i + addingValue)) * (0.5 * addingValue);
        }

    }
}
