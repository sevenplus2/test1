package com.barcodegen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import javax.imageio.ImageIO;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

public class BarcodeGen2 {
	public static void main(String[] args) throws BarcodeException, IOException, OutputException, NotFoundException,
			ChecksumException, FormatException {
		String imagePath = "/home/lappy-03/Pictures/CODE128A.png";
		enCode("PIDGRAPEX090295", imagePath);
		deCode(imagePath);
	}

	public static void enCode(String label, String imagePath) throws BarcodeException, IOException, OutputException {
		Barcode barcode = BarcodeFactory.createCode128A(label);
		// Barcode barcode = BarcodeFactory.createEAN13(label);
		// Barcode barcode = BarcodeFactory.createEAN128(label);

		// UPCA Barocde contains only 11 numeric labels
		// Barcode barcode = BarcodeFactory.createUPCA(label);
		barcode.setResolution(250);

		BarcodeImageHandler.savePNG(barcode, new File(imagePath));

	}

	public static void deCode(String imagePath)
			throws IOException, NotFoundException, ChecksumException, FormatException {
		InputStream barCodeInputStream = new FileInputStream(imagePath);
		BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
		LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Reader reader = new MultiFormatReader();
		Result result = reader.decode(bitmap);
		System.out.println("Barcode text is " + result.getText());

	}

}
