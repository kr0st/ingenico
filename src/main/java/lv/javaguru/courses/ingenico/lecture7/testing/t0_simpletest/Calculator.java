package lv.javaguru.courses.ingenico.lecture7.testing.t0_simpletest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

public class Calculator {

    private static final Map<Class, Function<BigDecimal, ?>> CONVERTERS;

    static {
        CONVERTERS = new HashMap<>();
        CONVERTERS.put(Byte.class, Number::byteValue);
        CONVERTERS.put(Short.class, Number::shortValue);
        CONVERTERS.put(Integer.class, Number::intValue);
        CONVERTERS.put(Long.class, Number::longValue);
        CONVERTERS.put(Float.class, Number::floatValue);
        CONVERTERS.put(Double.class, Number::doubleValue);
        CONVERTERS.put(BigInteger.class, BigDecimal::toBigInteger);
        CONVERTERS.put(AtomicInteger.class, result -> new AtomicInteger(result.intValue()));
        CONVERTERS.put(AtomicLong.class, result -> new AtomicLong(result.longValue()));
        CONVERTERS.put(BigDecimal.class, Function.identity());
    }

    public <T extends Number> T add(T... numbers) {
        Function<T[], BigDecimal> function = (args) ->  Arrays.stream(args)
                                  .map(Number::toString)
                                  .map(BigDecimal::new)
                                  .reduce(BigDecimal.ZERO, BigDecimal::add);
        return execute(function, numbers);
    }

    private <T> T execute(Function<T[], BigDecimal> operator, T[] args) {
        Class type = args.getClass().getComponentType();
        Function<BigDecimal, ?> converter = CONVERTERS.get(type);
        if (converter == null) {
            throw new IllegalArgumentException("unsupported type, add custom converter. Type : " + type.getName());
        }
        if (args.length == 0) {
            return cast(BigDecimal.ZERO, converter);
        }
        return cast(operator.apply(args), converter);
    }

    @SuppressWarnings("unchecked")
    private <T> T cast(BigDecimal bigDecimal, Function<BigDecimal, ?> converter) {
        return (T) converter.apply(bigDecimal);
    }

}
