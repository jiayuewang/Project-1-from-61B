

public class Sobel {
  
 
  private static void sobelFile(String filename, int numIterations,
                                boolean rle) {
    System.out.println("Reading image file " + filename);
    PixImage image = ImageUtils.readTIFFPix(filename);
    PixImage blurred = image;

    if (numIterations > 0) {
      System.out.println("Blurring image file.");
      blurred = image.boxBlur(numIterations);

      String blurname = "blur_" + filename;
      System.out.println("Writing blurred image file " + blurname);
      TIFFEncoder.writeTIFF(blurred, blurname);
    }

    System.out.println("Performing Sobel edge detection on image file.");
    PixImage sobeled = blurred.sobelEdges();

    String edgename = "edge_" + filename;
    System.out.println("Writing grayscale-edge image file " + edgename);
    TIFFEncoder.writeTIFF(sobeled, edgename);
    if (rle) {
      String rlename = "rle_" + filename;
      System.out.println("Writing run-length encoded grayscale-edge " +
                         "image file " + rlename);
      TIFFEncoder.writeTIFF(new RunLengthEncoding(sobeled), rlename);
    }

    if (numIterations > 0) {
      System.out.println("Displaying input image, blurred image, and " +
                         "grayscale-edge image.");
      System.out.println("Close the image to quit.");
      ImageUtils.displayTIFFs(new PixImage[] { image, blurred, sobeled });
    } else {
      System.out.println("Displaying input image and grayscale-edge image.");
      System.out.println("Close the image to quit.");
      ImageUtils.displayTIFFs(new PixImage[] { image, sobeled });
    }
  }

 
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("usage:  java Sobel imagefile [iterations] [RLE]");
      System.out.println("  imagefile is an image in TIFF format.");
      System.out.println("  interations is the number of blurring iterations" +
                         " (default 0).");
      System.out.println("  any third argument (RLE) turns on run-length " +
                         "encoding in the output file");
      System.out.println("The grayscale-edge image is written to " +
                         "edge_imagefile.");
      System.out.println("If blurring is selected, " +
                         "the blurred image is written to blur_imagefile.");
      System.exit(0);
    }

    int numIterations = 0;
    if (args.length >= 2) {
      try {
        numIterations = Integer.parseInt(args[1]);
      } catch (NumberFormatException ex) {
        System.err.println("The second argument must be a number.");
        System.exit(1);
      }
    }

    sobelFile(args[0], numIterations, args.length >= 3);
  }
}
