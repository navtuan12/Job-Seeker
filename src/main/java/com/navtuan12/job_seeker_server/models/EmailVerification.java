package com.navtuan12.job_seeker_server.models;

import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "verifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailVerification {
    @Id
    private ObjectId id;
    
    private String userId;
    private String token;
    private Date createdAt;
    private Date expiresAt;
}
