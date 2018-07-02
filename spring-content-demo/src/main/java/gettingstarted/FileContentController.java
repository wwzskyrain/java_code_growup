package gettingstarted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileContentController {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileContentStore fileContentStore;

    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.PUT)
    public ResponseEntity<?> setContent(@PathVariable("fileId") Long id, @RequestParam("file") MultipartFile file)
            throws IOException {

        File f = fileRepository.findOne(id);
        f.setMimeType(file.getContentType());

        fileContentStore.setContent(f, file.getInputStream());

        // save updated content-related info
        fileRepository.save(f);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<?> getContent(@PathVariable("fileId") Long id) {

        File f = fileRepository.findOne(id);
        InputStreamResource inputStreamResource = new InputStreamResource(fileContentStore.getContent(f));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(f.getContentLength());
        headers.set("Content-Type", f.getMimeType());
        return new ResponseEntity<Object>(inputStreamResource, headers, HttpStatus.OK);
    }
}