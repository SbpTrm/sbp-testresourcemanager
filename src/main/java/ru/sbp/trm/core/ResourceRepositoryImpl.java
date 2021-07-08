package ru.sbp.trm.core;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceRepositoryImpl implements ResourceRepository{
    @Override
    public List<ResourceData> getFreeResources() {
        List<ResourceData> freeResources = new ArrayList<>();
        freeResources.add(ResourceData.builder().name("int-1").build());
        freeResources.add(ResourceData.builder().name("int-2").build());
        freeResources.add(ResourceData.builder().name("int-3").build());
        freeResources.add(ResourceData.builder().name("int-4").build());
        freeResources.add(ResourceData.builder().name("int-8").build());
        freeResources.add(ResourceData.builder().name("int-9").build());
        freeResources.add(ResourceData.builder().name("perf-1").build());
        freeResources.add(ResourceData.builder().name("perf-2").build());
        return freeResources;
    }

    @Override
    public List<ResourceData> getMyResources() {
        List<ResourceData> myResources = new ArrayList<>();
        myResources.add(ResourceData.builder().name("int-8").build());
        myResources.add(ResourceData.builder().name("int-9").build());
        return myResources;
    }

}
