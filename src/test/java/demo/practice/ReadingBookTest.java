package demo.practice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;


public class ReadingBookTest {
    @Test
    void jsonDeserializeTest() throws Exception {
        // Создаем ObjectMapper для десериализации
        ObjectMapper objectMapper = new ObjectMapper();

        // Читаем JSON из файла и преобразуем его в объект Book
        Book book = objectMapper.readValue(new File("src/test/resources/extracted/book.json"), Book.class);

        // Проверяем корректность данных
        Assertions.assertEquals("Java Programming Guide", book.getTitle());
        Assertions.assertEquals("John Doe", book.getAuthor());
        Assertions.assertEquals(2023, book.getYear());
        Assertions.assertEquals("Tech Books Publishing", book.getPublisher());

        // Проверяем массив глав
        Assertions.assertEquals(3, book.getChapters().size());
        Assertions.assertEquals("Introduction to Java", book.getChapters().get(0).getTitle());
        Assertions.assertEquals(1, book.getChapters().get(0).getChapterNumber());
        }
}
