package com.example.ejb.async;

import javax.ejb.Asynchronous;
import javax.ejb.Remote;
import java.util.concurrent.Future;

/**
 * User: ivan
 * Date: 10/27/13
 * Time: 4:47 PM
 */

@Remote
public interface AsyncExample {
    public void asyncMethod();

    public Future<String> asyncMethodWithResult(long waitTime);
}
