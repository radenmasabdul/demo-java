package com.example.demo.util;

import com.example.demo.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.time.LocalDateTime;

public class ResponseHandler {
  // 200 OK
  public static <T> ResponseEntity<ApiResponse<T>> ok(String message, T data) {
    return buildResponse(HttpStatus.OK, message, data);
  }

  // 201 Created
  public static <T> ResponseEntity<ApiResponse<T>> created(String message, T data) {
    return buildResponse(HttpStatus.CREATED, message, data);
  }

  // 202 Accepted
  public static <T> ResponseEntity<ApiResponse<T>> accepted(String message, T data) {
    return buildResponse(HttpStatus.ACCEPTED, message, data);
  }

  // 204 No Content
  public static <T> ResponseEntity<ApiResponse<T>> noContent(String message) {
    return buildResponse(HttpStatus.NO_CONTENT, message, null);
  }

  // 301 Moved Permanently
  public static <T> ResponseEntity<ApiResponse<T>> movedPermanently(String message, String newUrl) {
    return buildRedirectResponse(HttpStatus.MOVED_PERMANENTLY, message, newUrl);
  }

  // 302 Found / Moved Temporarily
  public static <T> ResponseEntity<ApiResponse<T>> found(String message, String newUrl) {
    return buildRedirectResponse(HttpStatus.FOUND, message, newUrl);
  }

  // 304 Not Modified
  public static <T> ResponseEntity<ApiResponse<T>> notModified(String message) {
    return buildResponse(HttpStatus.NOT_MODIFIED, message, null);
  }

  // 307 Temporary Redirect
  public static <T> ResponseEntity<ApiResponse<T>> temporaryRedirect(String message, String newUrl) {
    return buildRedirectResponse(HttpStatus.TEMPORARY_REDIRECT, message, newUrl);
  }

  // 308 Permanent Redirect
  public static <T> ResponseEntity<ApiResponse<T>> permanentRedirect(String message, String newUrl) {
    return buildRedirectResponse(HttpStatus.PERMANENT_REDIRECT, message, newUrl);
  }

  // 400 Bad Request
  public static <T> ResponseEntity<ApiResponse<T>> badRequest(String message) {
    return buildResponse(HttpStatus.BAD_REQUEST, message, null);
  }

  // 401 Unauthorized
  public static <T> ResponseEntity<ApiResponse<T>> unauthorized(String message) {
    return buildResponse(HttpStatus.UNAUTHORIZED, message, null);
  }

  // 403 Forbidden
  public static <T> ResponseEntity<ApiResponse<T>> forbidden(String message) {
    return buildResponse(HttpStatus.FORBIDDEN, message, null);
  }

  // 404 Not Found
  public static <T> ResponseEntity<ApiResponse<T>> notFound(String message) {
    return buildResponse(HttpStatus.NOT_FOUND, message, null);
  }

  // 405 Method Not Allowed
  public static <T> ResponseEntity<ApiResponse<T>> methodNotAllowed(String message) {
    return buildResponse(HttpStatus.METHOD_NOT_ALLOWED, message, null);
  }

  // 408 Request Timeout
  public static <T> ResponseEntity<ApiResponse<T>> requestTimeout(String message) {
    return buildResponse(HttpStatus.REQUEST_TIMEOUT, message, null);
  }

  // 429 Too Many Requests
  public static <T> ResponseEntity<ApiResponse<T>> tooManyRequests(String message) {
    return buildResponse(HttpStatus.TOO_MANY_REQUESTS, message, null);
  }

  // 500 Internal Server Error
  public static <T> ResponseEntity<ApiResponse<T>> internalServerError(String message) {
    return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
  }

  // 501 Not Implemented
  public static <T> ResponseEntity<ApiResponse<T>> notImplemented(String message) {
    return buildResponse(HttpStatus.NOT_IMPLEMENTED, message, null);
  }

  // 502 Bad Gateway
  public static <T> ResponseEntity<ApiResponse<T>> badGateway(String message) {
    return buildResponse(HttpStatus.BAD_GATEWAY, message, null);
  }

  // 503 Service Unavailable
  public static <T> ResponseEntity<ApiResponse<T>> serviceUnavailable(String message) {
    return buildResponse(HttpStatus.SERVICE_UNAVAILABLE, message, null);
  }

  // 504 Gateway Timeout
  public static <T> ResponseEntity<ApiResponse<T>> gatewayTimeout(String message) {
    return buildResponse(HttpStatus.GATEWAY_TIMEOUT, message, null);
  }

  // 505 HTTP Version Not Supported
  public static <T> ResponseEntity<ApiResponse<T>> httpVersionNotSupported(String message) {
    return buildResponse(HttpStatus.HTTP_VERSION_NOT_SUPPORTED, message, null);
  }

  // Generator standar untuk response JSON biasa
  private static <T> ResponseEntity<ApiResponse<T>> buildResponse(HttpStatus status, String message, T data) {
    ApiResponse<T> response = ApiResponse.<T>builder()
      .status(status.value())
      .message(message)
      .data(data)
      .timestamp(LocalDateTime.now())
      .build();
    return new ResponseEntity<>(response, status);
  }

  // Generator khusus kelompok 3xx pengalihan agar menyertakan Header Location URL baru
  private static <T> ResponseEntity<ApiResponse<T>> buildRedirectResponse(HttpStatus status, String message, String url) {
    ApiResponse<T> response = ApiResponse.<T>builder()
      .status(status.value())
      .message(message)
      .data(null)
      .timestamp(LocalDateTime.now())
      .build();
    return ResponseEntity.status(status).location(URI.create(url)).body(response);
  }
}
