import mapper.PersonMapper;
import model.Person;
import Utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class testMyBatis {
    @Test
    public void testInsert(){
        SqlSession sqlSession=MybatisUtil.getSqlSession();
        PersonMapper mapper=sqlSession.getMapper(PersonMapper.class);
        Person person=new Person();
        person.setAge(20);
        person.setId(11);
        person.setName("beibei");
        try{
            mapper.insertPerson(person);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
            //close is important
        }
    }
    @Test
    public void testDelete(){

    }
    @Test
    public void testQueryByName(){
        Person person=null;
        SqlSession sqlSession=MybatisUtil.getSqlSession();
        PersonMapper mapper=sqlSession.getMapper(PersonMapper.class);
        try{
            person=mapper.queryPersonByName("beibei");
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }
        System.out.println(person.getId()+"   "+person.getAge()+"   "+person.getName());
    }
}
