package es.vir2al.fwk.fwk.configurations.database;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import es.vir2al.fwk.app.utils.constants.GeneralAppConstants;


public abstract class DBSessionFactoryConfig {

    public abstract DataSource createDataSource();
    public abstract PlatformTransactionManager txManager();
    
    @Bean(name="dbSessionFactory")
    @Primary
    public SqlSessionFactory dbSessionFactoryBean() throws Exception {
        
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(this.createDataSource());
        sqlSessionFactoryBean.setMapperLocations(
            new ClassPathResource[]{
                //new ClassPathResource("es/vir2al/"+GeneralAppConstants.APP_NAME+"/mappers/test.xml")
            }
        );
        // sqlSessionFactoryBean.setTypeAliasesPackage("es.vir2al."+GeneralAppConstants.APP_NAME+".domain");

        return sqlSessionFactoryBean.getObject();
        
    }

}
