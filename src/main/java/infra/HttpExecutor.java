package infra;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static java.net.HttpURLConnection.*;

    @Slf4j
    public class HttpExecutor {

        private HttpExecutor() {}

        public static Response<ResponseBody> execute(Call<ResponseBody> call) {
            try {
                return call.execute();
            } catch (IOException e) {
                throw new ExceptionWrapper("Unable execute request: ", e);
            }
        }

        public static void checkCode(Response<ResponseBody> response, int expectedCode) {
            int actualCode = response.raw().code();
            if (actualCode != expectedCode) {
                throw new ExceptionWrapper(
                        String.format(
                                "Unexpected response code, expected: %d, actual: %d, url: %s",
                                expectedCode, actualCode, response.raw().request().url()));
            }
        }

        public static Response<ResponseBody> executeAndCheck(Call<ResponseBody> call, int expectedCode) {
            Response<ResponseBody> response = execute(call);
            checkCode(response, expectedCode);
            return response;
        }

        public static Response<ResponseBody> executeWithRetry(
                Call<ResponseBody> call, int expectedCode, int retries) {
            return RetryUtil.retry(
                    () -> {
                        Response<ResponseBody> response = execute(call);
                        checkCode(response, expectedCode);
                        return response;
                    },
                    retries);
        }

        public static Response<ResponseBody> executeWithRetry(Call<ResponseBody> call, int retries) {
            return RetryUtil.retry(() -> execute(call), retries);
        }

        public static Response<ResponseBody> ok(Call<ResponseBody> call) {
            return executeAndCheck(call, HTTP_OK);
        }

        public static Response<ResponseBody> created(Call<ResponseBody> call) {
            return executeAndCheck(call, HTTP_CREATED);
        }

        public static Response<ResponseBody> noContent(Call<ResponseBody> call) {
            return executeAndCheck(call, HTTP_NO_CONTENT);
        }
    }

