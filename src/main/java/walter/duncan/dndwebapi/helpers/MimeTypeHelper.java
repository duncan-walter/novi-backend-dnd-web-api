package walter.duncan.dndwebapi.helpers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MimeTypeHelper {
    private final HttpServletRequest request;

    public MimeTypeHelper(HttpServletRequest request) {
        this.request = request;
    }

    public String getMimeType(Resource resource) {
        try {
            return request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            return MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
    }
}