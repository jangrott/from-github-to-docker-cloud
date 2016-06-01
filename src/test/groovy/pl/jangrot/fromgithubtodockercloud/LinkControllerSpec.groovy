package pl.jangrot.fromgithubtodockercloud

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.TestRestTemplate
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest('server.port:0')
class LinkControllerSpec extends Specification {

    @Value('${local.server.port}')
    def port;

    @Autowired
    ApplicationContext context

    RestTemplate restTemplate = new TestRestTemplate();

    def "returns links"() {
        setup:
        def entity = restTemplate.getForEntity("http://localhost:${port}/links", List.class);

        expect:
        entity.statusCode == HttpStatus.OK
        entity.headers.getContentType() == MediaType.APPLICATION_JSON_UTF8
        entity.body.size() == 2
        entity.body[0] == 'http://google.com'
        entity.body[1] == 'http://spring.io'

    }

}
