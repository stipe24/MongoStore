package com.stipe.mstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "follow")
public class Follow {

    @Id
    @JsonIgnore
    private String id;
    private String followerId;
    private String followingId;
    private Long time;
}