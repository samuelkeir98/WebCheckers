package com.webcheckers.ui;

import spark.*;

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;

/**
 * UI controller to GET signin page
 *
 * @author Anthony Massicci
 * Tested by Samuel Keir
 */
public class GetSigninRoute implements Route {

    public final static String TITLE_PARAM = "title";
    public final static String MESSAGE_PARAM = "message";
    public final static String PAGE_TITLE = "Sign-in";
    public final static String TEMPLATE_NAME = "signin.ftl";

    private final TemplateEngine templateEngine;

    /**
     * Create the spark component for the
     * {@code GET /signin} HTTP request.
     *
     * @param templateEngine
     *  The HTML template engine.
     */
    public GetSigninRoute(final TemplateEngine templateEngine) {
        Objects.requireNonNull(templateEngine, "templateEngine cannot be null");
        this.templateEngine = templateEngine;
    }

    /**
     * Render the sign-in page
     *
     * @param request
     *  The HTTP request object
     * @param response
     *  The HTTP response object
     * @return
     *  rendered HTML page
     */
    @Override
    public Object handle(Request request, Response response) {
        Map<String, Object> vm = new HashMap<>();

        vm.put(TITLE_PARAM, PAGE_TITLE);
        return templateEngine.render(new ModelAndView(vm, TEMPLATE_NAME));
    }
}
