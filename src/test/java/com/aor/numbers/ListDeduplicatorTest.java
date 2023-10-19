package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ListDeduplicatorTest {
    @Test
    public void deduplicate1() {
        List<Integer> expected = Arrays.asList(1,2,4);
        List<Integer> list2 = Arrays.asList(1,2,4,2);
        class SortStub implements GenericSorter{

            @Override
            public List<Integer> sort(List<Integer> list) {
                return Arrays.asList(1,2,2,4);
            }
        }

        ListDeduplicator deduplicator = new ListDeduplicator();
        List<Integer> distinct = deduplicator.deduplicate(list2, new SortStub());

        Assertions.assertEquals(expected, distinct);
    }
    @Test
    public void deduplicate() {
        List<Integer> expected = Arrays.asList(1,2,4,5);
        class SortStub implements GenericSorter{

            @Override
            public List<Integer> sort(List<Integer> list) {
                return Arrays.asList(1,2,2,4,5);
            }
        }
        ListDeduplicator deduplicator = new ListDeduplicator();
        List<Integer> distinct = deduplicator.deduplicate(list, new SortStub());

        Assertions.assertEquals(expected, distinct);
    }
    List<Integer> list;
    @BeforeEach
    public void helper(){
        list = Arrays.asList(1,2,4,2,5);}

}
