package com.example.ejb.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.concurrent.Future;

/**
 * User: ivan
 * Date: 10/26/13
 * Time: 9:05 PM
 */

@Stateless(name="asyncExample")
public class AsyncExampleImpl implements AsyncExample {

    private final static Logger LOG = LoggerFactory.getLogger(AsyncExampleImpl.class);


    @Asynchronous
    public void asyncMethod(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOG.debug(String.valueOf(e), e);
        }
    }


    @Asynchronous
    public Future<String> asyncMethodWithResult(long waitTime){
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            LOG.debug(String.valueOf(e), e);
        }

        return new AsyncResult<String>(""+waitTime);
    }

}
