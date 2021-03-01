package advice;

import org.springframework.aop.ThrowsAdvice;

public class WaiterWrong implements ThrowsAdvice {

    public void afterThrowing(RuntimeException e){

        System.out.println("No! " + e.getMessage());
    }
}
