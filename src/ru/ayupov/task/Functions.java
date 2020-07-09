package ru.ayupov.task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Functions {

    static String root;

    static {
        final File testFolder = new File("testFolder");
        testFolder.mkdir();
        root = testFolder.getAbsolutePath() + File.separator;
    }

    static void createNewFile(String filename) {
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

    static void createDir(String dirName) {
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

    static void renameFile(String oldFileName, String newFileName) {

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

    static void copyFile(String source, String dest) {
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

    static void recursiveDelete(String delFile) {
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

    static void getList(String path, int level) {
        File[] fileList = (new File(path)).listFiles();
        if (fileList != null) {
            for (File fl : fileList) {
                if (fl.isDirectory()) {
                    System.out.printf("%s[%s]\n", numToSpacing(level), fl.getName());
                    level++;
                    getList(path + fl.getName() + File.separator, level);
                    level--;
                } else {
                    System.out.println(numToSpacing(level) + fl.getName());
                }
            }
        }
    }

    static String numToSpacing(int num) {
        String result = "";
        for (int i = 0; i < num; i++) {
            result += "-";
        }
        return result;
    }

}
