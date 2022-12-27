# Spring筆記

### 1.複雜類型

```java
public class Address {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
```

### 2.真實測試對象

```java
public class Student {

    private String name;
    private Address address;
    private String[] books;
    private List<String> hobbys;
    private Map<String,String> card;
    private Set<String> games;
    private Properties info;
    private String wife;
}
```

### 3.beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <import resource="beans.xml"></import>

</beans>
```

### 4.測試類

```java
public class MyTest1 {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student = (Student) context.getBean("student");

        System.out.println(student.getName());

    }
}
```

### 完善注入訊息

```java
Student{name='danny',
         address=Address{address='null'},
         books=[紅樓夢, 西遊記, 三國演義],
         hobbys=[聽歌, 敲代碼, 看電影],
         card={身分證=11111, 銀行卡=22222},
         games=[LOL, COC, BOB],
         info={性別=男, 學號=111111},
         wife='null'}
```

## 6.3、拓展方式注入

我們可以使用p命名空間和c命名空間進行注入

官方解釋：

![Untitled](Spring%E7%AD%86%E8%A8%98%206a6af46c639b4a3a96144b2d27bc6261/Untitled.png)

使用!

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

<!--    p命名空間注入 p -> property ，可以直接注入屬性的值-->
<!--    <bean id="user" class="danny.pojo.User" p:name="danny" p:age="18"/>-->
    <!--   c命名空間注入 c -> construct-args ，通過構造函數注入-->
    <bean id="user2" class="danny.pojo.User" c:age='18' c:name="狂神" />

</beans>
```

測試：

```java
@Test
    public void MyTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = context.getBean("user2", User.class);

        System.out.println(user);
    }
```

注意點：p命名和c命名不能直接使用，需導入xml約束

```xml
xmlns:p="http://www.springframework.org/schema/p"
xmlns:c="http://www.springframework.org/schema/c"
```

## 6.4、bean的作用域

![Untitled](Spring%E7%AD%86%E8%A8%98%206a6af46c639b4a3a96144b2d27bc6261/Untitled%201.png)

### 1.單例模式(預設)

```xml
<bean id="user2" class="danny.pojo.User" c:age='18' c:name="狂神" scope="singleton"/>
```

### 2. 原型(prototype)模式 : 每次從容器中get的時候，都會產生一個新物件

```xml
<bean id="accountService" class="com.something.DefaultAccountService" scope="prototype"/>
```

### 3.其餘的request、session、application 這些只能在web開發中使用到!

## 7、bean 的自動裝配

- 自動裝配是Spring滿足bean依賴的一種方式！
- Spring 會在上下文自動尋找，並自動給bean裝配屬性

在Spring中有三種裝配的方式

1. 在xml中顯示的配置
2. 在java中顯示配置
3. 隱式的自動裝配bean【 重要  】

## 7.1 、 測試

1.環境搭建 : 一個人有兩個寵物

## 7.2 ByName自動裝配

```xml
<!--
    byName : 會自動在容器中上下文查找，和自己對象set方法後面的值對應的bean id
-->
    <bean id="people" class="danny.People" autowire="byName">
        <property name="name" value="小黃"/>
```

### 7.3 ByType

```xml
<bean id="cat" class="danny.Cat"/>
    <bean id="dog" class="danny.Dog"/>
    
<!--
    byName : 會自動在容器中上下文查找，和自己對象set方法後面的值對應的bean id
    byType : 會自動在容器中上下文查找，和自己對象屬性類型相同的 bean
-->
    <bean id="people" class="danny.People" autowire="byType">
        <property name="name" value="小黃"/>

    </bean>
```

小結 :

- byname的時候，需要保證所有bean的id唯一，並且這個bean需要和自動注入的屬性的方法一致
- bytype的時候，需要保證所有bean的class唯一，並且這個bean需要和自動注入的屬性的類型一致

## 7.4 使用註解實現自動裝配

jdk1.5支持的註解，Spring2.5就支持註解

The introduction of annotation-based configuration raised the question of whether this approach is “better” than XML.

要使用註解須知 :

1. 導入約束 context約束
2. 配置註解的支持  ***<context:annotation-config/> 【重要】***

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```

@Autowired

直接在屬性上使用即可，也可以在setter上使用

使用AutoWired 我們可以不用編寫Set方法了，前提是你這個自動裝配的屬性在IOC容器中存在且符合名字byname

科普 :

```xml
@Nullable  字段標記了這個註解，說明這個字段可以為null
```

```java
public @interface Autowired {
    boolean required() default true;
}
```

測試代碼 :

```java
public class People {
    //如果顯示定義了Autowired的required屬性為false，說明這個屬性可以為null，否則不允許為null
    @Autowired(required = false)
    private Cat cat;
    @Autowired
    private Dog dog;
    private String name;
}
```

如果@Autowired 自動裝配的環境比較複雜，自動裝配無法通過一個註解【@Autowired】完成的時候，我們可以使用`@Qualifier(value = **"xxx"**)` 去配置@Autowired的使用，指定一個唯一的bean對象注入

```java
public class People {
    
    @Autowired
    @Qualifier(value = "cat11")
    private Cat cat;
    @Autowired
    @Qualifier(value = "dog222")
    private Dog dog;
}
```

@Resource註解  也可以自動裝配

```java
public class People {

    @Resource
    private Cat cat;
    @Resource
    private Dog dog;
    private String name;
}
```

```java
public class People {

    @Resource(name = "cat2")
    private Cat cat;
    @Resource
    private Dog dog;
    private String name;
}
```

小結 : @Resource和@Autowired的區別 :

- 都是用來自動裝配的，都可以放在屬性字段上
- @Autowired  通過byType方式實現，而且必須要求這個對象存在!【常用】
- @Resource預設通過byName方式實現，如果找不到名字，則通過byType實現，如果兩個都找不到的情況下，就報錯 【常用】
- 執行順序不同 :@Autowired通過byType的方式實現，@Resource預設通過byName的方式實現

# 8.使用註解開發
- 在Spring4之後，要使用註解開發，需要保證aop的包導入了
- 使用註解需要導入context約束，增加註解的支持
1. bean
2. 屬性如何注入
3. 衍生的註解
4. 自動裝配配置
5. 作用域
6. 小結