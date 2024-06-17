package org.example.mukgenie.hate;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "Hate")
@Getter
@Setter
public class Hate {

    @Id
    private String id;

    private Map<String, List<String>> allergy; //싫어하는 음식

}
