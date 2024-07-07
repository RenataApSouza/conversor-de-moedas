package conversor;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class API {
    private static final String CHAVE_API = "87a24e27aabc52beb6a8efef";
    private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/";

    public static String obterTaxaCambio(String moedaBase) throws IOException, InterruptedException {
        String url = URL_BASE + CHAVE_API + "/latest/" + moedaBase;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("Erro na requisição. Código de status: " + response.statusCode());
        }
    }

    public static double converterMoeda(double valor, String deMoeda, String paraMoeda) throws IOException, InterruptedException {
        String respostaJson = obterTaxaCambio(deMoeda);
        Gson gson = new Gson();
        RespostaTaxaCambio respostaTaxaCambio = gson.fromJson(respostaJson, RespostaTaxaCambio.class);
        Double taxa = respostaTaxaCambio.taxasDeConversao.get(paraMoeda);
        if (taxa == null) {
            throw new IllegalArgumentException("Moeda não suportada: " + paraMoeda);
        }
        return valor * taxa;
    }
}
