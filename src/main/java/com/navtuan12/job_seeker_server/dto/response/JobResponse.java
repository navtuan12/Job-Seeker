package com.navtuan12.job_seeker_server.dto.response;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import com.navtuan12.job_seeker_server.models.Detail;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobResponse {
    String company;
    String jobTitle;
    String jobType;
    String location;
    int salary;
    int vacancies;
    int experience;
    Detail detail;
    List<ObjectId> application;
    ObjectId id;
    Date createdAt;
    Date updatedAt;

}
