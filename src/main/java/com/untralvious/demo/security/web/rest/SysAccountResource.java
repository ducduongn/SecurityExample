package com.untralvious.demo.security.web.rest;

import com.untralvious.demo.security.service.MailService;
import com.untralvious.demo.security.service.SysUserService;
import com.untralvious.demo.security.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysAccountResource {

    private static class SysAccountResourceException extends RuntimeException {

        private SysAccountResourceException(String message) {
            super(message);
        }
    }

    private final Logger log = LoggerFactory.getLogger(SysAccountResource.class);

    @Autowired
    private final SysUserServiceImpl sysUserService;

    @Autowired
    private final MailService mailService;

    public SysAccountResource(SysUserServiceImpl sysUserService, MailService mailService) {
        this.sysUserService = sysUserService;
        this.mailService = mailService;
    }
}
