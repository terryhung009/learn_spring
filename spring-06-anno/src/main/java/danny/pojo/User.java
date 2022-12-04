package danny.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//等價於<bean id="user" class="danny.pojo.User"/>
//@Component 組件

@Component
@Scope("singleton")
public class User {


    public String name;

    public String getName() {
        return name;
    }
    //相當於 <property name="name" value="danny"/>-->
    @Value("danny")
    public void setName(String name) {
        this.name = name;
    }
}
