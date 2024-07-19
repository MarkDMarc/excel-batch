package com.example.batch.item;

import com.example.batch.model.DemoExcelProcessorOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DemoExcelWriter implements ItemWriter<DemoExcelProcessorOutput> {

    @Override
    public void write(Chunk<? extends DemoExcelProcessorOutput> chunk) throws Exception {

    }
}
