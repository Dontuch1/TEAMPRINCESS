package com.princess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.princess.domain.CheckCondition.Display;
import com.princess.domain.Member;
import com.princess.domain.Report;
import com.princess.service.MemberService;
import com.princess.service.ReportService;

@Controller
@RequestMapping("/admin/")
public class AdminController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ReportService reportService;
	
	
	
    //@Autowired
   // private BoardService boardService;
    
    //@Autowired
    //private ProductService productService;

	@RequestMapping("/adminMain")
	public void adminMain() {

	}

	@RequestMapping("/accessDenied")
	public void accessDenied() {

	}

	@RequestMapping("/deleteMember")
	public String deleteMember(Member member, @RequestParam String id) {
		member.setId(id);
		memberService.deleteMember(member);
		return "redirect:getMemberList";
	}

	@GetMapping("/getMemberList")
	public void getMemberList(Member member, Model model) {

		model.addAttribute("memberList", memberService.getMemberList(member));
	}

	@GetMapping("/getReportList")
	public void getReportList(Model model,
			@PageableDefault(page = 0, size = 10, sort = "rptNo", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Report> reportListH = reportService.getReportList(pageable, Display.H);
		Page<Report> reportListY = reportService.getReportList(pageable, Display.Y);
		Page<Report> reportListN = reportService.getReportList(pageable, Display.N);

		model.addAttribute("reportListH", reportListH);
		model.addAttribute("reportListY", reportListY);
		model.addAttribute("reportListN", reportListN);
	}

	@GetMapping("/changeStatus")
	public String changeStatus( Long rptNo,String	type ) {
		reportService.changeReportStatus(rptNo, type);
		return "redirect:getReportList";
	}
   
	
     
            
    

    


	
	
	//@PostMapping("/reportComplete")
    //public  String handleReportComplete(  Long rptNo,String	type ) {
        // 신고 완료 시 board 또는 product의 display 속성을 'H'로 변경
       // if ("board".equals(type)) {
           // boardService.updateBoardDisplay(rptNo, "H");
        //} else if ("product".equals(type)) {
          //  productService.updateProductDisplay(rptNo, "H");
       // }

        // 클라이언트에 응답을 보냅니다.
        //return ResponseEntity.ok("신고가 완료되었습니다.");
    //}

	// 로그아웃
	@RequestMapping("/logout")
	public void logout() {

	}
}