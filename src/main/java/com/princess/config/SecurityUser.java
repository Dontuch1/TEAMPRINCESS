package com.princess.config;


import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.princess.domain.Member;

public class SecurityUser extends User{

   private static final long serialVersionUID = 1L;
   
   private Member member;
   
   public SecurityUser(Member member) {
      super(member.getId(),member.getPassword(),
            AuthorityUtils.createAuthorityList(member.getAuthList().toString()));
      this.member=member;
      
   }
   public Member getMember() {
      return member;
   }
}