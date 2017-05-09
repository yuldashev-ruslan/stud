package PackageRegEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//      Задачи
//        1. Написать регулярное выражение для проверки является ли заданная строка датой  dd.mm.yyyy
//        2. Заменить все вариации слова Таиланд на Италия, Испания на Кипр
//        «Таиланд, тайланд, Тайланд , Испания , Англия , испании, Тайланд2233, 333тайланд»

class Main {

    //Соответствие строки регулярному выражению
    public static boolean checkValueMathRegExp(String value, String regExp){
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    //Является ли строка датой
    public static void checkData(String data){
        if (checkValueMathRegExp(data, "^(0[1-9]|1[0-9]|2[0-9]|3[01])\\.(0[1-9]|1[012])\\.[0-9]{4}$")){
            System.out.println("Строка " + data + " является датой.");
        }
        else {System.out.println("Строка " + data + " не является датой.");}
    }

    //Заменить все вариации слова Таиланд на Италия, Испания на Кипр
    public static void replaceWord(String string){
        string = string.replaceAll("(Т|т)а(и|й)ланд", "Италия");
        string = string.replaceAll("Италия\\d{4}", "Италия");
        string = string.replaceAll("\\d{3}Италия", "Италия");
        string = string.replaceAll("(И|и)спани(я|и)", "Кипр");
        System.out.println(string);
    }

    public static void main (String[] args){

        String data = "31.04.9999";
        checkData(data);
        String string = "Таиланд, тайланд, Тайланд , Испания , Англия , испании, Тайланд2233, 333тайланд";
        System.out.println(string);
        replaceWord(string);

    }
}
