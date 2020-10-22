
package streamnet.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="play", url="${api.play.url}")
public interface PlayRootService {

    @RequestMapping(method= RequestMethod.POST, path="/playRoots")
    public void stop(@RequestBody PlayRoot playRoot);

}