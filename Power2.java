/**
 * Created by yuanxm on 1/19/17.
 */


public class Power2 {
    public boolean isPowerOfTwo(int n) {
        String bs = Integer.toBinaryString(n);
        if(bs.matches("10{0,30}")){
            System.out.println(bs);
            return true;
        }
        System.out.println("false");
        return false;
    }

    public static void main(String[] args){
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.toBinaryString(0));
        System.out.println(Integer.toBinaryString(-0));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE+1));
        Power2 p = new Power2();
        p.isPowerOfTwo(-2147483648);
        p.isPowerOfTwo(1);
        p.isPowerOfTwo(2);
        p.isPowerOfTwo(3);
        p.isPowerOfTwo(4);
        p.isPowerOfTwo(7);
        p.isPowerOfTwo(8);

        System.out.println(Integer.bitCount(5));
    }
}
