import org.apache.log4j.Logger;
import spark.Request;
import spark.Response;
import spark.Spark;

public class HorseesServerMain {

    private static final Logger LOGGER = Logger.getLogger(HorseesServerMain.class);
    public static final String GET_HORSE_INFO_HANDLER_ROUTE = "/horse/info/:id";

    public static void main(String[] args) {
        LOGGER.info("> Horsees Server Started...");
        PongServiceWrapper pongServiceWrapper = new PongServiceWrapper().init();
        Spark.get(GET_HORSE_INFO_HANDLER_ROUTE, HorseesServerMain::horseInfoHandler);
        LOGGER.info("-- Horsees Server Is Up! --");
    }

    private static Object horseInfoHandler(Request request, Response response) {
        //TODO
        return null;
    }
}
