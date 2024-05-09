package infra;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Cookie;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

    @Slf4j
    public class HeaderInterceptor implements Interceptor {

        private String key;
        protected String value;

        public HeaderInterceptor(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public HeaderInterceptor(String key, List<Cookie> cookies) {
            this.key = key;
            this.value = cookies.stream().map(Cookie::toString).collect(Collectors.joining(";"));
        }

        public Response intercept(Chain chain) throws IOException {
            log.debug("Set header: {}:{}", key, value);
            Request authenticatedRequest = chain.request().newBuilder().header(key, value).build();
            return chain.proceed(authenticatedRequest);
        }
    }

