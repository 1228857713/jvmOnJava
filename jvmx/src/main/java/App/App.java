package App;

public class App  {
    public static void main(String[] args) {
        int i = 0;
        int [] arr = {1,2};
        set(i);
        setArr(arr);
        System.out.println(i);
        System.out.println(arr[0]);
    }

    public static void  set (int i){
        i = 8;
    }
    public static void  setArr(int []arr){
        arr[0] = 100;
    }

}

