package com.dfby.controller;

import com.dfby.pojo.Board;
import com.dfby.pojo.Reply;
import com.dfby.pojo.Topic;
import com.dfby.service.BoardService;
import com.dfby.service.ReplyService;
import com.dfby.service.TopicService;
import com.dfby.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping("aaa")
public class DetailControl {
    @Autowired
    private TopicService topicService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private ReplyService replyService;
    @RequestMapping("/detail")
    public String list(Topic topic,Reply reply,Page page,Model model) {
        if (page==null) {
            page=new Page();
        }
        model.addAttribute("topic",topicService.selectByPrimaryKey(topic.getTopicid()));
        page.setPageSize(5);
        System.out.println(reply+"======");
        page.setRowCount(replyService.selectCoundByCon(reply));
        System.out.println(page+"~~~~~~");
        List<Reply> replyList=replyService.selectListByCon(reply,page);
        System.out.println(replyList);
        page.setPageList(replyList);
        model.addAttribute("mypage", page);
        model.addAttribute("board",boardService.selectByPrimaryKey(topic.getBoardid()));

//        model.addAttribute("reply",replyService.selectByTopicId(topic.getTopicid()));
//        List<Board> boardlist = boardService.selectAll();
//        model.addAttribute("BoardList", boardlist);
//        List<Topic> topiclist = topicService.countAll();
//        model.addAttribute("BoardList", boardlist);
        return "detail";
    }

    @RequestMapping("/delete")
    public String delete(Topic topic,Reply reply,Page page,Model model){
        if(replyService.deleteByPrimaryKey(reply.getReplyid())>0){
            model.addAttribute("msg","删除成功");
        }else{
            model.addAttribute("msg","删除失败");
        }
        //return "forward:/detail";
        return list(topic,reply,page,model);
    }

    @RequestMapping("/toupdate")
    public String toUpdate(Topic topic,Reply reply,Integer pageNo,Model model){
        model.addAttribute("reply",replyService.selectByPrimaryKey(reply.getReplyid()));
        model.addAttribute("board",boardService.selectByPrimaryKey(topic.getBoardid()));
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("topic",topic);
        return "topicupdate";
    }

    @RequestMapping("/update")
    public String update(Topic topic,Reply reply,Integer pageNo,Model model) {
        Date date =new Date();
        reply.setModifytime(date);
        if(replyService.updateByPrimaryKeySelective(reply)>0){
            model.addAttribute("msg","修改成功");
        }else
            model.addAttribute("msg","修改成功");
        System.out.println(reply);
        System.out.println(topic);
        Page page=new Page();
        page.setPageNo(pageNo);
        return list(topic,reply,page,model);
    }

    @RequestMapping("/totopic")
    public String totopic(Topic topic, Model model) {
        model.addAttribute("topic", topic);
        model.addAttribute("board",boardService.selectByPrimaryKey(topic.getBoardid()));
        return "newpost";
    }

    @RequestMapping("/topic")
    public String topic(Topic topic,Reply reply,Page page,Model model) {
        //topic.setUser((User) httpSession.getAttribute("User"));
        Date date =new Date();
        topic.setModifytime(date);
        if (topicService.insertSelective(topic) > 0) {
            model.addAttribute("msgg", "发贴成功");
        } else {
            model.addAttribute("msgg", "发贴失败");
        }
        System.out.println(topic);
        model.addAttribute("topic", topic);
        model.addAttribute("boardid",topic.getBoardid());
        model.addAttribute("topicid", topicService.selectTimeDesc());
        topic.setTopicid(topicService.selectTimeDesc());
        //return "redirect:/detail";
        return list(topic,reply,page,model);
    }

}
