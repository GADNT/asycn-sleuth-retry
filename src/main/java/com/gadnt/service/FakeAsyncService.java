package com.gadnt.service;

import java.util.concurrent.Future;

/**
 * @author gabriel.deaconu
 * @since November
 */
public interface FakeAsyncService {

    Future<String> getAFakeAsyncResponse() throws Exception;
}
