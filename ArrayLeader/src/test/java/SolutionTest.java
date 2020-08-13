import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SolutionTest {
  
  private Solution solution;
  
  @Before
  public void setup() {
    solution = new Solution();
  }
  
  @Test
  public void givenArrayLength7_whenEvaluateLeader_thenReturnArrayOfLeaders() {
    int k = 3;
    int m = 5;
    int[] a = {2, 1, 3, 1, 2, 2, 3};
    int[] leaders = solution.solution(k, m, a);
    assertThat(leaders).containsExactly(2, 3);
  }
  
  @Test
  public void givenArrayLength4_whenEvaluateLeader_thenReturnArrayOfLeaders() {
    int k = 4;
    int m = 2;
    int[] a = {1, 2, 2, 1, 2};
    int[] leaders = solution.solution(k, m, a);
    assertThat(leaders).containsExactly(2, 3);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void givenSegmentLengthEqualZero_whenEvaluateLeader_thenReturnIllegalArgumentException() {
    int k = 0;
    int m = 5;
    int[] a = {1, 2, 2, 1, 2};
    solution.solution(k, m, a);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void givenSegmentLengthGreaterArrayLength_whenEvaluateLeader_thenReturnIllegalArgumentException() {
    int k = 8;
    int m = 5;
    int[] a = {1, 2, 2, 1, 2};
    solution.solution(k, m, a);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void givenEmptyArray_whenEvaluateLeader_thenReturnIllegalArgumentException() {
    int k = 3;
    int m = 5;
    int[] a = {};
    solution.solution(k, m, a);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void givenArrayElementIsNotWithinRange_whenEvaluateLeader_thenReturnIllegalArgumentException() {
    int k = 3;
    int m = 4;
    int[] a = {1, 5, 7, 1, 3, 9};
    solution.solution(k, m, a);
  }
}