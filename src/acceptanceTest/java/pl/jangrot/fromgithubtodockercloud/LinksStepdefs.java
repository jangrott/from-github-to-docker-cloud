package pl.jangrot.fromgithubtodockercloud;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LinksStepdefs {

    private String host = System.getProperty("env.host");
    private String url;
    private ResponseEntity<List> responseEntity;
    private List<String> links;

    @Given("^App is running on port (\\d+)$")
    public void app_is_running_on_port(int port) throws Throwable {
        url = "http://" + host + ":" + port;
    }

    @When("^the client requests a list of links$")
    public void the_client_requests_a_list_of_links() throws Throwable {
        responseEntity = new TestRestTemplate().getForEntity(url + "/links", List.class);
    }

    @Then("^the response is the list containing two links$")
    public void the_response_is_the_list_containing_two_links() throws Throwable {
        links = responseEntity.getBody();

        assertThat(links).hasSize(2);
    }

    @Then("^first link is (.+)$")
    public void first_link_is(String link) throws Throwable {
        String firstLink = links.get(0);

        assertThat(firstLink).isEqualTo(link);
    }

    @Then("^second link is (.+)$")
    public void second_link_is(String link) throws Throwable {
        String secondLink = links.get(1);

        assertThat(secondLink).isEqualTo(link);
    }
}
