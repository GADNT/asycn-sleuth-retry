package com.gadnt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

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

    @Override
    public String getAFakeAsyncResponse() throws Exception {
        log.info("get in fake serviceImpl");

        Span span = this.tracer.createSpan("fake-service-impl");

        tracer.close(span);

        return fakeAsyncObject.getAFakeAsyncResponse();
    }
}
