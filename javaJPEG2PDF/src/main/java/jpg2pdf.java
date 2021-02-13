import static javax.swing.JOptionPane.*;

import java.io.File;

public class jpg2pdf {
    public static void main(String args[]){
        String a = System.getProperty("user.dir");
        try {
            doIt(a);
        }catch (Exception e){
            showMessageDialog(null, "something goofed.");
        }
    }

    public static void doIt(String dir) throws Exception{

        addJpgToPdfDelComJpg(dir,addJpgInCurrentDir(dir));

    }

    public static void addJpgToPdfDelComJpg(String dir, jpg[] in) throws Exception{
        pdf out = new pdf(dir+"\\out.pdf");

        for (int i = 0; i < in.length; i++) {

            if(in[i]!=null) {
                in[i].prep();
                out.addjpg(dir + "\\compressed" + (i) + ".jpg");
            }
        }
        out.write();

        File f = new File(dir + "\\compressed" + (0) + ".jpg");
        int x=1;
        while(f.exists()){
            f.delete();
            f= new File((dir + "\\compressed" + (0+x) + ".jpg"));
            x++;
        }


    }

    public static jpg[] addJpgInCurrentDir(String dir)throws Exception {
        String[] jpgs = jpgs(dir);
        jpg[] images = new jpg[99];
        for (int i = 0; i < jpgs.length-1; i++) {
            if(jpgs[i]!=null) {
                images[i] = new jpg(dir + "\\" + jpgs[i]);
            }
        }
        return images;
    }

    public static String[] jpgs(String dir){
        File currentDir = new File(dir);
        File[] files = currentDir.listFiles();
        String[] a = new String[99];
        int num = 0;
        for (int i = 0; i < files.length; i++) {
            if(!files[i].isDirectory() && files[i].exists()) {
                if((files[i].getName().contains(".jpg")) && !(files[i].getName().contains("compressed"))) {
                    a[num] = files[i].getName();
                    num++;
                }
            }
        }
        return a;
    }
}
