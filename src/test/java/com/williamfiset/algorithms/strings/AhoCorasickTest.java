package com.williamfiset.algorithms.strings;
import static com.google.common.truth.Truth.assertThat;
import org.junit.Test;

public class AhoCorasickTest {
    @Test
    public void FindDifferentStrings(){
        Aho_Corasick trie = new Aho_Corasick(500);
        trie.insertWord("oskar");
        trie.insertWord("Marina");
        trie.insertWord("Hiroyuki");
        trie.insertWord("Katarina");
        String mainString = "oskarmarinahiroyukikatarina";
        assertThat(Aho_Corasick.NumberOfFounds(trie,mainString)) .isEqualTo(4);
    }
    @Test
    public void NoMatchedStrings(){
        Aho_Corasick trie = new Aho_Corasick(500);
        trie.insertWord("oskar");
        trie.insertWord("Marina");
        trie.insertWord("Hiroyuki");
        trie.insertWord("Katarina");
        String mainString = "hasanisworkinghome";
        assertThat(Aho_Corasick.NumberOfFounds(trie,mainString)) .isEqualTo(0);
    }
    @Test
    public void FindSametStrings(){
        Aho_Corasick trie = new Aho_Corasick(500);
        trie.insertWord("oskar");
        String mainString = "oskaroskaroskaroskaroskaroskar";
        assertThat(Aho_Corasick.NumberOfFounds(trie,mainString)) .isEqualTo(6);
    }

    @Test
    public void FindOneChar(){
        Aho_Corasick trie = new Aho_Corasick(500);
        trie.insertWord("o");
        String mainString = "oskaroskaroskaroskaroskaroskar";
        assertThat(Aho_Corasick.NumberOfFounds(trie,mainString)) .isEqualTo(6);
    }
    @Test
    public void FindWithCapitals(){
        Aho_Corasick trie = new Aho_Corasick(500);
        trie.insertWord("oskar");
        String mainString = "OSKARHASANOSKARHASANOSKARHASANOSKAR";
        assertThat(Aho_Corasick.NumberOfFounds(trie,mainString)) .isEqualTo(4);
    }
}
