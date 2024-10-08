package com.navtuan12.job_seeker_server.dto.response;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobSearchResponse {
    ObjectId id;
    CompanyProfileResponse company;
    String jobTitle;
    String jobType;
    String location;
    int salary;
    int vacancies;
    int experience;
    Detail detail;
    List<ObjectId> application;
    Date createdAt;
    Date updatedAt;
}
