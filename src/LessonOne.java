import java.util.Random;

/**
 * Created by user on 22.03.2016.
 */
public class LessonOne {

    public static void main(String[] args) {

        int[] arr = new int[32000];
        for (int elm = 0; elm < arr.length; elm++) {
            arr[elm] = new Random().nextInt(32000);
        }


        int[] arrForSelect = arr.clone();
        int[] arrForBubble = arr.clone();
        int[] arrForInsert = arr.clone();
        int[] arrForInsBin = arr.clone();
        int[] arrForQuq = arr.clone();
        sortBubble(arrForBubble);
        sortInsert(arrForInsert);
        sortSelect(arrForSelect);
        sortInsertBinary(arrForInsBin);
        long start = System.currentTimeMillis();
        qSort(arrForQuq);
        long time = start - System.currentTimeMillis();
        System.out.println("qSort "+time);
        //System.out.println(Arrays.toString(arr));
       // System.out.println(Arrays.toString(arrForBubble));
       // System.out.println(Arrays.toString(arrForSelect));
       // System.out.println(Arrays.toString(arrForInsert));
       // System.out.println(Arrays.toString(arrForInsBin));
       // System.out.println(Arrays.toString(arrForQuq));
       // int[] a = {0,0,1,1,2,2,10,10,11,11};
       // int[] b = {-1,0,5,9,12,33,33,33,33};
       // int[] result = margeWile(a,b);
       // System.out.println(Arrays.toString(result));

    }
    public static void qSort(int[] arr) {
        int startIndex = 0;
        int endIndex = arr.length-1;
        doQ(arr,startIndex,endIndex);
    }
    public static void doQ(int[] arr, int start, int end){
        if (start >= end) {
            return;
        }
        int leftIndex = start;
        int rightIndex = end;
        int baseIndex = (leftIndex+rightIndex)/2;
        while (leftIndex < rightIndex) {
            while (arr[baseIndex] >= arr[leftIndex] && leftIndex < baseIndex) {
                leftIndex++;
            }
            while (arr[baseIndex] <= arr[rightIndex] && rightIndex > baseIndex){
                rightIndex--;
            }
            if (leftIndex < rightIndex) {
                int temp = arr[leftIndex];
                arr[leftIndex] = arr[rightIndex];
                arr[rightIndex] = temp;
                if(leftIndex == baseIndex) {
                    baseIndex = rightIndex;
                } else if(rightIndex == baseIndex) {
                    baseIndex = leftIndex;
                }
            }
        }
        doQ(arr,start,baseIndex);
        doQ(arr,baseIndex+1,end);

    }

    public static int[] marge(int[] a, int[] b){
        int sumLength = a.length+b.length;
        int[] result = new int[sumLength];
        int aIndex = 0;
        int bIndex = 0;
        for(int i = 0; i < sumLength; i++){
            if (aIndex < a.length&&bIndex<b.length){
                result[i] = a[aIndex] < b[bIndex] ? a[aIndex++] : b[bIndex++];
            } else if(aIndex >= a.length) {
                System.arraycopy(b,bIndex,result,i,b.length-bIndex);
                return result;
            } else {
                System.arraycopy(a,aIndex,result,i,a.length-aIndex);
                return result;
            }
        }
        return result;
    }
    public static int[] margeWile(int[] a, int[] b){
        int[] result = new int[a.length+b.length];
        int aIndex = 0;
        int bIndex = 0;
        while (aIndex+bIndex != result.length){
            if (aIndex>=a.length) {
                System.arraycopy(b, bIndex, result, aIndex + bIndex, b.length - bIndex);
                return result;
            }
            if (bIndex>=b.length) {
                System.arraycopy(a, aIndex, result, aIndex + bIndex, a.length - aIndex);
                return result;
            }
            if (a[aIndex]<b[bIndex]) {
                result[aIndex+bIndex] = a[aIndex++];
            } else {
                result[aIndex+bIndex] = b[bIndex++];
            }
        }
        return result;
    }

    public static void sortSelect(int[] arr){
        long start = System.currentTimeMillis();
        for (int barrier = 0; barrier < arr.length-1; barrier++){
            int minIndex = arr.length-1;
            for (int j = arr.length-1; j > barrier; j--){
                if (arr[j-1] < arr[minIndex]) {
                    minIndex = j-1;
                }
            }
            int minValue = arr[minIndex];
            arr[minIndex] = arr[barrier];
            arr[barrier] = minValue;
        }
        long time = start - System.currentTimeMillis();
        System.out.println("selectedSort "+time);
    }
    public static void sortBubble(int[] arr) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length-1; i++) {
            boolean isSort = true;
            for (int j = arr.length-1; j > i; j--) {
                int temp = arr[j];
                if (temp<arr[j-1]){
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    isSort = false;
                }
            }
            if (isSort){
                break;
            }
        }
        long time = start - System.currentTimeMillis();
        System.out.println("BubbleSort "+time);
    }
    public static void sortInsert(int[] arr) {
        long start = System.currentTimeMillis();
        for (int barrier = 1; barrier < arr.length; barrier++) {
            int tempValue = arr[barrier];
            for (int i = barrier; i > 0; i--) {
                if (arr[i-1] > tempValue) {
                    arr[i] = arr[i-1];
                    if(i==1) {
                        arr[i-1] = tempValue;
                    }
                } else {
                    arr[i] = tempValue;
                    break;
                }
            }
        }
        long time = start - System.currentTimeMillis();
        System.out.println("InsertSort "+time);
    }
    public static void sortInsertBinary(int[] arr) {
        long start = System.currentTimeMillis();
       // System.out.println(Arrays.toString(arr));
        for(int barrier = 1; barrier < arr.length; barrier++) {
            int poss = binary(arr,0,barrier,arr[barrier]);
            int value = arr[barrier];
            if (poss < 0) {
                poss = -(poss + 1);
                System.arraycopy(arr,poss,arr,poss+1,barrier-poss);
                arr[poss] = value;
            } else {
                System.arraycopy(arr,poss,arr,poss+1,barrier-poss);
                arr[poss] = value;
            }
            //System.out.println(Arrays.toString(arr));
        }
        long time = start - System.currentTimeMillis();
        System.out.println("BinaryInsert "+time);
    }
    private static int binary(int[] arr, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex-1;
        while (low <= high) {
            int middle = (low+high)>>>1;
            int midleValue = arr[middle];
            if(key > midleValue) {
                low = middle+1;
            } else if(key < midleValue){
                high = middle-1;
            } else return  middle;
        }
        return -(low+1);
    }


    public static void westNorthPlus() {
        for (int i = 0; i < 6; i ++) {
            for (int j = 0; j < 6; j++) {
                System.out.print((i+j)<=5?"+":"-");
            }
            System.out.println();
        }
    }
    public static void westSouthPlus() {
        for (int i = 0; i < 6; i ++) {
            for (int j = 0; j < 6; j++) {
                System.out.print((i>=j)?"+":"-");
            }
            System.out.println();
        }
    }
    public static void westSoughtOnly(){
        int[] arr = {1,2,3,4,5};
        System.out.println(" ");
        System.out.println(" ");
        for (int i = 0; i < arr.length; i ++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(arr[j]);
            }
            System.out.println();
        }
    }
    private static void invert(int[] arr){
        for(int i = 0; i < arr.length/2;i++){
            int temp = arr[i];
            arr[i]=arr[arr.length-i-1];
            arr[arr.length-i-1] = temp;
        }
    }

}
