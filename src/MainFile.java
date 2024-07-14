import java.io.File;
import java.util.Arrays;

public class MainFile {

    public static void main(String[] args)  {
        MainFile mainFile = new MainFile();
        File file = new File("/Users/gennadykudryavtsev/Desktop/MyCode/MyCourseBJ/basejava");
        mainFile.printFiles(file);
    }

    public void printFiles(File file) {

        File[] lst = file.listFiles();
        if (lst == null) return;
        Arrays.sort(lst);
        for (int i = 0; i < lst.length; i++) {
            if (lst[i].isDirectory()) {
                if (lst[i].getName().equals(".git")){
                    continue;
                }
                System.out.println(lst[i].getName());
                printFiles(lst[i]);
            }
        }
        for (int i = 0; i < lst.length; i++) {
            if (!lst[i].isDirectory()) {
                System.out.println(" " + lst[i].getName());
            }
        }
        System.out.println();
    }
}
