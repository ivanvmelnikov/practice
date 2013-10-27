package om.example.ejb.async.client;

import com.example.ejb.async.AsyncExample;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;


/**
 * User: ivan
 * Date: 10/26/13
 * Time: 9:54 PM
 */

//https://docs.jboss.org/author/display/AS71/EJB+invocations+from+a+remote+client+using+JNDI
//https://docs.jboss.org/author/display/AS72/Remote+EJB+invocations+via+JNDI+-+EJB+client+API+or+remote-naming+project
public class AsyncClient {

    public static void main(String[] args) throws Exception {
        AsyncExample asyncExampleImpl = lookup();

        //https://issues.jboss.org/browse/AS7-3749
        //asyncExampleImpl.asyncMethod();

        System.out.println(asyncExampleImpl.asyncMethodWithResult(100).get());

        System.out.println(asyncExampleImpl.asyncMethodWithResult(5000).get());

    }


    public static AsyncExample lookup() throws NamingException {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.jboss.naming.remote.client.InitialContextFactory");
        props.put(Context.PROVIDER_URL, "remote://localhost:4447");
        props.put(Context.SECURITY_PRINCIPAL, "admin");
        props.put(Context.SECURITY_CREDENTIALS, "!!cmx!!1");

        //remote-naming project flag
        //props.put("jboss.naming.client.ejb.context", true);

        final Context context = new InitialContext(props);
        final String appName = "";
        final String moduleName = "asynchronous";
        final String distinctName = "";
        final String beanName = "asyncExample";
        final String viewClassName = AsyncExample.class.getName();

        String name = "java:" + appName + "/"
                + moduleName + "/" + distinctName + "/" + beanName + "!"
                + viewClassName;
        System.out.println(name);
        return (AsyncExample) context.lookup(
                name);
    }


}
