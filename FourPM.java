public class FourPM {

    public static void main(String[] args) {
        int[] array = new int[] {8, 9, 7, 12, 12, 0, 1};
        int size = 3;
        new FourPM().printFirstAndThirdSmallest(array);
    }

    /**
     * arr[] = {8, 9, 7, 12, 12, 0, 1}, l < 50
     * size = 3
     *  -100<=arr[i]<=100
     *
     *  5000
     *
     *
     */
    // [8, 9, 7, 12, 12, 0, 1]
    private int findPerfectFactorialCount(int[] array, int size) {
        int perfectFactorials = 0;

        return perfectFactorials;
    }

    /**
     * array of integer => first and 3rd smallest element
     */
    private void printFirstAndThirdSmallest(int[] array) {
        if (array == null || array.length ==0 ) {
            System.out.println("No element in array");
        } else if (array.length == 1) {
            System.out.println("First smallest: " + array[0]);
            System.out.println("Second smallest not possible");
            System.out.println("Third smallest not possible");
        } else if (array.length == 2) {
            int smallest = Math.min(array[0], array[1]);
            int secondSmallest = array[0] + array[1] - smallest;
            System.out.println("First smallest: " + smallest);
            System.out.println("Second smallest: " + secondSmallest);
            System.out.println("Third smallest not possible");
        } else {
            int smallest = Integer.MAX_VALUE;
            int secondSmallest = Integer.MAX_VALUE;
            int thirdSmallest = Integer.MAX_VALUE;
            for (int i = 0; i < array.length; i++) {
                if (array[i] < smallest) {
                    secondSmallest = smallest;
                    smallest = array[i];
                } else {
                    if (array[i] < secondSmallest) {
                        thirdSmallest = secondSmallest;
                        secondSmallest = array[i];
                    } else {
                        if (array[i] < thirdSmallest) {
                            thirdSmallest = array[i];
                        }
                    }
                }
                System.out.println(array[i] + " " + smallest + " " + secondSmallest  + " " + thirdSmallest);
            }
            System.out.println("First smallest: " + smallest);
            System.out.println("Second smallest: " + secondSmallest);
            System.out.println("Third smallest: " + thirdSmallest);
        }
    }
}
