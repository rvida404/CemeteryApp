package ro.InnovaTeam.cemeteryApp;

import java.util.HashMap;

/**
 * Created by robert on 1/13/2015.
 */
public class PrintableStatisticsDTO extends BaseDTO {

    public static enum Fields {
        TITLE
    }

    private HashMap<String, Object> data = new HashMap<>();

    public PrintableStatisticsDTO() {
    }

    public PrintableStatisticsDTO(HashMap<String, Object> data) {
        this.data = data;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public Object get(Fields field) {
        return data.get(field.toString());
    }

    public void add(Fields field, Object obj) {
        data.put(field.toString(), obj);
    }
}
