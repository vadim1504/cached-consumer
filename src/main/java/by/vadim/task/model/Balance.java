package by.vadim.task.model;

import com.couchbase.client.java.repository.annotation.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Document(expiry = 0)
public class Balance {

    @Id
    private Integer id;

    @Field
    private Integer value;

    public Integer getId() {
        return id;
    }

    @XmlElement
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    @XmlElement
    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "id='" + id + '\'' +
                ", value=" + value +
                '}';
    }
}