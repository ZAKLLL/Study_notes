#  设计模式



###  装饰器模式

 1. 定义接口

    ```java
    public interface Component {
        void doSomeThing();
    }
    ```

2. 具体构建角色

    ```java
    public class ConcreateCompnent implements Component {
        @Override
        public void doSomeThing() {
            System.out.println("A功能");
        }
    }
    ```

3. 声明装饰器

    ```java
    public class Decorator implements Component {
        private Component component;
    
        Decorator(Component component) { 
            this.component = component;
        }
        @Override
        public void doSomeThing() {
            component.doSomeThing();
        }
    }
    
    ```

4. 装饰类的具体实现

    ```java
    public class ConcratorDecorator1 extends Decorator {
    
        ConcratorDecorator1(Component component) { //必须传入具体构建角色才能够装饰
            super(component);
    
        }
        @Override
        public void doSomeThing() { //在保留父类方法的同时拓展新的方法
            super.doSomeThing();
            this.doAnotherThing();
        }
        public void doAnotherThing() { //拓展的新方法
            System.out.println("功能B");
        }
    }
    
    public class ConcratorDecorator2 extends Decorator {
        ConcratorDecorator2(Component component) {
            super(component);
        }
        @Override
        public void doSomeThing() {  
            super.doSomeThing();
            this.doAnotherThing();
    
        }
        public void doAnotherThing() {
            System.out.println("功能C");
        }
    }
    
    ```

5. Test

    ```java
    Component component = new ConcratorDecorator2(new ConcratorDecorator1(new ConcreateCompnent())); //具体构建对象new ConreateComponent（）同时具有了装饰器1和在装饰器2所提供的拓展方法
    component.doSomeThing(); 
    ```


## 单例模式

1. 双重检查模式(线程安全)

   ```java
   class Singleton {
       private volatile static Singleton singleton;
   
       //不允许直接被外部调用构造方法
       private Singleton() {
   
       }
       public static Singleton getSingleton(){
           if (singleton == null) {
               synchronized (Singleton.class) {
                   if (singleton == null) {
                       singleton = new Singleton();
                   }
               }
           }
           return singleton;
       }
   
   }
   ```

2. 静态内部类单例模式

   ```java
   class Singleton {
       private Singleton() {
   
       }
   
       public static Singleton getInstance() {
           return SingletonHolder.instance;
       }
   
       private static class SingletonHolder {
           private static final Singleton instance = new Singleton();
       }
   }
   ```

   

3. 枚举单例

   ```java
   enum Sigleton{
       Instance
   }
   ```


***持续补充中***

