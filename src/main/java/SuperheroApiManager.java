import infra.ApiHelper;
import models.Superhero;
import models.Superheroes;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;

import java.io.IOException;
import java.util.List;

import static infra.HttpExecutor.ok;

public class SuperheroApiManager {
    private SuperheroApiManager() {}
    static SuperheroApi superheroApi = ApiHelper.getClient().create(SuperheroApi.class);

    public static Response<List<Superhero>> getAllSuperheroes() throws IOException {

        Call<List<Superhero>> call = superheroApi.getAllSuperheroes();
        return call.execute();
    }

    public static Response<Superhero> createSuperhero(Superhero superhero) throws IOException {
        Call<Superhero> call = superheroApi.createSuperhero(superhero);
        return  call.execute();
    }
}
