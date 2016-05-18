import java.util.Arrays;

/**
 * Created by user on 29.04.2016.
 */
public class LessonTwo {
    public static void main(String[] args) {
        //System.out.println(fib(6));
       // System.out.println(factorial(3));
        int[] arr = {1,2};
        permutation(arr);
    }

    public static int factorial(int a){
        return (a < 2)?(a <= 0 ? 0 : 1): a * factorial(a-1);
    }
    public static int fib(int a) {
        return a<2?1:fib(a-1)+fib(a-2);
    }


    public static void permutation(int[] arr) {
        permutation(arr,arr.length);
    }
    public static void permutation(int[] arr, int tail) {
        if (tail < 2) {
            System.out.println(Arrays.toString(arr));
        } else {
            for (int i = 0; i < tail; i++) {
                swap(arr,i,tail-1);
                permutation(arr,tail-1);
                swap(arr,tail-1,i);
            }

        }
    }
    private static void swap(int[] arr, int indexFirst, int indexSecond) {
        int tmp = arr[indexFirst];
        arr[indexFirst] = arr[indexSecond];
        arr[indexSecond] = tmp;
    }
    public static int parser(String str) {


        return 0;
    }
}
