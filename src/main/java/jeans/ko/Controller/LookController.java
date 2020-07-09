package jeans.ko.Controller;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import jeans.ko.Dao.IBoardDao;
import jeans.ko.Dto.BoardDto;
import jeans.ko.Service.CommentService;
import jeans.ko.Service.IBoardService;
import jeans.ko.exception.NotFoundException;
import jeans.ko.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;


@Controller
public class LookController {

    @Autowired
    HttpSession session;
    @Autowired
    CommentService commentService;
    @Autowired
    IBoardService boardService;
    @Autowired
    IBoardDao boardDao;

    //게시판 작성페이지 이동
    @RequestMapping("/look_write")
    public String look_write()
    { return "look_write"; }

    //게시판 상세보기 model and view 웹용
    @RequestMapping("/look")
    public String view(@RequestParam("look_num")int look_num ,Model model)
    {
        boardDao.countUpdate(look_num); //글상세보기 하면 조회수 증가
        model.addAttribute("view",boardDao.view(look_num)); //게시글정보가져오기
        model.addAttribute("comment",commentService.list(look_num)); //게시글에 댓글정보가져오기
        return "look_info";
    }

    //게시판 수정 페이지 이동
    @RequestMapping("/lookModify")
    public String lookModify(@RequestParam("look_num")int look_num,Model model)
    {
        BoardDto boardDto=boardDao.view(look_num);
        model.addAttribute("view",boardDto); //게시글정보 가져오기
        return "lookModify";
    }

    @ResponseBody
    @GetMapping("/looks")  //룩 전체 리스트
    public List<BoardDto> searchAllLook(){
        return boardDao.list();
    }

    @ResponseBody
    @GetMapping("/looks/{id}") //룩상세보기 안드로이드에 값주게 json 데이터만 넘기는용
    public  HashMap<String,Object> searchLook(@PathVariable int id)
    {   //looks/1   looks/3  -->String으로 오는데 int id 해서 int 로 변환해서 받음
        System.out.println("WERwerwer");
        HashMap<String, Object> map = new HashMap<String, Object>();
        //게시글 가져오기
        BoardDto boardDto=boardDao.view(id);
        if(boardDto==null){
            //게시글이 없으면 not found 에러 return
            throw new NotFoundException(String.format("ID[%s] not found",id));
        }
        map.put("look",boardDto); //게시글 가져오기
        map.put("lookCommentList", commentService.list(id)); //댓글정보 가져오기
        boardDao.countUpdate(id); //글상세보기 하면 조회수 증가

        return map;
    }

    //삭제
    @ResponseBody
    @DeleteMapping("/looks/{id}")
    public void deleteLook (@PathVariable int id)  {
     //먼저 게시글이 있는지 확인
     BoardDto boardDto;
     boardDto=boardDao.view(id);

         if(session.getAttribute("userid").equals(boardDto.getFk_userid_user_userid())){
             if(boardDto!=null){
                 boardService.delete(id);
             }else {
                 //찾는 게시글 없어서 not found 오류
                 throw new NotFoundException(String.format("ID[%s] not found",id));
             }
         }else {
             //스크립트에서 1차로 차단하지만 서버로 바로접근하는 경우 차단용
             throw new UnauthorizedException(String.format("unauthorized you"));
         }

    }

    //룩게시판 작성
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping ("/looks")
    public BoardDto boardWrite(BoardDto boardDto) {
        if(session.getAttribute("userid")==null){
            //서버로 바로접근하는 경우 아이디값 없으면 클라이언트 권한없음 오류보냄
            throw new UnauthorizedException(String.format("unauthorized you"));
        }
        //게 시글등록
        boardService.insert(boardDto);
        //selectKey로 등록된 게시글 가져온 기본키로 등록된 게시글 정보보내줌 새롭게 추가되 댓글이없으므로 게시글만넘김
        return boardDao.view(boardDto.getLook_num());
    }

    //룩게시판 수정
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping ("/looks")
    public BoardDto boardModify(BoardDto boardDto) {
        int lookNum=boardDto.getLook_num();
        //로그인 아이디와 작성자 아이디가 같은지 확인한다
        if(session.getAttribute("userid").equals(boardDto.getFk_userid_user_userid())){
            //찾는 값이 없으면 404 에러 보냄
            if(boardDao.view(lookNum)==null){
                throw new NotFoundException(String.format("lookNum[%s] not found",lookNum));
            }
            //게시글 수정
            boardService.update(boardDto);
            //수정된 게시글 정보 넘겨주기
            return boardDao.view(lookNum);
        }else {
            //작성자 아디이와 로그인한 아이디가 다르면 권한없음 오류 보냄
            throw new UnauthorizedException(String.format("unauthorized you"));
        }
    }

}



