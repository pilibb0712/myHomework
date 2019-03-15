package Utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MybatisUtil {
        private static SqlSessionFactory sqlSessionFactory;

        private static void initSqlSesiionFactory(){
            String resource="mybatis-config.xml";
            Reader reader =null;
            try {
                reader = Resources.getResourceAsReader(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }

        /**
         * 获取SqlSessionFactory
         * @return SqlSessionFactory
         */
        public static SqlSessionFactory getSqlSessionFactory(){
            if(sqlSessionFactory==null){
                initSqlSesiionFactory();
            }
            return sqlSessionFactory;
        }

        /**
         * 获取SqlSession
         * @return SqlSession
         */
        public static SqlSession getSqlSession(){
            if(sqlSessionFactory==null){
                initSqlSesiionFactory();
            }
            return sqlSessionFactory.openSession();
        }
        /**
         * 关闭SqlSession
         */
        public  static void closeSession(SqlSession sqlSession){
            if (sqlSession!=null)
                sqlSession.close();
        }
}
