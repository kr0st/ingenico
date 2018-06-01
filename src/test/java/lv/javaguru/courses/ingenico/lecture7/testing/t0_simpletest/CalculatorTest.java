package lv.javaguru.courses.ingenico.lecture7.testing.t0_simpletest;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAccumulator;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.*;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldAddBytes() throws Exception {
        byte result = calculator.add((byte)1, (byte)2, (byte)3);
        assertEquals(6, result);
    }

    @Test
    public void shouldAddShorts() throws Exception {
        short result = calculator.add((short)1, (short)2, (short)3);
        assertEquals(6, result);
    }

    @Test
    public void shouldAddLongs() throws Exception {
        long result = calculator.add(1L, 2L, 3L);
        assertEquals(6, result);
    }

    @Test
    public void shouldAddIntegers() throws Exception {
        int result = calculator.add(1, 2, 3);
        assertEquals(6, result);
    }

    @Test
    public void shouldAddFloats() throws Exception {
        float result = calculator.add(1.1F, 2.1F, 3.1F);
        assertThat((double)result, closeTo(6.3, 0.0001));
    }

    @Test
    public void shouldAddDoubles() throws Exception {
        double result = calculator.add(1.1, 2.1, 3.1);
        assertThat(result, closeTo(6.3, 0.0001));
    }

    // use closeTo matcher from Hamcrest to compare doubles and BigDecimal
    @Test
    public void shouldAddBigIntegers() throws Exception {
        BigInteger result = calculator.add(new BigInteger("1"), new BigInteger("2"));
        assertEquals(3, result.intValueExact());
    }

    @Test
    public void shouldAddAtomicIntegers() throws Exception {
        AtomicInteger result = calculator.add(new AtomicInteger(1), new AtomicInteger(2));
        assertEquals(3, result.get());
    }

    @Test
    public void shouldAddAtomicLongs() throws Exception {
        AtomicLong result = calculator.add(new AtomicLong(1L), new AtomicLong(2L));
        assertEquals(3L, result.get());
    }

    // use closeTo matcher from Hamcrest to compare doubles and BigDecimal
    @Test
    public void shouldAddBigDecimals() throws Exception {
        BigDecimal result = calculator.add(new BigDecimal("1.1"), new BigDecimal("2.1"));
        assertThat(result, closeTo(new BigDecimal("3.2"), new BigDecimal("0.0001")));
    }

    // simple way to check exceptional case.
    // Use this approach if no need in exception fields verification
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTypeNotSupported() throws Exception {
        calculator.add(new DoubleAccumulator(null, 0), new DoubleAccumulator(null, 2));
    }

    // check exception via Rule, the most usable approach
    // Use this approach if no need in exception fields verification
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTypeNotSupported() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("unsupported type, add custom converter. Type : java.util.concurrent.atomic.DoubleAccumulator");
        calculator.add(new DoubleAccumulator(null, 0), new DoubleAccumulator(null, 2));
    }
}
