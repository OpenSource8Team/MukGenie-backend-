package org.example.mukgenie.log;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Log")
@Getter
@Setter
public class Log {
    @Id
    private String id;

    private String userId;
    private List<String> foods;
}

