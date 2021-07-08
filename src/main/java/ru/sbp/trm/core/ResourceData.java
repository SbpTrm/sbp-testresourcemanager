package ru.sbp.trm.core;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResourceData {
    private String name;
    private ResourceStatus status;
    private String userId;
    private String date;
}
