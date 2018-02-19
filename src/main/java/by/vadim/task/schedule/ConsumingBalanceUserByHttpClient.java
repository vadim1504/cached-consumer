package by.vadim.task.schedule;

import by.vadim.task.model.CollectionClient;
import by.vadim.task.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

@Component
public class ConsumingBalanceUserByHttpClient {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private ClientService clientService;
    @Autowired
    private HttpEntity entity;
    @Autowired
    private Transformer transformer;
    @Autowired
    private Unmarshaller jaxbUnmarshaller;
    @Autowired
    private DocumentBuilder builder;

    @Scheduled(fixedDelayString  = "${N}")
    public void getResponse() throws IOException, SAXException, TransformerException, JAXBException {
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange("http://http://192.168.99.100:8080/tariff-store/user", HttpMethod.GET, entity, String.class);
        }catch (Exception exception){
            System.out.println("Application #1 is dead");
        }
        if(response!=null){
            DOMSource source = new DOMSource(builder.parse(new InputSource(new StringReader(response.getBody()))));

            File empTableOutput = new File("temp.xml");
            StreamResult result = new StreamResult(empTableOutput);

            transformer.transform(source, result);

            CollectionClient list = (CollectionClient) jaxbUnmarshaller.unmarshal(empTableOutput);

            list.getItem().forEach(client -> clientService.createClient(client));
        }

    }
}
