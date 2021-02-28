package aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.core.Ordered;

@Aspect
public class DeclareParentsAspectJ implements Ordered {

    @DeclareParents(value = "aspectj.AspectJTargetBeanChild", defaultImpl = SimpleExt.class)
    public AspectJExt ext;

    @Override
    public int getOrder() {
        return 2;
    }
}
