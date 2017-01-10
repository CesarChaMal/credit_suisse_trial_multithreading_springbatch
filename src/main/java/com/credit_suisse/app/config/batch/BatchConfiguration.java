package com.credit_suisse.app.config.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.credit_suisse.app.util.CommonConstants;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Bean
    public ItemReader<InstrumentBatch> reader() {
        FlatFileItemReader<InstrumentBatch> reader = new FlatFileItemReader<InstrumentBatch>();
        reader.setResource(new ClassPathResource(CommonConstants.INPUT_FILE));
        reader.setLineMapper(new DefaultLineMapper<InstrumentBatch>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] {"name", "idate", "price"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<InstrumentBatch>() {{
                setTargetType(InstrumentBatch.class);
            }});
        }});
        return reader;
    }
   
    @Bean
    public ItemWriter<InstrumentBatch> writer() {
    	FlatFileItemWriter<InstrumentBatch> writer = new FlatFileItemWriter<InstrumentBatch>();
    	writer.setResource(new ClassPathResource("instruments.txt"));
    	DelimitedLineAggregator<InstrumentBatch> delLineAgg = new DelimitedLineAggregator<InstrumentBatch>();
    	delLineAgg.setDelimiter(",");
    	BeanWrapperFieldExtractor<InstrumentBatch> fieldExtractor = new BeanWrapperFieldExtractor<InstrumentBatch>();
    	fieldExtractor.setNames(new String[] {"name", "idate", "price"});
    	delLineAgg.setFieldExtractor(fieldExtractor);
    	writer.setLineAggregator(delLineAgg);
        return writer;
    }
    
    @Bean
    public ItemProcessor<InstrumentBatch, InstrumentBatch> processor() {
        return new InstrumentBatchItemProcessor();
    }

    @Bean
    public Job createInstrument(JobBuilderFactory jobs, Step step) {
        return jobs.get("createInstrumentBatch")
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step step(StepBuilderFactory stepBuilderFactory, ItemReader<InstrumentBatch> reader,
            ItemWriter<InstrumentBatch> writer, ItemProcessor<InstrumentBatch, InstrumentBatch> processor) {
        return stepBuilderFactory.get("step")
                .<InstrumentBatch, InstrumentBatch> chunk(5)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
 
}
