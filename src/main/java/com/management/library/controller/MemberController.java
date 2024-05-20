package com.management.library.controller;

import com.management.library.entity.Member;
import com.management.library.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {
    final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("/members")
    public String findAllMembers(Model model) {
        List<Member> members = memberService.findAllMembers();
        model.addAttribute("members", members);
        model.addAttribute("memberCount", members.size());
        return "list-members";
    }

    @RequestMapping("/searchMember")
    public String searchMember(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("members", memberService.searchMembers(keyword));
        model.addAttribute("keyword", keyword);
        return "list-members";
    }

    @RequestMapping("/member/{id}")
    public String findMemberById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("member", memberService.findMemberById(id));
        return "list-member";
    }

    @GetMapping("/add-member")
    public String showCreateForm(Member member, Model model) {
        return "add-member";
    }

    @PostMapping("/add-member")
    public String createMember(@ModelAttribute Member member, Model model) {
        memberService.createMember(member);
        return "redirect:/members";
    }

    @GetMapping("/update-member/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("member", memberService.findMemberById(id));
        return "update-member";
    }

    @PostMapping("/update-member/{id}")
    public String updateMember(@PathVariable("id") Long id, @ModelAttribute Member member, Model model) {
        memberService.updateMember(member);
        return "redirect:/members";
    }

    @RequestMapping("/remove-member/{id}")
    public String deleteMember(@PathVariable("id") Long id, Model model) {
        memberService.deleteMember(id);
        return "redirect:/members";
    }
}
