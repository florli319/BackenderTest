import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {
  
  public static void main(String[] args) {
    //Example to test
    int k = 3; //Segment length
    int m = 5; //Range for array element
    int[] a = {2, 1, 3, 1, 2, 2, 3};
    
    new Solution().solution(k, m, a);
  }
  
  /**
   * Find integers that may become a leader
   * @param k: array N integers
   * @param m: Segment length
   * @param a: Max value allowed for array elements
   * @return int array
   */
  public int[] solution(int k, int m, int[] a) {
    
    validateParameters(k, m, a);
    
    int arrayLength = a.length;
    Set<Integer> leaders = new HashSet<>();
    int[] arrayCopy;
    int half = arrayLength / 2;
    
    for (int i = 0; i <= arrayLength - k; i++) {
      arrayCopy = Arrays.copyOf(a, arrayLength);
      //Get leader in modified array
      getLeader(getModifiedArray(arrayCopy, i, k), half)
        .ifPresent(leaders::add);
    }
    System.out.println("Leaders: " + leaders);
    
    return leaders.stream()
      .mapToInt(Integer::intValue)
      .toArray();
  }
  
  private int[] getModifiedArray(int[] a, int segmentStart, int segmentLength) {
    for (int i = segmentStart; i < segmentStart + segmentLength; i++) {
      a[i] += 1;
    }
    return a;
  }
  
  private Optional<Integer> getLeader(int[] a, int half) {
    return Arrays.stream(a)
      .parallel()
      .boxed()
      .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
      .entrySet()
      .stream()
      .parallel()
      .filter(entry -> entry.getValue() > half)
      .map(Map.Entry::getKey)
      .findFirst();
  }
  
  private void validateParameters(int k, int m, int[] a) {
    int n = a.length;
    int ini = 1;
    validateIntegerRange(n, "N (Array length)", ini, 100000);
    validateIntegerRange(m, "M", ini, 100000);
    validateIntegerRange(k, "K (Segment length)", ini, n);
    
    if (isInvalidArrayElement(a, m)) {
      throw new IllegalArgumentException("Array elements should be integers within the range [" + 1 + ".." + m + "]");
    }
  }
  
  private void validateIntegerRange(int num, String name, int ini, int fin) {
    if (num < ini || num > fin) {
      throw new IllegalArgumentException(name + " must be an integer within the range [" + ini + ".." + fin + "]");
    }
  }
  
  private boolean isInvalidArrayElement(int[] a, int m) {
    return Arrays.stream(a)
      .parallel()
      .boxed()
      .anyMatch(n -> (n < 1 || n > m));
  }
}
