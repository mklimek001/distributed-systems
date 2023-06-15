package homework;

public class KeysGenerator {
    private final static String person_key = "transport.*.person.*";
    private final static String load_key = "transport.*.load.*";
    private final static String satellite_key = "transport.*.satellite.*";

    private final static String notification_base = "notification.";
    private final static String exchange_key = "cosmic_services";

    public static String getExchange_key() {
        return exchange_key;
    }

    public static String getPerson_key(){
        return person_key;
    }

    public static String getLoad_key(){
        return load_key;
    }

    public static String getSatellite_key(){
        return satellite_key;
    }

    public static String getPerson_key(int agency_id, int number){
        return "transport." + agency_id + ".person." + number;
    }

    public static String getLoad_key(int agency_id, int number){
        return "transport." + agency_id + ".load." + number;
    }

    public static String getSatellite_key(int agency_id, int number){
        return "transport." + agency_id + ".satellite." + number;
    }

    public static String getNotification_key(){
        return notification_base + "#";
    }

    public static String getNotification_key(String str, int number){
        return notification_base + str + "." + number;
    }

    public static String getNotification_key(String str){
        return notification_base + str + ".#";
    }
}
