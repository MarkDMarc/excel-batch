package com.example.batch.mapper;

import com.example.batch.model.Player;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class PlayerFieldSetMapper implements FieldSetMapper<Player> {
    public Player mapFieldSet(FieldSet fieldSet)  {
        return Player.builder()
                .name(fieldSet.readString(0))
                .surname(fieldSet.readString(1))
                .height(fieldSet.readString(2))
                .build();
    }
}
