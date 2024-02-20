import com.demo.Hello;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class HelloTest {
    @Test
    public void testSayHello() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(os, true);

        Hello hi = new Hello();
        hi.sayHello(stream);

        assertThat(os.toString(), is(equalTo(String.format("%s%s", "Hello!", System.lineSeparator()))));
    }

    @Test
    public void testSayHelloAFewTimes() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(os, true);

        Hello hi = new Hello();
        hi.setTimes(3);
        hi.sayHello(stream);

        // Does it say "Hello!" three times?
        String goal = String.format("%1$s%2$s%1$s%2$s%1$s%2$s", "Hello!", System.lineSeparator());
        assertThat(os.toString(), is(equalTo(goal)));
    }

    @Test
    public void testIllegalArgumentForHelloTooMuch() {
        Hello hi = new Hello();
        assertThrows(IllegalArgumentException.class, () -> hi.setTimes(Hello.MAXIMUM_AMOUNT_OF_TIMES + 1));
    }

    @Test
    public void testIllegalArgumentForHelloNegative() {
        Hello hi = new Hello();
        assertThrows(IllegalArgumentException.class, () -> hi.setTimes(-1));
    }
}
