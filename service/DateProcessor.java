package service;

import java.util.Date;
import java.util.List;

public interface DateProcessor {
    List<String> performanceRelatedCases(final Date d1, final Date d2) throws Exception;
}
