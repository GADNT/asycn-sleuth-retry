package com.gadnt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.concurrent.Future;

/**
 * @author gabriel.deaconu
 * @since November
 */
@Service
public class FakeAsyncImpl implements FakeAsyncService {

    public static final Logger log = LoggerFactory.getLogger(FakeAsyncImpl.class);

    @Autowired
    FakeAsyncObject fakeAsyncObject;

    @Autowired
    Tracer tracer;

    @Async
    @Retryable(include = {RestClientException.class}, maxAttempts = 5, backoff = @Backoff(delay = 2000) )
    @Override
    public Future<String> getAFakeAsyncResponse() throws Exception {
        log.info("get in fake serviceImpl");

        Span span = this.tracer.createSpan("fake-service-impl");

        tracer.close(span);

        return fakeAsyncObject.getAFakeAsyncResponse();
    }
}
