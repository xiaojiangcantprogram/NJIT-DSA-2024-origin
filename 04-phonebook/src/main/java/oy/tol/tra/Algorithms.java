package oy.tol.tra;

import java.util.function.Predicate;
import java.util.Comparator;

public class Algorithms<K extends Comparable<K>, V, T> {

    private T[] array = null;

    public void Array(T[] array) {
        this.array = array;
        for (int counter = 0; counter < array.length; counter++) {
            this.array[counter] = array[counter];
        }
    }

    public static <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    T tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }

    public static <T> void reverse(T[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            T temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }

    public static <T> void swap(T[] array, int first, int second) {
        T tem = array[first];
        array[first] = array[second];
        array[second] = tem;
    }

    public static <K extends Comparable<K>, V> void fastSort(Pair<K, V>[] array) {
        @SuppressWarnings("unchecked")
        Pair<K, V>[] temp = (Pair<K, V>[]) new Pair[array.length];
        quickSort(array, temp, 0, array.length - 1);
    }

    private static <K extends Comparable<K>, V> void quickSort(Pair<K, V>[] array, Pair<K, V>[] temp, int left,
            int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            quickSort(array, temp, left, mid);
            quickSort(array, temp, mid + 1, right);
            partition(array, temp, left, mid, right);
        }
    }

    private static <K extends Comparable<K>, V> void partition(Pair<K, V>[] array, Pair<K, V>[] temp, int left, int mid,
            int right) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (array[i].getKey().compareTo(array[j].getKey()) <= 0) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }
        for (i = left; i <= right; i++) {
            array[i] = temp[i];
        }
    }

    public T[] getArray() {
        return array;
    }

    public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            return -1;
        }
        int mid = fromIndex + (toIndex - fromIndex) / 2;
        int comparison = aValue.compareTo(fromArray[mid]);
        if (comparison == 0) {
            return mid;
        } else if (comparison < 0) {
            return binarySearch(aValue, fromArray, fromIndex, mid - 1);
        } else {
            return binarySearch(aValue, fromArray, mid + 1, toIndex);
        }
    }

    public static <K extends Comparable<K>, V> int partitionByRule(Pair<K, V>[] array, int size,
            Predicate<Pair<K, V>> rule) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (!rule.test(array[i])) {
                Pair<K, V> temp = array[i];
                array[i] = array[index];
                array[index] = temp;
                index++;
            }
        }
        return index;
    }

    public static <T> void sortWithComparator(T[] array, Comparator<T> comparator) {
        fastSortWithComparator(array, comparator, 0, array.length - 1);
    }

    private static <T> void fastSortWithComparator(T[] array, Comparator<T> comparator, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionWithComparator(array, comparator, left, right);
            fastSortWithComparator(array, comparator, left, pivotIndex - 1);
            fastSortWithComparator(array, comparator, pivotIndex + 1, right);
        }
    }

    private static <T> int partitionWithComparator(T[] array, Comparator<T> comparator, int left, int right) {
        T pivot = array[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, right);
        return i + 1;
    }

}
