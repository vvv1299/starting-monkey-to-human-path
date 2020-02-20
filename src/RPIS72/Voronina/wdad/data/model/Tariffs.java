package RPIS72.Voronina.wdad.data.model;

import java.util.HashMap;

//WTF
public class Tariffs {
    public final HashMap<String, Double> values;
    public static final String COLD_WATER_KEY = "coldwater";
    public static final String HOT_WATER_KEY = "hotwater";
    public static final String ELECTRICITY_KEY = "electricity";
    public static final String GAS_KEY = "gas";

    public Tariffs(double coldwater, double hotwater, double electricity, double gas) {
        values = new HashMap<>();
        values.put(COLD_WATER_KEY, coldwater);
        values.put(HOT_WATER_KEY, hotwater);
        values.put(ELECTRICITY_KEY, electricity);
        values.put(GAS_KEY, gas);
    }
}
