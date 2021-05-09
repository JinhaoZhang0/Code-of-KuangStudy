import com.jin.dao.BlogMapper;
import com.jin.pojo.Blog;
import com.jin.utils.IDUtils;
import com.jin.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

public class MyTest {
    @Test
    public void addInitBlog(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = new Blog();
            blog.setId(IDUtils.getId());
            blog.setTitle("Mybatis如此简单");
            blog.setAuthor("狂神说");
            blog.setCreateTime(new Date());
            blog.setViews(9999);

            mapper.addBlog(blog);

            blog.setId(IDUtils.getId());
            blog.setTitle("Java如此简单");
            mapper.addBlog(blog);

            blog.setId(IDUtils.getId());
            blog.setTitle("Spring如此简单");
            mapper.addBlog(blog);

            blog.setId(IDUtils.getId());
            blog.setTitle("微服务如此简单");
            mapper.addBlog(blog);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryBlogIF(){
        try(final SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Map map = new HashMap();
            map.put("title","Java如此简单2");
//            map.put("author","狂神说");
//            map.put("id",1);
            List<Blog> blogs = mapper.queryBlogChoose(map);
//            List<Blog> blogs = mapper.queryBlogIF(map);
            for (Blog blog : blogs) {
                System.out.println(blog);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updateBlogTest(){
        try(final SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Map map = new HashMap();
            map.put("title","Java如此简单2");
            map.put("author","狂神说");
            map.put("id","a4b13ee10b764ca8b188d3b5495b1c7a");
            mapper.updateBlog(map);
//            for (Blog blog : blogs) {
//                System.out.println(blog);
//            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updateBlogForeach(){
        try(final SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Map map = new HashMap();
            ArrayList<Integer> ids = new ArrayList<>();
            ids.add(1);
            map.put("ids",ids);
//            map.put("author","狂神说");
//            map.put("id","a4b13ee10b764ca8b188d3b5495b1c7a");
            List<Blog> blogs = mapper.queryBlogForeach(map);
            for (Blog blog : blogs) {
                System.out.println(blog);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
