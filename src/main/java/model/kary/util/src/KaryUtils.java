import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Optional;

/**
 *
 * @author Kary.Qiu
 */
public class KaryUtils {

    public static <T> T getValue(T input, T def) {

        return Optional.ofNullable(input).orElseGet(() -> def);
    }

    public static BigDecimal getBigDecimal(BigDecimal input) {

        return getValue(input, BigDecimal.ZERO);
    }

    /**
     * 生成祖父类的对应方法句柄
     *
     * @param methodType 需要调用的方法Type
     * @param methodName 需要调用的方法名
     * @param grandClass 祖父类
     * @return
     * @throws Exception
     */
    public static MethodHandle generateGrandSource(MethodType methodType, String methodName, Class<?> grandClass) throws Exception {

        // 获得 impl_lookup 并取消权限检查
        Field IMPL_LOOKUP = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
        IMPL_LOOKUP.setAccessible(true);
        MethodHandles.Lookup lkp = (MethodHandles.Lookup) IMPL_LOOKUP.get(null);
        return lkp.findSpecial(grandClass, methodName, methodType, grandClass);
    }

    public static void main(String[] args) {

        System.out.println(KaryUtils.getValue(null, BigDecimal.ZERO));
    }
}
