package baobao.account_service.feignclient;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@RibbonClient(name = "artwork-service")
@FeignClient(name = "artwork-service", path = "/api/v1/artworks")
public interface ArtworkFeignClient {

    @GetMapping()
    public String gretting();
}
