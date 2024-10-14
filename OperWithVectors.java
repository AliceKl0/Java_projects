import java.util.Arrays;
import java.util.Scanner;

public class OperWithVectors {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите координаты 1-го вектора:");
        double[] coords_1 = new double[3];
        for (int i = 0; i < 3; i++) {
            coords_1[i] = scanner.nextDouble();
        }

        System.out.println("Введите координаты 2-го вектора:");
        double[] coords_2 = new double[3];
        for (int i = 0; i < 3; i++) {
            coords_2[i]  = scanner.nextDouble();
        }

        // 1 - Скалярное произведение
        double sc_ = ScProduct(coords_1, coords_2);
        System.out.println("Скалярное произведение: " + sc_);

        // 2 - Векторное произведение
        double[] vec_prod = VecProduct(coords_1, coords_2);
        System.out.println("Векторное произведение: " + Arrays.toString(vec_prod));

        // 3 - Сложение двух векторов
        double[] sum_ = AddVec(coords_1, coords_2);
        System.out.println("Сложение векторов: " + Arrays.toString(sum_));

        // 4 - Вычитание двух векторов
        double[] diff_ = SubVec(coords_1, coords_2);
        System.out.println("Вычитание векторов: " + Arrays.toString(diff_));

        // 5 - Модуль вектора
        double modul_ = Modul(coords_1);
        System.out.println("Модуль первого вектора: " + modul_);

        // 6 - Угол между двумя векторами
        double angle = AngBetVec(coords_1, coords_2);
        System.out.println("Угол между векторами: " + angle + " радиан");

        // 7 - Вращение вектора по матрице поворота (например, вокруг оси z на 90 градусов)
        double[][] rotationMatrix = {
                {0, -1, 0},
                {1, 0, 0},
                {0, 0, 1}
        };
        double[] rotatedVector = RotateVector(coords_1, rotationMatrix);
        System.out.println("Вращение вектора: " + Arrays.toString(rotatedVector));

        // 8 - Проекция на плоскость
        double[] proec_ = Proec(coords_1, "xy");
        System.out.println("Проекция на плоскость XY: " + Arrays.toString(proec_));

        // 9 - Проверка параллельности
        boolean isParallel = IsParallel(coords_1, coords_2);
        System.out.println("Параллельны ли векторы: " + isParallel);

        // 10 - Проверка перпендикулярности
        boolean isPerpendicular = IsPerpendicular(coords_1, coords_2);
        System.out.println("Перпендикулярны ли векторы: " + isPerpendicular);

        // 11 - Побитовые операции
        int[] intVector1 = ToInt(coords_1);
        BitOper0(intVector1);

        // 12 - Побитовые операции между двумя векторами
        int[] intVector2 = ToInt(coords_2);
        BitOper(intVector1, intVector2);

        // 13 - Преобразование в строковое представление
        StrRep(coords_1);
    }

    // 1 - Скалярное произведение
    public static double ScProduct(double[] v1, double[] v2) {
        return v1[0] * v2[0] + v1[1] * v2[1] + v1[2] * v2[2];
    }

    // 2 - Векторное произведение
    public static double[] VecProduct(double[] v1, double[] v2) {
        return new double[]{
                v1[1] * v2[2] - v1[2] * v2[1],
                v1[2] * v2[0] - v1[0] * v2[2],
                v1[0] * v2[1] - v1[1] * v2[0]
        };

    }

    // 3 - Сложение векторов
    public static double[] AddVec(double[] v1, double[] v2) {
        return new double[]{v1[0] + v2[0], v1[1] + v2[1], v1[2] + v2[2]};
    }

    // 4 - Вычитание векторов
    public static double[] SubVec(double[] v1, double[] v2) {
        return new double[]{v1[0] - v2[0], v1[1] - v2[1], v1[2] - v2[2]};
    }

    // 5 - Модуль вектора
    public static double Modul(double[] v) {
        return Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
    }

    // 6 - Угол между двумя векторами
    public static double AngBetVec(double[] v1, double[] v2) {
        double sc = ScProduct(v1, v2);
        double modul1 = Modul(v1);
        double modul2 = Modul(v2);
        return Math.acos(sc / (modul1 * modul2));
    }

    // 7 - Вращение вектора по заданной матрице поворота
    public static double[] RotateVector(double[] v, double[][] rotationMatrix) {
        return new double[]{
                rotationMatrix[0][0] * v[0] + rotationMatrix[0][1] * v[1] + rotationMatrix[0][2] * v[2],
                rotationMatrix[1][0] * v[0] + rotationMatrix[1][1] * v[1] + rotationMatrix[1][2] * v[2],
                rotationMatrix[2][0] * v[0] + rotationMatrix[2][1] * v[1] + rotationMatrix[2][2] * v[2]
        };
    }

    // 8 - Проекция на плоскость
    public static double[] Proec(double[] v, String axis_) {
        switch (axis_) {
            case "xy":
                return new double[]{v[0], v[1], 0};
            case "xz":
                return new double[]{v[0], 0, v[2]};
            case "yz":
                return new double[]{0, v[1], v[2]};
            default:
                return v;
        }
    }

    // 9 - Проверка параллельности
    public static boolean IsParallel(double[] v1, double[] v2) {
        double[] vec_pr = VecProduct(v1, v2);
        return vec_pr[0] == 0 & vec_pr[1] == 0 & vec_pr[2] == 0;
    }

    // 10 - Проверка перпендикулярности
    public static boolean IsPerpendicular(double[] v1, double[] v2) {
        return ScProduct(v1, v2) == 0;
    }

    // 11 - В int и побитовые операции
    public static int[] ToInt(double[] v) {
        return new int[]{(int) v[0], (int) v[1], (int) v[2]};
    }

    public static void BitOper0(int[] v) {
        System.out.println("Исходный вектор: " + Arrays.toString(v));

        // Побитовый оператор НЕ
        int[] not_ = new int[3];
        for (int i = 0; i < 3; i++) {
            not_[i] = ~v[i];
        }
        System.out.println("Побитовый НЕ: " + Arrays.toString(not_));

        // Влево на 3 бита
        int[] left_ = new int[3];
        for (int i = 0; i < 3; i++) {
            left_[i] = v[i] << 3;
        }
        System.out.println("Сдвиг влево на 3 бита: " + Arrays.toString(left_));

        // Вправо на 3 бита
        int[] right_ = new int[3];
        for (int i = 0; i < 3; i++) {
            right_[i] = v[i] >> 3;
        }
        System.out.println("Сдвиг вправо на 3 бита: " + Arrays.toString(right_));
    }

    // 12 - Побитовые операции между векторами
    public static void BitOper(int[] v1, int[] v2) {
        System.out.println("Исходный вектор 1: " + Arrays.toString(v1));
        System.out.println("Исходный вектор 2: " + Arrays.toString(v2));

        // Побитовый И
        int[] and_ = new int[3];
        for (int i = 0; i < 3; i++) {
            and_[i] = v1[i] & v2[i];
        }
        System.out.println("Побитовый И: " + Arrays.toString(and_));

        // Побитовый ИЛИ
        int[] or_ = new int[3];
        for (int i = 0; i < 3; i++) {
            or_[i] = v1[i] | v2[i];
        }
        System.out.println("Побитовый ИЛИ: " + Arrays.toString(or_));

        // Побитовое исключающее ИЛИ
        int[] xor_ = new int[3];
        for (int i = 0; i < 3; i++) {
            xor_[i] = v1[i] ^ v2[i];
        }
        System.out.println("Побитовое исключающее ИЛИ: " + Arrays.toString(xor_));
    }

    // 13 - В строковое представление
    public static void StrRep(double[] v) {
        int[] IntVector = ToInt(v);
        int[] mod10 = new int[3];
        for (int i = 0; i < 3; i++) {
            mod10[i] = IntVector[i] % 10;
        }
        String[] words = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < 3; i++) {
            result.append(words[mod10[i]]);
            if (i < 2) {
                result.append(", ");
            }
        }
        result.append("]");
        System.out.println("Строковое представление: " + result);

        // Подсчёт символов
        String str = result.toString();
        StringBuilder count_res = new StringBuilder();

        // Проходим по каждому символу строки
        for (int i = 0; i < str.length(); i++) {
            char CurrentChar = str.charAt(i);

            // Если символ уже подсчитан, пропускаем
            if (count_res.toString().contains(String.valueOf(CurrentChar))) {
                continue;
            }

            int count = 0;
            // Подсчитываем количество вхождений этого символа
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == CurrentChar) {
                    count++;
                }
            }

            // Добавляем символ и кол-во
            count_res.append(CurrentChar).append(":").append(count).append(", ");
        }

        // Вывод частоты символов
        System.out.print("Частота символов: " + count_res.substring(0, count_res.length() - 2));
        System.out.println();

        // К верхнему регистру
        System.out.println("В верхнем регистре: " + str.toUpperCase());
    }
}