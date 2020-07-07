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
import java.nio.file.Files;
import java.util.Scanner;

public class Main {
    static int level;

    String root = (new File(".")).getAbsolutePath() + "\\testFolder\\";

    void createNewFile(String filename) {
        File file = new File(root + filename);
        System.out.printf("Создание файла \"%s\"\n", file.getAbsolutePath());
        try {
            if (file.exists()) {
                System.out.printf("Невозможно создать файл \"%s\", файл с таким именем уже существует\n", file.getAbsolutePath());
            } else {
                if (file.createNewFile()) {
                    System.out.printf("Файл \"%s\" успешно создан\n", file.getAbsolutePath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void createDir(String dirName) {
        File dir = new File(root + dirName);
        if (dir.exists()) {
            System.out.printf("Невозможно создать директорию \"%s\", директория с таким именем уже существует\n", dir.getAbsolutePath());
            return;
        }
        if (dir.mkdirs()) {
            System.out.printf("Директория \"%s\" успешно создана\n", dir.getAbsolutePath());
        } else {
            System.out.printf("Ошибка при созаднии каталога %s\n", dir.getAbsolutePath());
        }
    }

    void renameFile(String oldFileName, String newFileName) {

        File file = new File(root + oldFileName);
        File newFile = new File(root + newFileName);
        if (newFile.exists()) {
            System.out.printf("Невозможно переименовать файл \"%s\", файл с таким именем уже существует\n", newFile.getAbsolutePath());
            return;
        }
        if (file.renameTo(newFile)) {
            System.out.printf("Файл \"%s\" успешно переименован на \"%s\"\n", file.getAbsolutePath(), newFile.getAbsolutePath());
        } else {
            System.out.println("Ошибка при переименовании файла");
        }
    }

    void copyFile(String source, String dest) {
        File sourceFile = new File(root + source);
        File destFile = new File(root + dest);
        if (destFile.exists()) {
            System.out.printf("Невозможно скопировать файл \"%s\", файл с таким именем уже существует\n", destFile.getAbsolutePath());
            return;
        }
        try {
            Files.copy(sourceFile.toPath(), destFile.toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void recursiveDelete(String delFile) {
        File file = new File(delFile);

        if (!file.exists()) {
            return;
        }
        if (file.isDirectory() || file.listFiles() != null) {
            for (File f : file.listFiles()) {
                recursiveDelete(f.getAbsolutePath());
            }
        }
        if (file.delete()) {
            System.out.printf("\"%s\" удален\n", file.getAbsolutePath());
        }

    }

    void getList(String path) {
        File[] fileList = (new File(path)).listFiles();
        if (fileList != null) {
            for (File fl : fileList) {
                if (fl.isDirectory()) {
                    System.out.printf("%s[%s]\n", numToSpacing(level), fl.getName());
                    level++;
                    getList(path + fl.getName() + File.separator);
                } else {
                    System.out.println(numToSpacing(level) + fl.getName());
                }
            }
            level--;
        }
    }

    String numToSpacing(int num) {
        String result = "";
        for (int i = 0; i < num; i++) {
            result += "-";
        }
        return result;
    }

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
        getList(root);

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
