package com.cuupa.opengarden.configuration

import com.cuupa.opengarden.persistence.SqliteDialect
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import java.io.File
import java.util.*
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = ["com.cuupa.opengarden.persistence.field"],
    entityManagerFactoryRef = "plant_entityManagerFactory",
    transactionManagerRef = "plant_transactionManager"
)
class DatabaseConfiguration {

    @Value("\${application.datapath}")
    private lateinit var datapath: String

    @Bean("plant_datasource")
    fun dataSource(): DataSource {
        return DataSourceBuilder.create()
            .driverClassName("org.sqlite.JDBC")
            .url("jdbc:sqlite:${getDatabaseName()}")
            .build()
    }

    @Bean("plant_entityManagerFactory")
    fun entityManagerFactory(@Qualifier("plant_datasource") dataSource: DataSource): LocalSessionFactoryBean? {
        return LocalSessionFactoryBean().apply {
            setDataSource(dataSource)
            setPackagesToScan("com.cuupa.opengarden.persistence.field")
            hibernateProperties = hibernateProperties()
        }
    }

    @Bean("plant_transactionManager")
    fun dbTransactionManager(@Qualifier("plant_entityManagerFactory") entityManager: LocalSessionFactoryBean): PlatformTransactionManager {
        return JpaTransactionManager().apply {
            entityManagerFactory = entityManager.getObject()
        }
    }

    private fun hibernateProperties(): Properties {
        return Properties().apply {
            setProperty("hibernate.hbm2ddl.auto", "update")
            setProperty("hibernate.dialect", SqliteDialect::class.java.canonicalName)
        }
    }

    private fun getDatabaseName() = "$datapath${File.separator}database.db"
}