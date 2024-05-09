package infra;

import lombok.extern.slf4j.Slf4j;
import models.Superhero;
import models.Superheroes;
import retrofit2.Response;

import java.util.List;

@Slf4j
    public class LoggerHelper {

        public static void logResponse(Response<List<Superhero>> response) {
            log.info("Response Code: " + response.code());
            log.info("Response Body: " + response.body());
        }

    public static void logResponseForCreation(Response<Superhero> response) {
        log.info("Response Code: " + response.code());
        log.info("Response Body: " + response.body());
    }
    }
