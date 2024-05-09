import infra.ApiHelper;
import java.io.IOException;
import java.util.List;
import models.Superhero;
import retrofit2.Call;
import retrofit2.Response;

public class SuperheroApiManager {
  private SuperheroApiManager() {}

  static SuperheroApi superheroApi = ApiHelper.getClient().create(SuperheroApi.class);

  public static Response<List<Superhero>> getAllSuperheroes() throws IOException {

    Call<List<Superhero>> call = superheroApi.getAllSuperheroes();
    return call.execute();
  }

  public static Response<Superhero> createSuperhero(Superhero superhero) throws IOException {
    Call<Superhero> call = superheroApi.createSuperhero(superhero);
    return call.execute();
  }
}
