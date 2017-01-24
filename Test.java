import java.util.Arrays;
import java.util.Collections;

/**
 * Created by yuanxm on 1/23/17.
 */
public class Test {
    public static  void main(String[] args){
        int[] counts = new int[32];
        for (int i =0 ; i < 32; i++) counts[i] =0;
        System.out.println(counts[0]);
        System.out.println('b'-'a');
        System.out.println(Integer.toBinaryString('a'));

        System.out.println(Character.toChars('d'+3));
        int[] t = new int[5];
        char[] c = new char[5];
        System.out.println(Integer.valueOf(String.valueOf('2')));

        Integer[] b = {1,2,3,4,5,7,8,9};
        Arrays.sort(b, Collections.reverseOrder());
        int i = 0;
        while (b[i++] > 7);
        Integer[] d = Arrays.copyOfRange(b,i,b.length);


    }
}
