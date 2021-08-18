package com.dfby.controller;

import com.dfby.pojo.Board;
import com.dfby.pojo.Boardtype;
import com.dfby.pojo.Reply;
import com.dfby.pojo.Topic;
import com.dfby.service.BoardService;
import com.dfby.service.BoardtypeService;
import com.dfby.service.ReplyService;
import com.dfby.service.TopicService;
import com.dfby.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/topic")
public class listControl {
    @Autowired
    private TopicService topicService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private ReplyService replyService;
    @RequestMapping("/list")
    public String List(Topic topic,Page page,Model model) {
        if (page==null) {
            page=new Page();
        }
        page.setPageSize(10);
        page.setRowCount(topicService.selectCoundByCon(topic));
        List<Topic> topicList=topicService.selectListByCon(topic,page);
        page.setPageList(topicList);
        model.addAttribute("mypage", page);
        model.addAttribute("board",boardService.selectByPrimaryKey(topic.getBoardid()));
//        List<Board> boardlist = boardService.selectAll();
//        model.addAttribute("BoardList", boardlist);
//        List<Topic> topiclist = topicService.countAll();
//        model.addAttribute("B
//        oardList", boardlist);
        System.out.println(topic+"======");
        System.out.println(page+"~~~~~~");
        System.out.println(topicList);
        return "list";
    }

    @RequestMapping("/delete")
    public String delete(Topic topic, Page page,Model model){
        System.out.println(topic);
        if(replyService.deleteByTopicKey(topic.getTopicid())>=0){
            if(topicService.deleteByPrimaryKey(topic.getTopicid())>0) {
                model.addAttribute("msg", "删除成功");
            }
        }else{
            model.addAttribute("msg","删除失败");
        }
        //return "forward:/detail";
        return List(topic,page,model);
    }

    @RequestMapping("/toupdate")
    public String toUpdate(Topic topic,Integer pageNo,Model model){
        model.addAttribute("topic",topicService.selectByPrimaryKey(topic.getTopicid()));
        model.addAttribute("board",boardService.selectByPrimaryKey(topic.getBoardid()));
        model.addAttribute("pageNo",pageNo);
        return "topicupdate";
    }

    @RequestMapping("/update")
    public String update(Topic topic,Reply reply,Integer pageNo,Model model) {
        Date date =new Date();
        topic.setModifytime(date);
        if(topicService.updateByPrimaryKeySelective(topic)>0){
            model.addAttribute("msg","修改成功");
        }else
            model.addAttribute("msg","修改成功");
        Page page=new Page();
        page.setPageNo(pageNo);
        //return list(topic,reply,page,model);
        return "forward:/detail";
    }
}
