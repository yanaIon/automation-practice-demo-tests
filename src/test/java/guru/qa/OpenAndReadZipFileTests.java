package guru.qa;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class OpenAndReadZipFileTests {
    // Инициализация загрузчика классов, теперь cl имеет доступ к файлам, которые находятся в ресурсах
    private static final ClassLoader cl = OpenAndReadZipFileTests.class.getClassLoader();

    @Test
    void zipTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("example.zip");
             ZipInputStream zs = new ZipInputStream(is)) { // создается объект ZipInputStream для разархивации содержимого InputStream
            File extractedFolder = new File("src/test/resources/extracted"); // будет хранить ссылку на директорию для извлечения файлов из архива
            List<String> fileNames = new ArrayList<>(); // создаем список для имен файлов

            ZipEntry entry; // представляющая текущий файл внутри архива.
            while ((entry = zs.getNextEntry()) != null) { // убеждаемся, что имя текущего файла (entry.getName()) содержит нужные названия
                // Пропускаем специальные директории
                if (entry.getName().startsWith("__MACOSX")) {
                    continue;
                }
                fileNames.add(entry.getName()); // добавляем имя файла в список

                // Записываем содержимое файла из архива
                File file = new File(extractedFolder, entry.getName());
                try (OutputStream os = new FileOutputStream(file)) {
                    zs.transferTo(os);  // Копируем данные из архива в файл на диске
                }
            }

            // Проверки на наличие файлов после цикла
            Assertions.assertTrue(fileNames.contains("example.csv"), "Файл example.csv отсутствует в архиве");
            Assertions.assertTrue(fileNames.contains("example.xlsx"), "Файл example.xlsx отсутствует в архиве");
            Assertions.assertTrue(fileNames.contains("acrobat_example.pdf"), "Файл acrobat_example.pdf отсутствует в архиве");
        }
    }
}