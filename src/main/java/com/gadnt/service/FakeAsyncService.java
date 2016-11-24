package com.gadnt.service;

import org.springframework.scheduling.annotation.Async;

/**
 * @author gabriel.deaconu
 * @since November
 */
public interface FakeAsyncService {

    @Async
//    @Retryable(include = {RestClientException.class}, maxAttempts = 5, backoff = @Backoff(delay = 2000) )
    String getAFakeAsyncResponse() throws Exception;
}
