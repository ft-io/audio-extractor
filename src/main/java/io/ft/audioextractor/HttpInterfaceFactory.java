package io.ft.audioextractor;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 *  [ref]: https://mangkyu.tistory.com/291
 */
public class HttpInterfaceFactory {
    public <S> S create(Class<S> clientClass) {
        HttpExchange httpExchange = AnnotationUtils.getAnnotation(clientClass, HttpExchange.class);
        if (httpExchange == null) {
            throw new IllegalStateException("HttpExchange annotation not found");
        }

        if (!StringUtils.hasText(httpExchange.url())) {
            throw new IllegalArgumentException("HttpExchange url is empty");
        }

        return HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(
                        WebClient.builder()
                                .baseUrl(httpExchange.url())
                                .exchangeStrategies(ExchangeStrategies.builder()
                                        .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(4 * 1024 * 1024))
                                        .build())
                                .build())
                )
                .build()
                .createClient(clientClass);
    }
}
