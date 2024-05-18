
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ImageDownloader {

    public void downloadImage(String imageUrl, String fileName) {
        //создаем входящий поток для чтения данных из указанного url
        try (InputStream in = new URL(imageUrl).openStream()) {
            //создаем обьект Path что бы указать путь к файлу куда сохраним картинку
            // Path.of(fileName) создаем путь к файлу с именем fileName
            Path destination = Path.of(fileName);
            //копируем данные из потока in в файл указанный в destination
            // StandardCopyOption.REPLACE_EXISTING означает что если файл
            // существует с таким именем он будет заменен
            Files.copy(in, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
