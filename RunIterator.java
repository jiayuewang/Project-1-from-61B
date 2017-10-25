

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("rawtypes")
public class RunIterator implements Iterator {

 
  RunIterator() {
    // Your solution here.  You may add parameters to the method signature.
  }

  /**
   *  hasNext() returns true if this iterator has more runs.  If it returns
   *  false, then the next call to next() may throw an exception.
   *
   *  @return true if the iterator has more elements.
   */
  public boolean hasNext() {
    // Replace the following line with your solution.
    return false;
  }

  
  public int[] next() {
    // Construct a new array of 4 ints, fill in its values, and return it.
    // Don't forget to advance the RunIterator's pointer so that the next
    // call to next() will return the subsequent run.

    // Replace the following line with your solution.
    return new int[4];
  }

  /**
   *  remove() would remove from the underlying run-length encoding the run
   *  identified by this iterator, but we are NOT implementing it.
   *
   *  DO NOT CHANGE THIS METHOD.
   */
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
