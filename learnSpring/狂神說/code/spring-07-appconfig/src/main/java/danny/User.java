package danny;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


//這裡這個註解的意思，就是說明這個類被Spring接管了，註冊到了容器中
@Component
public class User {
    private String name;

    public String getName() {
        return name;
    }

    @Value("Danny") // 屬性注入值
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
