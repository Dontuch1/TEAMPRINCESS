package com.princess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.princess.domain.LikeWish;
import com.princess.domain.Member;

@RunWith(SpringRunner.class)
@SpringBootTest
public class annoyTest {
	
	@Test
	public void getWishList(Member member) {
		member.setId("jojo");
		ArrayList<LikeWish> wishList = (ArrayList<LikeWish>)member.getLikeWishList();
		System.out.println(wishList.toString());
	}
}
