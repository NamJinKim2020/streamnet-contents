
package streamnet.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="contentsHistory", url="${api.contentsHistory.url}")
public interface ContentsHistoryService {

    @RequestMapping(method= RequestMethod.POST, path="/contentsHistories")
    public void insert(@RequestBody ContentsHistory contentsHistory);

}