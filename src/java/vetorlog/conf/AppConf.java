package vetorlog.conf;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import io.sentry.Sentry;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.Swagger;
import io.swagger.models.auth.ApiKeyAuthDefinition;
import io.swagger.models.auth.BasicAuthDefinition;
import io.swagger.models.auth.In;
import lombok.extern.log4j.Log4j2;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Log4j2
@ApplicationPath("api")
public class AppConf extends ResourceConfig {
    @Inject
    private ServiceLocator serviceLocator;

    private void confJersey2() {
        packages("vetorlog.api;");
        property(ServerProperties.TRACING, "ALL");
        register(RolesAllowedDynamicFeature.class);
    }

    private void confDependencyInjection() {
        register(new JerseyCDIConf());
    }

    private void confSentry() {
        Sentry.init();
    }

    private void confSwagger() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Econometer");
        beanConfig.setDescription("Documentação das requisições do projeto e interface de teste");
        beanConfig.setVersion("1.0");
        beanConfig.setResourcePackage("vetorlog.api");
        beanConfig.setScan(true);
        beanConfig.setPrettyPrint(true);
        beanConfig.setBasePath("/api");
        beanConfig.setSchemes(new String[]{"http", "https"});
        beanConfig.setHost(Constant.EMETER_APP_URL);

        register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        Swagger swagger = new Swagger();
        swagger.securityDefinition("apiKey", new ApiKeyAuthDefinition("Authorization", In.HEADER ));
        new SwaggerContextService().updateSwagger(swagger);
    }

    private void confJackson() {
        register(JacksonConf.class);
    }

    public AppConf() {
        confJersey2();
        confDependencyInjection();
        confSentry();
        confSwagger();
        confJackson();
    }
}

