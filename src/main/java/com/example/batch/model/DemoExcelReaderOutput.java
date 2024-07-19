package com.example.batch.model;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class DemoExcelReaderOutput {

    private Collection<Player> players;
}
