package mytools;

/**
 * Created by adrian on 5/15/17.
 */


import java.io.File;
import java.util.List;

public class RecursiveFolderEnumerator {

    public void listRecursive(File dir) {
        if (dir.isDirectory()) {
            File[] items = dir.listFiles();
            for (File item : items) {
                System.out.println(item.getAbsoluteFile());
                if (item.isDirectory())
                    listRecursive(item);
            }
        }
    }

    public List<String> listRecursiveFunction(File dir, List<String> actualList) {
        if (dir.isDirectory()) {
            File[] items = dir.listFiles();
            for (File item : items) {
                actualList.add(item.getAbsolutePath());
                if (item.isDirectory())
                    listRecursiveFunction(item, actualList);

            }
        }
        return actualList;
    }
}