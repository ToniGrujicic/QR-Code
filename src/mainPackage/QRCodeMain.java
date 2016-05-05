package mainPackage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;

import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeMain {
	
	static JTextField textField = null;
	static String qrCodeData = null;
	static String filePath = "myQRCode.png";
	public static QR qr = new QR(qrCodeData, filePath);
	public static String QRData;
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws WriterException, IOException, NotFoundException {
		@SuppressWarnings("unused")
		GUI mygui = new GUI();
		// Initial Hard coded data
		String qrCodeData = "Day:Tuesday\nTime:09h00 to 11h00\nSubject:Embedded\nRoom: D2004";
		String filePath = "myQRCode.png";
		
		Map hintMap = new HashMap();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// create the QR barcode
		qr.createQRCode(qrCodeData, filePath, hintMap, 200, 200);
		
		// read the barcode
		QRData = qr.readQRCode(filePath, hintMap);
		
		
		
	}		
}