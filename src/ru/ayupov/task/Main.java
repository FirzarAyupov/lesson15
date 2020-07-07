package ru.ayupov.task;

/**
 * Написать программу, которая будет создавать, переименовывать, копировать и удалять файл.
 * <p>
 * Написать рекурсивный обход всех файлов и подкаталогов внутри заданного каталога.
 * <p>
 * Дополнительное задание (необязательно): программа должна следить за глубиной рекурсии,
 * сдвигая название файла/каталога на соответствующее количество пробелов.
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    void createNewFile(String filename) {
        File file = new File(filename);
        try {
            if (file.createNewFile()) {
                System.out.printf("Создан новый файл %s", file.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void getList(String path) {
        try {
            String[] fileList = (new File(path)).list();
            if (fileList != null) {

                for (String fl : fileList) {
                    File file = new File(path + fl);
                    if (file.isFile()) {
                        System.out.println(file.getName());
                    }else {
                        System.out.printf("[%s]\n", file.getName());
                        getList(path + file.getName() + File.separator);
                    }

                }

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    void renameFile() {
        Scanner sc = new Scanner(System.in);
        getList("./testFolder/");
        System.out.println("Введите полное имя файла которое хотите переименовать");
        String oldFileName = sc.next();
        System.out.println("Введите новое полное имя файла");
        String newFileName = sc.next();

        try {
            File file = new File(oldFileName);
            File newFile = new File(newFileName)
            file.renameTo(newFileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void copyFile() {

    }

    void deleteFile() {

    }

    private void mainCommandList() {
        System.out.println("Выберите команду для выполнения:");
        System.out.println("1 - Создать файл");
        System.out.println("2 - Переименовать файл");
        System.out.println("3 - Копирвать файл");
        System.out.println("4 - Удалить файл");
        System.out.println("0 - Выход");
    }

    void run() {
        Scanner sc = new Scanner(System.in);
        mainCommandList();
        while (sc.hasNextInt()) {
            switch (sc.nextInt()) {
                case 1:
                    createNewFile(sc.next());
                    break;
                case 2:
                    renameFile();
                    break;
                case 3:
                    copyFile();
                    break;
                case 4:
                    deleteFile();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неизвестная комманда!");
                    mainCommandList();
                    break;
            }
        }


    }

    public static void main(String[] args) {
        new Main().run();
    }


}
