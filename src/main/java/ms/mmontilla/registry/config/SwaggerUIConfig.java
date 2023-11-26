package ms.mmontilla.registry.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info =@Info(
                title = "User API",
                version = "${app.version}",
                contact = @Contact(
                        name = "${app.contact.name}", email = "${app.contact.email}", url = "${app.contact.url}"
                ),
                license = @License(
                        name = "GNU General Public License v3.0", url = "https://www.gnu.org/licenses/quick-guide-gplv3.html"
                ),
                termsOfService = "${tos.uri}",
                description = "${app.description}"
        )
)
public class SwaggerUIConfig { }
