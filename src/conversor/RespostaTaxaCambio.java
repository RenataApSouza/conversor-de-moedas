package conversor;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class RespostaTaxaCambio {
    @SerializedName("base_code")
    public String moedaBase;

    @SerializedName("conversion_rates")
    public Map<String, Double> taxasDeConversao;
}

