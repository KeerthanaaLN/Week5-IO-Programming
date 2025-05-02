package Annotations_Reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@interface CacheResult {
}

class ExpensiveComputation {
    private static final Map<String, Object> cache = new HashMap<>();

    @CacheResult
    public int expensiveMethod(int input) {
        String key = "expensiveMethod_" + input;
        if (cache.containsKey(key)) {
            System.out.println("Returning cached result for input: " + input);
            return (int) cache.get(key);
        }

        System.out.println("Computing result for input: " + input);
        int result = input * input;  // Simulating an expensive computation
        cache.put(key, result);
        return result;
    }
}

public class CachingSystemTest {
    public static void main(String[] args) throws Exception {
        ExpensiveComputation computation = new ExpensiveComputation();
        
        System.out.println("Result: " + computation.expensiveMethod(5));
        System.out.println("Result: " + computation.expensiveMethod(10));
        System.out.println("Result: " + computation.expensiveMethod(5)); 
    }
}
