import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class HelloWorld {
    public static void main(String[] args) {
        new HelloWorld().process();
    }

    private void process() {
        int[] arr = new int[] {1, 2, 3, 4};
        System.out.println(findIndexesWithTarget(arr, 7 ));
    }
    // arr [], target

    public class Pair<K, V> {
        private final K first;
        private final V second;

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        public K getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }

        @Override public String toString() {
            return "Pair{" + "first=" + first + ", second=" + second + '}';
        }
    }

    private Pair<Integer, Integer> findIndexesWithTarget(int[] array, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int rem = target - array[i];
            Integer number = map.get(rem);
            if (number != null) {
                return new Pair<>(i, number);
            }
            map.putIfAbsent(array[i], i);
        }
        return new Pair<>(-1, -1);
    }


    // timestamp, functionId, String->start or stop
    /**
     *  0   0       start
     *  1   1       start
     *  2   1       stop
     *  4   0       stop
     *
     *
     *  ====================================================
     *      P0 ->1-> P0,P1->1->P0->2->P0 => P0=> 3, P1 -> 1
     *  ====================================================
     *
     *
     * 0 0 start
     * 1 1 start
     * 2 1 end
     * 3 1 start
     * 4 1 end
     * 5 0 end
     *
     *  1=> 1+1 = 2
     *  0 => 1+1+1 =3
     * =====================================================
     *
     *
     * 0 0 start
     * 1 1 start
     * 2 1 start
     * 3 1 end
     * 4 1 end
     * 5 0 end
     *
     *  0 => 1 +1
     *  1 => 1 + 2
     */

    public class Process {
        private Integer timestamp;
        private Integer id;
        private Status status;

        public Integer getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Integer timestamp) {
            this.timestamp = timestamp;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }
    }

    public enum Status {
        start,
        end
    }

    private Map<Integer, Integer> findProcessRunningTime(List<Process> processes) {
        Map<Integer, Integer> pIdTimeMap = new HashMap<>();
        Stack<Process> processStack = new Stack<>();
        Process lastProcessed = null;
        for (Process p : processes) {
            if (processStack.isEmpty()) {
                if (p.getStatus() == Status.start) {
                    processStack.add(p);
                    pIdTimeMap.putIfAbsent(p.getId(), 0);
                }
            }
            else {
                Process peek = processStack.peek();
                if (p.getStatus() == Status.start) {
                    processStack.add(p);
                    pIdTimeMap.putIfAbsent(p.getId(), 0);
                    pIdTimeMap.put(peek.getId(), p.getTimestamp() - peek.getTimestamp());
                }
                else {
                    Process pop = processStack.pop();
                    pIdTimeMap.put(pop.getId(), p.getTimestamp() - lastProcessed.getTimestamp());
                }
            }
            lastProcessed = p;
        }
        return pIdTimeMap;
    }

}
