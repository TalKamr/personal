import java.awt.geom.Area;
import java.io.File;
import java.io.FileNotFoundException;

import com.itextpdf.*;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.AreaBreakType;

public class pdf {

    File[] files = new File[99];
    int num=0;
    String output="";

    public pdf(String output){
        this.output = output;
    }
    public void addjpg(String in){
        files[num] = new File(in);
        num++;

    }

    public void write() throws Exception{
        createDoc(new File(output));
    }

    private void createDoc(File in) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(in));
        Document doc = new Document(pdfDoc);
        for (int i = 0; i < num; i++) {
            ImageData image = ImageDataFactory.create(String.valueOf(files[i]));
            Image pdfI = new Image(image);
            pdfI.setRotationAngle(-1.5708);
            pdfI.setAutoScale(true);
            doc.setMargins(0,0,0,0);
            doc.add(pdfI);

            if(i!=num-1)
            doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        }
        doc.close();
    }

}
