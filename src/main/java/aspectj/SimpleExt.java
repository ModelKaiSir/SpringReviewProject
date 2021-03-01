package aspectj;

public class SimpleExt implements AspectJExt{

    @Override
    public void ext() {

        System.out.println("ext handle.");
    }
}
