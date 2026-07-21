package ru.otus.wishlist.config;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.assertj.core.presentation.Representation;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
class IntegrationTestConfiguration {

  @Bean
  PostgreSQLContainer<?> postgresqlContainer() {
    return new PostgreSQLContainer<>("postgres:16")
        .withDatabaseName("wishlist")
        .withUsername("usr")
        .withPassword("pwd")
        .withCreateContainerCmdModifier(
            cmd ->
                cmd.withHostConfig(
                    new HostConfig()
                        .withPortBindings(
                            new PortBinding(Ports.Binding.bindPort(5432), new ExposedPort(5432)))));
  }

  @Bean
  Representation representation() {
    return new GrpcRepresentation();
  }
}
