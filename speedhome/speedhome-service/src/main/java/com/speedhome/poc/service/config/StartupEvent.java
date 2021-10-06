package com.speedhome.poc.service.config;

import com.speedhome.poc.service.constant.Constants;
import com.speedhome.poc.service.exception.BadRequestException;
import com.speedhome.poc.service.search.IndexingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartupEvent implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private IndexingService indexingService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            indexingService.loadIndexing();
        }
        catch (InterruptedException e){
            throw new BadRequestException(Constants.ConstantPost.ERROR_INDEX);
        }
    }
}
