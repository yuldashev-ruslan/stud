package PackageDate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//Файл, в нем построчно записаны ФИО и дата рождения, уменьшить даты на год, вывести на консоль

class Main {

    public static void main (String[] args) throws IOException, ParseException {

        //Читаем из файла в список строк
        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Администратор\\IdeaProjects\\untitled16\\yuldashev\\src\\PackageDate\\file.txt"), StandardCharsets.UTF_8);
        //Результирующий список строк
        List<String> lineList = new ArrayList<>();
        //Печать в консоль
        lines.forEach((k)->System.out.println(k));
        //Дата
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        Date currentTime;
        String[] strings;
        System.out.println();
        //Построчно обрабатываем даты
        for (String str: lines) {
           strings = str.split(" ");
           currentTime = format.parse(strings[3]);
           calendar.setTime(currentTime);
           //Уменьшаем на год
           calendar.add(Calendar.YEAR, -1);
           //Новые значения дат
           lineList.add(strings[0] + " " + strings[1] + " " + strings[2] + " " + format.format(calendar.getTime()));
        }
        //Печать новых значений
        lineList.forEach((k)->System.out.println(k));
    }
}
