package integral;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IntegralSum integralSum = new IntegralSum();
        List<Integral> list = new ArrayList<>();
        int n = Runtime.getRuntime().availableProcessors();
        double addingValue = 1 / (double) n;
        for(double i = 0; i < 1; i+= addingValue) {
            list.add(new Integral(i, i + addingValue));
        }
        list.forEach(Thread::start);
        for (Thread thread : list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for(Integral i: list) {
            integralSum.addSum(i.getSum());
        }
        System.out.println(integralSum.getSum());
    }
}
