import com.jin.dao.TeacherMapper;
import com.jin.pojo.Teacher;
import com.jin.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {
    @Test
    public void test() {
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
            final Teacher teacher = mapper.getTeacher2(1);
            System.out.println(teacher);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
