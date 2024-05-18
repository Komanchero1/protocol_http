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
import java.util.Map;


public class Main {
    public static final String REMOTE_SERVICE_URI = "https://api.nasa.gov/" +
            "planetary/apod?api_key=D7vWMOaCVElWrVmYfebDRLKmMYGmf1eLO7NbAriZ";

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
        Arrays.stream(response.getAllHeaders())
                .forEach(System.out::println);

        // чтение тела ответа
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
        //выводим тело ответа в консоль
        System.out.println(body);

        //создаем обьект мапер для преобразования  json d java  обьекты
        ObjectMapper objectMapper = new ObjectMapper();
        //преобразовываем json строку  в обьект типа Template
        Map<String, Template> result = objectMapper.readValue(body, Map.class);
        //выводим result в консоль
        System.out.println(result);

        //создаем обьект класса  ImageDownloader
        ImageDownloader imageDownloader = new ImageDownloader();
        //извлекаем url  из result
        String imageUrl = String.valueOf(result.get("url"));
        //находим последнее вхождение символа '/ '  в строке  imageUrl
        // и берем все символы после него б это и есть имя файла
        String fileName = imageUrl.substring(imageUrl.lastIndexOf('/') + 1);
        //вызываем метод downloadImage передаем внего URL и имя файла ,
        //скачиваем изображение и сохраняем его
        imageDownloader.downloadImage(imageUrl, fileName);
    }
}
