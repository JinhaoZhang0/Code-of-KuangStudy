import com.jin.dao.UserDaoImpl;
import com.jin.dao.UserDaoOracleImpl;
import com.jin.service.UserService;
import com.jin.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
//        //用户实际调用的是业务层，dao层他们不需要接触！
//        UserService userService = new UserServiceImpl();
//        userService.setUserDao(new UserDaoOracleImpl());
//        userService.getUser();

        //获取ApplicationContext；拿到Spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        //容器在手，天下我有，需要什么，就直接get什么！
        UserService userService = (UserService) context.getBean("userServiceImpl");
        userService.getUser();
    }
}
