/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.coe.info6205.sort.elementary;

import com.google.common.collect.Lists;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 *
 * @author MacbookPro
 */
public class InsertionSortBenchmark {

    private static final int REPETITION = 100;
    private static final int FROM = -1000;
    private static final int TO = 1000;
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        final List<Integer[]> randNumList = Lists.newArrayList(
                ArrayGenerationrandomNum(150),
                ArrayGenerationrandomNum(300),
                ArrayGenerationrandomNum(600),
                ArrayGenerationrandomNum(1200),
                ArrayGenerationrandomNum(2400),
                ArrayGenerationrandomNum(4800),
                ArrayGenerationrandomNum(9600),
                ArrayGenerationrandomNum(19200),
                ArrayGenerationrandomNum(38400),
                ArrayGenerationrandomNum(76800)
        );
        for (Integer[] randomNo : randNumList) {
            System.out.println("No of elements --> N: " + randomNo.length);
            Arrays.sort(randomNo, Collections.reverseOrder());
            benchmarkCallTimerFunction("Array In Reverse Order", randomNo);
            benchmarkCallTimerFunction("Array In Random Order", randomNo);
            Arrays.sort(randomNo, 0, (randomNo.length) / 2);
            benchmarkCallTimerFunction("Array In Partial Order", randomNo);
            Arrays.sort(randomNo);
            benchmarkCallTimerFunction("Array In Order", randomNo);
            System.out.println("----------------------------------");
        }
    }

    private static Integer[] ArrayGenerationrandomNum(int k) {
        Integer[] randomNumbers = new Integer[k];
        for (int i = 0; i < k; i++) {
            randomNumbers[i] = RANDOM.nextInt(TO + 1 - FROM) + FROM;
        }
        return randomNumbers;
    }

    public static void benchmarkCallTimerFunction(final String description, final Integer[] inArr) {
        final Consumer<Integer[]> sortUsingInsertion = InsertionSort::sort;
        final Supplier<Integer[]> supplier = () -> Arrays.copyOf(inArr, inArr.length);
        final Benchmark_Timer<Integer[]> benchmark_timer = new Benchmark_Timer<>(description, null, sortUsingInsertion, null);
        final double meanOfArraySortingTimeInMilliSeconds = benchmark_timer.runFromSupplier(supplier, REPETITION);
        System.out.println(description + " : mean sort time (in ms): " + meanOfArraySortingTimeInMilliSeconds);
    }
}
