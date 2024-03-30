package oy.tol.tra;

public class Algorithm {
        public static <T extends Comparable<T>> void sort(T [] array) {
// implementation here...

            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j].compareTo(array[j + 1]) > 0) {
                        T temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }

        // ...
        public static <T> void reverse(T [] array) {
// implementation here...
            int start = 0;
            int end = array.length - 1;
            while (start < end) {
                T temp = array[start];
                array[start] = array[end];
                array[end] = temp;
                start++;
                end--;
            }

        }
    }
}
