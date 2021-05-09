import com.jin.dao.UserMapper;
import com.jin.pojo.User;
import com.jin.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class UserMapperTest {
    @Test
    public void test() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            //底层主要应用反射
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//            List<User> userList = userMapper.getUsers();
//            for (User user : userList) {
//                System.out.println(user);
//            }
//            User userById = userMapper.getUserById(1);
//            System.out.println(userById);
//            userMapper.addUser(new User(5,"Hello","123123"));
//            userMapper.updateUser(new User(5,"to","2213"));
            userMapper.deleteUser(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
