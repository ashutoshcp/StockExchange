package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import service.DateProcessor;

public class DateProcessorImpl implements DateProcessor {
    @Override
    public List<String> performanceRelatedCases(final Date d1, final Date d2) throws Exception {
//        System.out.println("Received: " + d1 + " " + d2);
        List<String> r = new ArrayList<>();
        for (long i = d1.getTime(); i < d2.getTime(); ) {
            if (1624471996695L == i) {
                System.out.println("Exceptions");
                throw new Exception("Test Exception");
            }
            r.add(String.valueOf(i));
            i = Math.min(i + 86400000L, d2.getTime());
        }
        return r;
    }
}
