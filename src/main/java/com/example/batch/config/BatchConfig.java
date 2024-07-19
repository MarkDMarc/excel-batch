package com.example.batch.config;

import com.example.batch.item.DemoExcelProcessor;
import com.example.batch.item.DemoExcelWriter;
import com.example.batch.mapper.PlayerFieldSetMapper;
import com.example.batch.model.DemoExcelProcessorOutput;
import com.example.batch.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BatchConfig {

    private final JobRepository jobRepository;
    private final DemoExcelProcessor demoExcelProcessor;
    private final DemoExcelWriter demoExcelWriter;

    @Bean
    public Job demoExcelJob(Step demoExcelStep) {
        return new JobBuilder("demoExcelJob", jobRepository)
                .start(demoExcelStep)
                .build();
    }

    @Bean
    @JobScope
    public Step demoExcelStep(PlatformTransactionManager platformTransactionManager,
                              FlatFileItemReader<Player> demoExcelItemReader) {
        return new StepBuilder("demoExcelStep", jobRepository)
                .<Player, DemoExcelProcessorOutput>chunk(1, platformTransactionManager)
                .reader(demoExcelItemReader)
                .processor(demoExcelProcessor)
                .writer(demoExcelWriter)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Player> demoExcelItemReader() throws Exception {
        FlatFileItemReader<Player> reader = new FlatFileItemReader<>();
        final Resource resource = new FileSystemResource("/resources/giocatori_data.csv");
        reader.setResource(resource);
        DefaultLineMapper<Player> lineMapper =  new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
        lineMapper.setFieldSetMapper(new PlayerFieldSetMapper());
        reader.setLineMapper(lineMapper);
        reader.open(new ExecutionContext());
        Player player = reader.read();
        return reader;
    }
}
