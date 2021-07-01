import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import model.Pair;
import service.impl.DateProcessorImpl;

public class MultiThreading {
    private Integer performanceCaseBatchDays = 5;
    public static void main(String[] args) {
        new MultiThreading().process();
    }

    private void process() {
        Long t2 = 1624558396695L;
        Long t1 = 1622484796695L;
        List<Pair<Date, Date>> times = new ArrayList<>();
        long currentStartTime = t1;
        long DAYS_BATCH = performanceCaseBatchDays * 86400000L;
        long currentEndTime = Math.min(currentStartTime + DAYS_BATCH - 1000, t2);
        Date startTime, endTime;
        while (currentStartTime < currentEndTime) {
            startTime = new Date(currentStartTime);
            endTime = new Date(currentEndTime);
            System.out.println(startTime + ", " + endTime);
            times.add(new Pair<>(startTime, endTime));
            currentStartTime = Math.min(currentStartTime + DAYS_BATCH, t2);
            currentEndTime = Math.min(currentEndTime + DAYS_BATCH, t2);
        }
        System.out.println();
        times.forEach(System.out::println);

        //List<CompletableFuture<List<String>>> completableFutureList = new ArrayList<>();
        times.forEach(time -> {
                    CompletableFuture.supplyAsync(() -> {
                        try {
                            return new DateProcessorImpl()
                                    .performanceRelatedCases(time.getFirst(), time.getSecond());
                        }
                        catch (Exception e) {
                            System.out.println("here");
                        }
                        return null;
                    }).whenComplete((cases, ex) -> {
                        if (ex == null) {
                            System.out.println(cases);
                        }
                        else {
                            System.out.printf("Failed for task: {}, Error: {}", time, ex);
                        }
                    });
            //completableFutureList.add(eachTask);
        });
//        completableFutureList.forEach(task -> task.whenComplete((cases, ex) -> {
//            if (ex == null) {
//                System.out.println(cases);
//            }
//            else {
//                System.out.printf("Failed for task: {}, Error: {}", task, ex);
//            }
//        }));
        /*times.stream().map(time -> CompletableFuture
                .runAsync(() -> new DateProcessorImpl().performanceRelatedCases(time.getFirst(), time.getSecond()))
                .whenCompleteAsync((cases, ex) -> {
                    if (ex == null) {
                        System.out.println(cases);
                    }
                    else {
                        System.out.printf("Failed for batch: {}, Error: {}%n", time, ex);
                    }
                })).collect(Collectors.toList()).stream().map(CompletableFuture::join).collect(Collectors.toList());*/
    }
}
