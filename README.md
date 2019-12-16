# Light DI framework

## Description

This project is an attempt of creating a light dependency injection framework.

The scope of the project was to build a very simple to use dependency injection framework, to use in
simple applications, without the necessity of using more "heavier" frameworks like spring or Google Guice.

Another reason behind the creation of such a framework was also to play arround with the concept of dependency injection, 
and also with java 10 new features; ultimately it was for having fun and learning new stuff :).

## How to use:


### Maven dependency
The project uses maven. The dependency is not on maven central so, to use it you have to:
- Download the project locally to you're dev environment.
- Install the dependency to your local maven repository: `mvn clean install -U`;
- Add it to your maven project by adding the dependency to your `pom.xml`:
```aidl
<dependency>
    <groupId>ami.lightdi.framework</groupId>
    <artifactId>di-light-framework</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### Framework overview:

The framework allows you to mark your classes as beans managed by the framework, and allows you to inject
those classes into other beans.

## Annotations:

There are 2 main annotations that will allow you to use dependency injection together with this framework:
- @Component
- @Inject



#### **@Component** annotation : 
Marks this class as a managed bean by the framework. You have the option of
making your component either a **singleton** or a **prototype**.

- **Singleton** component:
    
    ```aidl
    @Component(scope = Scope.SINGLETON)
    public class SingletonExample{
  
    }

    ```
    
    Marking the class as a singleton ensures that every time you retrieve your bean instance from the 
    framework, it will be the same. Also, **@Injecting** the bean into other **@Component**s only one, unique instance is used everywhere.
    
- **Prototype** component:
    
    ```aidl
    @Component(scope = Scope.PROTOTYPE)
    public class SingletonExample{
  
    }

    ```
    
    Marking the class as a prototype ensures that every time you retrieve your bean instance from the framework, a new instance is retrieved.
    - **@Injecting** the prototype bean into other prototype **@Component** means you get a brand new instance of the bean each time you retrieve the parent component.
    - **@Injecting** the prototype bean into other singleton **@Component** means the prototype bean inside the singleton will stay the same. To get around this whenever you need a prototype component into a singleton, you can use the **PrototypeFactory<T>**.
 - **Named bean**:
    
    You can create a named @Component. This helps you retrieve / inject the component based on the name.
    
    Example:
    
    - Component supertype:
    
    ```aidl
    public interface DummyClass{

     }

    ```
    
    - Component implementation:
    
    ```aidl
    @Component(name = "myDummyClass")
    public class DummyClassImpl implements DummyClass{

     }
    ```
    
    - Component injection:
    
    ```
    @Component
    public class MyDummyInjectionClass{
        
        @Inject("myDummyClass")
        private DummyClass dummy;
        
    }
    
    or 
    
    public class Application{
            
        public void testMethod(){
            DummyClass dummyClass = LighDI.getBean("myDummyClass", DummyClass.class);
        }
            
    }
    ```
    
#### **@Inject** annotation : 

The inject annotation allows you to inject managed components inside other components.
The @Inject annotation can be applied to:

  - Fields:
  
  ```
   @Component
   public class MyDummyInjectionClass{
          
    @Inject
    private DummyClass dummy;
          
   }
  ```
  
  - Fields using bean name:
  
  ```
  @Component
  public class MyDummyInjectionClass{
          
          @Inject("myDummyClass")
          private DummyClass dummy;
          
  }
  ```
  
  
  - Constructors:
  
  ```
     @Component
     public class MyDummyInjectionClass{
            
      private DummyClass dummy;
      
      @Inject
      public MyDummyInjectionClass(DummyClass dummy){
            this.dummy = dummy
      }
            
     }
   ```
    
## Scanning components

 - Component scanning is performed by calling the static method `LightDI.init("com.package.a", "com.package.b")`
 - If you want to programatticaly retrieve a managed component, you can use `LightDI.getBean()` method




 
