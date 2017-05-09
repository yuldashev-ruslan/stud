package PackageMatrix;

import java.util.Random;

//Реализовать класс по работе с матрицами, должны быть реализованы методы:
//заполнение матрицы произвольными значениями,
//        сложение матриц,
//        Получение размеров матрицы
//        Вывод матрицы на экран
//        Предусмотреть обработку ошибок, если при создании матрицы в конструктор передаются отрицательные значения размеров матрицы.

class Matrix {

    public int A, B;
    public double[][] matrix;

    //Создание матрицы
    Matrix(int a, int b){
        try {
            A = a;
            B = b;
            if (A == 0 | B == 0){System.out.println("В размерах матрицы нулевые значения.");}
            else{this.matrix = new double[A][B];}
        }
        catch (Exception e){
            System.out.println("Отрицательные значения размеров матрицы.");
        }
    }

    //Заполнение матрицы
    public void fillingMatrix(){

        Random random = new Random();
        for (int i = 0; i < A ; i++) {
            for (int j = 0; j < B; j++) {
                this.matrix[i][j] = random.nextInt(100);
            }
        }
    }

    //Печать матрицы
    public void printingMatrix(){
        for (int i = 0; i < A ; i++) {
            for (int j = 0; j < B; j++) {
                System.out.print(this.matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //Размеры матрицы
    public void measureMatrix(){
        System.out.println("Размерность матрицы: " + this.A + " на " + this.B);
    }
}

class Main {

    //Сложение матриц
    public void additionMatrix(Matrix matrix1, Matrix matrix2, Matrix matrix3){
        if ((matrix1.A == matrix2.A & matrix1.A == matrix3.A & matrix2.A == matrix3.A) &
                (matrix1.B == matrix2.B & matrix1.B == matrix3.B & matrix2.B == matrix3.B)){
            for (int i = 0; i < matrix1.A ; i++) {
                for (int j = 0; j < matrix1.B; j++) {
                    matrix3.matrix[i][j] = matrix1.matrix[i][j] + matrix2.matrix[i][j];
                }
            }
            matrix3.printingMatrix();
            matrix3.measureMatrix();
        }
        else {
            System.out.println("Размерность матриц не одинаковая.");
        }
    }

    public static void main (String[] args){

        Matrix matrix1 = new Matrix(4,5);
        matrix1.fillingMatrix();
        matrix1.printingMatrix();
        matrix1.measureMatrix();

        Matrix matrix2 = new Matrix(4,5);
        matrix2.fillingMatrix();
        matrix2.printingMatrix();
        matrix2.measureMatrix();

        Matrix matrix3 = new Matrix(4,5);
        Main main = new Main();
        main.additionMatrix(matrix1, matrix2, matrix3);
    }
}

