package com.baikja.util;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class LoginUtil {

    public static String currentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getUsername();
    }

    public static boolean hasAdminRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        
        return authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
    }
    
    public static void UserInfoPrint() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getUsername());
        Iterator<GrantedAuthority> ite = user.getAuthorities().iterator() ;
        
        while( ite.hasNext() )
        {
            GrantedAuthority aaa = ite.next();
            System.out.println("==========>  : "+aaa.getAuthority());
        }

    }
    
    public static String getUserRole() {
        String role = "";
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Iterator<GrantedAuthority> ite = user.getAuthorities().iterator() ;
        
        while( ite.hasNext() )
        {
            GrantedAuthority aaa = ite.next();
           
            if(aaa.getAuthority().equals("ADMIN")) {
                role = "ADMIN";
                break;
            } else if(aaa.getAuthority().equals("USER")) {
                role = "USER";
                break;
            } else {
                role = "GUEST";
            }
        }
        
        return role;
    }

}
