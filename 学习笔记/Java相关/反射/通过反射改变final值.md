```java
public class Solution{
    private final static int age = 10;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Solution target = new Solution();
        Field ageField = target.getClass().getDeclaredField("age");
        ageField.setAccessible(true);
        Field modifiers = ageField.getClass().getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        //提出
        modifiers.setInt(ageField, ageField.getModifiers() & ~Modifier.FINAL);

        ageField.set(target,16);
        System.out.println(ageField.get(target));
		
        //重置 此处原理为 A&~B^B => A&~(B^B) ==> A
        modifiers.setInt(ageField, ageField.getModifiers() ^ Modifier.FINAL);
        modifiers.setAccessible(false);
        ageField.setAccessible(false);
    }

}
//output 
16
```

