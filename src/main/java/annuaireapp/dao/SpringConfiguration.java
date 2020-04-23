package annuaireapp.dao;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"annuaireapp.dao", "annuaireapp.business"})
@EnableTransactionManagement
@PropertySource("application.properties")
public class SpringConfiguration {

    /*
     * Définition de la source de données
     */
    @Bean
    public DataSource dataSource(//
                                 @Value("${spring.datasource.driverName}") String driverName, //
                                 @Value("${spring.datasource.url}") String url, //
                                 @Value("${spring.datasource.user}") String user, //
                                 @Value("${spring.datasource.password}") String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    /*
     * Construction de l'EMF à partir de la source de données et du
     * choix d'hibernate. Cette configuration remplace le fichier
     * persistence.xml
     */
    @Bean("mydata")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(//
                                                                       @Autowired DataSource ds) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(ds);
        em.setPackagesToScan(new String[]{"annuaireapp.models", "annuaireapp.business"});
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setBeanName("mydata");
        // Configuration d'hibernate
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        em.setJpaProperties(properties);
        return em;
    }

    /*
     * Construction d'un gestionnaire de transaction
     * en liaison avec l'usine à EM.
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    /*
     * Activer le traitement des annotations
     * de gestion du contexte de persistence.
     */
    @Bean
    public PersistenceAnnotationBeanPostProcessor annotationProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean
    public JavaMailSenderImpl mailSender(
            @Value("${spring.mail.protocol}") String protocol,
            @Value("${spring.mail.host}") String host,
            @Value("${spring.mail.port}") int port,
            @Value("${spring.mail.username}") String username,
            @Value("${spring.mail.password}") String password
    ) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    /**
     * Template du message de récupération du mot de passe utilisateur
     */
    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(
                "Hi %s,\n" +
                "Your account password has been reset - Please log in as soon ass possible and change it to your desired password\n\n" +
                "Your login information is as follows : \n" +
                "Login : %s\n" +
                "Password: %s\n" +
                "Don't forget to change your password !! \n");
        return message;
    }

}