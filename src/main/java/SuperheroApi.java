import java.util.List;
import models.Superhero;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SuperheroApi {

  @GET("/superheroes")
  Call<List<Superhero>> getAllSuperheroes();

  @POST("https://superhero.qa-test.csssr.com/superheroes")
  Call<Superhero> createSuperhero(@Body Superhero superhero);
}
