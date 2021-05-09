import com.jin.pojo.Student;
import com.jin.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student);
        /**
         * Student{
         * name='秦疆',
         * address=Address{address='西安'},
         * books=[红楼梦, 水浒传, 西游记, 三国演义],
         * hobbies=[听歌, 敲代码, 看电影],
         * card={身份证=111111222222223333, 银行卡=12321312313},
         * games=[LOL, COC, BOB],
         * wife='null',
         * info={password=123456, driver=ere, url=e, username=root}}
         */
    }

    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
        //在第二参数中设置类属性，则不用强转
        User user = context.getBean("user", User.class);
        System.out.println(user);
    }
}
