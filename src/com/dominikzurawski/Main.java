package com.dominikzurawski;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {

        String path = "zadanie.txt";
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        // TreeMap will sort collection alphabetically by key
        // key for treemap is word itself, value is arraylist where first value is number of occurences and every other value is number of line

        TreeMap<String, ArrayList<Integer>> words = new TreeMap<>();

        String row;
        int numberOfCurrentLine = 0;

        while ((row = br.readLine()) != null) {
            numberOfCurrentLine++;

            String wordsInLine[] = row.replaceAll("\\p{Punct}", "").replaceAll("–", "").toLowerCase().split(" ");

            for (String word : wordsInLine){

                if (words.containsKey(word)) {

                    ArrayList<Integer> valuesToUpdate = words.get(word);

                    int tempOcc = valuesToUpdate.get(0) + 1;
                    valuesToUpdate.set(0, tempOcc);

                    valuesToUpdate.add(numberOfCurrentLine);

                    words.put(word, valuesToUpdate);

                } else {

                    ArrayList<Integer> valueForWordsMap = new ArrayList<>();
                    valueForWordsMap.add(1);
                    valueForWordsMap.add(numberOfCurrentLine);
                    words.put(word, valueForWordsMap);

                }
            }
        }

        for (Map.Entry<String, ArrayList<Integer>> singleWord : words.entrySet()) {
            String word = singleWord.getKey();
            ArrayList<Integer> value = singleWord.getValue();
            int occurences = value.get(0); // extract first value (which is number of occurences)
            value.remove(0); // remove number of occurences and leave line numbers

            String rows = "";

            for (Integer singleRow : value) {
                rows = rows + singleRow.toString() + ", ";
            }

            System.out.println(word + " : x" + occurences + " - rows: " + rows);
        }

    }
}
