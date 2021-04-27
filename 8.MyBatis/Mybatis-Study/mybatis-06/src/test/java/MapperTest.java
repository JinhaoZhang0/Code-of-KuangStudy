import com.jin.dao.StudentMapper;
import com.jin.dao.TeacherMapper;
import com.jin.pojo.Student;
import com.jin.pojo.Teacher;
import com.jin.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MapperTest {

    @Test
    public void testStudent2() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            //底层主要应用反射
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> students = mapper.getStudent2();
            for (Student student : students) {
                System.out.println(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStudent() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            //底层主要应用反射
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> students = mapper.getStudent();
            for (Student student : students) {
                System.out.println(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
