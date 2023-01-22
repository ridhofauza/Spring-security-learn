/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springnotes.clientnotes.model.dto.response;

import com.springnotes.clientnotes.model.Role;
import java.util.List;

/**
 *
 * @author user
 */
public class UserResponse {
    private Long id;
    private String username;
    private List<Role> roles;
}
