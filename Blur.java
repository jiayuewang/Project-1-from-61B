

public class Blur {
  
  /**
   *  blurFile() reads a TIFF image file, blurs it, write the blurred image to
   *  a new TIFF image file, and displays both images.
   *
   *  @param filename the name of the input TIFF image file.
   *  @param numIterations the number of iterations of blurring to perform.
   */
  private static void blurFile(String filename, int numIterations) {
    System.out.println("Reading image file " + filename);
    PixImage image = ImageUtils.readTIFFPix(filename);

    System.out.println("Blurring image file.");
    PixImage blurred = image.boxBlur(numIterations);

    String blurname = "blur_" + filename;
    System.out.println("Writing blurred image file " + blurname);
    TIFFEncoder.writeTIFF(blurred, blurname);
    /*
    TIFFEncoder.writeTIFF(new RunLengthEncoding(edges), "rle" + blurname);
    */

    System.out.println("Displaying input image and blurred image.");
    System.out.println("Close the image to quit.");
    ImageUtils.displayTIFFs(new PixImage[] { image, blurred });
  }

  /**
   *  main() reads the command-line arguments and initiates the blurring.
   *
   *  The first command-line argument is the name of the image file.
   *  An optional second argument is number of iterations of blurring.
   *
   *  @param args the usual array of command-line argument Strings.
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("usage:  java Blur imagefile [iterations]");
      System.out.println("  imagefile is an image in TIFF format.");
      System.out.println("  interations is the number of blurring iterations" +
                         " (default 1).");
      System.out.println("The blurred image is written to blur_imagefile.");
      System.exit(0);
    }

    int numIterations = 1;
    if (args.length > 1) {
      try {
        numIterations = Integer.parseInt(args[1]);
      } catch (NumberFormatException ex) {
        System.err.println("The second argument must be a number.");
        System.exit(1);
      }
    }

    blurFile(args[0], numIterations);
  }
}
