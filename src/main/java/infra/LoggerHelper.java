package infra;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import models.Superhero;
import retrofit2.Response;

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
