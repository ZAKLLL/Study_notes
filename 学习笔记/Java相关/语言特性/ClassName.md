# SimpleName/Name/CanonicalName

```java
class.getName();
class.getCanonicalName();
class.getSimpleName();
```

+ **class.getName()**:

  + 返回该所表示的实体（类，接口，数组类，基本类型，或空隙）的名称Class对象，作为一个String 。
    如果此类对象表示不是数组类型的引用类型，则返回该类的二进制名称，如The Java™ Language Specification所指定。
    如果这个类对象表示一个原始类型或void，则返回的名称是一个String等于原始类型或void对应的Java语言关键字。
    如果此类对象表示一类数组，则名称的内部形式由元素类型的名称组成，前面有一个或多个代表数组嵌套深度的“ [ ”字符。 元素类型名称的编码如下：
    
  | Element Type       | Encoding    |
    | ------------------ | ----------- |
    | boolean            | Z           |
    | byte               | B           |
    | char               | C           |
    | class or interface | L classname |
    | double             | D           |
    | float              | F           |
    | int                | I           |
    | long               | J           |
    | short              | S           |
    
    类或接口名称classname是上面指定的类的二进制名称.
    
   + eg:

     ```java
     String.class.getName()
                returns "java.lang.String"
     byte.class.getName()
                returns "byte"
     (new Object[3]).getClass().getName()
                returns "[Ljava.lang.Object;"
     (new int[3][4][5][6][7][8][9]).getClass().getName()
                returns "[[[[[[[I"
     ```

+ **class.getCanonicalName()**:

  + 返回 Java 语言规范定义的基础类的规范名称。 如果底层类没有规范名称（即，如果它是本地或匿名类或者组件类型没有规范名称的数组），则返回 null.

+ **class.getSimpleName()**：

  + 返回源代码中给出的基础类的简单名称。 如果基础类是匿名的，则返回一个空字符串。数组的简单名称是附加“[]”的组件类型的简单名称。 特别是组件类型为匿名的数组的简单名称是“[]”

