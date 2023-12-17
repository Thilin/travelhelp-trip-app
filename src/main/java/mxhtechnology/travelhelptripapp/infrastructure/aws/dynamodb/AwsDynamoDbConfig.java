package mxhtechnology.travelhelptripapp.infrastructure.aws.dynamodb;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;

@Configuration
public class AwsDynamoDbConfig {

    @Value("${config.aws.region}")
    private String region;
    @Value("${config.aws.dynamodb.url}")
    private String url;
    @Value("${config.aws.dynamodb.access-key}")
    private String accessKey;
    @Value("${config.aws.dynamodb.secret-key}")
    private String secretKey;


    @Bean
    public AmazonDynamoDB amazonDynamoDB(){
        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(getCredentialsProvider())
                .withEndpointConfiguration(getEndpointConfiguration(url))
                .build();
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(){
        return new DynamoDBMapper(amazonDynamoDB());
    }


    private EndpointConfiguration getEndpointConfiguration(String url){
        return new EndpointConfiguration(url, region);
    }

    private AWSStaticCredentialsProvider getCredentialsProvider(){
        return new AWSStaticCredentialsProvider(getBasicAWSCredentials());
    }

    private BasicAWSCredentials getBasicAWSCredentials(){
        return new BasicAWSCredentials(accessKey, secretKey);
    }
}
