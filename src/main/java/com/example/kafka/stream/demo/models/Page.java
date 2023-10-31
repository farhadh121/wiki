package com.example.kafka.stream.demo.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Page {
    private int pageid;
    private int ns;
    private String title;
    private List<Link> links;

    // Getters and setters
}
