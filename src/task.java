import java.util.Random;
import java.util.Scanner;

public class task {

    public static int Max;

    public static void main (String[] args){
        Scanner in = new Scanner(System.in);

        System.out.print("Input size pancake array (max 1000): ");

        int num = checkingIntInput(0, 1001);
//        int num = 6;
        int array[] = new int[num];

        System.out.print("if you want to fill the array yourself, enter \"yes\": ");
        String genAns = in.next();
        if(genAns.equals("yes") ){
            userFeildArr(array);
        }else {
            randArrGen(array);
        }

//        int[] array = {1, 2, 3, 6, 6, 9, 17, 18, 6};

        printArr(array);
        System.out.println();
        bruteRecForceMax(array);
        System.out.print("the maximum weight, you can make: " + task.Max);
    }

    static void bruteRecForceMax(int[] arr){

        permute(arr);

        if (arr.length > 1){
            for (int i = 0; i < arr.length; i++){
                bruteRecForceMax(removeIdChar(arr, i));
            }
        }
    }

    static int bruteOneTime(int[] arr){
        for (int i = 0; i < arr.length; i++){
            int sumBefore = 0;
            for (int j = 0; j < i + 1; j++){
                sumBefore += arr[j];
            }
            int sumAfetr = 0;
            for (int j = i + 1; j < arr.length; j++){
                sumAfetr += arr[j];
            }
            if(sumAfetr == sumBefore){
                return sumAfetr*2;
            }
        }
        return 0;
    }

    static int[] removeIdChar(int[] arr, int id){
        int outArr[] = new int[arr.length - 1];
        for (int i = 0, j = 0; i < arr.length; i++){
            if (i != id){
                outArr[j] = arr[i];
                j++;
            }
        }
        return outArr;
    }

    public static void permute(int[] arr){
        permuteHelper(arr, 0);
    }

    public static void permuteHelper(int[] arr, int index){
        if(index >= arr.length - 1){
            int Max = bruteOneTime(arr);
            if (Max > task.Max) task.Max = Max;
//            printArr(arr);
//            System.out.println( " " + Max);
            return;
        }

        for(int i = index; i < arr.length; i++){
            //For each index in the sub array arr[index...end]
            //Swap the elements at indices index and i
            int t = arr[index];
            arr[index] = arr[i];
            arr[i] = t;

            //Recurse on the sub array arr[index+1...end]
            permuteHelper(arr, index+1);

            //Swap the elements back
            t = arr[index];
            arr[index] = arr[i];
            arr[i] = t;
        }
    }

    static void userFeildArr (int[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.print("pancake " + (i + 1) + " = ") ;
            arr[i] = checkingIntInput(0, 21);
        }
        System.out.println("array entered");
    }

    static void randArrGen(int[] arr){
        Random rnd = new Random (System.currentTimeMillis());
        int defaultMin = 1;
        int defaultMax = 20;
        for (int i = 0; i < arr.length; i++){
            arr[i] = defaultMin + rnd.nextInt(defaultMax - defaultMin + 1);

        }
        System.out.println("array generated");
    }

    static void printArr(int[] arr){
        System.out.print("< ");
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.print(">");

    }

    static int checkingIntInput(int st, int fn){
        while (true){
            Scanner in = new Scanner(System.in);
            int out = in.nextInt();
            if (out > st && out < fn){
                return out;
            }else {
                System.out.print("out of range! try agin: ");
            }
        }
    }


}
