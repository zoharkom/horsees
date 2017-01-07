import spark.Request;
import spark.Response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import static spark.Spark.get;
import static spark.Spark.post;

public class PongServiceWrapper {
    AtomicBoolean pongValid = new AtomicBoolean();

    public PongServiceWrapper() {
        pongValid.set(true);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    public static String utcNowInLongFormat() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        df.setTimeZone(tz);
        String result = df.format(new Date());
        return result;
    }

    public PongServiceWrapper init() {
        post("/_/pong-as-fail", this::failPong);
        post("/_/pong-as-fail/", this::failPong);
        get("/_/ping", this::pong);
        get("/_/ping/", this::pong);
        return this;
    }

    @SuppressWarnings("UnusedParameters")
    public Object pong(Request request, Response response) {
        response.type("text/plain");
        String firstPartOfMessage;
        if (pongValid.get()) {
            firstPartOfMessage = "pong";
        } else {
            response.status(503);
            firstPartOfMessage = "applicative failure";
        }

        return String.format("%s\n%s\n%s\n", firstPartOfMessage, utcNowInLongFormat(), UUID.randomUUID());
    }

    public Object failPong(Request request, Response response) {
        pongValid.set(false);
        return null;
    }

}
