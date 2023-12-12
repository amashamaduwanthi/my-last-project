package lk.ijse.QR;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class GenerateQr {
    public GenerateQr(String id, String name, String address) throws WriterException, IOException {
        System.out.println("Hello QR!");

        String []data={id,name,address};
        String path="/home/amasha/Desktop/qr/student.jpg";

        BitMatrix matrix=new MultiFormatWriter().encode(Arrays.toString(data), BarcodeFormat.QR_CODE,500,500);

        MatrixToImageWriter.writeToPath(matrix,"jpg", Paths.get(path));


    }
}
