# Project-1-from-61B
Color Images, Edge Detection, and Run-Length Encodings
Part I:  Image Blurring and Edge Detection
==========================================
This part is worth 40% of your total score.  (8 points out of 20).
Implement a class called PixImage that stores a color image.  The PixImage
class will include methods for reading or changing the image’s pixels.  It will
also include a method for blurring an image and a method for detecting edges in
an image.  We have provided a skeleton file named PixImage.java that includes
prototypes for the public methods the class offers.  You are required to
provide implementations of all these methods.


readme
3
Part II:  Converting a Run-Length Encoding to an Image
======================================================
This part is worth 25% of your total score.  (5 points out of 20).
Part II(a):  Implement two simple constructors for RunLengthEncodings.  One
constructs a run-length encoding of a jet black image.  The other constructs
a run-length encoding based on four arrays provided as parameters to the
constructor.  These arrays tell you exactly what runs your run-length encoding
should contain, so you are simply converting arrays to a linked list.  (Read
the prototype in RunLengthEncoding.java.)
A large number of large image files can consume a lot of disk space.  Some
PixImages can be stored more compactly if we represent them as "run-length
encodings."  Imagine taking all the rows of pixels in the image, and connecting
them into one long strip.  Think of the pixels as being numbered thusly:

Part III:  Converting an Image to a Run-Length Encoding
=======================================================
This part is worth 25% of your total score.  (5 points out of 20).
Part IV:  Changing a Pixel in a Run-Length Encoding
===================================================
The last part is the hardest, but it is only worth 10% of the total score
(2 points out of 20), so don’t panic if you can’t finish it.
Write a RunLengthEncoding constructor that takes a PixImage object as its sole
parameter and converts it into a run-length encoding of the PixImage.
Implement the setPixel() method of the RunLengthEncoding class, which is
similar to the setPixel() method of the PixImage class.  However, this code is
much trickier to write.  Observe that setPixel() can lengthen, or even shorten,
an existing run-length encoding.  To change a pixel in a run-length encoded
image, you will need to find the right run in the linked list, and sometimes
break it apart into two or three runs.  If the changed pixel is adjacent to
other pixels of identical color, you should consolidate runs to keep memory use
down.  (Your check() method ensures that your encoding is as compact as
possible.)
The fields of the PixImage class MUST be private, so the RunLengthEncoding
constructor will rely upon the getWidth(), getHeight(), getRed(), getGreen(),
and getBlue() methods.
