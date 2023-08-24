
import java.util.stream.IntStream;

public class MiniTest_1 {
    public static void main(String[] args) {
        //Generate array angka 1-100
        int[] arr = IntStream.range(1,101).toArray();
        //looping pengecekan
        for(int i = 99 ; i >= 0 ; i--){
            //bilangan prisma
            if(prisma(arr[i])){
                continue;
            }else if(arr[i] % 3 == 0 && arr[i] % 5 == 0 ){ // bagi 3 dan 5
                System.out.print("FooBar, ");
            }else if(arr[i] % 5 == 0){ // bagi 5
                System.out.print("Bar, ");
            }else if(arr[i] % 3 == 0){ // bagi 3
                System.out.print("Foo, ");
            }else{
                System.out.print(arr[i]+ ", ");
            }
        }
    }
    private static boolean prisma(int x){
        int count =0;
        for(int i = 1; x >= i;i++){
            if(x % i == 0){
                count++;
            }
        }
        //dibagi bilangan 1 dan dirinya saja
        if (count == 2){
            return true;
        }else{
            return false;
        }
    }
}