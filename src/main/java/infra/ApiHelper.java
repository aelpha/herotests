package infra;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Slf4j
public class ApiHelper {

  private static final String BASE_URL = "https://superhero.qa-test.csssr.com";
  private static Retrofit retrofit = null;

  public static Retrofit getClient() {
    if (retrofit == null) {
      HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
      logging.setLevel(HttpLoggingInterceptor.Level.BODY);

      OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

      retrofit =
          new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .client(client)
              .build();
    }
    return retrofit;
  }
}
