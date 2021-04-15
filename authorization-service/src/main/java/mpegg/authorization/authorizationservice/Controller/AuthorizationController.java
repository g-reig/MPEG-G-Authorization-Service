package mpegg.authorization.authorizationservice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wso2.balana.Balana;
import org.wso2.balana.PDP;
import org.wso2.balana.finder.impl.FileBasedPolicyFinderModule;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class AuthorizationController {
    @PostMapping("/authorize")
    String authorize(@RequestBody String request) {
        try {
            String policyLocation = (new File("src")).getCanonicalPath() + File.separator + "main" + File.separator + "resources" + File.separator + "support" + File.separator + "policy";
            System.setProperty(FileBasedPolicyFinderModule.POLICY_DIR_PROPERTY, policyLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Balana balana = Balana.getInstance();
        PDP pdp = new PDP(balana.getPdpConfig());
        return pdp.evaluate(request);
    }
}
