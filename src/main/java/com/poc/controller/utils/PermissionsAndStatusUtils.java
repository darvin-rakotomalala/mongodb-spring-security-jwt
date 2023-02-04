package com.poc.controller.utils;

public interface PermissionsAndStatusUtils {
    String AUTH_ADMIN = "hasAnyAuthority('ROLE_ADMIN')";
    String AUTH_USER = "hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')";
}
