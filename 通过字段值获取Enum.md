```java
public class Test {
    enum TestEnum {
        E1(123, "ABC"),
        E2(1234, "ABCD");

        public int a;
        public String b;

        TestEnum(int a, String b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "TestEnum{" +
                    "a=" + a +
                    ", b='" + b + '\'' +
                    '}';
        }
    }

    public static <T, E> T getTargetEnum(Class<T> c, String fieldName, E fieldValue) throws Exception {
        Method values = c.getMethod("values");
        T[] allEnum = (T[]) values.invoke(null);
        for (T t : allEnum) {
            Class<?> aClass = t.getClass();
            Field field = aClass.getField(fieldName);
            Object o = field.get(t);
            if (o == null) {
                break;
            }
            if (o.getClass() == fieldValue.getClass()) {
                if (((E) o).hashCode() == fieldValue.hashCode()) {
                    return t;
                }

            }
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            TestEnum targetEnum = getTargetEnum(TestEnum.class, "b", "AB1CD");
            System.out.println(targetEnum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```