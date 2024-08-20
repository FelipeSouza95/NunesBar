package Mode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GeradorCSV {

    private String nomeArquivo = "./ArquivoCSV/Relatorio.csv";

    public void gerarCSV(ArrayList<Relatorios> listaRelatorios) {
        try {
            File arquivo = new File(nomeArquivo);
            boolean arquivoExiste = arquivo.exists(); // Verifica se o arquivo já existe

            // Abre o escritor para adicionar dados ao arquivo
            FileWriter escritor = new FileWriter(nomeArquivo, StandardCharsets.ISO_8859_1, true);

            // Se o arquivo não existir, adiciona o cabeçalho
            if (!arquivoExiste) {
                escritor.write("Nota;Valor;Data\n");
            }

            // Formato para a data
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // Escreve cada relatório no arquivo
            for (Relatorios r : listaRelatorios) {
                String dataFormatada = sdf.format(r.getData());
                escritor.write(r.getNota() + ";" + r.getValor() + ";" + dataFormatada + "\n");
            }

            escritor.flush();
            escritor.close();

            System.out.println("Arquivo CSV gerado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
