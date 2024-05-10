import java.io.*;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        int maxSKUNumber = 3937;
        int SKUsPerThread = 500;
        String filePath = "add file path here";

        handleSpecialCases(filePath);
        ExecutorService execService = Executors.newCachedThreadPool();
        int numsExecuted = 0;
        while (numsExecuted < maxSKUNumber) {
            final int startSKUToExecute = numsExecuted + 1;
            int endSKUToExecute = numsExecuted + SKUsPerThread;
            if (endSKUToExecute > maxSKUNumber)
                endSKUToExecute = maxSKUNumber;
            final int finalEndSKUToExecute = endSKUToExecute;
            execService.execute(() -> {
                startThread(startSKUToExecute, finalEndSKUToExecute, filePath);
            });
            numsExecuted = numsExecuted + SKUsPerThread;
        }
        execService.shutdown();
    }

    public static void startThread(int minSKUNumber, int maxSKUNumber, String filePath) {
        String current;
        for(double i = minSKUNumber; i <= maxSKUNumber; i++ ){
            current = String.format("%03.0f", i) + "A";
            try {
                URL url = new URL("https://cuddlyoctopus.com/wp-content/uploads/daki-thumbnails/" + current + ".jpg");
                readImage(url, current, filePath);
                System.out.println("Successfully downloaded " + current);
            } catch (Exception e){
                System.out.println("error with " + current);
                e.printStackTrace();
            }
            current = String.format("%03.0f", i) + "B";
            try {
                URL url = new URL("https://cuddlyoctopus.com/wp-content/uploads/daki-thumbnails/" + current + ".jpg");
                readImage(url, current, filePath);
                System.out.println("Successfully downloaded " + current);
            } catch (Exception e){
                System.out.println("Error with " + current);
                e.printStackTrace();
            }
        }
    }

    public static void handleSpecialCases(String filePath) {
        try {
            readImage(new URL("https://cuddlyoctopus.com/wp-content/uploads/2020/11/1149.png"), "1149", filePath);
            readImage(new URL("https://cuddlyoctopus.com/wp-content/uploads/2020/11/1150.png"), "1150", filePath);
            readImage(new URL("https://cuddlyoctopus.com/wp-content/uploads/2024/01/3690-daki-thumbnail.jpg"), "3690", filePath);
            readImage(new URL("https://cuddlyoctopus.com/wp-content/uploads/2024/01/3691-daki-thumbnail.jpg"), "3691", filePath);
            readImage(new URL("https://cuddlyoctopus.com/wp-content/uploads/2024/01/3704-daki-thumbnail.jpg"), "3704", filePath);
            readImage(new URL("https://cuddlyoctopus.com/wp-content/uploads/2024/02/3759-daki-thumbnail.jpg"), "3759", filePath);
            //SKU 3769 doesn't seem to exist
            //readImage(new URL(""), "3769", filePath);
            readImage(new URL("https://cuddlyoctopus.com/wp-content/uploads/2024/03/3819-daki-thumbnail.jpg"), "3819", filePath);
            readImage(new URL("https://cuddlyoctopus.com/wp-content/uploads/2024/03/3820-daki-thumbnail.jpg"), "3820", filePath);
        } catch (Exception e) {
            System.out.println("Failed to handle special cases");
            e.printStackTrace();
        }
    }

    public static void readImage(URL url, String current, String filePath) throws IOException {
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n;
        while (-1 != (n = in.read(buf)))
        {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();
        FileOutputStream fos = new FileOutputStream(filePath + current + ".jpg");
        fos.write(response);
        fos.close();
    }
}