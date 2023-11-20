package ee.mihkel.resttemplate.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Nordpool {
    private boolean success;
    private Countries data;
}

@Data
class Countries{
    private ArrayList<TimestampPrice> ee;
    private ArrayList<TimestampPrice> fi;
    private ArrayList<TimestampPrice> lv;
    private ArrayList<TimestampPrice> lt;
}

@Data
class TimestampPrice{
    private int timestamp;
    private double price;
}
