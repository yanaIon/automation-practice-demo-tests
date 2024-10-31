package demo.practice;


import com.codeborne.pdftest.PDF;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenAndReadZipFileTests {
    // Инициализация загрузчика классов, теперь cl имеет доступ к файлам, которые находятся в ресурсах
    private static final ClassLoader cl = OpenAndReadZipFileTests.class.getClassLoader();

    @BeforeAll
    static void zipTest() throws Exception {
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
            assertTrue(fileNames.contains("example.xlsx"), "Файл example.xlsx отсутствует в архиве");
            assertTrue(fileNames.contains("acrobat_example.pdf"), "Файл acrobat_example.pdf отсутствует в архиве");
        }
    }

    @Test
    void pdfTest() throws Exception {
        File pdfFile = new File("src/test/resources/extracted/acrobat_example.pdf"); // указываем путь к PDF-файлу после извлечения

        // Проверяем, что PDF-файл существует
        assertTrue(pdfFile.exists(), "Файл PDF не найден");

        // Читаем содержимое PDF-файла
        PDF pdf = new PDF(pdfFile);
        String expectedText = "Руководство пользователя";// ожидаемый текст

        // Проверяем, что PDF содержит ожидаемый текст
        assertTrue(pdf.text.contains(expectedText), "Ожидаемый текст не найден в PDF-файле");
    }

    @Test
    void xlsxParseTest() throws IOException {
        // Указываем путь к XLSX файлу
        File xlsxFile = new File("src/test/resources/extracted/example.xlsx");

        // Открываем файл
        try (FileInputStream fis = new FileInputStream(xlsxFile);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            // Проверяем заголовки
            Assertions.assertEquals("Внешний идентификатор для импорта", sheet.getRow(0).getCell(0).getStringCellValue());
            Assertions.assertEquals("Вышестоящий отдел", sheet.getRow(0).getCell(1).getStringCellValue());
            Assertions.assertEquals("Название", sheet.getRow(0).getCell(2).getStringCellValue());

            // Проверяем первую строку данных
              Assertions.assertEquals("OU001", sheet.getRow(1).getCell(0).getStringCellValue());
             Assertions.assertEquals("Коммерческий департамент", sheet.getRow(1).getCell(2).getStringCellValue());

            // Проверяем вторую строку данных
            Assertions.assertEquals("OU002", sheet.getRow(2).getCell(0).getStringCellValue());
            Assertions.assertEquals("OU001", sheet.getRow(2).getCell(1).getStringCellValue());
            Assertions.assertEquals("Маректинг и реклама", sheet.getRow(2).getCell(2).getStringCellValue());
        }
    }
}
