package com.navtuan12.job_seeker_server.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Detail {
    private String desc;
    private String requirements;
}
