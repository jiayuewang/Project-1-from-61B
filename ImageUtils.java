

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.media.jai.JAI;
import javax.media.jai.RenderedImageAdapter;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class ImageUtils {

 
  private static PixImage buffer2PixImage(BufferedImage bImage) {
    PixImage pImage = new PixImage(bImage.getWidth(), bImage.getHeight());
    for (int x = 0; x < bImage.getWidth(); x++) {
      for (int y = 0; y < bImage.getHeight(); y++) {
        Color color = new Color(bImage.getRGB(x, y));
        pImage.setPixel(x, y, (short) color.getRed(), (short) color.getGreen(),
                        (short) color.getBlue());
      }
    }
    return pImage;
  }


  static BufferedImage pixImage2buffer(PixImage pImage) {
    BufferedImage bImage = new BufferedImage(pImage.getWidth(),
                                             pImage.getHeight(),
                                             BufferedImage.TYPE_INT_ARGB);
    for (int x = 0; x < bImage.getWidth(); x++) {
      for (int y = 0; y < bImage.getHeight(); y++) {
        bImage.setRGB(x, y, new Color(pImage.getRed(x, y),
                                      pImage.getGreen(x, y),
                                      pImage.getBlue(x, y)).getRGB());
      }
    }
    return bImage;
  }

 
  private static BufferedImage readTIFF(String filename) {
    return (new RenderedImageAdapter(JAI.create("fileload", filename)))
           .getAsBufferedImage();
  }

  public static PixImage readTIFFPix(String filename) {
    return buffer2PixImage(readTIFF(filename));
  }

 
  public static RunLengthEncoding readTIFFRLE(String filename) {
    return new RunLengthEncoding(readTIFFPix(filename));
  }

 
  private static void writeTIFF(BufferedImage image, String filename) {
    JAI.create("filestore", image, filename, "tiff");
  }

  public static void writeTIFF(PixImage image, String filename) {
    writeTIFF(pixImage2buffer(image), filename);
  }

 
  public static void writeTIFF(RunLengthEncoding rle, String filename) {
    writeTIFF(rle.toPixImage(), filename);
  }

  private static void displayFrame(final JFrame frame) {
    try {
      synchronized (ImageUtils.class) {
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
              synchronized (ImageUtils.class) {
                ImageUtils.class.notify();
                frame.dispose();
              }
            }
          });
        frame.pack();
        frame.setVisible(true);
        ImageUtils.class.wait();
      }
    } catch (InterruptedException e) {
      System.out.println("Interrupted Exception in displayFrame().");
      e.printStackTrace();
    }
  }



  public static void displayTIFFs(PixImage[] images) {
    JFrame frame = new JFrame();
    Box box = Box.createHorizontalBox();
    for (int i = 0; i < images.length; i++) {
      box.add(new JLabel(new ImageIcon(pixImage2buffer(images[i]))));
      if (i < images.length - 1) {
        box.add(Box.createHorizontalStrut(10));
      }
    }
    frame.add(box);
    displayFrame(frame);
  }

 
  public static void displayTIFF(PixImage image) {
    displayTIFFs(new PixImage[] { image });
  }
}
