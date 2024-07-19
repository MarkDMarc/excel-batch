package com.example.batch.item;

import com.example.batch.model.DemoExcelProcessorOutput;
import com.example.batch.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DemoExcelProcessor implements ItemProcessor<Player, DemoExcelProcessorOutput> {

    @Override
    public DemoExcelProcessorOutput process(Player item) throws Exception {
        return null;
    }
}
