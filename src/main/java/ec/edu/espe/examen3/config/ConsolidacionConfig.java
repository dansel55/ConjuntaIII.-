package ec.edu.espe.examen3.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import ec.edu.espe.examen3.dao.CajeroRepository;
import ec.edu.espe.examen3.dao.ConsolidadoRepository;
import ec.edu.espe.examen3.dao.TransaccionRepository;
import ec.edu.espe.examen3.task.ConsolidacionTask;

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
public class ConsolidacionConfig {
    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private ConsolidadoRepository consolidadoRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private CajeroRepository cajeroRepository;

    @Bean
    protected Step test(){
        return steps
                .get("test")
                .tasklet(new ConsolidacionTask(consolidadoRepository, transaccionRepository, cajeroRepository))
                .build();
    }
    
    @Bean
    public Job consolidacionJob() {
        return jobs
                .get("consolidacionJob")
                .start(test())
                .build();
    }

    @Scheduled(fixedRate = 900000)
    public void launchJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(consolidacionJob(), params);
    }
}
