package com.gadnt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.util.Random;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * @author gabriel.deaconu
 * @since November
 */
@Component
public class FakeAsyncObject {

    public static final Logger log = LoggerFactory.getLogger(FakeAsyncObject.class);

    @Autowired
    Tracer tracer;

    Future<String> getAFakeAsyncResponse() throws Exception {

        Span span = this.tracer.createSpan("fake-async-obj");
        log.info("get in fake object");

        try {
            IntStream ints = new Random().ints(10, 33, 38);

            if (ints.findAny().getAsInt() == 35) {
                throw new RestClientException("Error, not found the value checked!");
            }

        } finally {
            tracer.close(span);
        }

        return new AsyncResult<>("fakeResult");
    }
}
