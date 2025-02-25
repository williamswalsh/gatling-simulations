package ie.williamswalsh.simulations;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;


public class SimpleSimulation extends Simulation {

    public static final String TARGET_WEBSITE = "https://jsonplaceholder.typicode.com/posts";
    // Define the HTTP protocol
    private static HttpProtocolBuilder httpProtocol = http
            .baseUrl(TARGET_WEBSITE)
            .acceptHeader("application/json");

    // Define the scenario
    private ScenarioBuilder scn = scenario("Basic Load Test")
            .exec(http("Home Page Request")
                    .get("/"))
            .pause(Duration.ofSeconds(2));

    // Define the load profile
    // IF missing -> ERROR -> No scenario configured, make sure to call setUp
    {
        setUp(
                scn.injectOpen(atOnceUsers(10)) // Simulates 10 users at once
        ).protocols(httpProtocol);
    }
}

