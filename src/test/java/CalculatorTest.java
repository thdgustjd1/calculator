import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;

class CalculatorTest {

    static Calculator cal;

    @BeforeEach
    void init(){
        Scanner scanner = new Scanner(System.in);
        cal = new Calculator(scanner);
    }


    @AfterEach
    void fin(){
        cal = null;
    }

    @Test
    void testAdd(){
        int a = 1;
        int b = 2;
        String input1 = "23";
        String input2 = "10,20";
        String input3 = "10,20:30";
        String input4 = "100,100,100";
        String input5 = "0,00,001";
        assertThat(cal.add(a,b)).isEqualTo(a+b); // test
        assertThat(cal.add(b,a)).isEqualTo(a+b); // 교환법칙
        assertThat(cal.add(input1)).isEqualTo(23);
        assertThat(cal.add(input2)).isEqualTo(30);
        assertThat(cal.add(input3)).isEqualTo(60);
        assertThat(cal.add(input4)).isEqualTo(300);
        assertThat(cal.add(input5)).isEqualTo(1);
    }

    @Test
    void testSubtract(){
        int a = 5;
        int b = 10;
        assertThat(cal.subtract(a,b)).isEqualTo(a-b); // test
    }

    @Test
    void testMultiply(){
        int a = 5;
        int b = 10;
        assertThat(cal.multiply(a,b)).isEqualTo(a*b); // test
        assertThat(cal.multiply(b,a)).isEqualTo(a*b); // 교환법칙
        assertThat(cal.multiply(a,0)).isEqualTo(0); // a*0
        assertThat(cal.multiply(0,a)).isEqualTo(0); // 0*a
    }

    @Test
    void testDivide(){
        int a = 5;
        int b = 2;
        assertThat(cal.divide(a,b)).isEqualTo(a/b);
    }

    @Test
    void testDivideByZero(){
        int a = 5;
        assertThatExceptionOfType(ArithmeticException.class)
                .isThrownBy(() -> cal.divide(a, 0));
    }

    @Test
    void testCalValidInput() {
        String input = "10+20-5";
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
        cal = new Calculator(sc);
        int result = cal.cal();
        assertThat(result).isEqualTo(25);
    }

    @Test
    void testCalInvalidInput() {
        String input = "10+20+";
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
        cal = new Calculator(sc);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> cal.cal())
                .withMessage("Invalid input");
    }
}