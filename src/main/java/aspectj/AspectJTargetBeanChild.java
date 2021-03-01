package aspectj;

public class AspectJTargetBeanChild extends AspectJTargetBean {

    public int child() {

        System.out.println("AspectJTargetBeanChild");
        return 100;
    }

    public void test(int i) {

        if (i < 0) {
            throw new IllegalArgumentException("args < 0! ");
        } else {

            throw new RuntimeException("Bitch。");
        }
    }
}
