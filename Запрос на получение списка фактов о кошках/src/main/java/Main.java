import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    // переменная с адресом куда будем обращаться для получения данных
    public static final String REMOTE_SERVICE_URI = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

    public static void main(String[] args) throws IOException {

        //создаем и настраиваем клиент для HTTP запросов
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build()) //возвращает настроенный RequestConfig
                .build();//возвращает CloseableHttpClient с указанными параметрами запроса


        // создание объекта запроса с произвольными заголовками
        HttpGet request = new HttpGet(REMOTE_SERVICE_URI);
        //формируем запрос
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        // отправка запроса
        CloseableHttpResponse response = httpClient.execute(request);
        // вывод полученных заголовков
        Arrays.stream(response.getAllHeaders()).forEach(System.out::println);
        // чтение тела ответа
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
        //выводим тело ответа в консоль
        System.out.println(body);
        //создаем обьект мапер для преобразования  json d java  обьекты
        ObjectMapper objectMapper = new ObjectMapper();
        //преобразовываем json  в java обтекты
        //readValue  принимает  json  данные и тип в который
        // нужно преобразовать эти данные
        List<Cats> cats = objectMapper.readValue(body,
                objectMapper.getTypeFactory()
                        .constructParametricType(List.class, Cats.class)); //constructParametricType создает список обьектов заданного типа
        //фильтруем список cats  и записываем данные  у которых upvoters  не равен  null
        // в новый список
        List<Cats> filteredFacts = cats.stream()
                .filter(fact -> fact.upvotes != null && fact.upvotes > 0)
                .collect(Collectors.toList());
        // выводим список в консоль
        filteredFacts.forEach(fact -> System.out.println("Upvotes: "
                + fact.upvotes + ", Text: " + fact.text));

    }

}
