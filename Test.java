import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by yuanxm on 1/23/17.
 */


public class Test {

    public <T> void print(T c){
        System.out.println(c);
    }
    public <T> void print(T... c){
        for(T t: c){
            System.out.println(t);
        }
    }
    public <T> void printt(T[] args){
        for(T t: args){
            System.out.println(t);
        }
    }

    public static  void main(String[] args){
//        int[] counts = new int[32];
//        for (int i =0 ; i < 32; i++) counts[i] =0;
//        System.out.println(counts[0]);
//        System.out.println('b'-'a');
//        System.out.println(Integer.toBinaryString('a'));
//
//        System.out.println(Character.toChars('d'+3));
//        int[] t = new int[5];
//        char[] c = new char[5];
//        System.out.println(Integer.valueOf(String.valueOf('2')));
//
//        Integer[] b = {1,2,3,4,5,7,8,9};
//        Arrays.sort(b, Collections.reverseOrder());
//        int i = 0;
//        while (b[i++] > 7);
//        Integer[] d = Arrays.copyOfRange(b,i,b.length);
        long start1 = System.currentTimeMillis();
        long start2 = System.nanoTime();
        System.out.println(Integer.MAX_VALUE);
        System.out.println(-Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(-(Integer.MIN_VALUE+1));
        System.out.println(Math.abs(Integer.MIN_VALUE));
        long end1 = System.currentTimeMillis();
        long end2 = System.nanoTime();
        System.out.println("1:" + (end1-start1));
        System.out.println("2:" + (end2-start2));

        Random r = new Random();
        for(int i = 0; i < 20; i++){
            System.out.println(r.nextInt(5));
        }
        Test t = new Test();
        t.print("addd");
        t.print("adb",1234,11.1);
        int[] n = {1,2,3,4,5};
        //t.printt(n);
        char[] cc = {'a','b','c','d','e'};
        //t.printt(cc);

        String s = "sbcdef";
        System.out.print(s.substring(0,6));
        Map<String, Integer> m = new HashMap<>();
        m.put("a",0);
        m.put("b",1);
        System.out.println(m.get("a").intValue());
        m.put("a",1);
        System.out.println(m.get("a").intValue());
        int i = 2;
        int j = i++;
        System.out.print(j);
        System.out.print(i);


    }
}
