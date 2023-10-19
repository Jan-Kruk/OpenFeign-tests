package com.proj.project1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "shawnmendes-client", url = "https://itunes.apple.com")
@Component
public interface ShawnMendesProxy {

    @GetMapping("/search22")
    ShawnMendesResponse makeSearchRequest(
            @RequestParam("term") String term,
            @RequestParam("limit") Integer limit
    );
}
