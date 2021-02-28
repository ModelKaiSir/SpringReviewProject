package aspectj;

@AspectJEnable
public class AspectJTargetBean implements AspectJTarget {

    @Override
    public void start() {

        System.out.println("aspectJ start");
    }

    public void beginTo() {
        System.out.println("beginTo");
    }

    public void beginTo(String name) {

        System.out.println("beginTo name: " + name);
    }

    public void setTarget(AspectJTargetBean object) {

        System.out.println("setTarget object: " + object);
    }

    public void endTo(int id, String name) {

        System.out.println("endTo id : " + id + " name : " + name);
    }
}
