package hk.ust.aed.mtt;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 22/08/2017.
 */

public class Results {
    ArrayList<Boolean> trialIsLocation;
    ArrayList<Boolean> arrowDirectionIsLeft;
    ArrayList<Boolean> arrowLocationIsLeft;
    ArrayList<Long> latencies;
    ArrayList<Boolean> correctness;
    long unixTimeMillis = (new Date()).getTime();
    float averageCorrectnessPercentage;
    float averageLatencyMillis;

    public Results(){
        trialIsLocation = new ArrayList<>();
        arrowDirectionIsLeft = new ArrayList<>();
        arrowLocationIsLeft = new ArrayList<>();
        latencies = new ArrayList<>();
        correctness = new ArrayList<>();
    }

    public void recordAttempt(boolean trialIsLocation, boolean arrowDirectionIsLeft, boolean arrowLocationIsLeft, long latency, boolean isCorrectAnswer) {
        this.trialIsLocation.add(trialIsLocation);
        this.arrowDirectionIsLeft.add(arrowDirectionIsLeft);
        this.arrowLocationIsLeft.add(arrowLocationIsLeft);
        this.latencies.add(latency);
        this.correctness.add(isCorrectAnswer);
    }

    public void summarize() {
        if(correctness.size() == 0){
            averageCorrectnessPercentage = 0;
            averageLatencyMillis = 0;
        }
        else {
            for (int i = 0; i < correctness.size(); i++) {
                averageCorrectnessPercentage += correctness.get(i) ? 1 : 0;
                averageLatencyMillis += latencies.get(i);
            }
            averageCorrectnessPercentage = 100f * averageCorrectnessPercentage / correctness.size();
            averageLatencyMillis /= latencies.size();
        }
    }

}
