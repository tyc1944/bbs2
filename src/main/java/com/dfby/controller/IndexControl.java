package com.dfby.controller;

import com.dfby.pojo.Board;
import com.dfby.pojo.Boardtype;
import com.dfby.service.BoardService;
import com.dfby.service.BoardtypeService;
import com.dfby.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexControl {
    @Autowired
    private BoardtypeService boardtypeService;
//    private BoardService boardService;
//    private TopicService topicService;
    @RequestMapping("/index")
    public String list(Model model) {
        List<Boardtype> list = boardtypeService.selectAll();
        model.addAttribute("List", list);
//        List<Board> boardlist = boardService.selectAll();
//        model.addAttribute("BoardList", boardlist);
//        List<Topic> topiclist = topicService.countAll();
//        model.addAttribute("BoardList", boardlist);
        return "index";
    }
}
