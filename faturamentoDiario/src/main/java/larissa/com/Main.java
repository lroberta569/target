package larissa.com;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class Main {
      try {
        Gson gson = new Gson();
        FileReader reader = null;
        try {
            reader = new FileReader("larissa/com/faturamentoDiario.json");
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }


        Type listType = new TypeToken<List<FaturamentoDiario>>() {
        }.getType();
        List<FaturamentoDiario> faturamento = gson.fromJson(reader, listType);

        double menor = Double.MAX_VALUE;
        double maior = Double.MIN_VALUE;
        double soma = 0.0;
        int diasComFaturamento = 0;

        for (FaturamentoDiario dia : faturamento) {
            double valor = dia.getValor();
            if (valor > 0) {
                if (valor < menor) menor = valor;
                if (valor > maior) maior = valor;
                soma += valor;
                diasComFaturamento++;
            }
        }

        double media = soma / diasComFaturamento;

        int diasAcimaDaMedia = 0;
        for (FaturamentoDiario dia : faturamento) {
            if (dia.getValor() > media) {
                diasAcimaDaMedia++;
            }
        }

        System.out.printf("Menor faturamento: %.2f\n", menor);
        System.out.printf("Maior faturamento: %.2f\n", maior);
        System.out.printf("Dias acima da média: %d\n", diasAcimaDaMedia);

    } catch (Exception e) {
        e.printStackTrace();
    }

}
