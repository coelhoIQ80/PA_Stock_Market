/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pastockmarket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Esta classe é responsável por fazer o download de um ficheiro web.
 * O construtor recebe o url, e.g. "http://www.yahoo.com" e tenta fazer
 * o download do documento. Se forem lançadas excepções é porque o endereço
 * é inválido ou ocorreu outro problema qualquer (por exemplo, o PC não está
 * ligado à internet.
 *
 * @author brunomnsilva
 */
public class UrlDownloader {

    /* Buffer para guardar o ficheiro*/
    StringBuffer buffer;

    /**
     * Constroi uma instancia desta classe com o url do ficheiro pretendido.
     *
     * @param url O url do ficheiro pretendido
     */
    public UrlDownloader(String url) throws RecursoIndisponivelException {

        //tipicamente serao ficheiros relativamente grandes, portanto vamos
        //estabelecer uma capacidade inicial elevada
        buffer = new StringBuffer(10000);

        //objectos necessários para descarregar um ficheiro
        URL u;
        InputStream is = null;
        BufferedReader dis;

        try {
            u = new URL(url);

            is = u.openStream(); //lança IOException
            dis = new BufferedReader(new InputStreamReader(is));

            String linha = "";
            while ((linha = dis.readLine()) != null) {
                buffer.append(linha);
                buffer.append("\n");
            }

        } catch (MalformedURLException e1) {
            throw new RecursoIndisponivelException("O URL não é válido: " + url);
        } catch (IOException e2) {
            throw new RecursoIndisponivelException("Ocorreu um erro: " + e2.getMessage());
        } finally {
            try {
                is.close();
            } catch (Exception ioe) {
                // tipicamente se ocorrer, quer dizer que houve problema a descarregar o conteudo,
                //possivelmente porque nao existe
                throw new RecursoIndisponivelException("O recurso não existe: " + url);
            }
        }

    }

    public String obterFicheiro() {
        return buffer.toString();
    }
}
