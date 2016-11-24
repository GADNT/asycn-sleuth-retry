package com.gadnt.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestClientException;

/**
 * @author gabriel.deaconu
 * @since November
 */
public interface FakeAsyncService {

    @Async
    @Retryable(include = {RestClientException.class}, maxAttempts = 5, backoff = @Backoff(delay = 2000) )
    String getAFakeAsyncResponse() throws Exception;
}
