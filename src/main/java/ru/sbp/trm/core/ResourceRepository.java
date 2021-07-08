package ru.sbp.trm.core;

import java.util.List;

public interface ResourceRepository {
    List<ResourceData> getFreeResources();
    List<ResourceData> getMyResources();
}
