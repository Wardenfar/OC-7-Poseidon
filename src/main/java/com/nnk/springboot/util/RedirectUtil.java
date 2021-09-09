package com.nnk.springboot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.model.ActionError;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

@Log4j2
public class RedirectUtil {

    public static ObjectMapper mapper = new ObjectMapper();

    /**
     * Util method to redirect with message and error
     */
    public static RedirectView redirectTo(String path, String msg, ActionError error) {
        UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
        builder.replacePath(path);
        return processURI(builder, msg, error);
    }

    /**
     * Add query parameters
     */
    public static RedirectView processURI(UriComponentsBuilder builder, String msg, ActionError error) {
        // Remove all query params
        builder.query("");

        // Add only not null params
        if (error != null) {
            String json = null;
            try {
                json = mapper.writeValueAsString(error);
            } catch (JsonProcessingException e) {
                log.error("error to json", e);
            }
            builder.replaceQueryParam("error", json);
        }
        if (msg != null) {
            builder.replaceQueryParam("msg", msg);
        }

        // Build the uri
        String redirectUri = builder.toUriString();

        // Logs
        if (error != null) {
            log.error("Redirect {} : {}", error, redirectUri);
        } else {
            log.info("Redirect {} : {}", msg, redirectUri);
        }
        log.debug("Redirect to {}", redirectUri);

        // Return the view
        return new RedirectView(redirectUri);
    }
}
