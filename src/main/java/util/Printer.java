package util;

public class Printer {

    public static <T> void print(T[] a){
        StringBuilder builder = new StringBuilder();
        for (int i =0; i < a.length; i++){
            builder.append(a[i]);
            builder.append(i==a.length-1?"":",");
        }
        System.out.println(builder.toString());
    }
}
