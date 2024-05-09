import infra.ApiHelper;
import infra.LoggerHelper;
import lombok.extern.slf4j.Slf4j;
import models.Superhero;
import models.Superheroes;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SuperheroTests {

        @Test
        public void getAllHeroes() throws IOException {

            Response<List<Superhero>> superheroes = SuperheroApiManager.getAllSuperheroes();
            LoggerHelper.logResponse(superheroes);

            assertThat(superheroes.body()).isNotNull();
            assertThat(superheroes.body().size()).as("List of heroes is not empty").isGreaterThan(0);
        }

        @Test
    public void createHero() throws IOException {
            Superhero newHero = new Superhero();
            Response<Superhero> createdHero = SuperheroApiManager.createSuperhero(newHero);
            LoggerHelper.logResponseForCreation(createdHero);
            assertThat(createdHero.body()).isNotNull();
            SuperheroAssert.checkCreatedHero(newHero, createdHero.body());
        }

    }



