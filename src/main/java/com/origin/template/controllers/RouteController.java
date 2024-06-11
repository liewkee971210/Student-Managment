package com.origin.template.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.origin.standard.models.OriginStandardResponse;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestController
public class RouteController implements ErrorController {
  @RequestMapping("/error")
  public OriginStandardResponse handleError(HttpServletRequest request) {
    OriginStandardResponse response = new OriginStandardResponse(500, false, "Something broke");
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

    if (status != null) {
      Integer statusCode = Integer.valueOf(status.toString());

      if (statusCode == HttpStatus.NOT_FOUND.value()) {
        response.setCode(404);
        response.setMessage("Method not found");
      }
    }
    return response;
  }
}
