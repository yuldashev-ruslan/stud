package PackageMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


//Задача – Создать HashMap , ключ  - Имя, значение – Дата рождения, увеличить каждую дату рождения на один месяц, вывести на консоль

class Main {

    public static void main (String[] args) throws ParseException {

        //Заполняем массив
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("Дмитрий", "01.01.1995");
        hashMap.put("Сергей", "02.02.1996");
        hashMap.put("Николай", "03.03.1997");
        //Печать
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        //Работа с датами
        SimpleDateFormat formatter = new SimpleDateFormat();
        formatter.applyPattern("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        Date currentTime;
        String key, value;
        System.out.println();
        //Печать результата
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            currentTime = formatter.parse(value);
            calendar.setTime(currentTime);
            //Увеличиваем на месяц
            calendar.add(Calendar.MONTH, +1);
            System.out.println(key + " : " + formatter.format(calendar.getTime()));
        }

    }

}
