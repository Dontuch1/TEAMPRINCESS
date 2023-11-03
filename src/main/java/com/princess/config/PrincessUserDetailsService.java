package com.princess.config;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.princess.domain.Member;
import com.princess.persistence.MemberRepository;

@Service
public class PrincessUserDetailsService implements UserDetailsService {

   @Autowired
   private MemberRepository memberRepo;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      
      Optional<Member> optional=memberRepo.findById(username);
      if(!optional.isPresent()) {
         throw new UsernameNotFoundException(username+"사용자 없음");
      }else {
         Member member = optional.get();
         return new SecurityUser(member);
      }
      
   } 
}