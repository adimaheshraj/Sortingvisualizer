package com.sorting.visualizer.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/sort")
@CrossOrigin(origins = "*")
public class SortingController {

    @PostMapping("/bubble")
    public List<Integer> bubbleSort(@RequestBody List<Integer> input) {
        List<Integer> arr = new ArrayList<>(input);
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = 0; j < arr.size() - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    Collections.swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    @PostMapping("/selection")
    public List<Integer> selectionSort(@RequestBody List<Integer> input) {
        List<Integer> arr = new ArrayList<>(input);
        for (int i = 0; i < arr.size(); i++) {
            int min = i;
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(j) < arr.get(min)) {
                    min = j;
                }
            }
            Collections.swap(arr, i, min);
        }
        return arr;
    }

    @PostMapping("/insertion")
    public List<Integer> insertionSort(@RequestBody List<Integer> input) {
        List<Integer> arr = new ArrayList<>(input);
        for (int i = 1; i < arr.size(); i++) {
            int key = arr.get(i);
            int j = i - 1;
            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j));
                j = j - 1;
            }
            arr.set(j + 1, key);
        }
        return arr;
    }
}
