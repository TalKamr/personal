import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

public class jpg {
    BufferedImage i;
    static public int num=0;
     String name;
    public jpg(String in) throws IOException{
        i = (ImageIO.read(new File(in)));
        name = in;

    }

    public String getName(){
        return name;
    }
    public int getNum(){
        return num;
    }

    private void compress() throws Exception {
        File out = new File("compressed"+num+".jpg");
        OutputStream os = new FileOutputStream(out);
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = (ImageWriter) writers.next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);
        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.2f);
        writer.write(null, new IIOImage(i, null, null), param);
        ios.close();
        os.close();
        num++;
    }

    public void prep()throws Exception{
        compress();
    }

}
