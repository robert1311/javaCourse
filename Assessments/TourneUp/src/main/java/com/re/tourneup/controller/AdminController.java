/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.tourneup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author rober
 */
@Controller
public class AdminController {
    
    @GetMapping("/admin")
    public String displayAdminPage(){
        return "admin";
    }
}
