package hundred2;

import org.junit.Assert;

/**
 * Created by yuanxm on 1/19/17.
 */


public class _231_PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        String bs = Integer.toBinaryString(n);
        if(bs.matches("10{0,30}")){
            System.out.println(bs);
            return true;
        }
        System.out.println("false");
        return false;
    }

    public boolean isPowerOfTwo2(int n) {
        while (n>0&&n%2==0) {
            n /= 2;
        }
        return n==1;
    }


    public static void main(String[] args){
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.toBinaryString(0));
        System.out.println(Integer.toBinaryString(-0));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE+1));
        _231_PowerOfTwo p = new _231_PowerOfTwo();
        Assert.assertEquals(false, p.isPowerOfTwo2(0));
        Assert.assertEquals(true, p.isPowerOfTwo2(-2147483648));
        Assert.assertEquals( true, p.isPowerOfTwo2(1));;
        Assert.assertEquals(true, p.isPowerOfTwo2(2));;
        Assert.assertEquals(false, p.isPowerOfTwo2(3));;
        Assert.assertEquals(true, p.isPowerOfTwo2(4));;
        Assert.assertEquals(false, p.isPowerOfTwo2(7));;
        Assert.assertEquals(true, p.isPowerOfTwo2(8));;

        System.out.println(Integer.bitCount(5));
    }
}
