package shb.slc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lic-info/v1/batch")
public class SlcLicSchedularController {

    @GetMapping
    public ResponseEntity LicDataBatch(){
        return null;
    }
}
