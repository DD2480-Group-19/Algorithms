package com.williamfiset.algorithms.datastructures.suffixarray;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class SuffixArrayFastTest {
    @Test
    public void constructTest() {
        int[] array = {1};//check for N == 1
        SuffixArrayFast suffixArrayFast = new SuffixArrayFast(array);
        SuffixArrayMed suffixArrayMed = new SuffixArrayMed(array);
        suffixArrayFast.construct();
        suffixArrayMed.construct();
        assertThat(suffixArrayFast.sa2).isEqualTo(new int[1]);
    }
    @Test
    public void compareResultsTest() {
        int[] array2 = {1,2};
        SuffixArrayFast suffixArrayFast = new SuffixArrayFast(array2);
        SuffixArrayMed suffixArrayMed = new SuffixArrayMed(array2);
        suffixArrayFast.construct();suffixArrayMed.construct();
        assertThat(suffixArrayFast.sa[1]).isEqualTo(suffixArrayMed.sa[1]);
        suffixArrayFast.print_coverage();

    }
}
