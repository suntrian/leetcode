import org.junit.Before;
import org.junit.Test;
import util.Stack;

import java.util.HashMap;
import java.util.Map;

public class MyStackTest {

    Stack<Map> stack ;

    @Before
    public void setUp() {
        this.stack = new Stack<Map>();
    }

    @Test
    public void testPush() {
        this.stack.push(new HashMap<String, Integer>(){{put("a", 1);}});
        System.out.println(this.stack.pop());
    }

}
