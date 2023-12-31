package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class ListAggregatorTest {

    @Test
    public void max_bug_7263(){

        List<Integer> list1 = Arrays.asList(-1,-4,-5);
        ListAggregator aggregator = new ListAggregator();
        int max = aggregator.max(list1);

        Assertions.assertEquals(-1, max);
    }

    @Test
    public void sum() {
        List<Integer> list = Arrays.asList(1,2,4,2,5);

        ListAggregator aggregator = new ListAggregator();
        int sum = aggregator.sum(list);

        Assertions.assertEquals(14, sum);
    }

    @Test
    public void max() {

        ListAggregator aggregator = new ListAggregator();
        int max = aggregator.max(list);

        Assertions.assertEquals(5, max);
    }

    @Test
    public void min() {

        ListAggregator aggregator = new ListAggregator();
        int min = aggregator.min(list);

        Assertions.assertEquals(1, min);
    }


    @Test
    public void distinct_bug_8726(){
        ListSorter genericlistsorter = new ListSorter();

        GenericListDeduplicator deduplicator = Mockito.mock(GenericListDeduplicator.class);
        List<Integer> list2 = Arrays.asList(1,2,4,2);
        Mockito.when(deduplicator.deduplicate(list2 ,genericlistsorter)).thenReturn(Arrays.asList(1, 2, 4));


        ListAggregator aggregator = new ListAggregator();
        int distinct = aggregator.distinct(list2, deduplicator, genericlistsorter);

        Assertions.assertEquals(3, distinct);
    }

    @Test
    public void distinct() {
        class DistinctStub implements GenericListDeduplicator{

            @Override
            public List<Integer> deduplicate(List<Integer> list, GenericSorter genericsorter) {
                return Arrays.asList(1,2,4,5);
            }

        }
        DistinctStub listdeduplicator = new DistinctStub();
        ListAggregator aggregator = new ListAggregator();
        int distinct = aggregator.distinct(list, listdeduplicator, null);

        Assertions.assertEquals(4, distinct);
    }

    List<Integer> list;
    @BeforeEach
    public void helper(){
       list = Arrays.asList(1,2,4,2,5);
    }
}
