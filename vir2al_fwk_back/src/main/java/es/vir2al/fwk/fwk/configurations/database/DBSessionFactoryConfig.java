package es.vir2al.fwk.fwk.configurations.database;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.transaction.PlatformTransactionManager;


public abstract class DBSessionFactoryConfig {

    public abstract DataSource createDataSource();
    public abstract PlatformTransactionManager txManager();

    @Autowired
    private ResourceLoader rl;
    
    @Bean(name="dbSessionFactory")
    @Primary
    public SqlSessionFactory dbSessionFactoryBean() throws Exception {
        
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(this.createDataSource());
        sqlSessionFactoryBean.setMapperLocations(
            this.loadResources()
        );
        sqlSessionFactoryBean.setTypeAliasesPackage("es.vir2al.**.domain");

        return sqlSessionFactoryBean.getObject();
        
    }

    private Resource[] loadResources() {
        
        try {
            return ResourcePatternUtils.getResourcePatternResolver(rl).getResources("classpath:/es/vir2al/**/*.xml");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    
    }

}
