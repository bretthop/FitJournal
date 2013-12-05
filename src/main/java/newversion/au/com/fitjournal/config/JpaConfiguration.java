package newversion.au.com.fitjournal.config;

import org.hibernate.cache.HashtableCacheProvider;
import org.hibernate.dialect.PostgreSQLDialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JpaConfiguration
{
	@Value("#{fitJournalDataSource}")
	private javax.sql.DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean()
    {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();

        lef.setDataSource(this.dataSource);
        lef.setJpaPropertyMap(this.jpaProperties());
        lef.setJpaVendorAdapter(this.jpaVendorAdapter());
        lef.setPersistenceUnitName("application");

        return lef;
    }

	@Bean
	public Map<String, Object> jpaProperties()
    {
		Map<String, Object> props = new HashMap<String, Object>();

        props.put("hibernate.dialect", PostgreSQLDialect.class.getName());
		props.put("hibernate.cache.provider_class", HashtableCacheProvider.class.getName());

        return props;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter()
    {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();

        hibernateJpaVendorAdapter.setShowSql(false);
		hibernateJpaVendorAdapter.setGenerateDdl(false);
		hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);

        return hibernateJpaVendorAdapter;
	}
}