package com.gadnt.resource;


import com.gadnt.service.FakeAsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * @since gabriel.deaconu
 */
@RestController
@RequestMapping(value = "/async")
public class FakeResource {

    @Autowired
    Environment environment;

    @Autowired
    FakeAsyncService fakeAsyncService;

    private static final Logger log = LoggerFactory.getLogger(FakeResource.class);
    private static final String NO_MESSAGE = "no-message";

    @GetMapping(value = "/fake", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fakeResponseAsync() throws Exception {
        String reqBody;

        try {
            log.info("*** we are gonna get a fake ***");

            Future<String> fAsync = fakeAsyncService.getAFakeAsyncResponse();
            String aFakeAsyncResponse = fAsync.get();

            reqBody = "{\"message\": \"" + aFakeAsyncResponse + "\" }";
            return new ResponseEntity<>(reqBody, HttpStatus.OK);

        } catch (Exception e) {
            log.error(" *** Something happen during request! *** ");
            return new ResponseEntity<>(NO_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
