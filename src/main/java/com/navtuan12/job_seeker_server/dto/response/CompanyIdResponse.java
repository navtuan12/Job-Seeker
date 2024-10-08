package com.navtuan12.job_seeker_server.dto.response;

import java.util.List;
import org.bson.types.ObjectId;
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
public class CompanyIdResponse {
    ObjectId id;
    String name;
    String email;
    List<JobResponse> jobPosts;
    boolean verified;
}
