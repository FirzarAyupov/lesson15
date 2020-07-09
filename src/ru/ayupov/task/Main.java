package ru.ayupov.task;

import java.util.Scanner;

import static ru.ayupov.task.Functions.*;

/**
 * Написать программу, которая будет создавать, переименовывать, копировать и удалять файл.
 * <p>
 * Написать рекурсивный обход всех файлов и подкаталогов внутри заданного каталога.
 * <p>
 * Дополнительное задание (необязательно): программа должна следить за глубиной рекурсии,
 * сдвигая название файла/каталога на соответствующее количество пробелов.
 */

public class Main {

    void run() {
        //Создание тестовой папки
        createDir("");

        createNewFile("file1.txt");
        createNewFile("file2.txt");
        createNewFile("file3.txt");
        createNewFile("file4.txt");

        createDir("newFolder");
        createDir("newFolder1\\newFolder2");
        createDir("newFolder3\\newFolder4\\newFolder5");

        renameFile("file1.txt", "file1NewName.txt");
        renameFile("file2.txt", "newFolder\\file2NewName.txt");
        renameFile("file3.txt", "newFolder1\\newFolder2\\file3NewName.txt");
        renameFile("file4.txt", "newFolder3\\newFolder4\\newFolder5\\file4NewName.txt");

        copyFile("file1NewName.txt", "newFolder3\\newFolder4\\file5NewName.txt");

        System.out.printf("Структура папки \"%s\":\n", root);
        getList(root, 0);

        Scanner sc = new Scanner(System.in);
        System.out.printf("Удалить директорию \"%s\" полностью? (y/n)", root);
        String answer = sc.next();

        switch (answer.toUpperCase()) {
            case "Y":
                recursiveDelete(root);
                break;
            case "N":
                break;
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
