### 四种元注解类型

1. @Documented – 表示使用该注解的元素应被javadoc或类似工具文档化，它应用于类型声明，类型声明的注解会影响客户端对注解元素的使用。如果一个类型声明添加了Documented注解，那么它的注解会成为被注解元素的公共API的一部分。

2. @Target – 表示支持注解的程序元素的种类，一些可能的值有TYPE, METHOD, CONSTRUCTOR, FIELD等等。如果Target元注解不存在，那么该注解就可以使用在任何程序元素之上。

3. @Inherited – 表示一个注解类型会被自动继承，如果用户在类声明的时候查询注解类型，同时类声明中也没有这个类型的注解，那么注解类型会自动查询该类的父类，这个过程将会不停地重复，直到该类型的注解被找到为止，或是到达类结构的顶层（Object），使用该注解后的注解只能够被标注在类上。

4. @Retention – 表示注解类型保留时间的长短，它接收RetentionPolicy参数，可能的值有SOURCE, CLASS, 以及RUNTIME。

### java提供的三种内置注解

1. @Override – 当我们想要覆盖父类的一个方法时，需要使用该注解告知编译器我们正在覆盖一个方法。这样的话，当父类的方法被删除或修改了，编译器会提示错误信息。大家可以学习一下为什么我们总是应该在覆盖方法时使用[Java覆盖注解](http://www.journaldev.com/817/overriding-methods-in-java-always-use-override-annotation)。

2. @Deprecated – 当我们想要让编译器知道一个方法已经被弃用(deprecate)时，应该使用这个注解。Java推荐在javadoc中提供信息，告知用户为什么这个方法被弃用了，以及替代方法是什么。

3. @SuppressWarnings – 这个注解仅仅是告知编译器，忽略它们产生了特殊警告，比如：在[java泛型](http://www.journaldev.com/1663/java-generics-tutorial-example-class-interface-methods-wildcards-and-much-more)中使用原始类型。它的保持性策略(retention policy)是SOURCE，在编译器中将被丢弃。



#### 注解实例

1. 类注解

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeAnno {
    String table() default "table";

}
```

2. 方法注解

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MethodAnno {
    FiledAnnoName setAnnoName() default @FiledAnnoName;

    FiledAnnoAge setAnnoAge() default @FiledAnnoAge;

    String method() default "method";
}
```

3. 字段注解(Filed)

```java
@Target(ElementType.FIELD)//全局变量
@Retention(RetentionPolicy.RUNTIME)//运行时注解
@interface FiledAnnoAge {
    int age() default 0;//注解 构造方法无参 必须有默认值
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FiledAnnoName {
    String value() default "";
}
```

#### 注解的使用

```java
@TypeAnno
public class AnnoBean {
    @FiledAnnoName("嘿嘿")
    String name = "Lucy";
    @FiledAnnoAge(age = 12)
    int age = 20;

    //value可不需要写成key=value的样式,直接写value,但是必须是value的属性.
    @MethodAnno(setAnnoName = @FiledAnnoName("方法"), setAnnoAge = @FiledAnnoAge(age = 18))
    public void testAnno() {

    }
}
```

#### 注解解析器

```java
public class AnnoCreator {

    /**
     * 解析局部变量的注解
     *
     * @throws Exception
     */
    public static void annoFiled() throws Exception {
        Class<AnnoBean> clazz = AnnoBean.class;
        //创建实例
        AnnoBean annoBean = clazz.newInstance();
        //得到类中的所有定义的属性
        for (Field filed : clazz.getDeclaredFields()) {
            //得到属性的注解，对一个目标可以使用多个注解
            Annotation[] anns = filed.getAnnotations();//得到所有注解
            if (anns.length < 1) {
                continue;
            }
            //MyAge注解分析
            if (anns[0] instanceof FiledAnnoAge) {
                FiledAnnoAge filedAnnoAge = (FiledAnnoAge) anns[0];//注解的值
                String name = filed.getName();
                Log.e("filedName_AGE", name);
                int age = filed.getInt(annoBean);//实际的值
                Log.e("AGE", age + filedAnnoAge.age() + "");//实际的值+注解的值
            }
            //MyName注解分析
            if (anns[0] instanceof FiledAnnoName) {
                FiledAnnoName filedAnnoName = (FiledAnnoName) anns[0];
                String name = filedAnnoName.value();
                String fileName = (String) filed.get(annoBean);
                String filedName = filed.getName();
                Log.e("filedName_NAME", filedName);
                Log.e("Name", name + fileName + "");

            }
        }
    }


    /**
     * 解析类的注解
     *
     * @throws Exception
     */
    public static void annoType() throws Exception {
        Class<AnnoBean> clazz = AnnoBean.class;
        TypeAnno typeAnno = clazz.getAnnotation(TypeAnno.class);//得到单个注解
        Log.e("TableAnno", typeAnno.table());
    }

    /**
     * 解析方法的注解
     *
     * @throws Exception
     */
    public static void annoMethod() throws Exception {
        Class<AnnoBean> clazz = AnnoBean.class;
        //根据反射得到方法
        Method[] methods = clazz.getMethods();
        for (Method method:methods) {
            if (method.getName().equals("testAnno")){
                MethodAnno annotation = method.getAnnotation(MethodAnno.class);
                FiledAnnoAge filedAnnoAge = annotation.setAnnoAge();
                int age = filedAnnoAge.age();
                FiledAnnoName filedAnnoName = annotation.setAnnoName();
                String name = filedAnnoName.value();
                Log.e("Method_ANno", age + name + annotation.method());
            }

        }
    }
}

```

